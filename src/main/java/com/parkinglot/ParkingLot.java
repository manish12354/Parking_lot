package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {
  private final HashMap <Object,Vehicle> vehicles;
  private int capacity;
  private LotEventListeners listeners;
  private LotEventListeners lotEventListener;

  public ParkingLot(int capacity) {
    this.capacity = capacity;
    this.vehicles = new HashMap<> ( );
  }

  public Object park(Vehicle car) throws VehicleCannotBeParkedException {
    if (isFull ()|| vehicles.containsValue ( car ))
      throw new VehicleCannotBeParkedException ();
    Object token = new Object ();
    vehicles.put(token,car);
    if (isFull ()) {
      listeners.fireEventForEmptyMessage ();
    }
    return token;
  }

  public Vehicle unParkCar(Object token) throws VehicleNotFoundException {
    if (!hasCar (token))
      throw new VehicleNotFoundException ();
    if (isFull ())
      listeners.fireEventForVacantMessage ();
    Vehicle car = vehicles.remove (token);
    return car;
  }

  public boolean hasCar(Object token) {
    return vehicles.containsKey ( token );
  }


  public boolean isFull() {
    return capacity == vehicles.size ();
  }

  public void addListeners(LotEventListeners lotEventListeners) {
    this.lotEventListener= lotEventListeners;
  }

//  public void addListener(ParkingLotListeners parkingLotListeners) {
//    listeners.addListener ( parkingLotListeners );
//  }
}
