package Easy.CoffeeVendingMachine;

public class CoffeMachineDemo {
  public void run(){
    CoffeeMachine coffeeMachine = CoffeeMachine.getInstance();

    // Display coffee menu
    coffeeMachine.displayMenu();

    // Simulate user requests
    coffeeMachine.selectCoffee("Espresso");
    coffeeMachine.pay(100);

    coffeeMachine.selectCoffee("Latte");
    coffeeMachine.pay(20);


    coffeeMachine.selectCoffee("Cappachino");
    coffeeMachine.pay(80);
  }
}