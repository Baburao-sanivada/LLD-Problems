package ParkingLotSystem.Services;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.PricingRule;
import ParkingLotSystem.Repository.PricingRuleRepository;

public class PricingService {
    private PricingRuleRepository pricingRuleRepository;

    public PricingService(PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public double calculatePrice(VehicleType vehicleType, int durationInHours) {
        PricingRule pricingRule = pricingRuleRepository.findByVehicleType(vehicleType);
        // Hourly
        double hourly = durationInHours * pricingRule.getRatePerHour();

        //daily
        double daily = pricingRule.getFlatRate();

        return Math.min(hourly,daily);
    }

    public PricingRule getPricingRule(VehicleType vehicleType) {
        return pricingRuleRepository.findByVehicleType(vehicleType);
    }

    public void updatePricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        PricingRule pricingRule = pricingRuleRepository.findByVehicleType(vehicleType);
        if(pricingRule != null) {
            pricingRule.updateRates(ratePerHour, flatRate);
            pricingRuleRepository.update(pricingRule);
        }
    }

    public void addPricingRule(VehicleType vehicleType, double ratePerHour, double flatRate) {
        PricingRule newRule = new PricingRule(vehicleType, ratePerHour, flatRate);
        pricingRuleRepository.save(newRule);
    }

}
