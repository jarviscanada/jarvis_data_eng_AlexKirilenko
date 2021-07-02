package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Count-Primes-d28baa878e754fe5bc31eb5b2ac951d0
 * Count the number of primes less than a positive integer n
 * Solution is tested using https://leetcode.com/problems/count-primes/
 */
public class CountPrimes {

  /**
   * Sieve of Eratosthenes
   * Takes O(n) extra space
   * @param n positive integer
   * @return number of primes less than n
   */
  public static int countPrimes(int n) {
    boolean[] primes = new boolean[n];
    for (int i = 2; i < n; i ++) {
      primes[i] = true;
    }
    for (int i = 2; i*i <= n; i++) {
      if (!primes[i]) continue;
      for (int j = i*i; j < n; j += i) {
        primes[j] = false;
      }
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (primes[i]) count++;
    }
    return count;
  }
}
