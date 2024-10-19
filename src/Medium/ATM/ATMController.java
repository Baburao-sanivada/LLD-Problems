package Medium.ATM;

import java.util.List;
import java.util.Scanner;

public class ATMController {

    private static ATMController instance;
    private final AccountManager accountManager;
    private final CardManager cardManager;
    private final CashController cashController;
    private final Scanner sc;
    private Account currentLoggedInAccount;
    public static synchronized ATMController getInstance(AccountManager accountManager,CardManager cardManager,CashController cashController){
        if(instance==null){
            instance = new ATMController(accountManager,cardManager,cashController);
        }
        return instance;
    }

    private ATMController(AccountManager accountManager, CardManager cardManager, CashController cashController) {
        this.accountManager = accountManager;
        this.cardManager = cardManager;
        this.cashController = cashController;
        sc = new Scanner(System.in);
    }

    public void start(){
        while (true){
            if(currentLoggedInAccount == null){
                System.out.println("Please enter your login credentails");
                validateUser();
            }
            else{
                System.out.println(" Please choose any option");
                System.out.println("1. Balance Enquiry");
                System.out.println("2. Cash Withdrawal");
                System.out.println("3. Cash Deposit");
                System.out.println("4. Log Out");
                int choosenIndex = sc.nextInt();
                System.out.println("Choosen Index: "+choosenIndex);
//                sc.next();
                switch (choosenIndex){
                    case 1:
                        System.out.println("Balance : "+currentLoggedInAccount.getBalance());
                        break;
                    case 2:
                        // with drawal
                        dispenseCash();
                        break;

                    case 3:
                        // cash Dep
                        collectCash();
                        break;

                    case 4:
                        currentLoggedInAccount = null;
                        break;
                    default:
                        System.out.println("Please select from above option");
                }
            }
        }
    }

    public synchronized void collectCash(){
        System.out.println("Please enter the deposit amount");
        double amount = sc.nextDouble();
        currentLoggedInAccount.credit(amount);
        cashController.collectCash(amount);
    }

    public synchronized void dispenseCash(){
        System.out.println("Please enter the withdrawal amount");
        double amount = sc.nextDouble();
        if(amount> currentLoggedInAccount.getBalance()){
            System.out.println("Insufficient Balance in the account");
        }
        else{
            currentLoggedInAccount.debitAmount(amount);
            try {
                cashController.dispenseCash(amount);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void validateUser(){
        String cardNo = sc.next();
        String PIN = sc.next();
        Card card = cardManager.validateCardDetails(cardNo,PIN);
        if(card==null){
            System.out.println("Invalid Credentials Retry");
            return;
        }
        currentLoggedInAccount = accountManager.getAccountWithCardNo(cardNo);
    }



}
