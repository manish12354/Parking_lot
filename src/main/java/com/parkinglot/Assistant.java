package com.parkinglot;

public class Assistant implements ParkingLotListeners {
  public String message;
  public Assistant() {
    message = null;
  }

//  @Override
//  public void updateMessage(String message) {
//    this.message = message;
//    showFullMessage ();
//  }


  @Override
  public void showFullMessage() {
    System.out.println ("No Space!");
  }

  @Override
  public void showVacantMessage() {
    System.out.println ("Space Available");
  }
}
