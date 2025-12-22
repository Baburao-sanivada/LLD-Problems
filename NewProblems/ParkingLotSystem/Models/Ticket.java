package ParkingLotSystem.Models;

import ParkingLotSystem.Enums.VehicleType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String ticketId;
    private String vehicleNumber;
    private LocalDateTime entryTime;
    private VehicleType vehicleType;
    private String slotId;

    public Ticket(String vehicleNumber, LocalDateTime entryTime, VehicleType vehicleType,String slotId) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
        this.vehicleType = vehicleType;
        this.slotId = slotId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getSlotId() {
        return slotId;
    }



    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
