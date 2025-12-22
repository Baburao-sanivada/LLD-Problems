package ParkingLotSystem.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reciept {
    private String recieptId;
    private String ticketId;
    private double amount;
    private LocalDateTime exitTime;

    public Reciept(String ticketId, double amount, LocalDateTime exitTime) {
        this.recieptId = UUID.randomUUID().toString();
        this.ticketId = ticketId;
        this.amount = amount;
        this.exitTime = exitTime;
    }

    public String getRecieptId() {
        return recieptId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
}
