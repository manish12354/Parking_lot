package com.parkinglot;

public class VehicleNotFoundException extends Throwable {
  public VehicleNotFoundException() {
    super("Car is not parked!");
  }
}
