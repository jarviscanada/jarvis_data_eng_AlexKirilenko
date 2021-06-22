package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

public class OnlyDigitsStringTest {

  @Test
  public void onlyDigitsASCII() {
    assertTrue(OnlyDigitsString.onlyDigitsASCII("123123"));
    assertTrue(OnlyDigitsString.onlyDigitsASCII("1"));
    assertTrue(OnlyDigitsString.onlyDigitsASCII("00000"));
    assertFalse(OnlyDigitsString.onlyDigitsASCII("-123"));
    assertFalse(OnlyDigitsString.onlyDigitsASCII(" 123"));
    assertFalse(OnlyDigitsString.onlyDigitsASCII("123 "));
    assertFalse(OnlyDigitsString.onlyDigitsASCII("123a"));
    assertFalse(OnlyDigitsString.onlyDigitsASCII("1.23"));
  }

  @Test
  public void onlyDigitsParse() {
    assertTrue(OnlyDigitsString.onlyDigitsParse("123123"));
    assertTrue(OnlyDigitsString.onlyDigitsParse("1"));
    assertTrue(OnlyDigitsString.onlyDigitsParse("00000"));
    assertFalse(OnlyDigitsString.onlyDigitsParse("-123"));
    assertFalse(OnlyDigitsString.onlyDigitsParse(" 123"));
    assertFalse(OnlyDigitsString.onlyDigitsParse("123 "));
    assertFalse(OnlyDigitsString.onlyDigitsParse("123a"));
    assertFalse(OnlyDigitsString.onlyDigitsParse("1.23"));
  }

  @Test
  public void onlyDigitsRegex() {
    assertTrue(OnlyDigitsString.onlyDigitsRegex("123123"));
    assertTrue(OnlyDigitsString.onlyDigitsRegex("1"));
    assertTrue(OnlyDigitsString.onlyDigitsRegex("00000"));
    assertFalse(OnlyDigitsString.onlyDigitsRegex("-123"));
    assertFalse(OnlyDigitsString.onlyDigitsRegex(" 123"));
    assertFalse(OnlyDigitsString.onlyDigitsRegex("123 "));
    assertFalse(OnlyDigitsString.onlyDigitsRegex("123a"));
    assertFalse(OnlyDigitsString.onlyDigitsRegex("1.23"));
  }
}