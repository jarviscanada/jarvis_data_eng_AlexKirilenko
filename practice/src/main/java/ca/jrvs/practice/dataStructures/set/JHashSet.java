package ca.jrvs.practice.dataStructures.set;

import java.util.HashMap;

public class JHashSet<E> implements JSet<E> {

  private HashMap<E,Boolean> map;

  public JHashSet() {
    map = new HashMap<>();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public boolean contains(Object o) {
    return map.containsKey(o);
  }

  @Override
  public boolean add(E e) {
    if (map.containsKey(e)) {
      return false;
    } else {
      map.put(e, true);
      return true;
    }
  }

  @Override
  public boolean remove(Object o) {
    if (map.containsKey(o)) {
      map.remove(o);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void clear() {
    map.clear();
  }
}
