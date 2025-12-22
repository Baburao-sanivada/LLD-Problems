package ParkingLotSystem.Models;

import ParkingLotSystem.Enums.PaymentMethod;
import ParkingLotSystem.Enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private String id;
    private double amount;
    private LocalDateTime paymentTime;
    private PaymentMethod paymentMethod; // e.g., CASH, CARD, UPI
    private PaymentStatus status; // e.g., PENDING, COMPLETED, FAILED
    private String ticketId;

    public Payment(double amount, LocalDateTime paymentTime, PaymentMethod paymentMethod, PaymentStatus status, String ticketId) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.paymentTime = paymentTime;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.ticketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
