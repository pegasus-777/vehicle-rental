package org.pegasus.services;

import org.pegasus.dbaccessor.DbAccessor;
import org.pegasus.models.Vehicle;
import org.pegasus.models.VehicleType;

import java.time.LocalDate;
import java.util.List;

public class RentalService {

    DbAccessor dbAccessor = new DbAccessor();
    public void addBranch(String branchName){
        dbAccessor.addBranch(branchName);
    }

    public void allocatePrice(String branchName, VehicleType vehicleType, int price){
        dbAccessor.allocatePrice(branchName, vehicleType, price);
    }

    public void addVehicle(String vehicleId, VehicleType vehicleType, String branchName, int inventory){
        dbAccessor.addVehicle(vehicleId, vehicleType, branchName, inventory);
    }

    public Vehicle bookVehicle(VehicleType vehicleType, LocalDate startTime, LocalDate endTime){
        Vehicle vehicle = dbAccessor.bookVehicle(vehicleType, startTime, endTime);
        System.out.println(vehicle);
        return vehicle;
    }

    public List<Vehicle> viewInventory(LocalDate startTime, LocalDate endTime){
        return dbAccessor.getAllBookingHistoriesInTheRange(startTime, endTime);
    }
}
