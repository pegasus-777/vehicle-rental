package org.pegasus.models;

public class HatchBack extends Vehicle {
    public HatchBack(String id, int inventory, org.pegasus.models.VehicleStatus vehicleStatus) {
        super(id, inventory, vehicleStatus);
        this.setType(VehicleType.HATCHBACK);
        this.setCost(80);
    }

}
