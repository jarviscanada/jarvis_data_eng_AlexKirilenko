package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Contains-Duplicate-89d2d6040e5f4f88b339c02c4bf529c8
 * Check if the array contains duplicates
 * tested using https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {


  /**
   * Using sorting
   * Takes O(nlogn) time
   * @param nums
   * @return
   */
  public static boolean containsDuplicateSorting(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i-1] == nums[i]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Using set
   * Takes O(n) time and O(n) extra space
   * @param nums
   * @return
   */
  public static boolean containsDuplicateSet(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return true;
      } else {
        set.add(nums[i]);
      }
    }
    return false;
  }

}
