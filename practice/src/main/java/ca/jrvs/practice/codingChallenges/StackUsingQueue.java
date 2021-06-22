package ca.jrvs.practice.codingChallenges;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements stack using queue
 * https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-aac448eabc5c46a8827930b93920d6ed
 * Tested using https://leetcode.com/problems/implement-stack-using-queues/
 */
public class StackUsingQueue {


  /**
   * Using two queues
   * Push operation is costs O(n) where n is the size of the stack
   * Pop operation is O(1)
   */
  public static class MyStackTwoQueues {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStackTwoQueues() {
      queue1 = new LinkedList<>();
      queue2 = new LinkedList<>();
    }

    public void push(int x) {
      queue2.add(x);
      int size1 = queue1.size();
      for (int i = 0; i < size1; i++) {
        queue2.add(queue1.remove());
      }
      Queue<Integer> tempQueue = queue1;
      queue1 = queue2;
      queue2 = tempQueue;

    }

    public int pop() {
      return queue1.remove();
    }

    public int top() {
      return queue1.peek();
    }

    public boolean empty() {
      return queue1.isEmpty();
    }
  }

  /**
   * Using a single queues
   * Push operation is costs O(n) where n is the size of the stack
   * Pop operation is O(1)
   */
  public static class MyStackSingleQueue {

    Queue<Integer> queue;

    public MyStackSingleQueue() {
      queue = new LinkedList<>();
    }

    public void push(int x) {
      queue.add(x);
      int size = queue.size();
      for (int i = 0; i < size - 1; i++) {
        queue.add(queue.remove());
      }
    }

    public int pop() {
      return queue.remove();
    }

    public int top() {
      return queue.peek();
    }

    public boolean empty() {
      return queue.isEmpty();
    }
  }


}
