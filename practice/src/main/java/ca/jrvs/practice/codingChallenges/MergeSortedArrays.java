package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Merge-Sorted-Array-7b1cde1af7814190abafeb901cab3d5d
 * Merge two sorted arrays
 * tested using https://leetcode.com/problems/merge-sorted-array/
 */
public class MergeSortedArrays {

  /**
   * takes O(m+n) time and O(1) extra space
   * @param nums1 array of length (m+n), contains m sorted elements and n zeros consecutively
   * @param m
   * @param nums2 contains n sorted elements
   * @param n
   */
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    System.arraycopy(nums1, 0, nums1, n, m);
    int i = 0;
    int j = n;
    int k = 0;
    while (j < (m+n) && k < n) {
      if (nums1[j] < nums2[k]) {
        nums1[i++] = nums1[j];
        j++;
      } else {
        nums1[i++] = nums2[k++];
      }
    }
    while (j < (m+n)) {
      nums1[i++] = nums1[j];
      j++;
    }
    while (k < n) {
      nums1[i++] = nums2[k++];
    }

  }}
