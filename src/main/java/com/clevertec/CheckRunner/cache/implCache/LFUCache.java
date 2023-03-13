package com.clevertec.CheckRunner.cache.implCache;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.factoryCache.CacheFactory;

import java.util.HashMap;
import java.util.LinkedHashSet;
/**
 * Least Frequently Used(LFU) cache algorithm uses a counter to keep track of how often an entry is accessed.
 * With the LFU cache algorithm, the entry with the lowest count is removed first. This method isn’t used that often,
 * as it does not account for an item that had an initially high access rate and then was not accessed for a long time
 * <p> The LFU cache provides two methods: put and get.
 *     put(key, value) - Add the value into cache if it is not already present.
 *     When the cache reached its capacity, it should invalidate the least frequently used item before
 *     inserting a new item. If there is a tie (i.e., two or more keys that have the same frequency), the least recently
 *     used key would be evicted.
 *     get(key) - If the key doesn’t exist in the cache, return the minimum value of Integer.
 *     Otherwise, return the value of the key and move this element to the proper position of the cache.
 * <p> It can be used in the following order using the factory pattern:
 * <pre>{@code
 * Class Foo{
 *      private final Cache<Integer, String> cache =
 *             new CacheFactory().getCacheMethod(4, CacheTypeMethod.LRU);
 * }}
 * , where 4 is the size of the required cache
 * </pre>
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @see Cache
 * @see CacheFactory
 * @author Sergey Sergeev
 */
public class LFUCache<K, V> implements Cache<K, V> {
    HashMap<K, V> cache;
    HashMap<K, Integer> counts;
    HashMap<Integer, LinkedHashSet<K>> lists;
    int capacity;
    int min = -1;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            new Throwable("Cache size must be greater than 0");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
        this.lists.put(0, new LinkedHashSet<>());
    }


    public void put(K key, V value) {

        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key); // trigger the reorder
            return;
        }

        if (cache.size() >= capacity) {
            var evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            cache.remove(evict);
            counts.remove(evict);
        }

        cache.put(key, value);
        counts.put(key, 0);
        min = 0;
        lists.get(0).add(key);
    }


    public V get(K key) {

        var count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0) {
            min++;
        }
        if (!lists.containsKey(count + 1)) {
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return cache.get(key);
    }

    public int size() {
        return cache.size();
    }
}
