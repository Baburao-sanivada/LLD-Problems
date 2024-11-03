package Medium.ElevatorSystem;

import java.util.PriorityQueue;

public class Elevator {
    private final String elevatorId;
    private final int elevatorCapacity;
    private Direction currDirection;
    private PriorityQueue<Integer> upRequest;
    private PriorityQueue<Integer> downRequests;
    private int currPos;
    private final int maxRange;
    private final int minRange;

    public Elevator(String id,int elevatorCapacity,int minRange,int maxRange){
        this.currDirection = Direction.IDLE;
        this.elevatorId = id;
        this.elevatorCapacity = elevatorCapacity;
        this.upRequest = new PriorityQueue<>();
        this.downRequests = new PriorityQueue<>((a,b)-> {
            return b-a;
        });
        this.maxRange = maxRange;
        this.minRange = minRange;
        this.currPos = -2;
    }

    public void addUpRequest(int source,int destination){
        upRequest.add(source);
        upRequest.add(destination);
    }

    public void addDownRequests(int source,int destination){
        System.out.println("Add Down Req Called");
        downRequests.add(source);
        downRequests.add(destination);
    }



    public void start(){
        while (!upRequest.isEmpty() || !downRequests.isEmpty()) {
            processRequests();
        }

        System.out.println("Finished all requests.");
        this.currDirection = Direction.IDLE;

    }

    public void processRequests(){
        if(currDirection == Direction.UP || currDirection == Direction.IDLE){
            // serve up as priority
            serveUpRequests();
            serveDownRequests();
        }
        else{
            serveDownRequests();
            serveUpRequests();
        }
    }

    public void serveUpRequests(){
        if(upRequest.isEmpty()) return;
        for(int floor=currPos;floor<=maxRange;floor++){
            this.currPos = floor;
            System.out.println("Lift is moving UP and is in "+floor+" floor");
            while(!upRequest.isEmpty() && floor == upRequest.peek()){
                upRequest.poll();
                System.out.println("Door is open at "+floor+" floor");
            }
            if(upRequest.isEmpty()){
                return;
            }
        }
        currDirection = Direction.DOWN;
    }
    public void serveDownRequests(){
        if(downRequests.isEmpty()) return;
        for(int floor=currPos;floor>=minRange && !downRequests.isEmpty();floor--){
            this.currPos = floor;
            System.out.println("Lift is moving Down and is in "+floor+" floor");
            while(!downRequests.isEmpty() && floor == downRequests.peek()){
                downRequests.poll();
                System.out.println("Door is open at "+floor+" floor");
            }
            if(downRequests.isEmpty()){
                break;
            }

        }
        currDirection = Direction.UP;
    }


}
