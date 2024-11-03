package Medium.RestaurantManagementSystem;

import java.util.Map;

public class Order {
    private final String orderId;
    private final Map<MenuItem,Integer> orderedItems;
    private final double amount;
    private OrderStatus orderStatus;

    public Order(String orderId, Map<MenuItem, Integer> orderedItems, double amount) {
        this.orderId = orderId;
        this.orderedItems = orderedItems;
        this.amount = amount;
        this.orderStatus = OrderStatus.PLACED;
    }

    public void markAsOrderCancelled(){
        this.orderStatus = OrderStatus.CANCELED;
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<MenuItem, Integer> getOrderedItems() {
        return orderedItems;
    }

    public double getAmount() {
        return amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
