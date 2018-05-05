package com.parkinglot;

import java.util.ArrayList;

public class LotEventListeners {
  private final ArrayList <ParkingLotListeners> parkingLotListeners;

  public LotEventListeners() {
    this.parkingLotListeners = new ArrayList <> ();
  }

  public void fireEventForEmptyMessage() {
    for (ParkingLotListeners parkingLotListeners : this.parkingLotListeners) {
      parkingLotListeners.showFullMessage ();
    }
  }

  public void fireEventForVacantMessage() {
    for (ParkingLotListeners parkingLotListeners : this.parkingLotListeners) {
      parkingLotListeners.showVacantMessage ();
    }
  }

  public void addListener(ParkingLotListeners parkingLotListeners) {
    this.parkingLotListeners.add ( parkingLotListeners );
  }

  public boolean has(ParkingLotListeners parkingLotListeners) {
    return this.parkingLotListeners.contains ( parkingLotListeners );
  }
}
