package ParkingLotSystem.Adapter;

public interface PaymentStrategy {
    boolean processPayment(String ticketId, double amount);
}
