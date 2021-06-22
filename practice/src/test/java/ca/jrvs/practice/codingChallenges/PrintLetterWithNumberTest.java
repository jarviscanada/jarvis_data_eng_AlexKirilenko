package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrintLetterWithNumberTest {

  @Test
  public void printLetterWithNumber() {
    assertEquals("a1b2c3e5e5", PrintLetterWithNumber.printLetterWithNumber("abcee"));

    assertEquals("A27", PrintLetterWithNumber.printLetterWithNumber("A"));

    assertEquals("z26", PrintLetterWithNumber.printLetterWithNumber("z"));

    assertEquals("A27B28C29D30E31E31", PrintLetterWithNumber.printLetterWithNumber("ABCDEE"));


    assertEquals("", PrintLetterWithNumber.printLetterWithNumber(""));

    try {
      assertEquals("a1", PrintLetterWithNumber.printLetterWithNumber("a1"));
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

  }
}