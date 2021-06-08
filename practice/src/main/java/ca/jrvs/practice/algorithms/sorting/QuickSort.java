package ca.jrvs.practice.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {

  public static <E extends Comparable<E>> E[] sort(E[] array) {
    E[] tempArray = Arrays.copyOf(array, array.length);
    sortRecursively(tempArray, 0, array.length - 1);
    return tempArray;
  }

  private static <E extends Comparable<E>> void sortRecursively(E[] array, int from, int to) {
    if (from > to) return;
    E pivot = array[to];
    int i = from;
    int j = to - 1;
    // move all elements less than pivot to the left subarray
    while (i <= j) {
      if (array[i].compareTo(pivot) >= 0) {
        swap(array, i, j);
        j--;
      } else {
        i++;
      }
    }
    // put pivot where it belongs
    swap(array, i, to);
    // recursively sort subarrays
    sortRecursively(array, from, i - 1);
    sortRecursively(array, i + 1, to);
  }

  private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
    E temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}
