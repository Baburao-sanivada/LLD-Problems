package ParkingLotSystem.Repository;

import ParkingLotSystem.Enums.VehicleType;
import ParkingLotSystem.Models.PricingRule;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PricingRuleRepository {
    private Map<UUID, PricingRule> map = new ConcurrentHashMap<>();
    private Map<VehicleType, PricingRule> vehicleToPricing = new ConcurrentHashMap<>();

    public PricingRule save(PricingRule pricingRule) {
        map.put(pricingRule.getId(), pricingRule);
        vehicleToPricing.put(pricingRule.getVehicleType(), pricingRule);
        return pricingRule;
    }
    public PricingRule findById(UUID id) {
        return map.get(id);
    }

    public PricingRule findByVehicleType(VehicleType vehicleType) {
        return vehicleToPricing.get(vehicleType);
    }

    public void deleteById(UUID id) {
        PricingRule pricingRule = map.get(id);
        if(pricingRule != null) {
            vehicleToPricing.remove(pricingRule.getVehicleType());
        }
        map.remove(id);
    }

    public void clearAll() {
        map.clear();
        vehicleToPricing.clear();
    }

    public void update(PricingRule pricingRule) {
        map.put(pricingRule.getId(), pricingRule);
        vehicleToPricing.put(pricingRule.getVehicleType(), pricingRule);
    }
}
