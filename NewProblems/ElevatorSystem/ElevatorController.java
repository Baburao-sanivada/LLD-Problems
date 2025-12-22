package ElevatorSystem;

import ElevatorSystem.Enums.ElevatorDirection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ElevatorController {
    private final Map<String,Elevator> elevators;
    private final Scheduler scheduler;

    public ElevatorController() {
        this.elevators = new ConcurrentHashMap<>();
        this.scheduler = new Scheduler(this);
    }

    public void initializeElevators(int numberOfElevators) {
        for(int i=1;i<=numberOfElevators;i++) {
            String elevatorId = "ELEVATOR_" + i;
            Elevator elevator = new Elevator(elevatorId, 0);
            elevators.put(elevatorId, elevator);
            new Thread(elevator).start();
        }
    }

    public void addFloorRequest(int floorNumber, ElevatorDirection direction) {
        String elevatorId = scheduler.scheduleElevatorRequest(floorNumber, direction);
        if(elevatorId != null) {
            Elevator elevator = elevators.get(elevatorId);
            System.out.println("Elevator " + elevatorId + " assigned to floor request " + floorNumber + " in direction " + direction);
            System.out.println("----------------------------------------");
            elevator.addFloorRequestFromOutside(floorNumber, direction);
        }
    }

    public void addFloorRequestFromInside(String elevatorId, int floorNumber) {
        Elevator elevator = elevators.get(elevatorId);
        if(elevator != null) {
            elevator.addInsideRequest(floorNumber);
        }
    }

    public Elevator getElevatorById(String elevatorId) {
        return elevators.get(elevatorId);
    }

    public Map<String, Elevator> getElevators() {
        return elevators;
    }
}
