package org.pegasus.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Vehicle {
    @Setter private int inventory;
    private String id;

    @Setter private VehicleType type;
    @Setter private VehicleStatus VehicleStatus;

    @Setter private int cost = 0;

    public Vehicle(){

    }

    public Vehicle(String id, int inventory, VehicleStatus vehicleStatus) {
        this.id = id;
        this.inventory = inventory;
        this.VehicleStatus = vehicleStatus;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "inventory=" + inventory +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", VehicleStatus=" + VehicleStatus +
                ", cost=" + cost +
                '}';
    }
}
