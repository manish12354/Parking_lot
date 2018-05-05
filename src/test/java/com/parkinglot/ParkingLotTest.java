package com.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest{
  public class TestCar implements Vehicle {}
  ParkingLot parkingLot;
  Vehicle car;

  @Before
  public void setUp() {
    parkingLot = new ParkingLot (5);
    car = new TestCar ();
  }

  @Test
  public void shouldParkCar() throws VehicleCannotBeParkedException {
    Object token = parkingLot.park (car);
    assertTrue (parkingLot.hasCar ( token ));
  }

  @Test(expected = VehicleCannotBeParkedException.class)
  public void shouldThrowExceptionParkCarIfCarIsAlreadyParked() throws VehicleCannotBeParkedException {
    parkingLot.park ( car );
    parkingLot.park ( car );
  }

  @Test
  public void shouldUnParkCar() throws VehicleNotFoundException, VehicleCannotBeParkedException {
    Object token = parkingLot.park ( car );
    Vehicle myCar = parkingLot.unParkCar (token);
    assertFalse ( parkingLot.hasCar ( this.car ) );
    assertEquals (car,myCar);
  }

  @Test
  public void shouldGiveFalseIfCarIsNotPresent() {
    assertFalse ( parkingLot.hasCar ( new Object () ) );
  }

  @Test
  public void shouldGiveTrueIfCarIsPresent() throws VehicleCannotBeParkedException {
    Object token = parkingLot.park ( car );
    assertTrue ( parkingLot.hasCar (token) );
  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldThrowExceptionIfCarIsNotInLot() throws VehicleNotFoundException {
    parkingLot.unParkCar ( car );
  }

  @Test
  public void shouldGiveTrueIfParkingLotIsFull() throws VehicleCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot ( 1 );
    parkingLot.park ( car );
    assertTrue ( parkingLot.isFull());
  }

  @Test
  public void shouldGiveFalseIfParkingLotIsNotFull() {
    ParkingLot parkingLot = new ParkingLot ( 1 );
    assertFalse ( parkingLot.isFull());
  }

  @Test(expected = VehicleCannotBeParkedException.class)
  public void shouldNotParkCarIfParkingLotIsFull() throws VehicleCannotBeParkedException {
    ParkingLot parkingLot = new ParkingLot ( 1 );
    parkingLot.park ( car );
    Vehicle anotherCar = new TestCar ();
    parkingLot.park ( anotherCar );
  }

  @Test
  public void shouldParkCarIfAnyCarIsUnParked() throws VehicleCannotBeParkedException, VehicleNotFoundException {
    ParkingLot parkingLot = new ParkingLot ( 2 );
    Vehicle anotherCar = new TestCar ();
    Object token = parkingLot.park ( car );
    parkingLot.park ( anotherCar );
    parkingLot.unParkCar(token);
    assertFalse ( parkingLot.hasCar(token));
    Object anotherToken = parkingLot.park (car);
    assertTrue ( parkingLot.hasCar(anotherToken));
  }
}

