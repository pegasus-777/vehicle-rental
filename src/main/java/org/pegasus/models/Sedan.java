package org.pegasus.models;

public class Sedan extends Vehicle{

    public Sedan(String id, int inventory, VehicleStatus vehicleStatus) {
        super(id, inventory, vehicleStatus);
        this.setCost(150);
        this.setType(VehicleType.SEDAN);
    }


}
