package Medium.ATM;

public class Account {
    private final String accountNo;
    private double balance;
    public Account(String accountNo,double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void credit(double amount){
        balance+=amount;
    }

    public void debitAmount(double amount){
        balance-=amount;
    }
}
