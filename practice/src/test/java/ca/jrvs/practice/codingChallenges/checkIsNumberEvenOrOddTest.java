package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class checkIsNumberEvenOrOddTest {

  @Test
  public void isEvenOrOddModulo() {
    assertEquals("even", CheckIsNumberEvenOrOdd.isEvenOrOddModulo(0));
    assertEquals("even", CheckIsNumberEvenOrOdd.isEvenOrOddModulo(22));
    assertEquals("even", CheckIsNumberEvenOrOdd.isEvenOrOddModulo(-50));

    assertEquals("odd", CheckIsNumberEvenOrOdd.isEvenOrOddModulo(131));
    assertEquals("odd", CheckIsNumberEvenOrOdd.isEvenOrOddModulo(-55));
  }

  @Test
  public void isEvenOrOddBitOperation() {
    assertEquals("even", CheckIsNumberEvenOrOdd.isEvenOrOddBitOperation(0));
    assertEquals("even", CheckIsNumberEvenOrOdd.isEvenOrOddBitOperation(22));
    assertEquals("even", CheckIsNumberEvenOrOdd.isEvenOrOddBitOperation(-50));

    assertEquals("odd", CheckIsNumberEvenOrOdd.isEvenOrOddBitOperation(131));
    assertEquals("odd", CheckIsNumberEvenOrOdd.isEvenOrOddBitOperation(-55));
  }
}