package ParkingLotSystem;

import ParkingLotSystem.Vehicle.Vehicle;
import ParkingLotSystem.Vehicle.VehicleType;

public class Spot {
  private final int spotId;
  private final VehicleType vehicleType;
  private Vehicle vehicleParked;

  public Spot(int spotId, VehicleType vehicleType){
    this.spotId = spotId;
    this.vehicleType = vehicleType;
  }

  public boolean isAvailable(){
    return vehicleParked == null;
  }

  public synchronized void parkVehicle(Vehicle vehicle){
    if(isAvailable() && vehicleType.equals(vehicle.getVehicleType())){
      vehicleParked = vehicle;
      return;
    }
    throw new IllegalArgumentException("Invalid vehicle type or spot already occupied.");
  }

  public synchronized void releaseVehicle(){
    vehicleParked = null;
  }

  public VehicleType getVehicleType(){
    return vehicleType;
  }

  public Vehicle getParkedVehicle(){
    return vehicleParked;
  }

  public int getSpotId(){
    return spotId;
  }

  
}