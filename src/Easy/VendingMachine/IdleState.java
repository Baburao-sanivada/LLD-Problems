package VendingMachine;

import VendingMachine.Cash.Cash;

public class IdleState implements VMState {

  private final VendingMachine vendingMachine;

  public IdleState(VendingMachine vendingMachine){
    this.vendingMachine = vendingMachine;
  }

  public void selectProduct(Product product) {
    vendingMachine.setProductSelected(product);
    vendingMachine.setState(vendingMachine.getCashState());
    System.out.println("Product Added: "+product.getProductName());
  }
  
  public void insertMoney(Cash cash) {
    System.out.println("Select the products First");
  }


  public void dispenseProduct() {
    System.out.println("Select the products and Make payment First");
  }

  public void returnChange() {
    System.out.println("Select the products and Make payment First");
  }
  
}