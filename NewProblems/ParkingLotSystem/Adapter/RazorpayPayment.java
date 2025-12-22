package ParkingLotSystem.Adapter;

public class RazorpayPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(String ticketId, double amount) {
        // Simulate Razorpay payment processing logic
        System.out.println("Processing payment through Razorpay for Ticket ID: " + ticketId + " Amount: " + amount);
        // Assume payment is always successful for this simulation
        return true;
    }
}
