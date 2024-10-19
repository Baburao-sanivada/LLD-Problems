package Easy.CoffeeVendingMachine;

import java.util.HashMap;

public class IngredientsController {
  private HashMap<String,Integer> ingredients;
  // private CoffeMachine coffeMachine;
  public IngredientsController(){
    // this.coffeMachine = coffeeMachine;
    ingredients = new HashMap<String,Integer>();
  }

  public synchronized void addIngredient(String name,int quantity){
    ingredients.put(name,ingredients.getOrDefault(name,0)+quantity);
  }

  public synchronized void removeIngredient(String name,int quantity){
    ingredients.put(name,ingredients.getOrDefault(name,0)-quantity);
  }

  public synchronized void removeIngredient(HashMap<String,Integer> ingredientsRequired){
    for(String ingredient:ingredientsRequired.keySet()){
      ingredients.put(ingredient,ingredients.get(ingredient)-ingredientsRequired.get(ingredient));
    }
  }

  public synchronized boolean checkIfIngredientsAreAvailable(HashMap<String,Integer> ingredientsRequired){
    for(String ingredient:ingredientsRequired.keySet()){
      if(ingredients.getOrDefault(ingredient,0)<ingredientsRequired.get(ingredient)) return false;
    }
    return true;
  }

  public void checkIfIngredientAreLow(){
    for(String ingredient:ingredients.keySet()){
      if(ingredients.get(ingredient)<10) System.out.println(ingredient+" is low");
    }
  }
}