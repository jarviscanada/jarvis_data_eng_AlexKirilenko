package ca.jrvs.practice.dataStructures.stackQueue;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedJListJDequeTest {

  @Test
  public void add() {
    // test queue operations
    LinkedJListJDeque<String> deque = new LinkedJListJDeque<>();
    deque.add("test1");
    deque.add("test2");
    deque.add("test3");
    assertEquals(3, deque.size());
    assertEquals("test1", deque.peek());
    assertEquals("test1", deque.remove());
    assertEquals("test2", deque.remove());
    assertEquals("test3", deque.remove());
  }


  @Test
  public void push() {
    // test stack operations
    LinkedJListJDeque<String> deque = new LinkedJListJDeque<>();
    deque.push("test1");
    deque.push("test2");
    deque.push("test3");
    assertEquals(3, deque.size());
    assertEquals("test3", deque.peek());
    assertEquals("test3", deque.pop());
    assertEquals("test2", deque.pop());
    assertEquals("test1", deque.pop());
  }

}