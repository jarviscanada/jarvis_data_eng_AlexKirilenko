package ca.jrvs.practice.dataStructures.stackQueue;

import ca.jrvs.practice.dataStructures.list.LinkedJList;
import java.util.NoSuchElementException;

public class LinkedJListJDeque<E> implements JDeque<E> {

  private LinkedJList<E> list;

  public LinkedJListJDeque() {
    this.list = new LinkedJList<>();
  }

  /**
   * queue enqueue operation
   * @param e the element to add to the end of the list
   * @return
   */
  @Override
  public boolean add(E e) {
    return list.add(e);
  }

  /**
   * queue dequeue operation
   * removes the element from the beginning of the list
   * @return e
   */
  @Override
  public E remove() {
    if (list.size() == 0) {
      throw new NoSuchElementException();
    }
    return list.remove(0);
  }

  /**
   * stack pop operation
   * removes the first element of the list
   * @return
   */
  @Override
  public E pop() {
    if (list.size() == 0) {
      throw new NoSuchElementException();
    }
    return list.remove(0);
  }

  /**
   * pushes element to the top of the list (first position)
   * @param e the element to push
   */
  @Override
  public void push(E e) {
    list.addFirst(e);
  }

  /**
   *
   * @return element from the first position of the list, without removing it
   */
  @Override
  public E peek() {
    if (list.size() == 0) return null;
    return list.get(0);
  }

  /**
   *
   * @return the size of the dequeue
   */
  public int size() {
    return list.size();
  }
}
