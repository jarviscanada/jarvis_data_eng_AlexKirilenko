package ca.jrvs.practice.codingChallenges;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Checks that a given set of parentheses is valid
 * https://www.notion.so/jarvisdev/Valid-Parentheses-8e936866d9184dc2b3402e8af7aba61b
 * Tested using https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

  /**
   * Uses a stack
   * Time complexity is O(n) and space complexity is O(n) where n is the legnth of the string
   * @param s string of parentheses
   * @return true if is valid
   */
  public static boolean isValid(String s) {
    Deque<Character> stack = new LinkedList<>();
    if (s == null || s.length() == 0) return true;
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
        continue;
      }
      if (stack.isEmpty()) {
        return false;
      }
      if (c == ')' && stack.pop() != '(') {
        return false;
      } else if (c == '}' && stack.pop() != '{') {
        return false;
      } else if (c == ']' && stack.pop() != '[') {
        return false;
      }
    }
    return stack.size() == 0;
  }

}
