package VendingMachine.Cash;

public enum CoinType{
  ONE(1),
  TWO(2),
  FIVE(3);

  private final int value;

  CoinType(int value){
    this.value = value;
  }

  public int rawValue(){
    return value;
  }
}