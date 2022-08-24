package org.pegasus.models;

public class SUV extends Vehicle{
    public SUV(String id, int inventory, org.pegasus.models.VehicleStatus vehicleStatus) {
        super(id, inventory, vehicleStatus);
        this.setCost(100);
        this.setType(VehicleType.SUV);
    }
}
