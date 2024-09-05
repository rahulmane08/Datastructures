package leetcode.design.hard;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
class AllOne {

  private static final String EMPTY = "";
  private final Map<String, Node> map;
  @ToString.Include
  private Node maxNode;
  @ToString.Include
  private Node minNode;
  public AllOne() {
    map = new HashMap<>();
  }

  public static void main(String[] args) {
    AllOne allOne = new AllOne();
    allOne.inc("rahul");
    allOne.inc("mane");
    System.out.println(allOne);

    allOne.inc("rahul");
    System.out.println(allOne);

    allOne.inc("mane");
    System.out.println(allOne);

    allOne.inc("rahul");
    System.out.println(allOne);

    allOne.inc("mane");
    System.out.println(allOne);

    allOne.inc("mane");
    System.out.println(allOne);

    allOne.inc("sharma");
    System.out.println(allOne);

    allOne.dec("sharma");
    System.out.println(allOne);
  }

  public void inc(String key) {
    if (key == null) {
      return;
    }
    Node curr = map.get(key);
    if (curr == null) {
      curr = new Node(key);
    }
    map.put(key, curr.increment());
    adjustMinMax(curr);
  }

  public void dec(String key) {
    if (key == null) {
      return;
    }
    Node curr = map.get(key);
    if (curr == null) {
      return;
    }
    curr.decrement();
    if (curr.count == 0) {
      deleteNode(curr);
      return;
    }
    adjustMinMax(curr);
  }

  private void deleteNode(Node curr) {
    if (curr == minNode) {
      assignAsMin(minNode.prev);
    }
    if (curr == maxNode) {
      assignAsMax(maxNode.next);
    }
    deleteFromCurrentPosition(curr);
  }

  private void adjustMinMax(Node curr) {
    if (maxNode == null && minNode == null) {
      maxNode = minNode = curr;
      return;
    }

    if (curr != maxNode && curr.count >= maxNode.count) {
      if (curr == minNode) {
        assignAsMin(minNode.prev);
      }
      deleteFromCurrentPosition(curr);
      assignAsMax(curr);
    }

    if (curr != minNode && curr.count <= minNode.count) {
      if (curr == maxNode) {
        assignAsMax(maxNode.next);
      }
      deleteFromCurrentPosition(curr);
      assignAsMin(curr);
    }
  }

  public String getMaxKey() {
    return maxNode != null ? maxNode.key : null;
  }

  public String getMinKey() {
    return minNode != null ? minNode.key : null;
  }

  private void assignAsMax(Node curr) {
    if (curr == null) {
      maxNode = null;
      return;
    }
    curr.next = maxNode;
    curr.next.prev = curr;
    maxNode = curr;
  }

  private void assignAsMin(Node curr) {
    if (curr == null) {
      minNode = null;
      return;
    }

    minNode.next = curr;
    minNode.next.prev = minNode;
    minNode = curr;
  }

  private void deleteFromCurrentPosition(Node curr) {
    if (curr.prev != null) {
      curr.prev.next = curr.next;
    }
    if (curr.next != null) {
      curr.next.prev = curr.prev;
    }
  }

  @Data
  @ToString(onlyExplicitlyIncluded = true)
  class Node {
    @EqualsAndHashCode.Include
    @ToString.Include
    private String key;
    @ToString.Include
    private int count;
    private Node next;
    private Node prev;

    public Node(String key) {
      this.key = key;
    }

    public Node increment() {
      count++;
      return this;
    }

    public Node decrement() {
      count--;
      return this;
    }
  }
}
