package ElevatorSystem;

import ElevatorSystem.Enums.ElevatorDirection;
import ElevatorSystem.Enums.ElevatorState;

public class Scheduler {

    private final ElevatorController elevatorController;
    private final int maxFloors = 10;
    public Scheduler(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    public String scheduleElevatorRequest(int floorNumber, ElevatorDirection direction) {
        System.out.println("----------------------------------------");
        System.out.println("Scheduling elevator for floor " + floorNumber + " in direction " + direction);
        String selectedElevatorId = null;
        int minDistance = Integer.MAX_VALUE;
        for (String elevatorId : elevatorController.getElevators().keySet()) {
            Elevator elevator = elevatorController.getElevatorById(elevatorId);
            int elevatorFloor = elevator.getCurrentFloor();
            ElevatorDirection elevatorDirection = elevator.getElevatorDirection();
            ElevatorState elevatorState = elevator.getElevatorState();
            System.out.println("Evaluating Elevator " + elevatorId + " at floor " + elevatorFloor + " moving " + elevatorDirection + " and state " + elevatorState);
            /*
            1. Same Direction and elevator can serve the request
            2. Same Direction but elevator can't serve now - adds to queue
            3. Opposite Direction don't serve now - adds to queue
             */
            if(elevatorState == ElevatorState.IDLE) {
                int distance = Math.abs(elevatorFloor - floorNumber);
                if(distance < minDistance) {
                    minDistance = distance;
                    selectedElevatorId =elevatorId;
                }
            }
            else if(elevatorDirection == direction) {
                if(direction == ElevatorDirection.UP) {
                    if(elevatorFloor <= floorNumber) {
                        int distance = floorNumber - elevatorFloor;
                        if(distance < minDistance) {
                            minDistance = distance;
                            selectedElevatorId = elevatorId;
                        }
                    }
                    else{
                        int distance = (maxFloors - elevatorFloor) + (maxFloors)+floorNumber;
                        if(distance < minDistance) {
                            minDistance = distance;
                            selectedElevatorId = elevatorId;
                        }
                    }
                }
                else{
                    if(elevatorFloor >= floorNumber) {
                        int distance = floorNumber - elevatorFloor;
                        if(distance < minDistance) {
                            minDistance = distance;
                            selectedElevatorId = elevatorId;
                        }
                    }
                    else{
                        int distance = elevatorFloor + maxFloors+ (maxFloors - floorNumber);
                        if(distance < minDistance) {
                            minDistance = distance;
                            selectedElevatorId = elevatorId;
                        }
                    }
                }
            }
            else{
                if(direction == ElevatorDirection.UP) {
                    int distance = elevatorFloor +  floorNumber;
                    if(distance < minDistance) {
                        minDistance = distance;
                        selectedElevatorId = elevatorId;
                    }
                }
                else{
                    int distance = maxFloors - elevatorFloor +  maxFloors-floorNumber;
                    if(distance < minDistance) {
                        minDistance = distance;
                        selectedElevatorId = elevatorId;
                    }
                }
            }

        }
        return selectedElevatorId;
    }
}
