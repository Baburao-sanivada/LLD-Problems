package VendingMachine;

import VendingMachine.Cash.Cash;

public class DispenseState implements VMState {

  private final VendingMachine vendingMachine;

  public DispenseState(VendingMachine vendingMachine){
    this.vendingMachine = vendingMachine;
  }

  public void selectProduct(Product product) {
    System.out.println("Product already selected please make the payment");
  }

  public void insertMoney(Cash cash) {
    System.out.println("Payment already made collect the dispensed product");
  }


  public void dispenseProduct() {
    vendingMachine.setState(vendingMachine.getCashState());
    Product productSelected = vendingMachine.getProductSelected();
    System.out.println("Product Amount in dispense state: "+productSelected.getProductPrice());
    if(productSelected.getProductPrice() <= vendingMachine.getCashController().getCurrTransactionAmout()){
      vendingMachine.getProductController().decreaseProductQuantity(productSelected, 1);
      System.out.println("Please Collect the dispensed product: "+productSelected.getProductName());
      vendingMachine.setState(vendingMachine.getReturnChangeState());
    }
    
  }

  public void returnChange() {
    System.out.println("Select the products and Make payment First");
  }

}