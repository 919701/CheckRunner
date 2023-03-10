package com.clevertec.CheckRunner.cache.factoryCache;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.serviseCache.LFUCacheImpl;
import com.clevertec.CheckRunner.cache.serviseCache.LRUCacheImpl;

public class CacheFactory {
    private int capacity;

    public <K,V> Cache<K,V> getCacheMethod(String method,int capacity) {
        return switch (method) {
            case "LFU" -> new LFUCacheImpl<>(capacity);
            case "LRU"-> new LRUCacheImpl<>(capacity);
        };
    }
}
