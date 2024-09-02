package datastructures.pq;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * A typical Priority Queue requires following operations to be efficient.
 * <p>
 * Get Top Priority Element (Get minimum or maximum)
 * Insert an element
 * Remove top priority element
 * Decrease Key
 * A Binary Heap supports above operations with following time complexities:
 * <p>
 * O(1)
 * O(Logn)
 * O(Logn)
 * O(Logn)
 * <p>
 * Is Binary Heap always better?
 * Although Binary Heap is for Priority Queue, BSTs have their own advantages and the list of advantages
 * is in-fact bigger compared to binary heap.
 * <p>
 * Searching an element in self-balancing BST is O(Logn) which is O(n) in Binary Heap.
 * We can print all elements of BST in sorted order in O(n) time, but Binary Heap requires O(nLogn) time.
 * Floor and ceil can be found in O(Logn) time.
 * K’th largest/smallest element be found in O(Logn) time by augmenting tree with an additional field.
 *
 * @param <T>
 */
public class MinPriorityQueue<T extends Comparable<T>> {
  private Object[] arr;
  private int capacity;
  private int size = 0;

  public MinPriorityQueue(int capacity) {
    this.capacity = getBalancedCapacity(capacity);
    this.arr = new Object[this.capacity];
  }

  private int getBalancedCapacity(int capacity) {
    int pow = (int) pow(2, ceil(log(new Double(capacity).doubleValue()) / log(new Double(2).doubleValue())));
    if (pow == capacity) {
      return getBalancedCapacity(capacity + 1);
    }
    return pow;
  }

  /**
   * Time complexity: log(n)
   *
   * @return
   */
  public boolean add(T elem) {
    if (elem == null) {
      return false;
    }
    if (size == capacity) {
      resize();
    }
    this.arr[size] = elem;
    heapifyUp(size++);
    return true;
  }

  private void resize() {
    Object[] resizedArr = new Object[capacity * 2];
    System.arraycopy(this.arr, 0, resizedArr, 0, this.capacity);
    this.capacity *= 2;
    this.arr = resizedArr;
  }

  private void heapifyUp(int i) {
    int largest = i;
    int parent = (i - 1) / 2;
    if (parent >= 0) {
      T elem = (T) this.arr[i];
      T parentElem = (T) this.arr[parent];
      if (parentElem != null && elem.compareTo(parentElem) < 0) {
        largest = parent;
      }
    }
    if (largest != i) {
      T temp = (T) this.arr[i];
      this.arr[i] = this.arr[largest];
      this.arr[largest] = temp;
      heapifyUp(largest);
    }
  }

  private void heapifyDown(int i) {
    int smallest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    if (left < this.size) {
      T elem = (T) arr[smallest];
      T leftChild = (T) arr[left];
      if (elem != null && elem.compareTo(leftChild) > 0) {
        smallest = left;
      }
    }

    if (right < this.size) {
      T elem = (T) arr[smallest];
      T rightChild = (T) arr[right];
      if (elem != null && elem.compareTo(rightChild) > 0) {
        smallest = right;
      }
    }

    if (smallest != i) {
      T temp = (T) this.arr[i];
      this.arr[i] = this.arr[smallest];
      this.arr[smallest] = temp;
      heapifyDown(smallest);
    }
  }

  public T peek() {
    return (T) this.arr[0];
  }

  /**
   * Time complexity: log(n)
   *
   * @return
   */
  public T poll() {
    if (size == 0) {
      return null;
    }
    T elem = peek();
    this.arr[0] = arr[--size];
    this.arr[size] = null;
    heapifyDown(0);
    return elem;
  }

  public int getSize() {
    return size;
  }

  public Object[] toArray() {
    if (size == 0) {
      return null;
    }
    Object[] output = new Object[this.size];
    System.arraycopy(this.arr, 0, output, 0, this.size);
    return output;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }
}
