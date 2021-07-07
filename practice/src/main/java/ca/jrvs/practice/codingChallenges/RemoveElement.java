package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Remove-Element-05d296fa0d95452d93ac0224a8bc65ee
 * Removes all occurrences of the element in an array in place
 * tested using https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {

  /**
   * Uses two pointers to move elements to the from of the array
   * O(n) time, O(1) space
   * @param nums array of numbers
   * @param val value to be deleted
   * @return the position of right end inside the resulting array
   */
  public static int removeElement(int[] nums, int val) {
    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      if (nums[i] == val) {
        if (nums[j] == val) {
          j--;
          continue;
        } else {
          nums[i] = nums[j];
          j--;

        }
      } else {
        i++;
      }
    }

    return i;
  }
}
