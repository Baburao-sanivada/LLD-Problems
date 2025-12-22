package ParkingLotSystem.Services;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.ParkingSlot;
import ParkingLotSystem.Repository.SlotRepository;

import java.util.List;
import java.util.Optional;

public class SlotService {
    private SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    // Add methods to manage parking slots
    public List<ParkingSlot> findAvailableSlotsByVehicleType(VehicleType vehicleType) {
        return slotRepository.findAvailableSlotsByVehicleType(vehicleType);
    }

    public Optional<ParkingSlot> allocateSlot(VehicleType vehicleType) {
        return slotRepository.allocateSlot(vehicleType);
    }

    public void releaseSlot(String slotId) {
        slotRepository.releaseSlot(slotId);
    }

    public ParkingSlot addSlot(ParkingSlot slot) {
        return slotRepository.save(slot);
    }

    public void clearAllSlots() {
        slotRepository.clearAll();
    }

    public Integer getAvailableSlotCountByFloor(int floorNumber, VehicleType vehicleType) {
        List<ParkingSlot> slots = slotRepository.findAllByFloorNumber(floorNumber);
        int count = 0;
        for (ParkingSlot slot : slots) {
            if (slot.getVehicleType() == vehicleType && !slot.isOccupied()) {
                count++;
            }
        }
        return count;
    }

}
