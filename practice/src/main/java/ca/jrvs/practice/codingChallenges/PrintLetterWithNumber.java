package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Print-letter-with-number-a0680a3403864b9980f6ba08acceb980
 * Given a string of letters return a new string where each letter is appended by its position in the original string
 */
public class PrintLetterWithNumber {

  /**
   * Using string builder
   * Solution time is O(n)
   * Solution uses O(n) extra space
   * @param s
   * @return
   */
  public static String printLetterWithNumber(String s) {

    StringBuilder sb = new StringBuilder(2*s.length());

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      sb.append(c);
      if (Character.isUpperCase(c)) {
        sb.append(c - 'A' + 27);
      } else if (Character.isLowerCase(c)) {
        sb.append(c - 'a' + 1);
      } else {
        throw new RuntimeException("Input string must containt only lower and upper case characters");
      }
    }
    System.arraycopy();

    return sb.toString();
  }

}
