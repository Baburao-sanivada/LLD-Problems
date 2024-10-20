package Medium.HotelManagementSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    private final String reservationId;
    private  Guest guest;
    private  Room room;
    private LocalDate expectedCheckInDate;
    private LocalDate expectedCheckOutDate;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private  ReservationStatus status;

    public Reservation(String reservationId, Guest guest, Room room,LocalDate expectedCheckInDate,LocalDate expectedCheckOutDate) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        status  = ReservationStatus.BOOKED;
        this.expectedCheckOutDate = expectedCheckOutDate;
        this.expectedCheckInDate = expectedCheckInDate;
    }

    public LocalDate getExpectedCheckInDate() {
        return expectedCheckInDate;
    }

    public LocalDate getExpectedCheckOutDate() {
        return expectedCheckOutDate;
    }

    public void cancelReservation(){
        status = ReservationStatus.CANCELLED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
