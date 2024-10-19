package VendingMachine;

import VendingMachine.Cash.Cash;

public class ReturnChangeState implements VMState {

  private final VendingMachine vendingMachine;

  public ReturnChangeState(VendingMachine vendingMachine){
    this.vendingMachine = vendingMachine;
  }

  public void selectProduct(Product product) {
    System.out.println("Product already selected please collect the change");
  }

  public void insertMoney(Cash cash) {
    System.out.println("Payment already made,Please collect the change");
  }


  public void dispenseProduct() {
    System.out.println("Product Already Dispensed Collect the change returned if any");
  }

  public void returnChange() {
    Product productSelected = vendingMachine.getProductSelected();
    CashController cashController = vendingMachine.getCashController();
    int change = cashController.getCurrTransactionAmout() - productSelected.getProductPrice();
    if(change>0){
      System.out.println("Collect the change: "+change);
      cashController.returnChange(change);
    }
    else{
      System.out.println("No Change to return");
    }
    vendingMachine.setState(vendingMachine.getIdleState());
  }

}