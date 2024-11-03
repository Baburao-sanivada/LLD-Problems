package Medium.RestaurantManagementSystem.Payment;

public class UPIPayment implements PaymentService {

    public boolean collectCash(double amount){
        System.out.println("Amount: "+amount+" recieved through UPI payment");
        return true;
    }
}
