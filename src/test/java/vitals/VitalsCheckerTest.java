package vitals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class VitalsCheckerTest {

   // Normal cases
    @Test
    public void testVitalsOkWhenAllNormal() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(98.6f, 75, 96));
    }

    // Temperature tests
    @Test
    public void testTemperatureTooHigh() throws InterruptedException {
        assertFalse(VitalsChecker.vitalsOk(102.1f, 75, 96));
    }

    @Test
    public void testTemperatureTooLow() throws InterruptedException {
        assertFalse(VitalsChecker.vitalsOk(94.9f, 75, 96));
    }

    @Test
    public void testTemperatureAtUpperBoundary() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(102.0f, 75, 96));
    }

    @Test
    public void testTemperatureAtLowerBoundary() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(95.0f, 75, 96));
    }

    // Pulse rate tests
    @Test
    public void testPulseTooHigh() throws InterruptedException {
        assertFalse(VitalsChecker.vitalsOk(98.6f, 101, 96));
    }

    @Test
    public void testPulseTooLow() throws InterruptedException {
        assertFalse(VitalsChecker.vitalsOk(98.6f, 59, 96));
    }

    @Test
    public void testPulseAtUpperBoundary() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(98.6f, 100, 96));
    }

    @Test
    public void testPulseAtLowerBoundary() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(98.6f, 60, 96));
    }

    // Oxygen saturation tests
    @Test
    public void testOxygenTooLow() throws InterruptedException {
        assertFalse(VitalsChecker.vitalsOk(98.6f, 75, 89));
    }

    @Test
    public void testOxygenAtBoundary() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(98.6f, 75, 90));
    }

    @Test
    public void testOxygenAboveBoundary() throws InterruptedException {
        assertTrue(VitalsChecker.vitalsOk(98.6f, 75, 100));
    }

    // Multiple vital failures
    @Test
    public void testMultipleVitalsOffRange() throws InterruptedException {
        assertFalse(VitalsChecker.vitalsOk(103f, 110, 85));
    }

  @Test
  public void testNotOkWhenAnyVitalIsOffRange() throws InterruptedException {
    assertFalse(VitalsChecker.vitalsOk(99f, 102, 70));
    assertTrue(VitalsChecker.vitalsOk(98.1f, 70, 98));
  }
}

