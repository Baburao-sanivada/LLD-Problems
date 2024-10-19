package VendingMachine;

import VendingMachine.Cash.Cash;
import VendingMachine.Cash.Note;
import VendingMachine.Cash.Coin;
import VendingMachine.Cash.CoinType;
import VendingMachine.Cash.NoteType;

public class VendingMachineDemo{
  private final VendingMachine vendingMachine;
  public VendingMachineDemo(){
    
    vendingMachine = VendingMachine.getInstance();

    // Create Products
    Product product1 = new Product(1,"Coke",20,1);
    Product product2 = new Product(2,"ThumbUp",40,1);

    Cash coin1 = new Coin(CoinType.FIVE);
    Cash note1 = new Note(NoteType.FIFTY);
    

    vendingMachine.addProduct(product1);
    vendingMachine.addProduct(product2);

    vendingMachine.productSelection(product1);
    vendingMachine.collectCash(coin1);
    
    vendingMachine.dispenseProduct();
    vendingMachine.collectCash(note1);

    // System.out.println("Amount: "+vendingMachine.getCashController().getCurrTransactionAmout());
    
    // vendingMachine.productSelection(product1);
    
    vendingMachine.dispenseProduct();

    
    vendingMachine.returnChange();

    vendingMachine.productSelection(product1);
    vendingMachine.collectCash(coin1);
    vendingMachine.collectCash(note1);
    vendingMachine.dispenseProduct();

    
  }
}