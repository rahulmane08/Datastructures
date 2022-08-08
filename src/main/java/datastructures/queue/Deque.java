package datastructures.queue;

public class Deque<T> {

    private final int capacity;
    private Object[] arr;
    private int front, rear;

    public Deque(int capacity) {
        this.capacity = capacity;
        arr = new Object[arr.length];
        front = rear = -1;
    }

    public T dequeueFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue is empty");
        }
        T elem = (T) arr[front];
        front = (front + 1) % capacity;
        if (front == rear)
            front = rear = -1;
        return elem;
    }

    public T dequeueLast() {
        if (isEmpty()) {
            throw new RuntimeException("Dequeue is empty");
        }
        T elem = (T) arr[rear--];
        if (rear == -1)
            rear = capacity - 1;
        if (front == rear)
            front = rear = -1;
        return elem;
    }

    public void enqueueFirst(T elem) {
        if (isFull()) {
            throw new RuntimeException("Dequeue is full");
        }
        if (isEmpty()) {
            arr[++front] = elem;
            rear = front;
        } else {
            if (front == 0) {
                front = capacity;
            }
            arr[--front] = elem;
        }
    }

    public void enqueueLast(T elem) {
        if (isFull()) {
            throw new RuntimeException("Dequeue is full");
        }
        if (isEmpty()) {
            arr[++front] = elem;
            rear = front;
        } else {
            rear = (rear + 1) % capacity();
            arr[rear] = elem;
        }
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    public boolean isFull() {
        return ((rear + 1) % capacity) == front;
    }

    public int capacity() {
        return this.capacity;
    }

    public T first() {
        if (isEmpty()) {
            return null;
        }
        return (T) arr[front];
    }

    public T last() {
        if (isEmpty()) {
            return null;
        }
        return (T) arr[rear];
    }
}
