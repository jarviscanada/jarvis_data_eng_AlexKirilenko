package ca.jrvs.practice.dataStructures.list;

/**
 * Implements List interface based on a doubly-linked list
 * @param <E>
 */
public class LinkedJList<E> implements JList<E> {

  private Node<E> head;
  private Node<E> tail;
  private int size;

  public LinkedJList() {
  }


  @Override
  public boolean add(E e) {
    Node<E> newNode = new Node<>();
    newNode.setValue(e);
    if (size == 0) {
      head = newNode;
      tail = newNode;
    } else {
      tail.setNextNode(newNode);
      newNode.setPreviousNode(tail);
      tail = newNode;
    }
    size++;
    return true;
  }

  public boolean addFirst(E e) {
    Node<E> newNode = new Node<>();
    newNode.setValue(e);
    if (size == 0) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setNextNode(head);
      head.setPreviousNode(newNode);
      head = newNode;
    }
    size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] array = new Object[size];
    Node<E> tempNode = head;
    for (int i = 0; i < size; i++) {
      array[i] = tempNode.getValue();
      tempNode = tempNode.getNextNode();
    }
    return array;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int indexOf(Object o) {
    Node<E> tempNode = head;
    int i = 0;
    while (i < size) {
      if (tempNode.getValue().equals(o)) {
        return i;
      }
      i++;
      tempNode = tempNode.getNextNode();
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public E get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds.", index));
    }
    if (index == 0) {
      return head.getValue();
    }
    if (index == size - 1) {
      return tail.getValue();
    }

    Node<E> tempNode = head;
    int i = 0;
    while (i < size) {
      if (i == index) {
        return tempNode.getValue();
      }
      i++;
      tempNode = tempNode.getNextNode();
    }
    return null;
  }

  @Override
  public E remove(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds.", index));
    }
    // removing the only element
    if (size == 1) {
      Node<E> tempNode = head;
      head = null;
      tail = null;
      size--;
      return tempNode.getValue();
    }
    // removing first element from list with size > 1
    if (index == 0) {
      Node<E> tempNode = head;
      head = head.getNextNode();
      head.setPreviousNode(null);
      tempNode.setNextNode(null);
      size--;
      return tempNode.getValue();
    }
    // removing the last element
    if (index == size - 1) {
      Node<E> tempNode = tail;
      tail = tail.getPreviousNode();
      tail.setNextNode(null);
      tempNode.setPreviousNode(null);
      size--;
      return tempNode.getValue();
    }
    // removing the element in the middle (size >= 3), as other cases are covered above
    int i = 0;
    Node<E> tempNode = head;
    while (i < size) {
      if (i == index) {
        Node<E> previousNode = tempNode.getPreviousNode();
        Node<E> nextNode = tempNode.getNextNode();
        previousNode.setNextNode(nextNode);
        nextNode.setPreviousNode(previousNode);
        tempNode.setPreviousNode(null);
        tempNode.setNextNode(null);
        size--;
        return tempNode.getValue();
      }
      i++;
      tempNode = tempNode.getNextNode();
    }
    return null;
  }

  @Override
  public void clear() {
    // Garbage collector should be able to collect all unused nodes automatically
    head = null;
    tail = null;
    size = 0;
  }

  // Internal class
  private static class Node<E> {
    private E value;
    private Node<E> previousNode;
    private Node<E> nextNode;

    public Node() {
    }

    public E getValue() {
      return value;
    }

    public Node<E> getPreviousNode() {
      return previousNode;
    }

    public Node<E> getNextNode() {
      return nextNode;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public void setPreviousNode(Node<E> previousNode) {
      this.previousNode = previousNode;
    }

    public void setNextNode(Node<E> nextNode) {
      this.nextNode = nextNode;
    }
  }
}
