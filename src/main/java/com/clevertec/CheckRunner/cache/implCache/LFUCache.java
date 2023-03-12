package com.clevertec.CheckRunner.cache.implCache;

import com.clevertec.CheckRunner.cache.Cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

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
