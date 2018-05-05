package com.parkinglot;

public class VehicleCannotBeParkedException extends Throwable {
  public VehicleCannotBeParkedException() {
    super("Car is already parked !!");
  }
}
