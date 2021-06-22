package ca.jrvs.practice.codingChallenges;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Missing-Number-0d59c67702ae4aeb986ae0546cda6d09
 * finds a missing number in the sequence of distinct integers
 * tested using https://leetcode.com/problems/missing-number
 */
public class MissingNumber {

  /**
   * uses Gauss formula for the sum of a series
   * takes O(n) time
   * Can result in overflow for large numbers
   * @param nums
   * @return
   */
  public static int missingNumberGauss(int[] nums) {
    long sum = 0;
    for (int num : nums) {
      sum += num;
    }
    long gauss = (nums.length*(nums.length+1)) / 2;
    return (int) (gauss - sum);
  }

  /**
   * Uses hashset to store all numbers
   * Then checks the whole range of numbers for their presence in the map
   * The solution takes O(n) time, because the array is iterated over once and
   * the constant time hashset operations are used n times
   * Takes O(n) extra space
   * @param nums
   * @return
   */
  public static int missingNumberMap(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    for (int i = 0; i <= nums.length; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }
    return 0;
  }




}
