package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class FindLargestSmallestElementTest {

  @Test
  public void findElementsLoop() {

    int[] array1 = new int[] {1};
    Map<String, Integer> map1 = new HashMap<>();
    map1.put("largest", 1);
    map1.put("smallest", 1);
    assertEquals(map1, FindLargestSmallestElement.findElementsLoop(array1));

    int[] array2 = new int[] {1, -5, 11};
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("largest", 11);
    map2.put("smallest", -5);
    assertEquals(map2, FindLargestSmallestElement.findElementsLoop(array2));

    int[] array3 = new int[] {11, -5, 10, 0};
    Map<String, Integer> map3 = new HashMap<>();
    map3.put("largest", 11);
    map3.put("smallest", -5);
    assertEquals(map3, FindLargestSmallestElement.findElementsLoop(array3));

    int[] array4 = new int[] {-6, 15, 10, 0};
    Map<String, Integer> map4 = new HashMap<>();
    map4.put("largest", 15);
    map4.put("smallest", -6);
    assertEquals(map4, FindLargestSmallestElement.findElementsLoop(array4));

  }

  @Test
  public void findElementsStream() {

    int[] array1 = new int[] {1};
    Map<String, Integer> map1 = new HashMap<>();
    map1.put("largest", 1);
    map1.put("smallest", 1);
    assertEquals(map1, FindLargestSmallestElement.findElementsStream(array1));

    int[] array2 = new int[] {1, -5, 11};
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("largest", 11);
    map2.put("smallest", -5);
    assertEquals(map2, FindLargestSmallestElement.findElementsStream(array2));

    int[] array3 = new int[] {11, -5, 10, 0};
    Map<String, Integer> map3 = new HashMap<>();
    map3.put("largest", 11);
    map3.put("smallest", -5);
    assertEquals(map3, FindLargestSmallestElement.findElementsStream(array3));

    int[] array4 = new int[] {-6, 15, 10, 0};
    Map<String, Integer> map4 = new HashMap<>();
    map4.put("largest", 15);
    map4.put("smallest", -6);
    assertEquals(map4, FindLargestSmallestElement.findElementsStream(array4));
  }

  @Test
  public void findElementsCollections() {

    int[] array1 = new int[] {1};
    Map<String, Integer> map1 = new HashMap<>();
    map1.put("largest", 1);
    map1.put("smallest", 1);
    assertEquals(map1, FindLargestSmallestElement.findElementsCollections(array1));

    int[] array2 = new int[] {1, -5, 11};
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("largest", 11);
    map2.put("smallest", -5);
    assertEquals(map2, FindLargestSmallestElement.findElementsCollections(array2));

    int[] array3 = new int[] {11, -5, 10, 0};
    Map<String, Integer> map3 = new HashMap<>();
    map3.put("largest", 11);
    map3.put("smallest", -5);
    assertEquals(map3, FindLargestSmallestElement.findElementsCollections(array3));

    int[] array4 = new int[] {-6, 15, 10, 0};
    Map<String, Integer> map4 = new HashMap<>();
    map4.put("largest", 15);
    map4.put("smallest", -6);
    assertEquals(map4, FindLargestSmallestElement.findElementsCollections(array4));
  }
}