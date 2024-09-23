package leetcode.design.hard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Supports following public functions:
 * put
 * get
 * remove
 * <p>
 * internal helper functions
 * addCacheNode : adds the CacheNode to front of list
 * removeCacheNode : delinks the CacheNode from the list.
 * evictEldestEntry : removes the last CacheNode prev to tail.
 * moveToHead : removeCacheNode + addCacheNode
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {

  protected final Map<K, CacheNode<K, V>> cache;

  // indicates if the cache size, when cache hits the capacity its full, we need to evict old entries.
  protected final int capacity;
  protected final CacheNode head, tail;

  public LRUCache(int capacity) {
    this.cache = new ConcurrentHashMap<>();
    this.capacity = capacity;
    this.head = new CacheNode(null, null); // dummy node
    this.tail = new CacheNode(null, null); // dummy node
    this.head.next = this.tail;
    this.tail.prev = this.head;
  }

  /**
   * If the cache node exists, the move it to head as its the most recently accessed.
   *
   * @param key
   * @return
   */
  public V get(K key) {
    CacheNode<K, V> cacheNode = cache.getOrDefault(key, null);
    if (cacheNode != null) {
      moveToHead(cacheNode);
      return cacheNode.value;
    }
    return null;
  }

  /**
   * This function upserts a cacheNode, always moves it to head of list.
   * If the cache is full then evictEldestEntry and add a new node.
   *
   * @param key
   * @param value
   */
  public void put(K key, V value) {
    CacheNode<K, V> cacheNode = cache.get(key);
    if (cacheNode != null) {
      moveToHead(cacheNode);
      cacheNode.value = value;
    } else {
      if (cache.size() == this.capacity) {
        evictEldestEntry();
        cacheNode = new CacheNode<>(key, value);
        addCacheNode(cacheNode);
      }
    }
  }

  public void remove(K key) {
    CacheNode<K, V> cacheNode = cache.remove(key);
    if (cacheNode != null) {
      removeCacheNode(cacheNode);
    }
  }

  /**
   * Removes the entry previous to tail.
   */
  protected void evictEldestEntry() {
    if (tail.prev != head) {
      CacheNode<K, V> oldNode = tail.prev;
      removeCacheNode(oldNode);
    }
  }

  /**
   * Adds a cache node at the front of the list.
   *
   * @param cacheNode
   */
  protected void addCacheNode(CacheNode<K, V> cacheNode) {
    cacheNode.next = head.next;
    cacheNode.next.prev = cacheNode;
    cacheNode.prev = head;
    head.next = cacheNode;
    cache.put(cacheNode.key, cacheNode);
  }

  /**
   * Delinks the node from the cache.
   *
   * @param cacheNode
   */
  protected void removeCacheNode(CacheNode<K, V> cacheNode) {
    cacheNode.next.prev = cacheNode.prev;
    cacheNode.prev.next = cacheNode.next;
    cacheNode.prev = null;
    cacheNode.next = null;
    cache.remove(cacheNode.key);
  }

  protected void moveToHead(CacheNode<K, V> cacheNode) {
    removeCacheNode(cacheNode);
    addCacheNode(cacheNode);
  }
}
