package pq;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class MaxPriorityQueue<T extends Comparable<T>> {
    private Object[] arr;
    private int capacity;
    private int size = 0;

    public MaxPriorityQueue(int capacity) {
        this.capacity = getBalancedCapacity(capacity);
        this.arr = new Object[this.capacity];
    }

    private int getBalancedCapacity(int capacity) {
        int pow = (int) pow(2, ceil(log(capacity) / log(2d)));
        if (pow == capacity)
            return getBalancedCapacity(capacity + 1);
        return pow;
    }

    /**
     * Time complexity: log(n)
     *
     * @param elem
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
        int smallest = i;
        int parent = (i - 1) / 2;
        if (parent >= 0) {
            T elem = (T) this.arr[i];
            T parentElem = (T) this.arr[parent];
            if (parentElem != null && elem.compareTo(parentElem) > 0) {
                smallest = parent;
            }
        }
        if (smallest != i) {
            T temp = (T) this.arr[i];
            this.arr[i] = this.arr[smallest];
            this.arr[smallest] = temp;
            heapifyUp(smallest);
        }
    }

    private void heapifyDown(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < this.size) {
            T elem = (T) arr[largest];
            T leftChild = (T) arr[left];
            if (elem != null && elem.compareTo(leftChild) < 0) {
                largest = left;
            }
        }

        if (right < this.size) {
            T elem = (T) arr[largest];
            T rightChild = (T) arr[right];
            if (elem != null && elem.compareTo(rightChild) < 0) {
                largest = right;
            }
        }

        if (largest != i) {
            T temp = (T) this.arr[i];
            this.arr[i] = this.arr[largest];
            this.arr[largest] = temp;
            heapifyDown(largest);
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
        if (size == 0)
            return null;
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
        if (size == 0)
            return null;
        Object[] output = new Object[this.size];
        System.arraycopy(this.arr, 0, output, 0, this.size);
        return output;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}
