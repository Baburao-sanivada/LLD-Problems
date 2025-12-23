package VendingMachine;

import VendingMachine.Cash.Cash;

public interface VMState {
    
    public void selectProduct(Product product);

    public void insertMoney(Cash cash);

    public void dispenseProduct();

    public void returnChange();
}