package ca.jrvs.practice.dataStructures.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E> {

  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;
  /**
   * The root of BST.
   */
  private Node<E> root;
  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    this.comparator = comparator;
    this.root = null;
  }

  /**
   * Insert an object into the BST.
   * Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    // if root is empty
    if (root == null) {
      Node<E> newNode = new Node<>(object, null);
      root = newNode;
      return newNode.getValue();
    }
    // if root is non-empty
    Node<E> currentNode = root;
    do {
      int compareResult = comparator.compare(object, currentNode.getValue());
      // when object exists
      if (compareResult == 0) {
        throw new IllegalArgumentException(String.format("Value %s already exists.", object.toString()));
      } else if (compareResult < 0) {         // when inserted object is less than current node
        // if left child is null, insert object there
        if (currentNode.getLeft() == null) {
          Node<E> newNode = new Node<>(object, currentNode);
          currentNode.setLeft(newNode);
          return object;
        } else { // set current node to the left child
          currentNode = currentNode.getLeft();
        }
      } else { // when inserted object is greater than current node
        // if right child is null, insert object there
        if (currentNode.getRight() == null) {
          Node<E> newNode = new Node<>(object, currentNode);
          currentNode.setRight(newNode);
          return object;
        } else { // set current node to the right child
          currentNode = currentNode.getRight();
        }
      }
    } while (currentNode != null);
    return null;
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    if (root == null) {
      return null;
    }
    Node<E> currentNode = root;
    do {
      int compareResult = comparator.compare(object, currentNode.getValue());
      if (compareResult == 0) {
        return currentNode.getValue();
      } else if (compareResult < 0) {         // when object is less than current node
        // if left child is null, object not found
        if (currentNode.getLeft() == null) {
          return null;
        } else { // set current node to the left child
          currentNode = currentNode.getLeft();
        }
      } else { // when object is greater than current node
        // if right child is null, object not found
        if (currentNode.getRight() == null) {
          return null;
        } else { // set current node to the right child
          currentNode = currentNode.getRight();
        }
      }
    } while (currentNode != null);
    return null;
  }

  /**
   * Remove a value from the tree.
   *
   * @param value to be removed
   * @return removed value
   * @throws IllegalArgumentException if the value not exists
   */
  @Override
  public E remove(E value) {
    if (this.search(value) == null) {
      throw new IllegalArgumentException("Object doesn not exist");
    }
    removeNode(root, value);
    return value;
  }

  /**
   * recursively removes the node
   * Assumes it exists
   * @param node
   * @param value
   * @return
   */
  private Node<E> removeNode(Node<E> node, E value) {
    if (node == null) {
      return node;
    }
    int comp = comparator.compare(value, node.getValue());
    if (comp < 0) {
      node.setLeft(removeNode(node.getLeft(), value));
    } else if (comp > 0) {
      node.setRight(removeNode(node.getRight(), value));
    } else { // node to be deleted is this node
      if (node.getLeft() == null && node.getRight() == null) { // no children
        return null;
      } else if (node.getLeft() != null && node.getRight() != null) { // both children
        // node's successor in its right subtree
        Node<E> successor = minimumNode(node.getRight());
        // copy successor's value
        node.setValue(successor.getValue());
        // remove successor
        node.setRight(removeNode(node.getRight(), successor.getValue()));
      } else { // only one child
        if (node.getLeft() != null) {
          return node.getLeft();
        } else {
          return node.getRight();
        }
      }
    }
    return node;
  }

  private Node<E> minimumNode(Node<E> node) {
    Node<E> tempNode = node;
    while (tempNode.getLeft() != null) {
      tempNode = tempNode.getLeft();
    }
    return tempNode;
  }

  /**
   * traverse the tree recursively
   * root - left - right
   * @return all objects in pre-order
   */
  @Override
  public E[] preOrder() {
    List<E> list = new ArrayList<>();
    preOrderHelper(root, list);
    E[] result = (E[]) new Object[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }
    return result;
  }

  private void preOrderHelper(Node<E> node, List<E> list) {
    if (node == null) {
      return;
    }
    list.add(node.getValue());
    preOrderHelper(node.getLeft(), list);
    preOrderHelper(node.getRight(), list);
  }

  /**
   * traverse the tree recursively
   * left - root- right
   * @return all objects in-order
   */
  @Override
  public E[] inOrder() {
    List<E> list = new ArrayList<>();
    inOrderHelper(root, list);
    E[] result = (E[]) new Object[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }
    return result;
  }

  private void inOrderHelper(Node<E> node, List<E> list) {
    if (node == null) {
      return;
    }
    inOrderHelper(node.getLeft(), list);
    list.add(node.getValue());
    inOrderHelper(node.getRight(), list);
  }

  /**
   * traverse the tree recursively
   * left-right-root
   * @return all objects pre-order
   */
  @Override
  public E[] postOrder() {
    List<E> list = new ArrayList<>();
    postOrderHelper(root, list);
    E[] result = (E[]) new Object[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }
    return result;
  }

  private void postOrderHelper(Node<E> node, List<E> list) {
    if (node == null) {
      return;
    }
    postOrderHelper(node.getLeft(), list);
    postOrderHelper(node.getRight(), list);
    list.add(node.getValue());
  }
  /**
   * traverse through the tree and find out the tree height
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    if (root == null) {
      throw new NullPointerException("BST is empty");
    }
    return findHeightHelper(root);
  }

  private int findHeightHelper(Node<E> node) {
    if (node == null) {
      return 0;
    } else {
      return Math.max(findHeightHelper(node.getLeft()), findHeightHelper(node.getRight())) + 1;
    }
  }

  static final class Node<E> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Node)) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()) &&
          Objects.equals(getLeft(), node.getLeft()) &&
          Objects.equals(getRight(), node.getRight()) &&
          getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }

}