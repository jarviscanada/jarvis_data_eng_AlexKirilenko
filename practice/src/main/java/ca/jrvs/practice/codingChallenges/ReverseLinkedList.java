package ca.jrvs.practice.codingChallenges;

/**
 * Reverses a linked list
 * https://www.notion.so/jarvisdev/Reverse-Linked-List-a755b60a7fff485396e6b790caa9fb00
 * tested using https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {


   public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }


  /**
   * Uses temporary nodes
   * Runs in O(n) and takes O(1) extra space
   * @param head
   * @return
   */
  public static ListNode reverseList(ListNode head) {
    if (head == null) return head;

    ListNode current = head;
    ListNode next = current.next;
    current.next = null;

    while (next != null) {
      ListNode temp = next.next;
      next.next = current;
      current = next;
      next = temp;
    }

    return current;

  }

  /**
   * Recuriseve solution
   * Takes O(n) space due to the stack of method calls
   * @param head
   * @return
   */
  public static ListNode reverseListRecursive(ListNode head) {
    return helper(null, head);
  }

  private static ListNode helper(ListNode currNode, ListNode nextNode) {
    if (nextNode == null) return currNode;

    ListNode temp = nextNode.next;
    nextNode.next = currNode;
    return helper(nextNode, temp);
  }

}
