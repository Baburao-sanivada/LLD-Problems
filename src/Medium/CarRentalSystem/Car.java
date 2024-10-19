package Medium.CarRentalSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Car {
    private String id;
    private String make;
    private String model;
    private int year;
    private String licenceNumber;
    private int rentPerDay;
    private Map<String,Boolean> bookingStatus;

    public Car(String id,String make, String model, int year, String licenceNumber, int rentPerDay) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licenceNumber = licenceNumber;
        this.rentPerDay = rentPerDay;
        bookingStatus = new ConcurrentHashMap<>();
    }

    public synchronized void markAsBooked(String date){
        bookingStatus.put(date,true);
    }

    public void markAsAvailable(String date){
        bookingStatus.remove(date);
    }

    public boolean checkIfCarAvailable(String date){
        return !bookingStatus.containsKey(date);
    }


    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public int getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(int rentPerDay) {
        this.rentPerDay = rentPerDay;
    }
}
