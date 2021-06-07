package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareTwoMapsTest {

  @Test
  public void compareMapsDefault() {
    Map<String, String> m1Empty = new HashMap<>();
    Map<String, String> m2Empty = new HashMap<>();
    assertTrue(CompareTwoMaps.compareMapsDefault(m1Empty, m2Empty));
    assertTrue(CompareTwoMaps.compareMapsDefault(m2Empty, m1Empty));

    Map<String, String> m2NotEmpty = new HashMap<>();
    m2NotEmpty.put("one", "value");
    assertFalse(CompareTwoMaps.compareMapsDefault(m1Empty, m2NotEmpty));
    assertFalse(CompareTwoMaps.compareMapsDefault(m2NotEmpty, m1Empty));

    Map<String, String> m1Same = new HashMap<>();
    Map<String, String> m2Same = new HashMap<>();
    m1Same.put("one", "1");
    m1Same.put("two", "2");
    m2Same.put("one", "1");
    m2Same.put("two", "2");
    assertTrue(CompareTwoMaps.compareMapsDefault(m1Same, m2Same));
    assertTrue(CompareTwoMaps.compareMapsDefault(m2Same, m1Same));
    // compare with itself
    assertTrue(CompareTwoMaps.compareMapsDefault(m1Same, m1Same));
    // compare with a different map
    assertFalse(CompareTwoMaps.compareMapsDefault(m1Same, m2NotEmpty));
    assertFalse(CompareTwoMaps.compareMapsDefault(m2NotEmpty, m1Same));

    // same keys, different value
    Map<String, String> m2DifferentValue = new HashMap<>();
    m2DifferentValue.put("one", "I");
    m2DifferentValue.put("two", "2");
    assertFalse(CompareTwoMaps.compareMapsDefault(m1Empty, m2DifferentValue));
    assertFalse(CompareTwoMaps.compareMapsDefault(m2DifferentValue, m1Empty));

    Map<String, String> m1SameNullValue = new HashMap<>();
    Map<String, String> m2SameNullValue = new HashMap<>();
    m1SameNullValue.put("one", null);
    m1SameNullValue.put("two", "2");
    m2SameNullValue.put("one", null);
    m2SameNullValue.put("two", "2");
    assertTrue(CompareTwoMaps.compareMapsDefault(m1SameNullValue, m2SameNullValue));
    assertTrue(CompareTwoMaps.compareMapsDefault(m2SameNullValue, m1SameNullValue));

    // compare with a different map
    assertFalse(CompareTwoMaps.compareMapsDefault(m1Same, m2SameNullValue));
    assertFalse(CompareTwoMaps.compareMapsDefault(m2SameNullValue, m1Same));


    Map<String, String> m2DifferentNullValue = new HashMap<>();
    m2DifferentNullValue.put("one", null);
    m2DifferentNullValue.put("two", "2");
    m2DifferentNullValue.put("three", "3");
    assertFalse(CompareTwoMaps.compareMapsDefault(m1SameNullValue, m2DifferentNullValue));
    assertFalse(CompareTwoMaps.compareMapsDefault(m2DifferentNullValue, m1SameNullValue));
  }

  @Test
  public void compareMaps() {
    Map<String, String> m1Empty = new HashMap<>();
    Map<String, String> m2Empty = new HashMap<>();
    assertTrue(CompareTwoMaps.compareMaps(m1Empty, m2Empty));
    assertTrue(CompareTwoMaps.compareMaps(m2Empty, m1Empty));

    Map<String, String> m2NotEmpty = new HashMap<>();
    m2NotEmpty.put("one", "value");
    assertFalse(CompareTwoMaps.compareMaps(m1Empty, m2NotEmpty));
    assertFalse(CompareTwoMaps.compareMaps(m2NotEmpty, m1Empty));

    Map<String, String> m1Same = new HashMap<>();
    Map<String, String> m2Same = new HashMap<>();
    m1Same.put("one", "1");
    m1Same.put("two", "2");
    m2Same.put("one", "1");
    m2Same.put("two", "2");
    assertTrue(CompareTwoMaps.compareMaps(m1Same, m2Same));
    assertTrue(CompareTwoMaps.compareMaps(m2Same, m1Same));
    // compare with itself
    assertTrue(CompareTwoMaps.compareMaps(m1Same, m1Same));
    // compare with a different map
    assertFalse(CompareTwoMaps.compareMaps(m1Same, m2NotEmpty));
    assertFalse(CompareTwoMaps.compareMaps(m2NotEmpty, m1Same));

    // same keys, different value
    Map<String, String> m2DifferentValue = new HashMap<>();
    m2DifferentValue.put("one", "I");
    m2DifferentValue.put("two", "2");
    assertFalse(CompareTwoMaps.compareMaps(m1Empty, m2DifferentValue));
    assertFalse(CompareTwoMaps.compareMaps(m2DifferentValue, m1Empty));

    Map<String, String> m1SameNullValue = new HashMap<>();
    Map<String, String> m2SameNullValue = new HashMap<>();
    m1SameNullValue.put("one", null);
    m1SameNullValue.put("two", "2");
    m2SameNullValue.put("one", null);
    m2SameNullValue.put("two", "2");
    assertTrue(CompareTwoMaps.compareMaps(m1SameNullValue, m2SameNullValue));
    assertTrue(CompareTwoMaps.compareMaps(m2SameNullValue, m1SameNullValue));

    // compare with a different map
    assertFalse(CompareTwoMaps.compareMaps(m1Same, m2SameNullValue));
    assertFalse(CompareTwoMaps.compareMaps(m2SameNullValue, m1Same));


    Map<String, String> m2DifferentNullValue = new HashMap<>();
    m2DifferentNullValue.put("one", null);
    m2DifferentNullValue.put("two", "2");
    m2DifferentNullValue.put("three", "3");
    assertFalse(CompareTwoMaps.compareMaps(m1SameNullValue, m2DifferentNullValue));
    assertFalse(CompareTwoMaps.compareMaps(m2DifferentNullValue, m1SameNullValue));
  }
}