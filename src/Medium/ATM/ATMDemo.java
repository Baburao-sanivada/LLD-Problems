package Medium.ATM;

public class ATMDemo {
    public static void run() {
        AccountManager accountManager = new AccountManager();
        CardManager cardManager = new CardManager();
        CashController cashController = new CashController(10000);

        ATMController atmController =  ATMController.getInstance(accountManager,cardManager,cashController);

        accountManager.addAccount(new Account("123",1000));
        accountManager.addAccount(new Account("1234",2000));
        accountManager.addAccount(new Account("12345",3000));

        cardManager.addCard(new Card("123","1234"));
        cardManager.addCard(new Card("1234","9876"));
        cardManager.addCard(new Card("1223453","2345"));

        atmController.start();
    }
}
