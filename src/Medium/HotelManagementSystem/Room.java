package Medium.HotelManagementSystem;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Room {
    private final String roomId;
    private final RoomType roomType;
    private double price;
    private final Map<LocalDate,Boolean> bookedDates;
    private boolean checkedIn;

    public Room(String roomId,RoomType roomType,double price){
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.bookedDates = new ConcurrentHashMap<>();
        checkedIn = false;
    }

    public boolean checkIfAvailable(LocalDate checkIn,LocalDate checkOut){
        while(checkIn.compareTo(checkOut) != 1){
            if(bookedDates.containsKey(checkIn)) return false;
            checkIn = checkIn.plusDays(1);
        }
        return true;
    }

    public synchronized void book(LocalDate checkIn,LocalDate checkOut){
        while(checkIn.compareTo(checkOut) != 1){
            bookedDates.put(checkIn,true);
            checkIn = checkIn.plusDays(1);
        }
    }

    public synchronized void cancelBooking(LocalDate checkIn,LocalDate checkOut) {
        while (checkIn.compareTo(checkOut) != 1) {
            bookedDates.remove(checkIn);
            checkIn = checkIn.plusDays(1);
        }
    }

    public void checkIn(){
        checkedIn = true;
    }

    public void checkOut(){
        checkedIn = false;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}
