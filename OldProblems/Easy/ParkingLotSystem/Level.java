package ParkingLotSystem;

import java.util.ArrayList;

import ParkingLotSystem.Vehicle.Vehicle;
import ParkingLotSystem.Vehicle.VehicleType;

public class Level {
  private final int totalSpots;
  private final int levelId;
  private final ArrayList<Spot> spots;
  
  public Level(int levelId, int totalSpots){
    this.levelId=levelId;
    this.totalSpots = totalSpots;

    this.spots=new ArrayList<Spot>();

    int totalBikeSpots = (int)(totalSpots*(0.5));
    int totalCarSpots = (int)(totalSpots*(0.4));

    for(int i=1;i<=totalBikeSpots;i++){
      spots.add(new Spot(i, VehicleType.BIKE));
    }
    for(int i=totalBikeSpots+1;i<=totalBikeSpots+totalCarSpots;i++){
      spots.add(new Spot(i, VehicleType.CAR));
    }
    for(int i=totalBikeSpots+totalCarSpots+1;i<=totalSpots;i++){
      spots.add(new Spot(i, VehicleType.TRUCK));
    }

  }

  public int getLevelId(){
    return levelId;
  }

  public int getTotalSpots(){
    return totalSpots;
  }

  public boolean parkVehicle(Vehicle vehicle){
    for(Spot spot: spots){
      if(spot.isAvailable() && spot.getVehicleType() == vehicle.getVehicleType()){
        spot.parkVehicle(vehicle);
        return true;
      }
    }
    return false;
  }


  public boolean releaseVehicle(Vehicle vehicle){
    for(Spot spot: spots){
      if(!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)){
        spot.releaseVehicle();
        return true;
      }
    }
    return false;
  }

  public void showEmptySpots(){
      for(Spot spot: spots){
        if(spot.isAvailable()){
          System.out.println("Spot is available with id  "+spot.getSpotId()+"  Supports "+spot.getVehicleType());
        }
      }
  }


  
}