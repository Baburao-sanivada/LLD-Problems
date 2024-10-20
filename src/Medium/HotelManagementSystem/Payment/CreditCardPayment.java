package Medium.HotelManagementSystem.Payment;

public class CreditCardPayment implements PaymentController{

    public CreditCardPayment(){

    }

    @Override
    public boolean collectPayment(double amount) {
        // Handle Payment
        return true;
    }
}
