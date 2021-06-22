package ca.jrvs.practice.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {

  public static <E extends Comparable<E>> E[] sort(E[] array) {
    E[] copyArray = Arrays.copyOf(array, array.length);
    sortRecursively(copyArray, 0, array.length - 1);
    return copyArray;
  }

  private static <E extends Comparable<E>> void sortRecursively(E[] array, int from, int to) {
    if (from >= to) return;
    int middle = (from + to) / 2;
    sortRecursively(array, from, middle);
    sortRecursively(array, middle + 1, to);
    merge(array, from, middle, to);
  }

  private static <E extends Comparable<E>> void merge(E[] array, int from, int middle, int to) {

    // tenporary arrays (sorted by contract)
    E[] tempLow = Arrays.copyOfRange(array, from, middle + 1);
    E[] tempHigh = Arrays.copyOfRange(array, middle + 1, to + 1);

    // index in the array where items are to be copied
    int k = from;
    // starting index in tempLow
    int i = 0;
    // starting index in tempHigh
    int j = 0;

    while (i < tempLow.length && j < tempHigh.length) {
      int comparison = tempLow[i].compareTo(tempHigh[j]);
      if (comparison < 0) {
        array[k++] = tempLow[i++];
      } else {
        array[k++] = tempHigh[j++];
      }
    }
    while (i < tempLow.length) {
      array[k++] = tempLow[i++];
    }
    while (j < tempHigh.length) {
      array[k++] = tempHigh[j++];
    }
  }


}
