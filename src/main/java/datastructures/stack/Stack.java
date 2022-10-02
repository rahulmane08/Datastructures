package datastructures.stack;

public class Stack<T> {
    private final Object[] array;
    private int top;

    public Stack(int capacity) {
        array = new Object[capacity];
        top = -1;
    }

    public T top() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty , nothing to pop");
        return (T) array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return array != null && array.length - 1 == top;
    }

    public T pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty , nothing to pop");
        return (T) array[top--];
    }

    public void push(T elem) {
        if (isFull())
            throw new RuntimeException("Stack is full , cant add " + elem);
        array[++top] = elem;
    }

    public int size() {
        return top + 1;
    }
}
