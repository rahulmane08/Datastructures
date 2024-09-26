package leetcode.design.hard;

import lombok.EqualsAndHashCode;

/**
 * Doubly linked list cache node
 *
 * @param <K>
 * @param <V>
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class CacheNode<K, V> {
  @EqualsAndHashCode.Include
  K key;
  V value;
  CacheNode next;
  CacheNode prev;
  long accessTime;
  long expiryInterval;
  int frequency;

  public CacheNode(K key, V value) {
    this.key = key;
    this.value = value;
    this.accessTime = System.currentTimeMillis();
  }

  public CacheNode(K key, V value, long expiryInterval) {
    this(key, value);
    this.expiryInterval = expiryInterval;
  }

  public void updateAccessTime() {
    this.accessTime = System.currentTimeMillis();
  }

  public boolean isExpired() {
    return this.accessTime + this.expiryInterval <= System.currentTimeMillis();
  }
}
