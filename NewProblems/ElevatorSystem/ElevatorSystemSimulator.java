package ElevatorSystem;

import ElevatorSystem.Enums.ElevatorDirection;

public class ElevatorSystemSimulator {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Elevator System Simulator Started");
        ElevatorController elevatorController = new ElevatorController();
        elevatorController.initializeElevators(2);
        // Simulate some floor requests
        elevatorController.addFloorRequest(5, ElevatorDirection.UP);
        Thread.sleep(1000);
        elevatorController.addFloorRequest(2, ElevatorDirection.DOWN);
        Thread.sleep(1000);
//        elevatorController.addFloorRequest(8, ElevatorDirection.UP);
//        Thread.sleep(1000);
//        elevatorController.addFloorRequest(1, ElevatorDirection.UP);
//        Thread.sleep(1000);
//        elevatorController.addFloorRequest(7, ElevatorDirection.DOWN);
        elevatorController.addFloorRequestFromInside("ELEVATOR_1", 9);
        elevatorController.addFloorRequestFromInside("ELEVATOR_2", 0);
    }
}
