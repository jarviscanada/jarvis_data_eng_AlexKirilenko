package ca.jrvs.practice.algorithms.search;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void binarySearchRecursion() {

    Integer[] arrayEmpty = new Integer[0];
    assertFalse(BinarySearch.binarySearchRecursion(arrayEmpty, 1).isPresent());

    Integer[] arrayTest = new Integer[] {-11, -4, -1, 0, 3, 5, 7, 12, 34, 36, 55, 101};

    assertFalse(BinarySearch.binarySearchRecursion(arrayTest, -12).isPresent());
    assertFalse(BinarySearch.binarySearchRecursion(arrayTest, 105).isPresent());
    assertFalse(BinarySearch.binarySearchRecursion(arrayTest, 33).isPresent());

    for (int i = 0; i < arrayTest.length; i++) {
      int returnedIndex =  BinarySearch.binarySearchRecursion(arrayTest, arrayTest[i]).get().intValue();
      assertEquals(i, returnedIndex);
    }
  }

  @Test
  public void binarySearchIteration() {

    Integer[] arrayEmpty = new Integer[0];
    assertFalse(BinarySearch.binarySearchIteration(arrayEmpty, 1).isPresent());

    Integer[] arrayTest = new Integer[] {-11, -4, -1, 0, 3, 5, 7, 12, 34, 36, 55, 101};

    assertFalse(BinarySearch.binarySearchIteration(arrayTest, -12).isPresent());
    assertFalse(BinarySearch.binarySearchIteration(arrayTest, 105).isPresent());
    assertFalse(BinarySearch.binarySearchIteration(arrayTest, 33).isPresent());

    for (int i = 0; i < arrayTest.length; i++) {
      int returnedIndex =  BinarySearch.binarySearchIteration(arrayTest, arrayTest[i]).get().intValue();
      assertEquals(i, returnedIndex);
    }
  }
}