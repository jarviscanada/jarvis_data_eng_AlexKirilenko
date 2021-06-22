package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/LinkedList-Cycle-df87f9ce375b45fab358793b00e02828
 * Determines if a linked list has a cycle
 * tested using https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {


  /**
   * Uses two pointers:
   * One fast and one slow. If at any time pointers point to the same node, than list has a cycle.
   * Takes O(n) time and O(1) extra space
   * @param head
   * @return
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    do {
      slow = slow.next;
      if (fast.next == null) {
        return false;
      }
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    } while (fast != null);
    return false;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }


}
