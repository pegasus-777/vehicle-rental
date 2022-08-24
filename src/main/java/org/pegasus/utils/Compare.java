package org.pegasus.utils;

import org.pegasus.models.Vehicle;

import java.util.Comparator;

public class Compare implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        return v1.getCost() - v2.getCost();
    }
}
