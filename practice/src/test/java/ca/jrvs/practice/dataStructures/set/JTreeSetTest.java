package ca.jrvs.practice.dataStructures.set;

import static org.junit.Assert.*;

import org.junit.Test;

public class JTreeSetTest {

  @Test
  public void add() {
    JTreeSet<String> set = new JTreeSet<>();
    assertEquals(0, set.size());
    set.add("first");
    assertEquals(1,set.size());
    set.add("first");
    assertEquals(1,set.size());
    set.add("second");
    assertEquals(2,set.size());
  }

  @Test
  public void remove() {
    JTreeSet<String> set = new JTreeSet<>();
    set.add("first");
    set.add("second");
    set.add("third");
    assertEquals(3,set.size());
    set.remove("second");
    assertEquals(2,set.size());
    set.remove("second");
    assertEquals(2,set.size());
    set.remove("empty");
    assertEquals(2,set.size());
  }

  @Test
  public void clear() {
    JTreeSet<String> set = new JTreeSet<>();
    set.add("first");
    set.add("second");
    set.add("third");
    assertEquals(3,set.size());
    set.clear();
    assertEquals(0,set.size());
  }
}