package ca.jrvs.practice.dataStructures.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayJListTest {


  @Test
  public void add() {

    // tests add and size methods
    ArrayJList<String> arrayJList = new ArrayJList<>();
    arrayJList.add("test");
    assertEquals("test", arrayJList.get(0));
    assertEquals(1, arrayJList.size());

    ArrayJList<String> arrayJListGrown= new ArrayJList<>(2);
    arrayJListGrown.add("test1");
    arrayJListGrown.add("test2");
    arrayJListGrown.add("test3");
    arrayJListGrown.add("test4");
    arrayJListGrown.add("test5");
    assertEquals("test5", arrayJListGrown.get(4));
    assertEquals(5, arrayJListGrown.size());
  }

  @Test
  public void toArray() {
    Object[] expectedArray = {"test1", "test2", "test3"};
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.add("test2");
    arrayJList.add("test3");
    assertArrayEquals(expectedArray, arrayJList.toArray());
  }


  @Test
  public void isEmpty() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    assertTrue(arrayJList.isEmpty());
    arrayJList.add("test1");
    assertFalse(arrayJList.isEmpty());
  }

  @Test
  public void indexOf() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.add("test2");
    arrayJList.add("test3");
    assertEquals(1, arrayJList.indexOf("test2"));
    assertEquals(-1, arrayJList.indexOf("test4"));
  }

  @Test
  public void contains() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.add("test2");
    arrayJList.add("test3");
    assertTrue(arrayJList.contains("test1"));
    assertFalse(arrayJList.contains("test4"));
  }

  @Test
  public void get() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.add("test2");
    arrayJList.add("test3");
    assertEquals("test2", arrayJList.get(1));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void getIndexOutOfBoundsException() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.get(5);
  }

  @Test
  public void remove() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.add("test2");
    arrayJList.add("test3");
    arrayJList.remove(1);
    assertEquals("test1", arrayJList.get(0));
    assertEquals("test3", arrayJList.get(1));
  }

  @Test
  public void clear() {
    ArrayJList<String> arrayJList= new ArrayJList<>();
    arrayJList.add("test1");
    arrayJList.add("test2");
    arrayJList.add("test3");
    assertFalse(arrayJList.isEmpty());
    arrayJList.clear();
    assertTrue(arrayJList.isEmpty());
  }
}