package ca.jrvs.practice.codingChallenges;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implements queue using stacks
 * https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-bb7cb1f180c044c39553bda4870f3771
 * The solution is tested using https://leetcode.com/problems/implement-queue-using-stacks/submissions/
 */
public class QueueUsingStack {

  /**
   * Push O(n), Pop O(1)
   * Uses a temporary stack to always keep the main stack in FIFO
   */
  public static class MyQueueConstantPop {

    Deque<Integer> stack;
    Deque<Integer> tempStack;
    public MyQueueConstantPop() {
      stack = new LinkedList<>();
      tempStack = new LinkedList<>();
    }

    /**
     * Uses tempStack to reverse items, when a new item is added
     */
    public void push(int x) {
      // moves everything to temp stack
      while (stack.size() != 0) {
        tempStack.push(stack.pop());
      }
      // pushes the added element
      stack.push(x);
      // pushes everything back
      while (tempStack.size() != 0) {
        stack.push(tempStack.pop());
      }
    }

    /**
     * stack contains the most recent element at its head
     * @return
     */
    public int pop() {
      return stack.pop();
    }

    /** Get the front element. */
    public int peek() {
      return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stack.isEmpty();
    }
  }

  /**
   * Push O(1) ammortized, Pop O(1) ammortized
   * Uses input and output stacks
   * The output stack is filled only on demand
   */
  public static class MyQueueAmmortized {

    Deque<Integer> inputStack;
    Deque<Integer> outputStack;
    public MyQueueAmmortized() {
      inputStack = new LinkedList<>();
      outputStack = new LinkedList<>();
    }

    public void push(int x) {
      inputStack.push(x);
    }

    /**
     * when the output stack is empty, fills it with elements from the input stack
     * otherwise, simply pop from it
     * @return
     */
    public int pop() {
      if (outputStack.size() == 0) {
        while (inputStack.size() != 0) {
          outputStack.push(inputStack.pop());
        }
      }
      return outputStack.pop();
    }


    public int peek() {
      if (outputStack.size() == 0) {
        while (inputStack.size() != 0) {
          outputStack.push(inputStack.pop());
        }
      }
      return outputStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return inputStack.isEmpty() && outputStack.isEmpty();
    }
  }

}
