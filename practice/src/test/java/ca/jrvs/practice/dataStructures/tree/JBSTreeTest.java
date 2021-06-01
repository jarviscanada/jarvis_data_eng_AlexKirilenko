package ca.jrvs.practice.dataStructures.tree;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

public class JBSTreeTest {
  /*
  test tree:
      15
     10 20
  8 12  18  25
   */
  @Test
  public void insert() {
    Comparator<Integer> comparator = (s1, s2) -> s1 - s2;
    JBSTree<Integer> tree = new JBSTree<>(comparator);
    assertEquals(null, tree.search(1));
    tree.insert(15);
    tree.insert(10);
    tree.insert(20);
    tree.insert(8);
    tree.insert(12);
    tree.insert(25);
    tree.insert(18);
    assertEquals(new Integer(10), tree.search(10));
    assertEquals(3, tree.findHeight());
    assertEquals(null, tree.search(30));
  }


  @Test
  public void remove() {
    //assertFalse(true);
    Comparator<Integer> comparator = (s1, s2) -> s1 - s2;
    JBSTree<Integer> tree = new JBSTree<>(comparator);
    tree.insert(15);
    tree.insert(10);
    tree.insert(20);
    tree.insert(8);
    tree.insert(12);
    tree.insert(25);
    tree.insert(18);
    assertEquals(new Integer(10), tree.search(10));
    assertEquals(new Integer(10), tree.remove(10));
    assertEquals(null, tree.search(10));
    assertEquals(new Integer(15), tree.remove(15));
    assertEquals(null, tree.search(15));

  }

  @Test
  public void preOrder() {
    Integer[] values = {15, 10, 8, 12, 20, 18, 25};
    Comparator<Integer> comparator = (s1, s2) -> s1 - s2;
    JBSTree<Integer> tree = new JBSTree<>(comparator);
    tree.insert(15);
    tree.insert(10);
    tree.insert(20);
    tree.insert(8);
    tree.insert(12);
    tree.insert(25);
    tree.insert(18);
    //System.out.println(Arrays.toString(tree.preOrder()));
    assertArrayEquals(values, tree.preOrder());
  }

  @Test
  public void inOrder() {
    Integer[] values = {8, 10, 12, 15, 18,  20, 25};
    Comparator<Integer> comparator = (s1, s2) -> s1 - s2;
    JBSTree<Integer> tree = new JBSTree<>(comparator);
    tree.insert(15);
    tree.insert(10);
    tree.insert(20);
    tree.insert(8);
    tree.insert(12);
    tree.insert(25);
    tree.insert(18);
    //System.out.println(Arrays.toString(tree.inOrder()));
    assertArrayEquals(values, tree.inOrder());
  }

  @Test
  public void postOrder() {
    Integer[] values = {8, 12, 10, 18, 25, 20, 15};
    Comparator<Integer> comparator = (s1, s2) -> s1 - s2;
    JBSTree<Integer> tree = new JBSTree<>(comparator);
    tree.insert(15);
    tree.insert(10);
    tree.insert(20);
    tree.insert(8);
    tree.insert(12);
    tree.insert(25);
    tree.insert(18);
    //System.out.println(Arrays.toString(tree.preOrder()));
    assertArrayEquals(values, tree.postOrder());
  }


}