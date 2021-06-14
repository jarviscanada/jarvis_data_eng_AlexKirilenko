package ca.jrvs.practice.codingChallenges;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;

public class DuplicateLinkedListNodeTest {

  @Test
  public void removeDuplicates() {
    assertEquals(new LinkedList<Integer>(),
        DuplicateLinkedListNode.removeDuplicates(new LinkedList<Integer>()));

    assertEquals(Arrays.asList("one", "two", "three"),
        DuplicateLinkedListNode.removeDuplicates(new LinkedList<>(Arrays.asList("one", "two", "three"))));

    assertEquals(Arrays.asList("one", "two", "three"),
        DuplicateLinkedListNode.removeDuplicates(
            new LinkedList<>(Arrays.asList("one", "two", "three", "two"))));

    assertEquals(Arrays.asList("one", "two", "three"),
        DuplicateLinkedListNode.removeDuplicates(
            new LinkedList<>(Arrays.asList("one", "one", "two", "three", "two"))));

    assertNotEquals(Arrays.asList("one", "two", "three"),
        DuplicateLinkedListNode.removeDuplicates(
            new LinkedList<>(Arrays.asList("one", "three", "two", "three", "two"))));

  }
}