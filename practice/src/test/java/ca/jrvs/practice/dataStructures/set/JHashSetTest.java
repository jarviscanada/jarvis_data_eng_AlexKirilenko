package ca.jrvs.practice.dataStructures.set;

import static org.junit.Assert.*;

import org.junit.Test;

public class JHashSetTest {

  @Test
  public void add() {
    JHashSet<String> set = new JHashSet<>();
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
    JHashSet<String> set = new JHashSet<>();
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
    JHashSet<String> set = new JHashSet<>();
    set.add("first");
    set.add("second");
    set.add("third");
    assertEquals(3,set.size());
    set.clear();
    assertEquals(0,set.size());
  }
}