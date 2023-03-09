package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.services.Cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class LRUCacheImpl<K, V> implements Cache<K, V> {

    Map<K, V> map = new HashMap<>();
    LinkedList<K> queue = new LinkedList<>();
    final int limit;

    public LRUCacheImpl(int limit) {
        this.limit = limit;
    }

    @Override
    public V get(K key) {

        queue.removeFirstOccurrence(key);
        queue.addFirst(key);
        return map.get(key);
    }

    @Override
    public void put(K key, V value) {
        V oldValue = map.put(key, value);

        if (oldValue != null) {
            queue.removeFirstOccurrence(key);
        }
        queue.addFirst(key);

        if (map.size() > limit) {
            final K removedKey = queue.removeLast();
            map.remove(removedKey);
        }
    }

    @Override
    public V getSilent(K key) {
        return map.get(key);
    }

    @Override
    public void remove(K key) {
        queue.removeFirstOccurrence(key);
        map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }
}
