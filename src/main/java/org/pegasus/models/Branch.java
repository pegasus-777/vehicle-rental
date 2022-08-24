package org.pegasus.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Branch {
    private String name;
    private List<Vehicle> vehiclesList;

    public Branch(String name) {
        this.name = name;
        vehiclesList = new ArrayList<>();
    }
}
