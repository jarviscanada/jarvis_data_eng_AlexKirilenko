package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Swap-two-numbers-68697ff0bd154735a442d5ac27da6aef
 * Swaps two numbers without using the third variable
 */
public class SwapTwoNumbers {

  public static int[] swapBitOperation(int[] input) {
    input[0] = input[0] ^ input[1];
    input[1] = input[0] ^ input[1];
    input[0] = input[0] ^ input[1];
    return input;
  }

  public static int[] swapArithmetic(int[] input) {
    input[0] = input[0] + input[1];
    input[1] = input[0]  - input[1];
    input[0] = input[0] - input[1];
    return input;
  }

}
