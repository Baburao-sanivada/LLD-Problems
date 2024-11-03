package Medium.ElevatorSystem;

public class ElevatorDemo {
    public static void run() {
        Elevator elevator1 = new Elevator("1",65,-2,5);

        elevator1.addUpRequest(1,3);
        elevator1.addDownRequests(2,-2);
        elevator1.addUpRequest(2,4);
        elevator1.addDownRequests(3,-2);
        elevator1.addUpRequest(0,4);
        elevator1.addDownRequests(5,-2);
        elevator1.addUpRequest(-2,-1);
        elevator1.addDownRequests(3,1);
        elevator1.start();

    }
}
