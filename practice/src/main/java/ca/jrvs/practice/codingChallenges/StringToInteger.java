package ca.jrvs.practice.codingChallenges;

import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * converts a string to a 32-bit signed integer
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-25fa6d81f8c74834b3924f90ba837c07
 * The solution is tested on https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger {

  /**
   * Iterating over the characters of given string and building a queue with only digits
   * Takes O(n) time and O(n) extra space, where n is the length of the input string
   * @param s input string
   * @return the integer representation of the number
   */
  public static int strintToIntegerManual(String s) {
    String sTrimmed = s.trim();
    boolean isPositive = sTrimmed.matches("^\\+?\\d+.*") ? true : false;
    Queue<Integer> digits = new LinkedList<>();
    for (char c : sTrimmed.toCharArray()) {
      if (digits.isEmpty() && (c == '-' || c == '+')) {
        digits.add(Character.getNumericValue('0'));
        continue;
      }

      if (!Character.isDigit(c)) {
        break;
      }
      digits.add(Character.getNumericValue(c));
    }

    // composing the number of its digits
    long result = 0;
    for (int i : digits) {
      if (result != 0) {
        result *= 10;
      }
      result += i;
      if (isPositive && result >= Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
      } else if (!isPositive && -1*result <= Integer.MIN_VALUE) {
        return Integer.MIN_VALUE;
      }
    }

    return isPositive ? (int) result : (int) (-1 * result);
  }

  /**
   * Parse the string using Java Language tools
   * Takes O(n) time and O(n) extra space, where n is the length of the input string
   * @param s input string
   * @return the integer representation of the number

   */
  public static int strintToIntegerDefault(String s) {
    s = s.trim();

    String patternString = "^([\\+-]?)(\\d+).*";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(s);
    if (!matcher.matches()) {
      return 0;
    }
    System.out.println(matcher.group(1));
    System.out.println(matcher.group(2));

    if (matcher.group(1).equals("-")) {
      BigInteger bi = new BigInteger("-" + matcher.group(2));
      if (bi.compareTo(new BigInteger(Integer.toString(Integer.MIN_VALUE))) <= 0) {
        return Integer.MIN_VALUE;
      } else {
        return bi.intValue();
      }
    } else {
      BigInteger bi = new BigInteger(matcher.group(2));
      if (bi.compareTo(new BigInteger(Integer.toString(Integer.MAX_VALUE))) >= 0) {
        return Integer.MAX_VALUE;
      } else {
        return bi.intValue();
      }
    }
  }

  }
