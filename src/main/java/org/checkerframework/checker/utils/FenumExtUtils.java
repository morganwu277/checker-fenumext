package org.checkerframework.checker.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FenumExtUtils {

  /**
   * [@android.stringdef.qual.StringDef({"A", "B"})] => List["A", "B"]
   * 
   * @return List["A", "B"]
   */
  public static List<String> getStringValueList(String annotationMirrorString) {
    List<String> result = new ArrayList<String>();
    Pattern p = Pattern.compile("\"(.+?)\"");
    Matcher m = p.matcher(annotationMirrorString);
    while (m.find()) {
      result.add(m.group());
    }
    return result;
  }
  
  /**
   * [@android.stringdef.qual.StringDef({1, 2})] => List[1, 2]
   * 
   * @return List[1, 2]
   */
  public static List<Integer> getIntValueList(String annotationMirrorString) {
    List<Integer> result = new ArrayList<Integer>();
    Pattern p = Pattern.compile("\\{(.+?)\\}");
    Matcher m = p.matcher(annotationMirrorString);
    if (m.find()) {
      String[] integers = m.group(1).split(",");
      for(String _i: integers){
        result.add(Integer.valueOf(_i.trim()));
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<String> result = FenumExtUtils.getStringValueList("[@android.stringdef.qual.StringDef({\"A\", \"B\"})]");
    System.out.println(result);
    List<Integer> result_integer = FenumExtUtils.getIntValueList("[@android.stringdef.qual.StringDef({1, 2})]");
    System.out.println(result_integer);
  }
}
