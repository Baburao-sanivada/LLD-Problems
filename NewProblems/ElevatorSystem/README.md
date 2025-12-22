

Requirements:
1. Multiple Elevators in a Building
2. User can click up/down buttons on each floor to request an elevator
3. User can select floor inside the elevator
4. Elevator move, close/open doors

Core Entities:
- Elevator
- Scheduler
- Floor
- Elevator System
- Request (Floor Number, Direction)
- Direction Enum (UP, DOWN, IDLE)
- Elevator State Enum (MOVING, IDLE, DOOR_OPEN, DOOR_CLOSED)

Flow:
 User Request -> Elevator Controller (has all elevators)  -> Scheduler (decides which elevator to send) -> Elevator (moves to floor, opens/closes doors)


Class Outline:
- Scheduler 
     - has ElevatorController Instance - Has access to current position of every Elevator
     - ScheduleRequest(request: Request): Elevator
     - SchedulesBasedOnStrategy(strategy: SchedulingStrategy) - FCFS, Nearest Car
     - strategy.schedule(request, elevators) - Get the best elevator for the request based on strategy
- ElevatorController
     - elevators: List<Elevator>
     - requestElevator(request: Request): Elevator
     - create threadpool - Submit each Object
- Elevator - Implements Runnable
     - Has 4 PQs for up and down requests, upPending and downPending
     - We override run method to simulate elevator movement
     - Run method has while(!shutdown) and synchronized on lock object - lock.wait()
     - Use getNextStop() method if it null wait on lock object
     - If not move to the stop
     - getNextStop(): Integer
       - if moving up get check for the items in up pq else down pq
       - When checked UP requests and no more requests check for pendingDownQueue and move down
     - addInsideRequest(floor: Integer) - lock.notifyAll()
        - If selectedFloor > currentFloor add to up pq else down pq
     - Add outsideRequest(request: Request) - lock.notifyAll()
        - If request.direction is UP - check current floor and direction if same and request is above add to up pq else pendingUpQueue
    
   



- Check how you wait when the elevator is at rest - On Lock Object