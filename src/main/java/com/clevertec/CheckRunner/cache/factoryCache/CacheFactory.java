package com.clevertec.CheckRunner.cache.factoryCache;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.implCache.LFUCache;
import com.clevertec.CheckRunner.cache.implCache.LRUCache;

public class CacheFactory {

    public <K,V> Cache<K,V> getCacheMethod(CacheTypeMethod methodCache,int capacity) {
        return switch (methodCache) {
            case LFU -> new LFUCache<>(capacity);
            case LRU-> new LRUCache<>(capacity);
        };
    }
}
