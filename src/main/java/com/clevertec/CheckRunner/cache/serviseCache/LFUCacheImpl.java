package com.clevertec.CheckRunner.cache.serviseCache;

import com.clevertec.CheckRunner.cache.Cache;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
@NoArgsConstructor
public class LFUCacheImpl<K, V> implements Cache<K, V> {

    private int limit;

    public LFUCacheImpl(int limit) {
        this.limit = limit;
    }


    @Override
    public Optional<V> get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public Optional<V> getSilent(K key) {
        return Optional.empty();
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public int size() {
        return 0;
    }
}
