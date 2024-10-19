package Easy.CoffeeVendingMachine;

import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeMachine {
  
  private static CoffeeMachine instance;
  private IngredientsController ingredientsController;
  private ArrayList<Coffee> coffeeTypes;
  private Coffee coffeeSelected;
  
  public static CoffeeMachine getInstance(){
    if(instance==null){
      instance = new CoffeeMachine();
    }
    return instance;
  }

  private CoffeeMachine(){
    // Add Ingredients
    // Add Coffe Types
    ingredientsController = new IngredientsController();
    coffeeTypes = new ArrayList<Coffee>();
    addInitialIngredients();
    addCoffeeTypes();
  }

  private void addCoffeeTypes(){
    // Add Coffee Types

    // Espresso
    HashMap<String,Integer> espressoIngredients = new HashMap<String,Integer>();
    espressoIngredients.put("Coffee",15);
    espressoIngredients.put("Milk",30);
    espressoIngredients.put("Water",25);

    Coffee espresso = new Coffee("Espresso",50,espressoIngredients);
    coffeeTypes.add(espresso);

    HashMap<String,Integer> cappachinoIngredients = new HashMap<String,Integer>();
    cappachinoIngredients.put("Coffee",20);
    cappachinoIngredients.put("Milk",40);
    cappachinoIngredients.put("Water",15);

    Coffee cappachino = new Coffee("Cappachino",80,cappachinoIngredients);
    coffeeTypes.add(cappachino);

    HashMap<String,Integer> latteIngredients = new HashMap<String,Integer>();
    latteIngredients.put("Coffee",25);
    latteIngredients.put("Milk",40);
    latteIngredients.put("Water",25);

    Coffee latte = new Coffee("Latte",50,latteIngredients);
    coffeeTypes.add(latte);
    
  }

  private void addInitialIngredients(){
    ingredientsController.addIngredient("Coffee",60);
    ingredientsController.addIngredient("Milk",100);
    ingredientsController.addIngredient("Water",200);
  }


  // Methods
  public void displayMenu(){
    for(Coffee coffee:coffeeTypes){
      System.out.println(coffee.getName());
    }
  }

  public void selectCoffee(String name){
    for(Coffee coffee:coffeeTypes){
      if(coffee.getName().equals(name)){
        coffeeSelected = coffee;
        break;
      }
    }
    if(!ingredientsController.checkIfIngredientsAreAvailable(coffeeSelected.getIngredientsRequired())){
      coffeeSelected = null;
      System.out.println("Not enough ingredients");
      return;
    }
    System.out.println("Coffee Selected: "+coffeeSelected.getName());
    System.out.println("\nPlease complete the payment");
  }

  public void pay(double amount){
    if(coffeeSelected==null){
      System.out.println("Please Select the Coffee first");
      return;
    }
    if(amount<coffeeSelected.getPrice()){
      System.out.println("Insufficient amount");
      return;
    }

    System.out.println("Dispensing Coffee");

    ingredientsController.removeIngredient(coffeeSelected.getIngredientsRequired());
    System.out.println("Enjoy your coffee");

    if(amount>coffeeSelected.getPrice()){
      System.out.println("Here is your change " + (amount-coffeeSelected.getPrice()));
      return;
    }

    coffeeSelected = null;
    
  }

  
}