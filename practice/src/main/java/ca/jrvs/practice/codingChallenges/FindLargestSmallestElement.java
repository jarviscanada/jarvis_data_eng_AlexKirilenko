package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://www.notion.so/jarvisdev/Find-Largest-Smallest-ef1ee91971dd4fd38e06d18db8996d9a
 * Finds largest and smallest element in the array
 */
public class FindLargestSmallestElement {

  /**
   * Uses for loop to iterate over the array once
   * has O(n) time complexity and uses O(1) extra space
   * @param array
   * @return map with largest/smallest keys and respective values
   */
  public static Map<String, Integer> findElementsLoop(int[] array) {
    int smallest = array[0];
    int largest = array[0];
    for (int i : array) {
      if (i < smallest) {
        smallest = i;
      } else if (i > largest) {
        largest = i;
      }
    }
    Map<String, Integer> result = new HashMap<>();
    result.put("largest", largest);
    result.put("smallest", smallest);
    return result;
  }

  /**
   * uses stream API
   * @param array
   * @return
   */
  public static Map<String, Integer> findElementsStream(int[] array) {
    int smallest = Arrays.stream(array).min().getAsInt();
    int largest = Arrays.stream(array).max().getAsInt();
    Map<String, Integer> result = new HashMap<>();
    result.put("largest", largest);
    result.put("smallest", smallest);
    return result;
  }

  /**
   * uses collections
   * @param array
   * @return
   */
  public static Map<String, Integer> findElementsCollections(int[] array) {
    List<Integer> list = Arrays.stream(array).mapToObj(Integer::new).collect(Collectors.toList());
    int smallest = Collections.min(list);
    int largest = Collections.max(list);
    Map<String, Integer> result = new HashMap<>();
    result.put("largest", largest);
    result.put("smallest", smallest);
    return result;
  }



}
