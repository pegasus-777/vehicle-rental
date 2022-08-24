package org.pegasus.dbaccessor;

import org.pegasus.database.Database;
import org.pegasus.database.IDatabase;
import org.pegasus.exceptions.BranchAlreadyAdded;
import org.pegasus.exceptions.BranchNotFound;
import org.pegasus.models.*;
import org.pegasus.utils.Compare;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class DbAccessor {

    private final IDatabase db = new Database();
    public void addBranch(String name){
        try {
            db.addBranch(name);
        } catch (BranchAlreadyAdded e) {
            System.out.println("Branch already added " +e.toString());
        }
    }

    public void addVehicle(String vehicleId, VehicleType vehicleType, String branchName, int inventory) {
        try {
            Branch branch = db.getBranch(branchName);
            Vehicle vehicle;
            if(vehicleType == VehicleType.SEDAN){
                vehicle = new Sedan(vehicleId, inventory,VehicleStatus.AVAILABLE);
            } else if (vehicleType == VehicleType.HATCHBACK) {
                vehicle = new HatchBack(vehicleId, inventory,VehicleStatus.AVAILABLE);
            } else{
                vehicle = new SUV(vehicleId, inventory,VehicleStatus.AVAILABLE);
            }

            db.addVehicle(vehicle, branch);


        } catch (BranchNotFound e) {
            System.out.println("Branch not found"+e.toString());
        }
    }


    public void allocatePrice(String branchName, VehicleType vehicleType, int price) {
        try {
            Branch branch = db.getBranch(branchName);
            List<Vehicle> vehicleList = branch.getVehiclesList();
            for(Vehicle vehicle : vehicleList){
                if(vehicle.getType() == vehicleType)
                    vehicle.setCost(price);
            }

        } catch (BranchNotFound e) {
            System.out.println("Branch not found"+e.toString());
        }
    }

    public Vehicle bookVehicle(VehicleType vehicleType, LocalDate startTime, LocalDate endTime) {
        List<Branch> branchesList = db.getBranchesList();
        PriorityQueue<Vehicle> pq = new PriorityQueue<>(new Compare());
        for(Branch branch : branchesList){
            pq.addAll(branch.getVehiclesList()
                    .stream()
                    .filter(v -> v.getType() == vehicleType && v.getVehicleStatus() == VehicleStatus.AVAILABLE)
                    .toList());
        }
        if(pq.size() == 0){
            System.out.println("No Vehicle found");
            return null;
        }
        Vehicle vehicle = pq.peek();
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        vehicle.setInventory(vehicle.getInventory() - 1);

        db.addBookingHistory(vehicle, startTime, endTime);
        return pq.peek();
    }

    public List<Vehicle> getAllBookingHistoriesInTheRange(LocalDate startDate, LocalDate endDate){
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Branch branch : db.getBranchesList()){
            vehicleList.addAll(branch.getVehiclesList().stream().filter(Vehicle::isAvailable).toList());
        }

        for(BookingHistory bookingHistory : db.getBookingHistories(startDate, endDate)){
            vehicleList.add(bookingHistory.getVehicle());
        }

        System.out.println("Vehicles list : ");
        for(Vehicle vehicle: vehicleList){
            System.out.println(vehicle);
        }
        return vehicleList;

    }

}
