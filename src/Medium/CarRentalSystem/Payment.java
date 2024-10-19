package Medium.CarRentalSystem;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Payment {
    private static Payment instance;
    Scanner sc;
    public static Payment getInstance(){
        if(instance == null){
            instance = new Payment();
        }
        return instance;
    }

    private Payment(){
        sc = new Scanner(System.in);
    }

    public boolean collectAmount(int amount){
        int amountCollected = 0;
        System.out.println("Please make the payment of: "+amount);

        while(amountCollected != amount) {
            int amountFromUser = sc.nextInt();
            System.out.println("Amount recieved :"+amountFromUser);
            if (amount == amountFromUser) {
                return true;
            } else {
                System.out.println("Make the complete payment at Once");
            }
        }
        return false;
    }
}
