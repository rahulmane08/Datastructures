package datastructures.list;

public class DoublyLinkedList<T> {
  Node<T> start;

  public DoublyLinkedList(Node<T> start) {
    super();
    this.start = start;
  }

  public DoublyLinkedList() {
    super();
  }

  public DoublyLinkedList(T[] arr) {
    for (T x : arr) {
      insert(x);
    }
  }

  public void insert(T data) {
    if (start == null) {
      start = new Node<>(data);
      return;
    }
    Node<T> curr = start;
    while (curr.next != null) {
      curr = curr.next;
    }
    curr.next = new Node<>(data);
    curr.next.prev = curr;
  }

  public boolean remove(T data) {
    if (start == null) {
      return false;
    }

    if (start.data == data) {
      Node next = start.next;
      start.next = null;
      start = next;
      if (next != null) {
        next.prev = null;
      }
      return true;
    }

    Node<T> curr = start;
    for (; curr.next != null && !curr.next.data.equals(data); curr = curr.next) ;
    if (curr.next == null) {
      return false;
    }
    Node next = curr.next;
    curr.next = next.next;
    next.next = null;
    next.prev = null;
    if (curr.next != null) {
      curr.next.prev = curr;
    }
    return true;
  }

  public int size() {
    int size = 0;
    for (Node<T> curr = this.start; curr != null; ++size, curr = curr.next) ;
    return size;
  }

  public boolean contains(T data) {
    Node<T> curr = start;
    for (; curr != null && !curr.data.equals(data); curr = curr.next) ;
    return curr != null;
  }

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
    StringBuilder s = new StringBuilder();
    for (Node<T> curr = this.start; curr != null; curr = curr.next) {
      if (curr == this.start) {
        s.append(curr.prev + "<- (" + curr.data + ")");
      } else {
        s.append(curr.data);
      }
      if (curr.next != null) {
        if (curr == curr.next.prev) {
          s.append(" <-> ");
        } else {
          s.append(" -> ");
        }
      } else {
        s.append(" -> " + curr.next);
      }
    }
    if (s.toString().isEmpty()) {
      return "(null)";
    }
    return s.toString();
  }
}
