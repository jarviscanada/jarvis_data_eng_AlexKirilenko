package ca.jrvs.practice.codingChallenges;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-9473873890a94c3086ab042a19ed2222
 * Calculate the i-th Fibonacci number
 * or the number of possible ways to climb n stairs, if only 1 and 2 steps can be taken at a time
 */
public class FibonacciNumbers {

  /**
   * Recursive solution
   * Uses Java's  function call stack, takes O(2^n) time complexity
   * @param n number of stairs/ position in Fibonacci's sequence
   * @return
   */
  public static long fibRecursive(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Input must be a positive number");
    }
    if (n == 0 || n ==  1) return 1;
    return fibRecursive(n-1) + fibRecursive(n-2);
  }

  /**
   * Dynamic programming solution
   * Reuses the previous results
   * Time and space complexity is O(n) since each number in the sequence is created only once
   * @param n
   * @return
   */
  public static long fibDP(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Input must be a positive number");
    }
    if (n == 0 || n == 1) return 1;
    List<Integer> sequence = new ArrayList<>(n+1);

    sequence.add(0, 1);
    sequence.add(1, 1);
    for (int i = 2; i <= n; i++) {
      sequence.add(i, sequence.get(i - 1) + sequence.get(i - 2));
    }
    return sequence.get(n);
  }


}
