package com.parkinglot;

import java.util.ArrayList;

public class Attendant implements ParkingLotListeners {


//  private final Assistant assistant;
  private final ArrayList <ParkingLot> parkingLots;

  public Attendant() {
    this.parkingLots = new ArrayList<> (  );
//    this.assistant = new Assistant ();
  }

  public Object park(Vehicle car) throws VehicleCannotBeParkedException {
    Object token;
    for (ParkingLot parkingLot : parkingLots) {
      if (!parkingLot.isFull ()) {
        token = parkingLot.park ( car );
        return token;
      }
    }
    throw new VehicleCannotBeParkedException ();
  }

  public void addLots(ParkingLot parkingLot){
    parkingLots.add ( parkingLot );
  }

  public Vehicle unPark(Object token) throws VehicleNotFoundException {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasCar ( token ))
        return parkingLot.unParkCar ( token );
    }
    throw new VehicleNotFoundException ();
  }

  public void subscribeItself(){
    for (ParkingLot parkingLot : parkingLots) {
      parkingLot.addListener ( this );
    }
  }

  @Override
  public void showFullMessage() {
    System.out.println ("Full");
  }

  @Override
  public void showVacantMessage() {
    System.out.println ("Not full");
  }
}
