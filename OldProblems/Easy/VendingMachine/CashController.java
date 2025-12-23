package VendingMachine;

public class CashController{
  private int totalCashInVM;
  private int currTransactionAmout;

  public CashController(){
    totalCashInVM = 0;
    currTransactionAmout = 0;
  }

  public void resetTransaction(){
    currTransactionAmout = 0;
  }

  public void collectCash(int amount){
    totalCashInVM += amount;
    currTransactionAmout += amount;
  }
  public int getTotalCashInVM(){
    return totalCashInVM;
  }

  public int getCurrTransactionAmout(){
    return currTransactionAmout;
  }

  public void returnChange(int amount){
    totalCashInVM -= amount;
    currTransactionAmout -= amount;
  }

  public void CashRequestedByAdmin(int amount){
    totalCashInVM -= amount;
  }
  
}