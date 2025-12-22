package ParkingLotSystem.Controller;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Services.AdminService;

import java.util.Map;

public class AdminController {
    private AdminService adminService;

    public  AdminController(AdminService AdminService) {
        this.adminService = AdminService;
    }

    public void addFloor(int floorNumber){
        adminService.addFloor(floorNumber);
    }

    public boolean releaseSlot(String slotId){
        return adminService.releaseSlot(slotId);
    }

    public void updatePricingRule(VehicleType vehicleType, double ratePerHour, double flatRate){
        adminService.updatePricingRule(vehicleType, ratePerHour, flatRate);
    }

    public void addPricingRule(VehicleType vehicleType, double ratePerHour, double flatRate){
        adminService.addPricingRule(vehicleType, ratePerHour, flatRate);
    }

    public void addSlotsToFloor(int floorNumber, VehicleType vehicleType, int count){
        adminService.addSlotsToFloor(floorNumber, vehicleType, count);
    }

    public void initializeDefaultPricingRules(){
        adminService.initializeDefaultPricingRules();
    }

    public Map<VehicleType, Integer> getAvailableSlots(int floorNumber) {
        return adminService.getAvailableSlots(floorNumber);
    }


}
