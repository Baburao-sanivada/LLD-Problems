package VendingMachine;

public class Product {
  private final int productId;
  private final String productName;
  private final int productPrice;
  private int productQuantity;
  public Product(int productId, String productName, int productPrice, int productQuantity){
    this.productId = productId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productQuantity = productQuantity;
  }
  public int getProductId() {
  	return productId;
  }
  public String getProductName() {
  	return productName;
  }
  public int getProductPrice() {
  	return productPrice;
  }
  public int getProductQuantity() {
  	return productQuantity;
  }
  public synchronized void increaseProductQuantity(int quantity){
    this.productQuantity+=quantity;
  }
  public synchronized void decreaseProductQuantity(int quantity){
    if(productQuantity<quantity){
      throw new IllegalArgumentException("Product Quantity is less than the required quantity");
    }
    this.productQuantity-=quantity;
  }

  public boolean checkIfProductAvailable(int quantity){
    return productQuantity>=quantity;
  }

  
}