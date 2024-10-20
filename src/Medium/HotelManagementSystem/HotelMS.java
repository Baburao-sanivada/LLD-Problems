package Medium.HotelManagementSystem;

import Medium.CarRentalSystem.Booking;
import Medium.HotelManagementSystem.Payment.PaymentController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class HotelMS {
    private static HotelMS instance;
    private final RoomController roomController;
    private final Map<String,Reservation> reservations;
    private AtomicLong reservationId = new AtomicLong(0);

    public static HotelMS getInstance(RoomController roomController){
        if(instance == null){
            instance = new HotelMS(roomController);
        }
        return instance;
    }
    private HotelMS(RoomController roomController){
        this.roomController = roomController;
        reservations = new ConcurrentHashMap<>();
    }

    public void getRoomsAvailable(LocalDate checkIn,LocalDate checkOut,RoomType roomType){
        List<Room> rooms = roomController.getAvailableRooms(checkIn,checkOut,roomType);
        System.out.println("List of rooms available on the mentioned date");
        for(Room room: rooms){
            System.out.println("Room Id: "+room.getRoomId());
        }
    }

    public synchronized Reservation bookRoom(Room room, LocalDate checkIn, LocalDate checkOut, Guest guest, PaymentController paymentMethod){
        if(!room.checkIfAvailable(checkIn,checkOut)){
            System.out.println("Sorry the room is already booked please look for another room");
            return null;
        }
        else{
            roomController.bookRoom(room,checkIn,checkOut);
            System.out.println("Please collect the payment: "+room.getPrice());
            if(paymentMethod.collectPayment(room.getPrice())){
                Reservation reservation = createReservation(room,guest,checkIn,checkOut);
                reservations.put(reservation.getReservationId(),reservation);
                System.out.println("Congratulations Your Booking is successful");
                System.out.println("Here is your Booking Id : "+reservation.getReservationId());
                return reservation;
            }
            else{
                room.cancelBooking(checkIn,checkOut);
                return null;
            }

        }
    }

    public void checkIn(String currReservationId, LocalDateTime dateTime){
        Reservation reservation = reservations.get(currReservationId);
        reservation.setCheckInTime(dateTime);
        reservation.getRoom().checkIn();
    }

    public void checkOut(String currReservationId,LocalDateTime dateTime){
        Reservation reservation = reservations.get(currReservationId);
        reservation.setCheckOutTime(dateTime);
        reservation.getRoom().checkOut();
    }

    public boolean cancelBooking(String currReservationId){
        Reservation reservation = reservations.get(currReservationId);
        reservation.cancelReservation();
        reservation.getRoom().cancelBooking(reservation.getExpectedCheckInDate(),reservation.getExpectedCheckOutDate());
        return true;
    }

    public Reservation createReservation(Room room, Guest guest,LocalDate checkIn, LocalDate checkOut){
        reservationId.incrementAndGet();
        return new Reservation(reservationId.get()+"",guest,room,checkIn,checkOut);
    }
}
