package leetcode.design.hard;

public class LRUCacheWIthExpiry<K, V> extends LRUCache<K, V> {

  private final long expiryTime;

  public LRUCacheWIthExpiry(int capacity, int expiryTime) {
    super(capacity);
    this.expiryTime = expiryTime;
  }

  @Override
  public V get(K key) {
    CacheNode<K, V> cacheNode = cache.get(key);
    if (cacheNode != null) {
      if (cacheNode.isExpired()) {
        removeCacheNode(cacheNode);
        return null;
      }
      moveToHead(cacheNode);
      return cacheNode.value;
    }
    return null;
  }

  @Override
  public void put(K key, V value) {
    CacheNode<K, V> cacheNode = cache.get(key);
    if (cacheNode != null) {
      if (cacheNode.isExpired()) {
        return;
      }
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

  @Override
  protected void addCacheNode(CacheNode<K, V> cacheNode) {
    super.addCacheNode(cacheNode);
    cacheNode.updateAccessTime();
  }
}
