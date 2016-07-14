package performance;

import java.util.Random;

/**
 * Created by morganwu on 7/13/16.
 */
public class PerfTest {
  public static final int D1_INT = 1;
  public static final int D2_INT = 2;
  public static final String D1_STR = "1";
  public static final String D2_STR = "2";


  public static void main(String[] args) {

    long startTime = System.nanoTime();
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      Day d1 = Day.FRIDAY;
      Day d2 = Day.SATURDAY;
//      int d1 = D1_INT;
//      int d2 = D2_INT;
      if (d1 == d2) {
        System.out.println("...");
      }


//      String d1 = "d1";
//      String d2 = "d2";
//      if (d1.equals(d2)) {
//        System.out.println("...");
//      }

    }
    long duration = System.nanoTime() - startTime;
    System.out.println("Taken " + duration + " nano seconds");
  }

  public Day randmonDay() {
    int index = (new Random().nextInt() % 7);
    switch (index) {
      case 0:
        return Day.MONDAY;
      case 1:
        return Day.TUESDAY;
      case 2:
        return Day.WEDNESDAY;
      case 3:
        return Day.THURSDAY;
      case 4:
        return Day.FRIDAY;
      case 5:
        return Day.SATURDAY;
      case 6:
        return Day.SUNDAY;
    }
    return Day.MONDAY;
  }
}
