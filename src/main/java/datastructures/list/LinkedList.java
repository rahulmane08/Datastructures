package datastructures.list;

import java.util.Arrays;

public class LinkedList<T> implements Cloneable {

  Node<T> start;

  public LinkedList() {
    super();
  }

  public LinkedList(Node<T> start) {
    super();
    this.start = start;
  }

  public LinkedList(T[] arr) {
    for (T data : arr) {
      insert(data);
    }
  }

  public void insert(T data) {
    if (start == null) {
      start = new Node(data);
      return;
    }
    insert(new Node<>(data));
  }

  public void insert(Node<T> node) {
    if (start == null) {
      start = node;
      return;
    }
    Node<T> curr = start;
    for (; curr.next != null; curr = curr.next) ;
    curr.next = node;
  }

  public int length() {
    return length(this.start);
  }

  private int length(Node n) {
    if (n == null) {
      return 0;
    }
    return 1 + length(n.next);
  }

  public void traverse() {
    if (start == null) {
      return;
    }
    Node tempNode = start;
    while (tempNode != null) {
      System.out.print(tempNode.data + " ");
      tempNode = tempNode.next;
    }
    System.out.println();
  }

  public int size() {
    int size = 0;
    for (Node<T> curr = this.start; curr != null; ++size, curr = curr.next) ;
    return size;
  }

  public boolean remove(T data) {
    if (start == null) {
      return false;
    }

    if (start.data == data) {
      start = start.next;
      return true;
    }

    Node<T> curr = start;
    for (; curr.next != null && curr.next.data != data; curr = curr.next) ;
    if (curr.next == null) {
      // node not found
      return false;
    }
    Node<T> next = curr.next;
    curr.next = next.next;
    next.next = null;
    return true;
  }

  public boolean contains(T data) {
    Node<T> curr = start;
    for (; curr != null && !curr.data.equals(data); curr = curr.next) ;
    return curr != null;
  }

  // 0-based
  public int indexOf(T data) {
    int i = 0;
    Node<T> curr = start;
    for (; curr != null && !curr.data.equals(data); curr = curr.next, i++) ;
    return (curr != null) ? i : -1;
  }

  public Node<T> nodeAt(int index) {
    Node<T> curr = start;
    for (int i = 0; i < index && curr != null; i++, curr = curr.next) ;
    return curr;
  }

  @Override
  public String toString() {
    Object[] arr = new Object[size()];
    int i = 0;
    Node<T> curr = start;
    while (curr != null) {
      arr[i++] = curr.data;
      curr = curr.next;
    }
    return Arrays.toString(arr);
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    LinkedList<T> clone = new LinkedList<>();
    for (Node<T> curr = this.start; curr != null; ) {
      Node<T> next = curr.next;
      Node<T> dupe = new Node<>(curr.data);
      curr.next = dupe;
      dupe.prev = curr;
      clone.insert(dupe);
      curr = next;
    }

    for (Node<T> curr = clone.start; curr != null; curr = curr.next) {
      Node<T> random = curr.prev.prev;
      if (random != null) {
        curr.prev = random.next;
      }
      if (curr.next != null) {
        curr.prev.next = curr.next.prev;
      }
    }
    return clone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LinkedList<T> that = (LinkedList<T>) o;

    if (that == null) {
      return false;
    }

    Node<T> curr1 = this.start;
    Node<T> curr2 = that.start;
    while (curr1 != null && curr1.data != null && curr2 != null && curr2.data != null) {
      if (!curr1.data.equals(curr2.data)) {
        return false;
      }
      curr1 = curr1.next;
      curr2 = curr2.next;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return start.hashCode();
  }
}
