package vitals; 



public abstract class VitalsChecker {
    static boolean vitalsOk(float temperature, float pulseRate, float spo2) throws InterruptedException {
        displayWarnings(temperature, pulseRate, spo2); // <-- early warning
        String errorMessage = checkVitals(temperature, pulseRate, spo2);
        if (null != errorMessage) {
            return handleError(errorMessage);
        }
        return true;
    }

    private static void displayWarnings(float temperature, float pulseRate, float spo2) {
    // Temperature warning
    checkWarning("Temperature", temperature, 95, 102, "hypothermia", "hyperthermia");

    // Pulse rate warning
    checkWarning("Pulse Rate", pulseRate, 60, 100, "low pulse rate", "high pulse rate");

      // SPO2 warnings (only lower limit)
    checkWarning("Oxygen Saturation", spo2, 90, 100, "low oxygen saturation", null);
}

    private static String checkVitals(float temperature, float pulseRate, float spo2) {
        if (!isTemperatureNormal(temperature)) {
            return "Temperature is critical!";
        }
        if (!isPulseRateNormal(pulseRate)) {
            return "Pulse Rate is out of range!";
        }
        if (!isSpo2Normal(spo2)) {
            return "Oxygen Saturation out of range!";
        }
        return null;
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

    private static void checkWarning(String vitalName, float value, float lower, float upper, String lowerWarningMsg, String upperWarningMsg) {
    float tolerance = 0.015f * upper;

    if (value >= lower && value <= lower + tolerance) {
        System.out.println("Warning: Approaching " + lowerWarningMsg);
    }

    if (upperWarningMsg != null && value <= upper && value >= upper - tolerance) {
        System.out.println("Warning: Approaching " + upperWarningMsg);
    }
}
}
