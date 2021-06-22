package ca.jrvs.practice.codingChallenges;

import ca.jrvs.practice.codingChallenges.NthNodeFromTheEndOfLinkedList.ListNode;

/**
 * Return the middle node of the linked list
 * https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-cf9efd8f5da64076ad6fe46cd60899f4
 * Solution is tested using https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {


  /**
   * Uses a fast and a slow pointer (moving only on even numbers)
   * Solution has O(n) time complexity
   * and O(1) extra space
   * @param head
   * @return
   */
  public static ListNode middleNode(ListNode head) {

    ListNode fastNode = head;
    ListNode slowNode = head;

    int i = 0;
    while (fastNode.next != null) {
      fastNode = fastNode.next;
      if (i % 2 == 0) {
        slowNode = slowNode.next;
      }
      i++;
    }
    return slowNode;

  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

}
