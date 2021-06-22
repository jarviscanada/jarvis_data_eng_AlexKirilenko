package ca.jrvs.practice.codingChallenges;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implements various methods to check if string has only digits
 * https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-ef000ace101b4cbb9f538ad9096341a7
 */
public class OnlyDigitsString {

  /**
   * Checks if each char in the string is 0 to 9
   * runs in O(n) where n is the length of the string
   * @param s
   * @return
   */
  public static boolean onlyDigitsASCII(String s) {
    for (int i = 0; i < s.length(); i++) {
      char c =  s.charAt(i);
      if ( (c - '0') > 9 || (c - '0') < 0 ) {
        return false;
      }
    }
    return true;
  }

  /**
   * Using inbuilt parsing method
   * @param s
   * @return
   */
  public static boolean onlyDigitsParse(String s) {
    try {
      int value = Integer.parseInt(s);
      return value >= 0;
    } catch (NumberFormatException e) {
    }
    return false;
  }

  /**
   * Using regex
   * @param s
   * @return
   */
  public static boolean onlyDigitsRegex(String s) {
    Pattern pattern = Pattern.compile("^\\d+$");
    Matcher matcher = pattern.matcher(s);
    return matcher.matches();
  }

}
