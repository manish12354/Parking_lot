package com.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AttendantTest {
  private Attendant attendant;
  ParkingLot parkingLot1;
  ParkingLot parkingLot2;

  public class TestCar implements Vehicle {}

  public class Owner implements ParkingLotListeners {
    @Override
    public void showFullMessage() {
      System.out.println ("Ooh!");
    }

    @Override
    public void showVacantMessage() {
      System.out.println ("Great!");
    }
  }

  public class CityCouncil implements ParkingLotListeners {

    @Override
    public void showFullMessage() {
      System.out.println ("Acknowledge");
    }

    @Override
    public void showVacantMessage() {
      System.out.println ("Acknowledge");
    }
  }

  @Before
  public void setUp() {
    parkingLot1 = new ParkingLot ( 1 );
    parkingLot2 = new ParkingLot ( 2 );
    attendant = new Attendant ();
    LotEventListeners lotEventListeners = new LotEventListeners ();
    lotEventListeners.addListener ( attendant );
    lotEventListeners.addListener ( new Assistant () );
    lotEventListeners.addListener ( new Owner () );
    lotEventListeners.addListener ( new CityCouncil () );
    parkingLot1.addListeners ( lotEventListeners );
    attendant.addLots ( parkingLot1 );
    attendant.addLots ( parkingLot2 );
    attendant.subscribeItself ();
  }

  @Test
  public void shouldBeAbleToParkCar() throws VehicleCannotBeParkedException {
    Vehicle car = new TestCar ();
    Object token = attendant.park(car);
    assertNotNull(token);
  }

  @Test
  public void shouldBeAbleToParkCarEvenIfOneParkingLotIsFull() throws VehicleCannotBeParkedException {
    Vehicle car1 = new TestCar ();
    Vehicle car2 = new TestCar ();

    attendant.park(car1);
    Object token2 = attendant.park(car2);
    assertNotNull(token2);
  }

  @Test (expected = VehicleCannotBeParkedException.class)
  public void shouldThrowExceptionForParkingAVehicleIfAllLotsAreFilled() throws VehicleCannotBeParkedException {
    attendant.park ( new TestCar () );
    attendant.park ( new TestCar () );
    attendant.park ( new TestCar () );
    attendant.park ( new TestCar () );
  }

  @Test
  public void shouldBeAbleToUnParkCar() throws VehicleCannotBeParkedException, VehicleNotFoundException {
    Vehicle car = new TestCar ();
    Object token = attendant.park(car);
    Vehicle anotherCar = attendant.unPark (token);
    assertEquals(car,anotherCar);
  }

  @Test(expected = VehicleNotFoundException.class)
  public void shouldThrowExceptionIfCarNotParkedInAnyLot() throws VehicleNotFoundException {
   attendant.unPark (new TestCar ());
  }

  @Test
  public void foo() throws VehicleCannotBeParkedException {
    attendant.park(new TestCar());
  }

  @Test
  public void foos() throws VehicleCannotBeParkedException, VehicleNotFoundException {
    TestCar car = new TestCar ();
    TestCar car2 = new TestCar ();
    Object token1 = attendant.park( car );
    Object token2 = attendant.park ( car2 );
    attendant.unPark ( token1 );
    attendant.unPark ( token2 );
  }
}
