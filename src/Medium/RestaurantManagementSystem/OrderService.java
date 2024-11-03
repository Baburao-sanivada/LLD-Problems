package Medium.RestaurantManagementSystem;

import Medium.RestaurantManagementSystem.Payment.PaymentService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class OrderService {
    private final Map<String,Order> orderMap;
    public AtomicLong orderId;
    public OrderService(){
        orderMap = new ConcurrentHashMap<>();
        orderId = new AtomicLong(0);
    }

    public Order placeOrder( Map<MenuItem, Integer> orderedItems, PaymentService paymentService){
        double amount = calculateAmout(orderedItems);
        Order order = new Order(orderId.incrementAndGet()+"",orderedItems,amount);
        if(paymentService.collectCash(amount)){
            orderMap.put(order.getOrderId(),order);
            System.out.println("Order place Successfully");
        }
        return order;
    }

    public double calculateAmout(Map<MenuItem, Integer> orderedItems){
        double amount=0;
        for(MenuItem item: orderedItems.keySet()){
            int quantity = orderedItems.get(item);
            amount += (quantity*item.getPrice());
        }
        return amount;
    }
    public void cancelOrder(String currOrderId){
        Order order = orderMap.get(currOrderId);
        order.markAsOrderCancelled();
        System.out.println("Order Cancelled Successfully");
    }

    public void markOrderAsDelivered(String currOrderId){
        Order order = orderMap.get(currOrderId);
        order.setOrderStatus(OrderStatus.DELIVERED);
    }

    public void showPendingOrders(){
        System.out.println("-----------------------");
        System.out.println("-----Pending Orders------");
        for(Order order: orderMap.values()){
            if(order.getOrderStatus() == OrderStatus.PLACED){
                System.out.println("Order: "+order.getOrderId());
                System.out.println("Items");
                for(MenuItem item: order.getOrderedItems().keySet()){
                    System.out.println("Item: "+item.getItemName()+" Quantity: "+order.getOrderedItems().get(item));
                }
            }
        }
        System.out.println("----------------------------");
    }
}
