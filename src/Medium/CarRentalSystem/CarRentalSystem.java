package Medium.CarRentalSystem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CarRentalSystem {

    private static CarRentalSystem instance;
    private final List<Booking> bookings;
    private final Map<String,User> users;
    private final Map<String,Car> cars;
    private final Payment paymentInstance;

    public static CarRentalSystem getInstance(){
        if(instance == null){
            instance = new CarRentalSystem();
        }
        return instance;
    }

    private CarRentalSystem(){
        bookings = new CopyOnWriteArrayList<>();
        users = new ConcurrentHashMap<>();
        cars = new ConcurrentHashMap<>();
        paymentInstance = Payment.getInstance();
    }

    public synchronized void bookCar(User user,String carId,String date){
        Car car = cars.get(carId);
        if(car.checkIfCarAvailable(date)){
            if(paymentInstance.collectAmount(car.getRentPerDay())){
                Booking booking = new Booking((bookings.size()+1)+"",user.getUserId(),car.getId(),date,date,car.getRentPerDay());
                bookings.add(booking);
                car.markAsBooked(date);
                System.out.println("Congratulations the car has been booked successfully on : "+date);
            }
        }
        else{
            System.out.println("Car is already booked");
        }
    }

    public synchronized void filterByPrice(int low,int high,String date){
        System.out.println("Cars Available");
        for(Car car: cars.values()){
            int price = car.getRentPerDay();
            if(price>=low && price<=high && car.checkIfCarAvailable(date)){
                System.out.println("Car Id: "+car.getId()+" Make: "+car.getMake()+" Model: "+car.getModel()+" Price: "+car.getRentPerDay());
            }
        }
    }

    public synchronized void updateBooking(String bookingId,String date){
        for(Booking booking: bookings){
            if(booking.getId().equals(bookingId)){
                Car car = cars.get(booking.getCarId());
                car.markAsAvailable(booking.getStartDate());
                booking.setStartDate(date);
                car.markAsBooked(date);
                break;
            }
        }
    }

    public synchronized void cancelBooking(String bookingId){
        for(Booking booking: bookings){
            if(booking.getId().equals(bookingId)){
                Car car = cars.get(booking.getCarId());
                car.markAsAvailable(booking.getStartDate());
                bookings.remove(booking);
                break;
            }
        }
    }

    public void showCarsAvailableOnDate(String date){
        System.out.println("Cars Available");
        for(Car car: cars.values()){
            if(car.checkIfCarAvailable(date)){
                System.out.println("Car Id: "+car.getId()+" Make: "+car.getMake()+" Model: "+car.getModel()+" Price: "+car.getRentPerDay());
            }
        }
    }

    public void addUser(User user){
        users.put(user.getUserId(), user);
    }

    public void addCar(Car car){
        cars.put(car.getId(),car);
    }

    public void removeCar(String id){
        cars.remove(id);
    }
}
