package design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> {

    private final Map<K, CacheNode<K, V>> cache;
    private final int capacity;
    private final CacheNode head, tail;
    public LRUCache(int capacity) {
        this.cache = new ConcurrentHashMap<>();
        this.capacity = capacity;
        this.head = new CacheNode(null, null);
        this.tail = new CacheNode(null, null);
    }

    public V get(K key) {
        CacheNode<K, V> cacheNode = cache.getOrDefault(key, null);
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
        } else {
            if (cache.size() == this.capacity) {
                evictOldEntries();
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

    private void evictOldEntries() {
        CacheNode<K, V> oldNode = tail.prev;
        removeCacheNode(oldNode);
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

    private void moveToHead(CacheNode<K, V> cacheNode) {
        if (cacheNode != null) {
            removeCacheNode(cacheNode);
            addCacheNode(cacheNode);
        }
    }

    /**
     * Doubly linked list cache node
     * @param <K>
     * @param <V>
     */
    class CacheNode<K, V> {
        K key;
        V value;
        CacheNode next;
        CacheNode prev;

        public CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheNode<?, ?> cacheNode = (CacheNode<?, ?>) o;
            return key.equals(cacheNode.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}
