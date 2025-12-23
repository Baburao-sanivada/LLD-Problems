package ParkingLotSystem;

import java.util.ArrayList;

import ParkingLotSystem.Vehicle.Vehicle;
import ParkingLotSystem.Vehicle.Car;
import ParkingLotSystem.Vehicle.Bike;
import ParkingLotSystem.Vehicle.Truck;
import ParkingLotSystem.ParkingLot;
import ParkingLotSystem.Vehicle.VehicleType;

public class ParkingLotDemo{
  private ParkingLot parkingLot;
  public ParkingLotDemo(){
    // Create Parking Lot
    parkingLot = ParkingLot.getInstance();
    
    // Create Levels
    Level level1 = new Level(1, 10);
    Level level2 = new Level(2, 20);

    parkingLot.addLevel(level1);
    parkingLot.addLevel(level2);

    // Create Vehicle
    Vehicle car = new Car("car1");
    Vehicle bike = new Bike("bike1");
    Vehicle truck = new Truck("truck");

    parkingLot.parkVehicle(car);
    parkingLot.showEmptySpots();
    System.out.println("------------------------------------------");

    parkingLot.parkVehicle(bike);
    parkingLot.showEmptySpots();
    System.out.println("------------------------------------------");

    parkingLot.releaseVehicle(car);
    parkingLot.showEmptySpots();
    System.out.println("------------------------------------------");
    
  }
}