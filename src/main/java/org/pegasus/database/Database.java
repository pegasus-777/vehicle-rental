package org.pegasus.database;

import lombok.Getter;
import org.pegasus.exceptions.BranchAlreadyAdded;
import org.pegasus.exceptions.BranchNotFound;
import org.pegasus.models.Branch;
import org.pegasus.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database implements IDatabase{

    @Getter
    private List<Branch> branchesList = new ArrayList<>();
    private HashMap<String, Branch> branchesTable = new HashMap<>();

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
}
