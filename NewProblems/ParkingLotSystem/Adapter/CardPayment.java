package ParkingLotSystem.Adapter;

public class CardPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(String ticketId, double amount) {
        // Simulate card payment processing logic
        System.out.println("Processing card payment for Ticket ID: " + ticketId + " Amount: $" + amount);
        // In a real-world scenario, integrate with a payment gateway here
        return true; // Assume payment is always successful for this example
    }

}
