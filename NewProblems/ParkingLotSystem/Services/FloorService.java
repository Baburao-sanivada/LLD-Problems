package ParkingLotSystem.Services;

import ParkingLotSystem.Models.Floor;
import ParkingLotSystem.Repository.FloorRepository;

import java.util.Optional;

public class FloorService {
    private FloorRepository floorRepository;

    public FloorService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Floor addFloor(Floor floor) {
        return floorRepository.save(floor);
    }

    public Optional<Floor> getFloorById(String UUID) {
        return floorRepository.findById(UUID);
    }

    public void deleteFloorById(String UUID) {
        floorRepository.deleteById(UUID);
    }

    public Optional<Floor> findByFloorNo(int floorNo) {
        return floorRepository.findByFloorNumber(floorNo);
    }
}
