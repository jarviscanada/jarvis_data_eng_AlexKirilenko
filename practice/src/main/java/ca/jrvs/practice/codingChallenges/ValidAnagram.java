package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Checks if two strings are anagrams of each other (i.e. contain the same characters)
 * https://www.notion.so/jarvisdev/Valid-Anagram-6aa71c0ea74e49919718045dba0c0856
 * The solutions are tested using https://leetcode.com/problems/valid-anagram/
 */
public class ValidAnagram {

  /**
   * Using sorting
   * O(nlogn) solution time, where n is the length of input string
   * O(n) extra space due to the created char arrays
   * @param s
   * @param t
   * @return
   */
  public static boolean isAnagramSorting(String s, String t) {
    if (s.length() != t.length()) return false;
    char[] sChars = s.toCharArray();
    Arrays.sort(sChars);
    char[] tChars = t.toCharArray();
    Arrays.sort(tChars);
    for (int i = 0; i < sChars.length; i++) {
      if (sChars[i] != tChars[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Using HashMap for storing characters count
   * O(n) solution time, where n is the length of input string
   * O(1) since hashmap stores at most the number of keys equal the alphabet size
   * @param s
   * @param t
   * @return
   */
  public static boolean isAnagramHashMap(String s, String t) {
    if (s.length() != t.length()) return false;
    Map<Character, Integer> characterCount = new HashMap<>();

    for (char c : s.toCharArray()) {
      characterCount.putIfAbsent(c, 0);
      characterCount.put(c, characterCount.get(c) + 1);
    }
    for (char c : t.toCharArray()) {
      if (characterCount.get(c) == null || characterCount.get(c) == 0) {
        return false;
      }
      characterCount.put(c, characterCount.get(c) - 1);
    }
    return true;
  }

}
