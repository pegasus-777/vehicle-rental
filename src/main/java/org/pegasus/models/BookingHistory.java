package org.pegasus.models;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookingHistory {
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookingHistory(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
