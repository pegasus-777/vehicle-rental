package org.pegasus.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Vehicle {
    @Setter private int inventory;
    private String id;

    @Setter private VehicleType type;
    @Setter private VehicleStatus vehicleStatus;

    @Setter private int cost = 0;

    public Vehicle(String id, int inventory, VehicleStatus vehicleStatus) {
        this.id = id;
        this.inventory = inventory;
        this.vehicleStatus = vehicleStatus;
    }

    public boolean isAvailable() {
        return this.getVehicleStatus().equals(VehicleStatus.AVAILABLE);
    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "inventory=" + inventory +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", VehicleStatus=" + vehicleStatus +
                ", cost=" + cost +
                '}';
    }
}
