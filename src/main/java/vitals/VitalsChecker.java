package vitals; 


public abstract class VitalsChecker {
    static boolean vitalsOk(float temperature, float pulseRate, float spo2) throws InterruptedException {
        if (!isTemperatureNormal(temperature)) {
            return handleError("Temperature is critical!");
        }
        if (!isPulseRateNormal(pulseRate)) {
            return handleError("Pulse Rate is out of range!");
        }
        if (!isSpo2Normal(spo2)) {
             return handleError("Oxygen Saturation out of range!");
        }
        return true;
    }

    private static boolean isTemperatureNormal(float temperature) {
        return temperature >= 95 && temperature <= 102;
    }

    private static boolean isPulseRateNormal(float pulseRate) {
        return pulseRate >= 60 && pulseRate <= 100;
    }

    private static boolean isSpo2Normal(float spo2) {
        return spo2 >= 90;
    }

    private static void displayAlertAnimation() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            System.out.print("\r* ");
            Thread.sleep(1000);
            System.out.print("\r *");
            Thread.sleep(1000);
        }
    }

    private static boolean handleError(String message) throws InterruptedException {
    System.out.println(message);
    displayAlertAnimation();
    return false;
  }
}
