package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-310c357ee72a4aacaddc1209a79f72e5
 * Checks if a number is even or odd
 */
public class CheckIsNumberEvenOrOdd {

  /**
   * Use modulo operation O(1) constant time
   * @param number
   * @return string with the parity of the number
   */
  public static String isEvenOrOddModulo(int number) {
    return number % 2 == 0 ? "even" : "odd";
  }

  /**
   * Check if the bit in the smallest position is 0 or 1
   * Constant time O(1)
   * @param number
   * @return string with the parity of the number
   */
  public static String isEvenOrOddBitOperation(int number) {
    return (number & 1) == 0 ? "even" : "odd";
  }


}
