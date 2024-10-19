package Medium.ATM;

import java.util.Scanner;

public class CashController {
    private double cashBalance;
    public CashController(double amount){
        cashBalance = amount;
    }

    public synchronized void dispenseCash(double amount) throws IllegalAccessException {
        if(amount>cashBalance){
            throw new IllegalAccessException("Insufficient Amount in the ATM");
        }
        cashBalance -= amount;
    }

    public synchronized void collectCash(double amount){
        cashBalance+=amount;
    }
}
