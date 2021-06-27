package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://www.notion.so/jarvisdev/Duplicate-Characters-a6af631f08c749c4b20faf952f164c15
 * Finds duplicate characters in a string
 */
public class DuplicateCharacters {

  /**
   * Uses linkedhashmap to store unique characters. Ignores spaces
   * Runs in O(n) time and takes O(n) extra space
   * @param s
   * @return
   */
  public static List<Character> findDuplicateCharacters(String s) {

    LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
    String sTrimmed = s.replaceAll("\\s+", "");
    for (int i = 0; i < sTrimmed.length(); i++) {
      Character c = sTrimmed.charAt(i);
      map.putIfAbsent(c, 0);
      map.put(c, map.get(c) + 1);
    }
    return map.entrySet().stream()
        .filter( e -> e.getValue() > 1 )
        .map(e -> e.getKey())
        .collect(Collectors.toList());
  }

}
