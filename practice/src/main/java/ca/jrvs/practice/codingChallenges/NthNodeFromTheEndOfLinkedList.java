package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-c015eb7bbcc4495b8921a75251479dbf
 * Removes n-th node from the end of a linked list
 * tested using https://leetcode.com/problems/remove-nth-node-from-end-of-list
 */
public class NthNodeFromTheEndOfLinkedList {

  /**
   * Uses two pointers
   * Runs in one pass, O(n) time
   * Extra space is O(1)
   * @param head
   * @param n
   * @return
   */
  public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head.next == null) {
      return null;
    }
    // create a new node that points to the current head
    ListNode newHead = new ListNode();
    newHead.next = head;

    // node that will point at the last element in the list
    ListNode leadingNode = newHead;
    // node that will point to the element that is previous to the one being deleted
    ListNode followingNode = newHead;

    int i = 0;
    while (i < n) {
      leadingNode = leadingNode.next;
      i++;
    }
    while (leadingNode.next != null) {
      leadingNode = leadingNode.next;
      followingNode = followingNode.next;
    }

    ListNode deletedNode = followingNode.next;
    followingNode.next = deletedNode.next;
    deletedNode.next = null;
    return newHead.next;
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
