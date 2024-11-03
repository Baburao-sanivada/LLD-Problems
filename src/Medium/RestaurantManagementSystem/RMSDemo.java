package Medium.RestaurantManagementSystem;

import Medium.RestaurantManagementSystem.Payment.PaymentType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RMSDemo {
    public static void run(){
        RestuarantManagementSystem rmsSystem = RestuarantManagementSystem.getInstance();

        MenuItem item1 = rmsSystem.addMenuItem("Chicken Biriyani",200,ItemCategory.BIRIYANI);
        MenuItem item2 = rmsSystem.addMenuItem("Chicken Fry Biriyani",230,ItemCategory.BIRIYANI);
        MenuItem item3 = rmsSystem.addMenuItem("Chicken 65",250,ItemCategory.STARTERS);
        MenuItem item4 = rmsSystem.addMenuItem("Chicken Boneless",290,ItemCategory.STARTERS);
        MenuItem item5 = rmsSystem.addMenuItem("Chicken Dum Biriyani",210,ItemCategory.BIRIYANI);

        rmsSystem.showMenu();

        System.out.println("Price Updated");
        rmsSystem.updateMenuItem(item1,290);
        rmsSystem.showMenu();

        Map<MenuItem,Integer> cart = new ConcurrentHashMap<>();
        cart.put(item1,2);
        cart.put(item3,1);

        String orderId = rmsSystem.placeOrder(cart, PaymentType.CARD);
        rmsSystem.showPendingOrder();

        System.out.println("Cancelled the order");
        rmsSystem.cancelOrder(orderId);

        System.out.println("Placed a new Order");
        orderId = rmsSystem.placeOrder(cart, PaymentType.CARD);
        rmsSystem.showPendingOrder();

        System.out.println("Marked as delived");
        rmsSystem.markOrderAsDelivered(orderId);

        rmsSystem.showPendingOrder();

    }
}
