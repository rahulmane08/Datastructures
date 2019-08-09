package list;

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
        for (T x : arr)
            insert(x);
    }

    public void insert(T data) {
        if (start == null) {
            start = new Node<>(data);
            return;
        }
        Node<T> curr = start;
        while (curr.next != null)
            curr = curr.next;
        curr.next = new Node<>(data);
        curr.next.prev = curr;
    }

    public void delete(T data) {
        if (start == null)
            return;
        if (data == null)
            return;
        Node<T> curr = this.start;
        while (!curr.data.equals(data))
            curr = curr.next;
        if (curr == null)
            return; // node not found
        Node<T> next = curr.next;
        Node<T> prev = curr.prev;
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = curr.prev;
        curr.prev = null;
        curr.next = null;
        if (curr == this.start)
            this.start = next;
    }

    public int size() {
        if (start == null)
            return 0;
        int size = 0;
        Node<T> curr = start;
        while (curr != null) {
            ++size;
            curr = curr.next;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node<T> curr = this.start; curr != null; curr = curr.next) {
            if (curr == this.start)
                s.append(curr.prev + "<- (" + curr.data + ")");
            else
                s.append(curr.data);
            if (curr.next != null) {
                if (curr == curr.next.prev)
                    s.append(" <-> ");
                else
                    s.append(" -> ");
            } else {
                s.append(" -> " + curr.next);
            }
        }
        return s.toString();
    }

}
