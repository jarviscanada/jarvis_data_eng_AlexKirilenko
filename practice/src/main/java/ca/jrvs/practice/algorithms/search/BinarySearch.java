package ca.jrvs.practice.algorithms.search;

import java.util.Optional;

public class BinarySearch {

  /**
   * find the the target index in a sorted array
   *
   * @param arr input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public static  <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    return binarySearchHelper(arr, target, 0, arr.length - 1);
  }

  private static  <E extends Comparable<E>> Optional<Integer> binarySearchHelper(E[] arr, E target, int low, int high) {
    if (low > high) {
      return Optional.empty();
    }
    int middle = (high + low) / 2;
    int comparisonResult = target.compareTo(arr[middle]);
    if (comparisonResult == 0) {
      return Optional.of(middle);
    } else if (comparisonResult > 0) { // target can only be in the right part
      return binarySearchHelper(arr, target, middle + 1, high);
    } else { // target can only be in the left part
      return binarySearchHelper(arr, target, low, middle - 1);
    }
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public static <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int low = 0;
    int high = arr.length - 1;

    while (low <= high) {
      int middle = (high + low) / 2;
      int comparisonResult = target.compareTo(arr[middle]);
      if (comparisonResult == 0) {
        return Optional.of(middle);
      } else if (comparisonResult > 0) { // target can only be in the right part
        low = middle + 1;
      } else { // target can only be in the left part
        high = middle - 1;
      }
    }

    return Optional.empty();
  }
}
