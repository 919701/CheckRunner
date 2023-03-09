package com.clevertec.CheckRunner.services.impl;

import com.clevertec.CheckRunner.services.Cache;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class LFUCacheImpl<K, V> implements Cache<K, V> {


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
