package VendingMachine.Cash;

public class Coin extends Cash{
  public Coin(CoinType coinType){
    super(coinType.rawValue());
  }
}