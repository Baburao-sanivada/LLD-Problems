package VendingMachine.Cash;

public abstract class Cash{
  private final int amount;
  public Cash(int amount){
    this.amount = amount;
  }

  public int getValue(){
    return amount;
  }
}