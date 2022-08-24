package org.pegasus.vehiclerentaltest;

import org.junit.Test;
import org.pegasus.models.Vehicle;
import org.pegasus.models.VehicleType;
import org.pegasus.services.RentalService;

import java.time.LocalDate;

public class VehicleRentalTest {

    RentalService rentalService = new RentalService();
    @Test
    public void VehicleTest(){
        rentalService.addBranch("CP");
        rentalService.addBranch("AB");
        rentalService.addBranch("BC");
        rentalService.addVehicle("1", VehicleType.HATCHBACK, "CP", 100);
        rentalService.addVehicle("2", VehicleType.HATCHBACK, "AB", 100);
        rentalService.allocatePrice("AB", VehicleType.HATCHBACK, 50);

        Vehicle vehicle = rentalService.bookVehicle(VehicleType.HATCHBACK, LocalDate.now(),LocalDate.now().plusDays(10));

        assert (vehicle.getId().equals("2"));

        vehicle = rentalService.bookVehicle(VehicleType.HATCHBACK, LocalDate.now(),LocalDate.now().plusDays(5));

        assert (vehicle.getId().equals("1"));

        rentalService.viewInventory(LocalDate.now(), LocalDate.now().plusDays(6));
    }
}
