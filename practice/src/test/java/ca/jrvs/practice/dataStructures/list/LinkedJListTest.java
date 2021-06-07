package ca.jrvs.practice.dataStructures.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedJListTest {

  @Test
  public void add() {

    // tests add and size methods
    LinkedJList<String> linkedJList = new LinkedJList<>();
    linkedJList.add("test1");
    assertEquals("test1", linkedJList.get(0));
    assertEquals(1, linkedJList.size());
    linkedJList.add("test2");
    assertEquals("test2", linkedJList.get(1));
    assertEquals(2, linkedJList.size());
    linkedJList.add("test3");
    assertEquals("test3", linkedJList.get(2));
    assertEquals(3, linkedJList.size());

  }

  @Test
  public void addFirst() {
    LinkedJList<String> linkedJList = new LinkedJList<>();
    linkedJList.addFirst("test1");
    assertEquals("test1", linkedJList.get(0));
    assertEquals(1, linkedJList.size());
    linkedJList.addFirst("test2");
    assertEquals("test2", linkedJList.get(0));
    assertEquals("test1", linkedJList.get(1));
    assertEquals(2, linkedJList.size());
    linkedJList.addFirst("test3");
    assertEquals("test3", linkedJList.get(0));
    assertEquals(3, linkedJList.size());

  }

  @Test
  public void toArray() {
    Object[] expectedArray = {"test1", "test2", "test3"};
    LinkedJList<String> linkedJList= new LinkedJList<>();
    linkedJList.add("test1");
    linkedJList.add("test2");
    linkedJList.add("test3");
    assertArrayEquals(expectedArray, linkedJList.toArray());
  }


  @Test
  public void isEmpty() {
    ArrayJList<String> linkedJList= new ArrayJList<>();
    assertTrue(linkedJList.isEmpty());
    linkedJList.add("test1");
    assertFalse(linkedJList.isEmpty());
  }

  @Test
  public void indexOf() {
    ArrayJList<String> linkedJList= new ArrayJList<>();
    linkedJList.add("test1");
    linkedJList.add("test2");
    linkedJList.add("test3");
    assertEquals(1, linkedJList.indexOf("test2"));
    assertEquals(-1, linkedJList.indexOf("test4"));
  }

  @Test
  public void contains() {
    LinkedJList<String> linkedJList= new LinkedJList<>();
    linkedJList.add("test1");
    linkedJList.add("test2");
    linkedJList.add("test3");
    assertTrue(linkedJList.contains("test1"));
    assertFalse(linkedJList.contains("test4"));
  }

  @Test
  public void get() {
    LinkedJList<String> linkedJList= new LinkedJList<>();
    linkedJList.add("test1");
    linkedJList.add("test2");
    linkedJList.add("test3");
    assertEquals("test2", linkedJList.get(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getIndexOutOfBoundsException() {
    LinkedJList<String> linkedJList= new LinkedJList<>();
    linkedJList.add("test1");
    linkedJList.get(5);
  }

  @Test
  public void remove() {
    LinkedJList<String> linkedJList= new LinkedJList<>();
    // removing the first element
    linkedJList.add("test1");
    assertEquals("test1", linkedJList.remove(0));
    assertEquals(0, linkedJList.size());
    // removing the last element
    linkedJList.add("test1");
    linkedJList.add("test2");
    assertEquals("test2", linkedJList.remove(1));
    assertEquals(1, linkedJList.size());
    // removing the middle element
    linkedJList.add("test2");
    linkedJList.add("test3");
    linkedJList.add("test4");
    assertEquals("test3", linkedJList.remove(2));
    assertEquals(3, linkedJList.size());
  }

  @Test
  public void clear() {
    LinkedJList<String> linkedJList= new LinkedJList<>();
    linkedJList.add("test1");
    linkedJList.add("test2");
    linkedJList.add("test3");
    assertFalse(linkedJList.isEmpty());
    linkedJList.clear();
    assertTrue(linkedJList.isEmpty());
  }


}