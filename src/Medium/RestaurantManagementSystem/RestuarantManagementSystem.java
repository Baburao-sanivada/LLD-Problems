package Medium.RestaurantManagementSystem;

import Medium.RestaurantManagementSystem.Payment.CardPayment;
import Medium.RestaurantManagementSystem.Payment.PaymentService;
import Medium.RestaurantManagementSystem.Payment.PaymentType;
import Medium.RestaurantManagementSystem.Payment.UPIPayment;

import java.util.Map;

public class RestuarantManagementSystem {

    private static RestuarantManagementSystem instance;
    private final OrderService orderService;
    private final MenuController menuController;
    private final InventoryService inventoryService;

    public synchronized static RestuarantManagementSystem getInstance(){
        if(instance == null){
            instance = new RestuarantManagementSystem();
        }
        return instance;
    }

    private RestuarantManagementSystem(){
        orderService = new OrderService();
        menuController = new MenuController();
        inventoryService = new InventoryService();
    }

    public String placeOrder(Map<MenuItem, Integer> orderedItems, PaymentType paymentType){
        PaymentService paymentService = paymentType == PaymentType.CARD ? new CardPayment() : new UPIPayment();
        return orderService.placeOrder(orderedItems,paymentService).getOrderId();
    }

    public void markOrderAsDelivered(String orderId){
        orderService.markOrderAsDelivered(orderId);
    }

    public void cancelOrder(String orderId){
        orderService.cancelOrder(orderId);
    }

    public void showPendingOrder(){
        orderService.showPendingOrders();
    }

    public MenuItem addMenuItem(String itemName, double price, ItemCategory category){
        return menuController.addItem(itemName,price,category);
    }

    public void updateMenuItem(MenuItem item,double price){
        menuController.updateItemPrice(item,price);
    }

    public void showMenu(){
        menuController.showMenuItems();
    }


}
