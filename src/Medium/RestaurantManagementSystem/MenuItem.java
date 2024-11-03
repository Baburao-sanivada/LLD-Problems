package Medium.RestaurantManagementSystem;

public class MenuItem {
    private final String itemId;
    private final String itemName;
    private double price;
    private final ItemCategory category;
    private AvailabilityStatus availabilityStatus;

    public MenuItem(String itemId, String itemName, double price, ItemCategory category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        availabilityStatus = AvailabilityStatus.AVAILABLE;
    }

    public void updatePrice(double price){
        this.price = price;
        System.out.println("Price Updated of: "+itemName);
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }


    public ItemCategory getCategory() {
        return category;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void markAsSoldOut() {
        this.availabilityStatus = AvailabilityStatus.SOLDOUT;
    }

    public void markAsAvailable(){
        this.availabilityStatus = AvailabilityStatus.AVAILABLE;
    }
}
