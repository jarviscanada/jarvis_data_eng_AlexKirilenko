package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwapTwoNumbersTest {

  @Test
  public void swapBitOperation() {
    assertArrayEquals(new int[] {0, 0}, SwapTwoNumbers.swapBitOperation(new int[] {0, 0}));

    assertArrayEquals(new int[] {10, 0}, SwapTwoNumbers.swapBitOperation(new int[] {0, 10}));

    assertArrayEquals(new int[] {10, 10}, SwapTwoNumbers.swapBitOperation(new int[] {10, 10}));

    assertArrayEquals(new int[] {-5, 3}, SwapTwoNumbers.swapBitOperation(new int[] {3, -5}));

    assertArrayEquals(new int[] {10, 20}, SwapTwoNumbers.swapBitOperation(new int[] {20, 10}));

    assertArrayEquals(new int[] {-30, -50}, SwapTwoNumbers.swapBitOperation(new int[] {-50, -30}));
  }

  @Test
  public void swapArithmetic() {
    assertArrayEquals(new int[] {0, 0}, SwapTwoNumbers.swapArithmetic(new int[] {0, 0}));

    assertArrayEquals(new int[] {10, 0}, SwapTwoNumbers.swapArithmetic(new int[] {0, 10}));

    assertArrayEquals(new int[] {10, 10}, SwapTwoNumbers.swapArithmetic(new int[] {10, 10}));

    assertArrayEquals(new int[] {-5, 3}, SwapTwoNumbers.swapArithmetic(new int[] {3, -5}));

    assertArrayEquals(new int[] {10, 20}, SwapTwoNumbers.swapArithmetic(new int[] {20, 10}));

    assertArrayEquals(new int[] {-30, -50}, SwapTwoNumbers.swapArithmetic(new int[] {-50, -30}));
  }
}