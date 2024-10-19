package ParkingLotSystem;

import java.util.ArrayList;

import ParkingLotSystem.Vehicle.Vehicle;

public class ParkingLot {
  
  private static ParkingLot instance;
  private final ArrayList<Level> levels;
  
  public synchronized static ParkingLot getInstance(){
    if(instance==null){
      instance=new ParkingLot();
    }
    return instance;
  }

  private ParkingLot(){
    levels = new ArrayList<Level>();
  }

  public ArrayList<Level> getLevels(){
    return levels;
  }

  public void addLevel(Level level){
    levels.add(level);
  }

  public void parkVehicle(Vehicle vehicle){
    for(Level level: levels){
      if(level.parkVehicle(vehicle)){
        System.out.println("Vehicle is parked");
        return;
      }
    }
    System.out.println("No available spot");
  }

  public void releaseVehicle(Vehicle vehicle){
    for(Level level: levels){
      if(level.releaseVehicle(vehicle)){
        System.out.println("Vehicle is Released");
        return;
      }
    }
    System.out.println("The Mentioned Vechicle is not present in the parking Lot");
  }

  public void showEmptySpots(){
    for(Level level: levels){
      System.out.println("Spots available in Level "+level.getLevelId());
      System.out.println("\n------------------------------------------");
      level.showEmptySpots();
      System.out.println("------------------------------------------");
    }
  }











  
}