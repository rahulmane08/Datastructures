package leetcode.design.hard;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class LRUCacheWithExpiry<K, V> {
  private final long expiryTime;

  private final Map<K, CacheNode<K, V>> cache;

  private final CacheNode<K, V> head, tail;

  private final int capacity;

  public LRUCacheWithExpiry(long expiryTime, int capacity) {
    this.expiryTime = expiryTime;
    this.capacity = capacity;
    this.cache = new HashMap<>();
    this.head = new CacheNode<K, V>(null, null);
    this.tail = new CacheNode<K, V>(null, null);
  }

  public V get(K key) {
    CacheNode<K, V> cacheNode = cache.getOrDefault(key, null);
    if (cacheNode != null && cacheNode.isExpired()) {
      remove(key);
      return null;
    }

    if (cacheNode != null) {
      moveToHead(cacheNode);
      return cacheNode.value;
    }
    return null;
  }

  public void put(K key, V value) {
    CacheNode<K, V> cacheNode = cache.getOrDefault(key, null);
    if (cacheNode != null) {
      moveToHead(cacheNode);
      cacheNode.value = value;
      cacheNode.creationTime = System.currentTimeMillis();
    } else {
      if (cache.size() == this.capacity) {
        evictExpiredEntries();
        evictOldestEntry();
      }
      cacheNode = new CacheNode<>(key, value);
      addCacheNode(cacheNode);
    }
  }

  private void evictOldestEntry() {
    CacheNode<K, V> oldNode = tail.prev;
    removeCacheNode(oldNode);
  }

  private void evictExpiredEntries() {
    CacheNode<K, V> oldNode = tail.prev;
    while (oldNode != head && oldNode.isExpired()) {
      CacheNode<K, V> prev = oldNode.prev;
      removeCacheNode(oldNode);
      oldNode = prev;
    }
  }

  private void moveToHead(CacheNode<K, V> cacheNode) {
    if (cacheNode != null) {
      removeCacheNode(cacheNode);
      addCacheNode(cacheNode);
    }
  }

  public void remove(K key) {
    CacheNode<K, V> cacheNode = cache.remove(key);
    if (cacheNode != null) {
      removeCacheNode(cacheNode);
    }
  }

  private void addCacheNode(CacheNode<K, V> cacheNode) {
    if (head.next == null) {
      head.next = cacheNode;
      tail.prev = cacheNode;
      cacheNode.next = tail;
      cacheNode.prev = head;
    } else {
      cacheNode.next = head.next;
      cacheNode.next.prev = cacheNode;
      cacheNode.prev = head;
      head.next = cacheNode;
    }
  }

  private void removeCacheNode(CacheNode<K, V> cacheNode) {
    if (cacheNode != null) {
      cacheNode.next.prev = cacheNode.prev;
      cacheNode.prev.next = cacheNode.next;
      cacheNode.prev = null;
      cacheNode.next = null;
    }
  }

  @Data
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  class CacheNode<K, V> {
    @EqualsAndHashCode.Include
    K key;
    V value;
    CacheNode next;
    CacheNode prev;

    Long creationTime;

    public CacheNode(K key, V value) {
      this.key = key;
      this.value = value;
      this.creationTime = System.currentTimeMillis();
    }

    boolean isExpired() {
      return creationTime + expiryTime <= System.currentTimeMillis();
    }
  }
}
