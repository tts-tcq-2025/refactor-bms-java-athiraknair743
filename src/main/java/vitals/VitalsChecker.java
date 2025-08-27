package vitals;

enum Condition {
    CRITICAL_LOW, WARNING_LOW, NORMAL, WARNING_HIGH, CRITICAL_HIGH
}

class VitalRange {
    String name;
    float lower;
    float upper;
    String lowMsg;
    String highMsg;

    VitalRange(String name, float lower, float upper, String lowMsg, String highMsg) {
        this.name = name;
        this.lower = lower;
        this.upper = upper;
        this.lowMsg = lowMsg;
        this.highMsg = highMsg;
    }
}

public abstract class VitalsChecker {

    static boolean vitalsOk(float temperature, float pulseRate, float spo2) throws InterruptedException {
        VitalRange tempRange = new VitalRange("Temperature", 95, 102, "hypothermia", "hyperthermia");
        VitalRange pulseRange = new VitalRange("Pulse Rate", 60, 100, "low pulse rate", "high pulse rate");
        VitalRange spo2Range = new VitalRange("Oxygen Saturation", 90, 100, "low oxygen saturation", "high oxygen saturation");

        boolean tempOk = processVital(temperature, tempRange);
        boolean pulseOk = processVital(pulseRate, pulseRange);
        boolean spo2Ok = processVital(spo2, spo2Range);

        return tempOk && pulseOk && spo2Ok;
    }

    private static boolean processVital(float value, VitalRange range) throws InterruptedException {
        Condition condition = getCondition(value, range);
        String message = getMessage(condition, range);

        if (message != null) {
            System.out.println(message);
        }

        if (condition == Condition.CRITICAL_LOW || condition == Condition.CRITICAL_HIGH) {
            displayAlertAnimation();
            return false;
        }

        return true;
    }

    private static Condition getCondition(float value, VitalRange range) {
        float tolerance = 0.015f * range.upper; // 1.5% of upper limit

        if (value < range.lower) return Condition.CRITICAL_LOW;
        if (value >= range.lower && value <= range.lower + tolerance) return Condition.WARNING_LOW;
        if (value > range.upper) return Condition.CRITICAL_HIGH;
        if (value >= range.upper - tolerance && value <= range.upper) return Condition.WARNING_HIGH;

        return Condition.NORMAL;
    }

    private static String getMessage(Condition condition, VitalRange range) {
        switch (condition) {
            case CRITICAL_LOW:
                return range.name + " is critical! (" + range.lowMsg + ")";
            case WARNING_LOW:
                return "Warning: Approaching " + range.lowMsg;
            case WARNING_HIGH:
                return "Warning: Approaching " + range.highMsg;
            case CRITICAL_HIGH:
                return range.name + " is critical! (" + range.highMsg + ")";
            default:
                return null;
        }
    }

    private static void displayAlertAnimation() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            System.out.print("\r* ");
            Thread.sleep(1000);
            System.out.print("\r *");
            Thread.sleep(1000);
        }
        System.out.println();
    }
}
