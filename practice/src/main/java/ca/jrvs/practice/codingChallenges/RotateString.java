package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Rotate-String-0ea1b25f2fb64e2fa3a7c2b91c191e19
 * Check if rotated string can be produced from given string by moving leftmost character to
 * the rightmost position any number of times
 * The solution is tested using https://leetcode.com/problems/rotate-string/
 */
public class RotateString {

  /**
   * Concatenates given string and checks if the second string is contained in in
   * Time complexity is O(n^2)
   * Space complexity is O(n)
   * @param s
   * @param goal
   * @return
   */
  public static boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) return false;
    return (s + s).contains(goal);

  }
}
