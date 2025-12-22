package ParkingLotSystem.Adapter;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(String ticketId, double amount) {
        // Simulate Stripe payment processing logic
        System.out.println("Processing payment through CASH for Ticket ID: " + ticketId + " Amount: " + amount);
        // Assume payment is always successful for this simulation
        return true;
    }
}
