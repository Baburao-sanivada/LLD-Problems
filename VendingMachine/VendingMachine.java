package VendingMachine;

import VendingMachine.Cash.Cash;

public class VendingMachine {

  private VMState currentVMState;
  private static VendingMachine instance;
  private final CashController cashController;
  private Product productSelected;
  private final ProductController productController;
  private final ReturnChangeState returnChangeState;
  private final IdleState idleState;
  private final DispenseState dispenseState;
  private final CashState cashState;

  public static VendingMachine getInstance(){
    if(instance==null){
      instance=new VendingMachine();
    }
    return instance;
  }

  private VendingMachine(){
    idleState = new IdleState(this);
    cashState = new CashState(this);
    dispenseState = new DispenseState(this);
    returnChangeState = new ReturnChangeState(this);
    productController = new ProductController();
    cashController = new CashController();

    currentVMState = idleState;
  
  }

  public void addProduct(Product product){
    productController.addProduct(product);
  }

  public void productSelection(Product product){
    currentVMState.selectProduct(product);
  }

  public void collectCash(Cash cash){
    currentVMState.insertMoney(cash);
  }

  public void dispenseProduct(){
    currentVMState.dispenseProduct();
  }
  
  public void returnChange(){
    currentVMState.returnChange();
  }


  public void resetState(){
    currentVMState = idleState;
    productSelected = null;
    cashController.resetTransaction();
  }

  public void setState(VMState state){
    currentVMState = state;
  }
  
  public Product getProductSelected() {
  	return productSelected;
  }
  
  public void setProductSelected(Product productSelected) {
  	this.productSelected = productSelected;
  }
  
  public ProductController getProductController() {
  	return productController;
  }
  
  public ReturnChangeState getReturnChangeState() {
  	return returnChangeState;
  }
  
  public IdleState getIdleState() {
  	return idleState;
  }
  
  public DispenseState getDispenseState() {
  	return dispenseState;
  }
  
  public CashState getCashState() {
  	return cashState;
  }

  public CashController getCashController(){
    return cashController;
  }

}