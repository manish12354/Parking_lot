package com.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LotEventListenersTest {
  boolean showFullMessageCalled = false;
  boolean showVacantMessageCalled = false;
  private class TestOwner implements ParkingLotListeners {
    @Override
    public void showFullMessage() {
      System.out.println ("Ooh!");
      showFullMessageCalled = true;
    }

    @Override
    public void showVacantMessage() {
      System.out.println ("Great!");
      showVacantMessageCalled = true;
    }
  }
  LotEventListeners lotEventListeners;
  @Before
  public void setUp() {
    lotEventListeners = new LotEventListeners ();
  }

  @Test
  public void shouldAddListener() {
    TestOwner listener = new TestOwner ();
    lotEventListeners.addListener ( listener );
    assertTrue(lotEventListeners.has(listener));
  }

  @Test
  public void shouldFireEventForEmptyMessage() {
    TestOwner testOwner = new TestOwner ();
    lotEventListeners.addListener ( testOwner );
    lotEventListeners.fireEventForEmptyMessage ();
    assertTrue ( showFullMessageCalled );
  }

  @Test
  public void shouldFireEventForVacantMessage() {
    TestOwner testOwner = new TestOwner ();
    lotEventListeners.addListener ( testOwner );
    lotEventListeners.fireEventForVacantMessage ();
    assertTrue ( showVacantMessageCalled);
  }
}
