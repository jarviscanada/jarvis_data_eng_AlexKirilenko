package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-1a5bc99f586f4dddbe5523ff5eb5e4b5
 * Finds a duplicate number within a integer array
 * tested using https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindDuplicateNumber {


  /**
   * Finds a duplicate integer using sorting
   * has O(nlogn) time complexity
   * @param nums
   * @return duplicate integer, or -1 if not found
   */
  public static int findDuplicateSort(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i-1] == nums[i]) {
        return nums[i-1];
      }
    }
    return -1;
  }

  /**
   * Find duplicate using set
   * Uses O(n) extra space and O(n) tume
   * @param nums
   * @return
   */
  public static int findDuplicateSet(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) {
        return num;
      } else {
        set.add(num);
      }
    }
    return -1;
  }



}
