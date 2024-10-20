package Medium.HotelManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RoomController {

    private Map<String,Room> rooms;

    public RoomController(){
        rooms = new ConcurrentHashMap<>();
    }

    public synchronized void addRoom(Room room){
        rooms.put(room.getRoomId(),room);
    }

    public synchronized void removeRoom(String roomId){
        rooms.remove(roomId);
    }

    public List<Room> getAvailableRooms(LocalDate checkIn,LocalDate checkOut,RoomType roomType){
        List<Room> roomsAvailable = new ArrayList<>();
        for(Room room: rooms.values()){
            if(room.getRoomType() == roomType && room.checkIfAvailable(checkIn,checkOut)){
                roomsAvailable.add(room);
            }
        }
        return roomsAvailable;
    }

    public void bookRoom(Room room,LocalDate checkIn,LocalDate checkOut){
        room.book(checkIn,checkOut);
    }
}
