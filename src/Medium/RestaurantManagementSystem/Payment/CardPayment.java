package Medium.RestaurantManagementSystem.Payment;

public class CardPayment implements PaymentService {
    public boolean collectCash(double amount){
        System.out.println("Amount: "+amount+" recieved through card payment");
        return true;
    }
}
