package leetcode.design.hard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LFUCache<K, V> {
  protected final Map<K, CacheNode<K, V>> cache;
  protected final int capacity;
  protected final CacheNode head, tail;

  public LFUCache(int capacity) {
    this.cache = new ConcurrentHashMap<>();
    this.capacity = capacity;
    this.head = new CacheNode(null, null); // dummy node
    this.tail = new CacheNode(null, null); // dummy node
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }
}
