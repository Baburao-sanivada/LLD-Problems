package ParkingLotSystem.Models;

import java.util.UUID;

public class Floor {
    private UUID id;
    private int floorNumber;

    public Floor(int floorNumber) {
        this.id = UUID.randomUUID();
        this.floorNumber = floorNumber;}

    public UUID getId() {
        return id;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

//    public List<ParkingSlot> getParkingSlotList() {
//        return parkingSlotList;
//    }
//
//    public void setParkingSlotList(List<ParkingSlot> parkingSlotList) {
//        this.parkingSlotList = parkingSlotList;
//    }
//
//    public void addParkingSlot(ParkingSlot parkingSlot) {
//        this.parkingSlotList.add(parkingSlot);
//    }
//
//    public void removeParkingSlot(ParkingSlot parkingSlot) {
//        this.parkingSlotList.remove(parkingSlot);
//    }
}
