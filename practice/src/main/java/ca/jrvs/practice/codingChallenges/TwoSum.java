package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Two Sum problem
 * https://www.notion.so/jarvisdev/Two-Sum-14ca25780d3e4c0d97ec6374304c6d0b
 * The code is tested through Leetcode https://leetcode.com/problems/two-sum
 */
public class TwoSum {

  /**
   * Brute force solution using loops
   * Has O(n^2) time complexity (double for loop)
   * @param nums array of numbers
   * @param target sum
   * @return array of indices of the first found pair, or {-1, -1} if not found
   */
  public static int[] twoSumBruteForce(int[] nums, int target) {
    int[] result = {-1, -1};
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          result[0] = i;
          result[1] = j;
          return result;
        }
      }
    }
    return result;
  }

  /**
   * Solution using sorted array and two pointers
   * Has O(nlogn) time complexity (sorting)
   * Because this implementation returns indices of the original array
   * it copies data into temporary array, resulting in additional space complexity of O(n).
   * If only the values need to be returned and the original array can be sorted in place,
   * then there is no need for extra space.
   * @param nums array of numbers
   * @param target sum
   * @return array of indices of the first found pair, or {-1, -1} if not found
   */
  public static int[] twoSumSorting(int[] nums, int target) {
    int[] temp = Arrays.copyOf(nums, nums.length);
    Arrays.sort(temp);
    int[] result = {-1, -1};
    int i = 0;
    int j = temp.length - 1;
    while (i < j) {
      int currentSum = temp[i] + temp[j];
      if (currentSum == target) {
        break;
      } else if (currentSum > target) { // the only option is to decrease the sum, by decrementing j
        j--;
      } else { // since target < currentSum, the only option to increase it is to increment i
        i++;
      }
    }
    // finding positions in the original array
    for (int k = 0; k < nums.length; k++) {
      if (nums[k] == temp[i]) {
        result[0] = k;
        break;
      }
    }
    for (int k = 0; k < nums.length; k++) {
      if (nums[k] == temp[j] && result[0] != k) {
        result[1] = k;
        break;
      }
    }
    return  result;
  }

  /**
   * HashMap solution
   * Has O(n) time complexity
   * Has O(n) space complexity due to adding elements in the map
   * @param nums array of numbers
   * @param target sum
   * @return array of indices of the first found pair, or {-1, -1} if not found
   */
  public static int[] twoSumHashMap(int[] nums, int target) {
    int[] result = {-1, -1};
    // mapping values to indices
    Map<Integer, List<Integer>> valueToIndex = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      valueToIndex.putIfAbsent(nums[i], new LinkedList<>());
      valueToIndex.get(nums[i]).add(i);
    }

    for (int thisNumber : valueToIndex.keySet()) {
      int otherNumber = target - thisNumber;
      if (!valueToIndex.containsKey(otherNumber)) {
        continue;
      }
      List<Integer> otherList = valueToIndex.get(otherNumber);
      if (thisNumber == otherNumber) {
        if (otherList.size() < 2) {
          continue;
        } else {
          result[0] = otherList.get(0);
          result[1] = otherList.get(1);
          return result;
        }
      }
      result[0] = valueToIndex.get(thisNumber).get(0);
      result[1] = otherList.get(0);
      return result;
    }
    return result;
  }


  }
