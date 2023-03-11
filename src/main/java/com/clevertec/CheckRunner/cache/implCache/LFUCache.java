package com.clevertec.CheckRunner.cache.implCache;

import com.clevertec.CheckRunner.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFUCache<K, V> implements Cache<K, V> {
    private final Map<K, V> cache;
    public LFUCache(int capacity) {

        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
                return super.size() > capacity;
            }
        };
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }
}
