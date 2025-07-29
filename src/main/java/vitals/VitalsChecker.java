package vitals;


public abstract class VitalsChecker {
  static boolean vitalsOk(float temperature, float pulseRate, float spo2) 
      throws InterruptedException {
    if (temperature > 102 || temperature < 95) {
      return handleError("Temperature is critical!");
    } else if (pulseRate < 60 || pulseRate > 100) {
      return handleError("Pulse Rate is out of range!");
    } else if (spo2 < 90) {
      return handleError("Oxygen Saturation out of range!");
    }
    return true;
  }

  private static void showAlertAnimation() throws InterruptedException {
     for (int i = 0; i < 6; i++) {
        System.out.print("\r* ");
        Thread.sleep(1000);
        System.out.print("\r *");
        Thread.sleep(1000);
      }
  } 

 private static boolean handleError(String errorMessage) throws InterruptedException{
    System.out.println(errorMessage);
    showAlertAnimation();
    return false;
 }
  
}
