package ca.jrvs.practice.algorithms.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

public class MergeSortTest {


  @Test
  public void sort() {

    Integer[] arrayTrue = new Integer[] {-11, -4, -1, 0, 3, 5, 7, 12, 34, 36, 55, 101};
    Integer[] arrayShuffled = new Integer[] { 3, -1, 55, 101, 0,  7, -11, -4, 12, 34, 36,  5};
    System.out.println(Arrays.asList(arrayTrue));
    System.out.println(Arrays.asList(MergeSort.sort(arrayShuffled)));
    assertArrayEquals(arrayTrue, MergeSort.sort(arrayShuffled));
    for (int i = 0; i < 10; i++) {
      Collections.shuffle(Arrays.asList(arrayShuffled));
      assertArrayEquals(arrayTrue, MergeSort.sort(arrayShuffled));
    }

    Integer[] arrayTrue1 = new Integer[] {};
    Integer[] arrayShuffled1 = new Integer[] {};
    assertArrayEquals(arrayTrue1, MergeSort.sort(arrayShuffled1));


    Integer[] arrayTrue2 = new Integer[] {-1,0,1,2,3,4,5};
    Integer[] arrayShuffled2 = new Integer[] {5,4,3,2,1,0,-1};
    assertArrayEquals(arrayTrue2, MergeSort.sort(arrayShuffled2));

    Integer[] arrayTrue3 = new Integer[] {-1};
    Integer[] arrayShuffled3 = new Integer[] {-1};
    assertArrayEquals(arrayTrue3, MergeSort.sort(arrayShuffled3));

    Integer[] arrayTrue4 = new Integer[] {-1,0,1,2,3,4,5};
    Integer[] arrayShuffled4= new Integer[] {-1,0,1,2,3,4,5};
    assertArrayEquals(arrayTrue4, MergeSort.sort(arrayShuffled4));




  }
}