package list;

class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;
    public boolean visited = false;

    public Node(T data) {
        super();
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + "]";
    }

}