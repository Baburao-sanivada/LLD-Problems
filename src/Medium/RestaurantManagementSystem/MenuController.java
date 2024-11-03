package Medium.RestaurantManagementSystem;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MenuController {
    private final Map<String,MenuItem> menuItems;
    public AtomicLong itemId;
    public MenuController(){
        menuItems = new ConcurrentHashMap<>();
        itemId = new AtomicLong(0);
    }

    public MenuItem addItem(String itemName, double price, ItemCategory category){
        MenuItem item = new MenuItem(itemId.incrementAndGet()+"",itemName,price,category);
        menuItems.put(item.getItemId(),item);
        System.out.println("Item Added Successfully");
        return item;
    }

    public void updateItemPrice(MenuItem item,double price){
        item.updatePrice(price);
    }

    public void showMenuItems(){
        System.out.println("----------------------------");
        System.out.println("Menu");
        showBiriryanis();
        showStarters();
        System.out.println("----------------------------");
    }

    public void showBiriryanis(){
        System.out.println("-----Biriyanis--------");
        for(String itemId: menuItems.keySet()){
            MenuItem item = menuItems.get(itemId);
            if(item.getCategory() == ItemCategory.BIRIYANI){
                System.out.println(item.getItemName()+" price: "+item.getPrice());
            }
        }
    }

    public void showStarters(){
        System.out.println("-------------Starters-------------------");
        for(String itemId: menuItems.keySet()){
            MenuItem item = menuItems.get(itemId);
            if(item.getCategory() == ItemCategory.STARTERS){
                System.out.println(item.getItemName()+" price: "+item.getPrice());
            }
        }
    }
}
