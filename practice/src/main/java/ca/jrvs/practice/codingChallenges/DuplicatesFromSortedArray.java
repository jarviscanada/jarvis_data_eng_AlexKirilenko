package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-095f839a19d14f6a9cdc89e48b3ddfcf
 * Removes duplicates from a sorted array in place
 * tested using https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class DuplicatesFromSortedArray {


  /**
   * Uses two pointers
   * Has O(1) extra spece and runs in O(n) time
   * @param nums sorted array
   * @return a position of the right end of the array
   */
  public static int removeDuplicates(int[] nums) {

    int currentPointer = 0;
    for (int nextPointer = 1; nextPointer < nums.length; nextPointer++) {
      if (nums[currentPointer] != nums[nextPointer]) {
        nums[++currentPointer] = nums[nextPointer];
      }
    }
    return currentPointer + 1;

  }



}
