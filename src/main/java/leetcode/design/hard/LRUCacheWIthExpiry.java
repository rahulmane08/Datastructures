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
      cacheNode.updateAccessTime();
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
        removeCacheNode(cacheNode);
        return;
      }
      cacheNode.value = value;
      cacheNode.updateAccessTime();
      moveToHead(cacheNode);
    } else {
      if (cache.size() == this.capacity) {
        evictEldestEntry();
        addCacheNode(new CacheNode<>(key, value));
      }
    }
  }

  @Override
  protected void addCacheNode(CacheNode<K, V> cacheNode) {
    super.addCacheNode(cacheNode);
    cacheNode.updateAccessTime();
  }
}
