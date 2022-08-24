package org.pegasus.database;

import org.pegasus.exceptions.BranchAlreadyAdded;
import org.pegasus.exceptions.BranchNotFound;
import org.pegasus.models.BookingHistory;
import org.pegasus.models.Branch;
import org.pegasus.models.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface IDatabase {

    public void addBranch(String name) throws BranchAlreadyAdded;
    public Branch getBranch(String name) throws BranchNotFound;
    public void addVehicle(Vehicle vehicle, Branch branch);
    public List<Branch> getBranchesList();
    public void addBookingHistory(Vehicle vehicle, LocalDate startDate, LocalDate endDate) ;
    public List<BookingHistory> getBookingHistories(LocalDate startDate, LocalDate endDate);
}
