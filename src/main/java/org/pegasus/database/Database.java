package org.pegasus.database;

import lombok.Getter;
import org.pegasus.exceptions.BranchAlreadyAdded;
import org.pegasus.exceptions.BranchNotFound;
import org.pegasus.models.BookingHistory;
import org.pegasus.models.Branch;
import org.pegasus.models.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database implements IDatabase{

    @Getter
    private List<Branch> branchesList = new ArrayList<>();
    private HashMap<String, Branch> branchesTable = new HashMap<>();

    @Getter private List<BookingHistory> bookingHistories = new ArrayList<>();

    public void addBranch(String name) throws BranchAlreadyAdded {
        if(branchesTable.containsKey(name)){
            throw new BranchAlreadyAdded("Branch already added");
        }

        Branch branch = new Branch(name);
        branchesList.add(branch);
        branchesTable.put(name, branch);
    }

    public Branch getBranch(String name) throws BranchNotFound {
        if(!branchesTable.containsKey(name)){
            throw new BranchNotFound("Branch not found");
        }
        return branchesTable.get(name);

    }

    @Override
    public void addVehicle(Vehicle vehicle, Branch branch) {
        List<Vehicle> vehiclesList = branch.getVehiclesList();
        vehiclesList.add(vehicle);
    }

    @Override
    public void addBookingHistory(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        bookingHistories.add(new BookingHistory(vehicle, startDate, endDate));
    }

    @Override
    public List<BookingHistory> getBookingHistories(LocalDate startDate, LocalDate endDate) {
        return bookingHistories
                .stream().
                filter(b -> (b.getStartDate().isAfter(startDate) ||
                        b.getStartDate().isEqual(startDate)) && (b.getEndDate().isBefore(endDate) ||
                        b.getEndDate().isEqual(endDate)))
                .toList();
    }


}
