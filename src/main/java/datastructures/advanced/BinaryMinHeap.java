package datastructures.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Data structure to support following operations
 * * extractMin - O(logn)
 * * add - O(logn)
 * * containsKey - O(1)
 * * decreaseKey - O(logn)
 * * getKeyWeight - O(1)
 * *
 * It is a combination of binary heap and hash map
 */

public class BinaryMinHeap<T> {

  private final List<Node<T>> elements = new ArrayList<>();
  private final Map<T, Integer> positions = new HashMap<>();

  public static void main(String[] args) {
    BinaryMinHeap heap = new BinaryMinHeap();
    System.out.println(heap);
    heap.add("A", 10);
    System.out.println(heap);
    heap.add("B", 2);
    System.out.println(heap);
    System.out.println("extractMin = " + heap.extractMin());
    System.out.println(heap);
    heap.add("C", 2);
    System.out.println(heap);
    heap.decreaseKey("A", 9);
    System.out.println(heap);
    System.out.println("extractMin = " + heap.extractMin());
    System.out.println(heap);
    System.out.println("extractMin = " + heap.extractMin());
    System.out.println(heap);
  }

  /**
   * T(n) = O(1)
   *
   * @param key
   * @return
   */
  public boolean containsKey(T key) {
    return positions.containsKey(key);
  }

  /**
   * T(n) = O(log(n))
   *
   * @param key
   * @param weight
   */
  public void add(T key, int weight) {
    if (containsKey(key)) {
      return;
    }
    Node elem = new Node(key, weight);
    elements.add(elem); // add at the end.
    int position = size() - 1;
    positions.put(key, position);
    heapifyUp(position);
  }

  /**
   * T(n) = O(log(n))
   *
   * @param key
   */
  public void decreaseKey(T key, int decreaseBy) {
    if (!containsKey(key)) {
      return;
    }
    int position = getPosition(key);
    if (position != -1) {
      Node<T> elem = elements.get(position);
      elem.setWeight(elem.getWeight() - decreaseBy);
      heapifyUp(position);
    }
  }

  /**
   * T(n) = O(log(n))
   * <p>
   * Get the node at position = 0.
   * Copy the last postition element to position = 0
   * Heapify down position 0.
   */
  public T extractMin() {
    if (isEmpty()) {
      return null;
    }

    int size = size();
    Node<T> elem = elements.get(0);
    Node<T> last = elements.get(size - 1);
    positions.remove(elem.key);
    if (elem != last) {
      elements.set(0, last);
      positions.put(last.key, 0);
      elements.remove(size - 1);
      heapifyDown(0);
    }
    return elem.key;
  }

  /**
   * T(n) = O(1)
   */
  public int getPosition(T key) {
    return positions.getOrDefault(key, -1);
  }

  /**
   * T(n) = O(1)
   */
  public T min() {
    return isEmpty() ? null : elements.get(0).key;
  }

  public int size() {
    return elements.size();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public Integer getKeyWeight(T key) {
    if (containsKey(key)) {
      return elements.get(getPosition(key)).weight;
    }
    return null;
  }

  // Heapification functions.
  private void heapifyUp(int position) {
    if (!isValidPosition(position)) {
      return;
    }

    int parent = (position - 1) / 2;
    // compare with parent.
    if (isValidPosition(parent)) {
      Node<T> curr = elements.get(position);
      Node<T> parentElem = elements.get(parent);
      if (parentElem != null && parentElem.weight > curr.weight) {
        swap(position, parent);
        heapifyUp(parent);
      }
    }
  }

  private void heapifyDown(int position) {
    if (!isValidPosition(position)) {
      return;
    }

    Node<T> curr = elements.get(position);
    int left = 2 * position + 1;
    int right = 2 * position + 2;
    int smallest = position;

    if (isValidPosition(left) && elements.get(left).weight < curr.weight) {
      smallest = left;
    }

    if (isValidPosition(right) && elements.get(right).weight < curr.weight) {
      smallest = right;
    }

    if (smallest != position) {
      swap(position, smallest);
      heapifyDown(smallest);
    }
  }

  private void swap(int i, int j) {
    if (i != j) {
      Node<T> x = elements.get(i);
      Node<T> y = elements.get(j);
      elements.set(i, y);
      positions.put(y.key, i);
      elements.set(j, x);
      positions.put(x.key, j);
    }
  }

  private boolean isValidPosition(int position) {
    return position >= 0 && position < elements.size();
  }

  @Override
  public String toString() {
    return String.format("size = %s, min = %s, positions = %s", size(), min(), positions);
  }

  @Data
  @AllArgsConstructor
  @ToString
  private class Node<T> {
    T key;
    int weight;
  }
}