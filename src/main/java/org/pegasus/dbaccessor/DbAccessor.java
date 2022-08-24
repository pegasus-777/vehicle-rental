package org.pegasus.dbaccessor;

import org.pegasus.database.Database;
import org.pegasus.database.IDatabase;
import org.pegasus.exceptions.BranchAlreadyAdded;
import org.pegasus.exceptions.BranchNotFound;
import org.pegasus.models.*;
import org.pegasus.utils.Compare;

import java.util.Date;
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

    public Vehicle bookVehicle(VehicleType vehicleType, Date startTime, Date endTime) {
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
        return pq.peek();
    }
}
