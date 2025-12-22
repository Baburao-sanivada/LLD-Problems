package ParkingLotSystem.Repository;

import ParkingLotSystem.Models.Floor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FloorRepository {
    private Map<String, Floor> floorMap = new ConcurrentHashMap<>();

    public Floor save(Floor floor) {
        floorMap.put(floor.getId().toString(), floor);
        return floor;
    }

    public Optional<Floor> findById(String UUID) {
        return Optional.ofNullable(floorMap.get(UUID));
    }

    public void deleteById(String UUID) {
        floorMap.remove(UUID);
    }

    public Optional<Floor> findByFloorNumber(int floorNo) {
        return floorMap.values().stream()
                .filter(floor -> floor.getFloorNumber() == floorNo)
                .findFirst();
    }


}
