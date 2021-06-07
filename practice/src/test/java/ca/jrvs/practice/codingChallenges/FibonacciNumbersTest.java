package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;


public class FibonacciNumbersTest {

  @Test
  public void fibRecursive() {
    try {
      FibonacciNumbers.fibRecursive(-2);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    assertEquals(1L, FibonacciNumbers.fibRecursive(0));
    assertEquals(1L, FibonacciNumbers.fibRecursive(1));
    assertEquals(2L, FibonacciNumbers.fibRecursive(2));
    assertEquals(3L, FibonacciNumbers.fibRecursive(3));
    assertEquals(5L, FibonacciNumbers.fibRecursive(4));
    assertEquals(8L, FibonacciNumbers.fibRecursive(5));
  }

  @Test
  public void fibDP() {
    try {
      FibonacciNumbers.fibDP(-2);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    assertEquals(1L, FibonacciNumbers.fibDP(0));
    assertEquals(1L, FibonacciNumbers.fibDP(1));
    assertEquals(2L, FibonacciNumbers.fibDP(2));
    assertEquals(3L, FibonacciNumbers.fibDP(3));
    assertEquals(5L, FibonacciNumbers.fibDP(4));
    assertEquals(8L, FibonacciNumbers.fibDP(5));
  }
}