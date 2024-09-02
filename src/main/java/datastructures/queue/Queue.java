package datastructures.queue;

public class Queue<T> {
  private final Object[] arr;
  private int front = -1, rear = -1;


  public Queue(int capacity) {
    arr = new Object[capacity];
  }

  public void offer(T elem) {
    if (isFull()) {
      throw new RuntimeException("Queue is full");
    }
    rear = (rear + 1) % capacity();
    arr[rear] = elem;
    if (front == -1) {
      front = rear;
    }
  }

  public T poll() {
    if (isEmpty()) {
      throw new RuntimeException("Queue is empty");
    }
    T elem = (T) arr[front];
    arr[front] = null;
    if (front == rear) {
      front = rear = -1;
    } else {
      front = (front + 1) % capacity();
    }
    return elem;
  }

  public int capacity() {
    return arr.length;
  }

  public int size() {
    if (rear > front) {
      return rear - front + 1;
    }
    return capacity() - (front - rear) + 1;
  }

  public boolean isFull() {
    return (rear + 1) % capacity() == front;
  }

  public boolean isEmpty() {
    return (front == -1);
  }

  public T first() {
    if (isEmpty()) {
      return null;
    }
    return (T) arr[front];
  }
}
