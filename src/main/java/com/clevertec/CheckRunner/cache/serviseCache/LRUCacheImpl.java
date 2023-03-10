package com.clevertec.CheckRunner.cache.serviseCache;

import com.clevertec.CheckRunner.cache.Cache;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

@Component
@Service
@NoArgsConstructor
public final class LRUCacheImpl<K, V> implements Cache<K, V> {

    Map<K, V> map = new HashMap<>();
    LinkedList<K> queue = new LinkedList<>();
    private int limit;

    public LRUCacheImpl(int limit) {
        this.limit = limit;
    }

    @Override
    public Optional<V> get(K key) {

        queue.removeFirstOccurrence(key);
        queue.addFirst(key);
        return (Optional<V>) map.get(key);
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
    public Optional<V> getSilent(K key) {
        return (Optional<V>) map.get(key);
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
