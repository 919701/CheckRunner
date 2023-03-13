package com.clevertec.CheckRunner.cache.implCache;

import com.clevertec.CheckRunner.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**LRU caching is one such mechanism that keeps track of the cache access order,
 * and when a user adds a new cache and if the cache is full, the LRU algorithm
 * will remove the last used cache from the cache to make room for the new cache.
 *<p>LRU cache implementation:
 *<li> The cache has a fixed size.
 *<li> The cache access order is preserved, in order to know which cache was least accessed. For this, LinkedHashMap is used.
 *<li> The cache is supplied in a <key, value> pair and therefore a Hashmap is used to store the cache data.
 *<li> The LRU cache implementation implements two important methods, namely put() and get().
 *<p> 1. Put(key, value) : used to add new data to the cache.
 *<p> 2. Get(key) : used to get data from the cache using a key.
 * @see Cache
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class LRUCache<K, V> implements Cache<K,V> {

    private final Map<K, V> cache;

    public LRUCache(int capacity) {

        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
                return super.size() > capacity;
            }
        };
    }

    /**
     *This method allows you to augment the cache with the object {@code V}
     *  and assign it the key {@code K}
     *  This method is implemented on the standard method of adding the map interface
     *
     * @param key associative key
     * @param value an object stored in the cache
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public Boolean remove(K key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            return true;
        }
        return false;
    }

    public int size() {
        return cache.size();
    }

    public String toString() {
        return cache.toString();
    }
}
