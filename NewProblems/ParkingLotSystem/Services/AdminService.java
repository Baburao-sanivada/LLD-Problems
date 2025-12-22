package ParkingLotSystem.Services;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.Floor;
import ParkingLotSystem.Models.ParkingSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AdminService {
    private SlotService slotService;
    private PricingService pricingService;
    private FloorService floorService;

    public AdminService(SlotService slotService, PricingService pricingService, FloorService floorService) {
        this.slotService = slotService;
        this.pricingService = pricingService;
        this.floorService = floorService;
    }

    public boolean releaseSlot(String slotId) {
        slotService.releaseSlot(slotId);
        return true;
    }

    public void updatePricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        pricingService.updatePricingRule(vehicleType, ratePerHour, flatRate);
    }

    public void addPricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        pricingService.addPricingRule(vehicleType, ratePerHour, flatRate);
    }

    public void addFloor(int floorNumber){
        Optional<Floor> floor = floorService.findByFloorNo(floorNumber);

        if(floor.isEmpty()){
            Floor newFloor = new Floor(floorNumber);
            floorService.addFloor(newFloor);
        }
    }

    public void addSlotsToFloor(int floorNumber, VehicleType vehicleType, int count){
        Optional<Floor> floor = floorService.findByFloorNo(floorNumber);

        if(floor.isEmpty()){
            addFloor(floorNumber);
        }

        for(int i = 0; i<count; i++){
            ParkingSlot slotToAdd = new ParkingSlot(floorNumber, vehicleType);
            slotService.addSlot(slotToAdd);
        }
    }


    public void initializeDefaultPricingRules() {
        System.out.println("[REPOSITORY] Initializing default pricing rules");

        pricingService.addPricingRule(VehicleType.BIKE, 10.0, 30.0);
        pricingService.addPricingRule(VehicleType.CAR, 20.0, 60.0);
        pricingService.addPricingRule(VehicleType.TRUCK, 15.0, 45.0);
    }


    public Map<VehicleType, Integer> getAvailableSlots(int floorNumber) {
        Map<VehicleType, Integer> availableSlots = new HashMap<>();

        // get all slots in that floor
        for (VehicleType vehicleType : VehicleType.values()) {
            int count = slotService.getAvailableSlotCountByFloor(floorNumber, vehicleType);
            availableSlots.put(vehicleType, count);
        }

        return availableSlots;
    }
}
