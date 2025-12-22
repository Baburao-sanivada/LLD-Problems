package ParkingLotSystem.Repository;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.ParkingSlot;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SlotRepository {
    private Map<String, ParkingSlot> slotMap = new ConcurrentHashMap<>();

    public ParkingSlot save(ParkingSlot slot) {
        slotMap.put(slot.getId().toString(), slot);
        return slot;
    }

    public ParkingSlot findById(String UUID) {
        return slotMap.get(UUID);
    }

    public void deleteById(String UUID) {
        slotMap.remove(UUID);
    }

    public List<ParkingSlot> findAllByFloorNumber(int floorNumber) {
        List<ParkingSlot> slots = new ArrayList<>();
        for (ParkingSlot parkingSlot : slotMap.values()) {
            if(parkingSlot.getFloorNumber() == floorNumber){
                slots.add(parkingSlot);
            }
        }
        return slots;
    }

    public List<ParkingSlot> findAvailableSlotsByVehicleType(VehicleType vehicleType) {
        return slotMap.values().stream().filter(slot -> slot.getVehicleType().equals(vehicleType) && !slot.isOccupied()).collect(Collectors.toList());
    }

    public Optional<ParkingSlot> allocateSlot(VehicleType vehicleType) {
        return slotMap.values().stream()
                .filter(slot -> slot.getVehicleType().equals(vehicleType) && !slot.isOccupied())
                .findFirst()
                .map(slot -> {
                    slot.setOccupied(true);
                    return slot;
                });
    }

    public void releaseSlot(String slotId) {
        slotMap.get(slotId).setOccupied(false);
    }

    public void clearAll() {
        slotMap.clear();
    }
}
