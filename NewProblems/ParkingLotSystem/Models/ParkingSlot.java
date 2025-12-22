package ParkingLotSystem.Models;

import ParkingLotSystem.Enums.VehicleType;

import java.util.UUID;

public class ParkingSlot {
    private UUID id;
    private int floorNumber;
    private boolean isOccupied;
    private VehicleType vehicleType;

    public ParkingSlot(int floorNumber, VehicleType vehicleType) {
        this.floorNumber = floorNumber;
        this.isOccupied = false;
        this.vehicleType = vehicleType;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
