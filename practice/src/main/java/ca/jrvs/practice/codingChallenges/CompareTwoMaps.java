package ca.jrvs.practice.codingChallenges;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-f6930bb730c54d3895e5557c0ccf2789
 * Compares two maps for equality
 */
public class CompareTwoMaps {

  /**
   * Using Java collections, the method checks if entrysets are the same
   * Runs in O(n) where n is the number of elements in the smaller map (assuming it is iterated over)
   * @param map1
   * @param map2
   * @param <K> type of keys
   * @param <V> type of values
   * @return true if maps are the same (have the same keys and values), false otherwise
   */
  public static <K,V> boolean compareMapsDefault(Map<K,V> map1, Map<K,V> map2){
    return map1.equals(map2);
  }

  /**
   * Manually comparing keys and values of two maps
   * Runs in O(n_1) where n_1 is the number of keys in map1
   * @param map1
   * @param map2
   * @param <K> type of keys
   * @param <V> type of values
   * @return true if maps are the same (have the same keys and values), false otherwise
   */
  public static <K,V> boolean compareMaps(Map<K,V> map1, Map<K,V> map2){
    // if the same object is passed
    if (map1 == map2) return true;
    if (map1.size() != map2.size()) return false;

    for (Entry<K,V> entry : map1.entrySet()) {
      K key = entry.getKey();
      V value = entry.getValue();
      if (map2.containsKey(key)) {
        if (value == null && map2.get(key) == null) {
          continue;
        }
        if (map2.get(key) == null || !map2.get(key).equals(value)) {
          return false;
        }
      } else  { // map2 doesn't contain this key from map1
        return false;
      }
    }
    return true;
  }

}
