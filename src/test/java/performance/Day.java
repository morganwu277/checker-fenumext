package performance;

/**
 * Created by morganwu on 7/13/16.
 */
public enum Day {
  SUNDAY("Sunday"),
  MONDAY("Monday"),
  TUESDAY("Tuesday"),
  WEDNESDAY("Wednesday"),
  THURSDAY("Thursday"),
  FRIDAY("Friday"),
  SATURDAY("Saturday");

  private String day;

  private Day(String day) {
    this.day = day;
  }

  public String getDay() {
    return this.day;
  }

  public static void main(String[] args) {

  }
}

