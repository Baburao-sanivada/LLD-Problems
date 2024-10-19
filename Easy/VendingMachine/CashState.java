package VendingMachine;

import VendingMachine.Cash.Cash;

public class CashState implements VMState {

  private final VendingMachine vendingMachine;

  public CashState(VendingMachine vendingMachine){
    this.vendingMachine = vendingMachine;
  }

  public void selectProduct(Product product) {
    System.out.println("Product already selected please make the payment");
  }

  public void insertMoney(Cash cash) {
    vendingMachine.getCashController().collectCash(cash.getValue());
    checkPaymentStatus();
    
  }


  public void dispenseProduct() {
    System.out.println("Complete the payment first");
  }

  public void returnChange() {
    System.out.println("Complete the payment First");
  }


  public void checkPaymentStatus(){
    if(vendingMachine.getCashController().getCurrTransactionAmout() >= vendingMachine.getProductSelected().getProductPrice()){
      vendingMachine.setState(vendingMachine.getDispenseState());
    }
  }

}