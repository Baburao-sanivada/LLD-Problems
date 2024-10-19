package Easy.CoffeeVendingMachine;

import java.util.HashMap;

public class Coffee{
  private String name;
  private double price;
  private HashMap<String,Integer> ingredientsRequired;

  public Coffee(String name,double price,HashMap<String,Integer> ingredientsRequired){
    this.name = name;
    this.price = price;
    this.ingredientsRequired = ingredientsRequired;
  }

  public String getName() {
  	return name;
  }
  
  public void setName(String name) {
  	this.name = name;
  }
  
  public double getPrice() {
  	return price;
  }
  
  public void setPrice(double price) {
  	this.price = price;
  }
  
  public HashMap<String, Integer> getIngredientsRequired() {
  	return ingredientsRequired;
  }
  
  public void setIngredientsRequired(HashMap<String, Integer> ingredientsRequired) {
  	this.ingredientsRequired = ingredientsRequired;
  }

}