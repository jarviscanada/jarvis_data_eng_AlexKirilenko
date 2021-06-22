package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-4566595c957f424d8bd5ab95ef58ffc7
 * Removes duplicate nodes in a linked list (mutates the list)
 */
public class DuplicateLinkedListNode {

  /**
   * Removes the duplicate elements in the input list
   * Takes O(n) time, each element in the list is accessed only once
   * and the set operations are run in constant time
   * Takes O(n) extra space to store unique elements in the Set
   * @param list
   * @param <E>
   * @return
   */
  public static <E> LinkedList<E> removeDuplicates(LinkedList<E> list) {
    Set<E> uniqueElements = new HashSet<>();

    ListIterator<E> iterator = list.listIterator();
    while (iterator.hasNext()) {
      E element = iterator.next();
      if (uniqueElements.contains(element)) {
        iterator.remove();
      } else {
        uniqueElements.add(element);
      }
    }

    return list;
  }


}
