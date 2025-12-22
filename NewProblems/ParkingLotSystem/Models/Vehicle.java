package ParkingLotSystem.Models;

import java.util.UUID;

public class Vehicle {
    private UUID id;
    private String licensePlate;
    private String vehicleType;

    public Vehicle(String licensePlate, String vehicleType) {
        this.id = UUID.randomUUID();
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public UUID getId() {
        return id;
    }
}
