package VendingMachine;

import java.util.ArrayList;

public class ProductController{
  public final ArrayList<Product> products;

  public ProductController(){
    products = new ArrayList<Product>();
  }

  public void addProduct(Product product){
    products.add(product);
  }

  public void decreaseProductQuantity(Product product,int quantity){
    product.decreaseProductQuantity(quantity);
  }

  public void increaseProductQuantity(Product product,int quantity){
    product.increaseProductQuantity(quantity);
    
  }

  public boolean checkIfProductAvailable(Product product,int quantity){
    return product.checkIfProductAvailable(quantity);
  }
}