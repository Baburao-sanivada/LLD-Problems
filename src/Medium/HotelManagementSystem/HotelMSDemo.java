package Medium.HotelManagementSystem;

import Medium.HotelManagementSystem.Payment.CreditCardPayment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HotelMSDemo {
    public static void run(){
        RoomController roomController = new RoomController();

        HotelMS hmSystem = HotelMS.getInstance(roomController);

        // Add Rooms
        Room room1 = new Room("1",RoomType.SINGLE,300.0);
        Room room2 = new Room("2",RoomType.SINGLE,300.0);
        Room room3 = new Room("3",RoomType.SINGLE,300.0);
        Room room4 = new Room("4",RoomType.DOUBLE,500.0);
        Room room5 = new Room("5",RoomType.DOUBLE,500.0);
        Room room6 = new Room("6",RoomType.DOUBLE,500.0);
        Room room7 = new Room("7",RoomType.DOUBLE,500.0);
        Room room8 = new Room("8",RoomType.SUITE,1000.0);
        Room room9 = new Room("9",RoomType.SUITE,1000.0);
        Room room10 = new Room("10",RoomType.DELUXE,800.0);

        roomController.addRoom(room1);
        roomController.addRoom(room2);
        roomController.addRoom(room3);
        roomController.addRoom(room4);
        roomController.addRoom(room5);
        roomController.addRoom(room6);
        roomController.addRoom(room7);
        roomController.addRoom(room8);
        roomController.addRoom(room9);
        roomController.addRoom(room10);

        Guest babu = new Guest("1","Babu","9390639493");
        Guest vinay = new Guest("2","Vinay","9390654493");

        LocalDate date = LocalDate.now();

        hmSystem.getRoomsAvailable(date,date,RoomType.SINGLE);

        Reservation reservationDetails = hmSystem.bookRoom(room1,date,date,babu,new CreditCardPayment());
        reservationDetails = hmSystem.bookRoom(room2,date,date,babu,new CreditCardPayment());

        hmSystem.cancelBooking("1");
        hmSystem.getRoomsAvailable(date,date,RoomType.SINGLE);

//        hmSystem.checkIn(reservationDetails.getReservationId(), LocalDateTime.now());


    }
}
