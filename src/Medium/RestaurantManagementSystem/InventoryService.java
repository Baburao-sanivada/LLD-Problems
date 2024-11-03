package Medium.RestaurantManagementSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {
    private final Map<String,Long> itemsQuantity;
    public InventoryService(){
        itemsQuantity = new ConcurrentHashMap<>();
    }

    public void addInventory(String name,long quantity){
        itemsQuantity.put(name,quantity);
        System.out.println("Inventory Item Added");
    }
    public void updateInventory(String name,long quantity){
        itemsQuantity.put(name,quantity);
        System.out.println("Inventory Item updated");
    }
    public void deleteInventory(String name,long quantity){
        itemsQuantity.remove(name);
        System.out.println("Inventory Item removed");
    }
}
