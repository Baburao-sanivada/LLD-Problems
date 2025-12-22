package ElevatorSystem;

import ElevatorSystem.Enums.ElevatorDirection;
import ElevatorSystem.Enums.ElevatorState;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Elevator implements Runnable {

    private String elevatorId;
    private volatile int currentFloor;
    private volatile ElevatorState elevatorState;
    private NavigableSet<Integer> upQueue;
    private NavigableSet<Integer> downQueue;
    private NavigableSet<Integer> pendingUpRequests;
    private NavigableSet<Integer> pendingDownRequests;
    private ElevatorDirection elevatorDirection;
    private volatile boolean shutDown;
    private Object lock;

    public Elevator(String elevatorId, int currentFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.elevatorState = ElevatorState.IDLE;
        this.upQueue = new ConcurrentSkipListSet<>();
        this.downQueue = new ConcurrentSkipListSet<>(Collections.reverseOrder());
        this.pendingUpRequests = new ConcurrentSkipListSet<>();
        this.pendingDownRequests = new ConcurrentSkipListSet<>(Collections.reverseOrder());
        this.elevatorDirection = ElevatorDirection.UP;
        this.shutDown = false;
        this.lock = new Object();
    }



    @Override
    public void run() {
        while (!shutDown) {
            try {
                Integer nextStop = getNextStop();
                if(nextStop == null) {
                    synchronized (lock) {
                        if(nextStop == null) {
                            elevatorState = ElevatorState.IDLE;
                            System.out.println("---------  Elevator Id: "+ elevatorId +" is IDLE at floor " + currentFloor);
                            lock.wait();

                        }
                    }
                    continue;
                }
                moveToFloor(nextStop);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Integer getNextStop() {
        if(elevatorDirection == ElevatorDirection.UP) {
            Integer stop = upQueue.pollFirst();
            if(stop != null) return stop;

            while (!pendingDownRequests.isEmpty()) {
                downQueue.add(pendingDownRequests.pollFirst());
            }

            stop = downQueue.pollFirst();
            if(stop != null) {
                elevatorDirection = ElevatorDirection.DOWN;
                return stop;
            }
        }
        else{
            Integer stop = downQueue.pollFirst();
            if(stop != null) {
                return stop;
            }

            // Transfer pending up requests to active queue when changing direction
            while (!pendingUpRequests.isEmpty()) {
                upQueue.add(pendingUpRequests.pollFirst());
            }

            stop = upQueue.pollFirst();
            if(stop != null)
            {
                elevatorDirection = ElevatorDirection.UP;
                return stop;
            }
        }
        return null;
    }

    private void moveToFloor(int targetFloor) {
        System.out.println("Elevator Id: "+ elevatorId +"   Moving to floor " + targetFloor);
        elevatorState = (elevatorDirection == ElevatorDirection.UP) ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
        try {
            if(elevatorDirection == ElevatorDirection.UP) {
                for(int i=currentFloor; i<=targetFloor; i++){
                    System.out.println("Elevator Id: "+ elevatorId +"   Currently at floor " + i);
                    Thread.sleep(1000);
                    currentFloor = targetFloor;
                }
            }
            else{
                for(int i=currentFloor; i>=targetFloor; i--){
                    System.out.println("Elevator Id: "+ elevatorId +"   Currently at floor " + i);
                    Thread.sleep(1000);
                    currentFloor = targetFloor;
                }
            }

            System.out.println("Elevator Id: "+ elevatorId +"   Arrived at floor " + targetFloor);
            elevatorState = ElevatorState.DOORS_OPEN;
            System.out.println("Elevator Id: "+ elevatorId +"   Doors open at floor:" + targetFloor);
            Thread.sleep(2000); // Simulate door open time
            System.out.println("Elevator Id: "+ elevatorId + "   Doors closed at floor:" + targetFloor);
            elevatorState = ElevatorState.IDLE;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public  void addInsideRequest(int floor) {
        synchronized (lock) {
            if (floor > currentFloor) {
                upQueue.add(floor);
            } else if (floor < currentFloor) {
                downQueue.add(floor);
            }
            if(elevatorState == ElevatorState.IDLE){
                elevatorDirection = floor > currentFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN;
            }
            lock.notifyAll();
        }
    }

    public void addFloorRequestFromOutside(int floor, ElevatorDirection direction) {
        synchronized (lock) {
            if (direction == ElevatorDirection.UP) {
                if (elevatorDirection == ElevatorDirection.UP && floor < currentFloor) {
                    // Elevator going up but request is below current floor
                    pendingUpRequests.add(floor);
                } else {
                    upQueue.add(floor);
                }
            } else {
                if (elevatorDirection == ElevatorDirection.DOWN && floor > currentFloor) {
                    // Elevator going down but request is above current floor
                    pendingDownRequests.add(floor);
                } else {
                    downQueue.add(floor);
                }
            }
            lock.notifyAll();
        }
    }


    public void shutDown() {
        this.shutDown = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public ElevatorDirection getElevatorDirection() {
        return elevatorDirection;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }
}
