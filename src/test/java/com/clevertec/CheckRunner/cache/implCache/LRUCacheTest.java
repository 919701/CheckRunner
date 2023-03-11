package com.clevertec.CheckRunner.cache.implCache;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.factoryCache.CacheFactory;
import com.clevertec.CheckRunner.cache.factoryCache.CacheTypeMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void put() {
        Cache<Integer,Integer> cache = new CacheFactory().getCacheMethod(CacheTypeMethod.LRU, 4);

        cache.put(0,0);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        boolean ok = cache.size () == 4;
        System.out.println(ok);

        cache.put ( 4, 4 );
        cache.put ( 5, 5 );
    }

    @Test
    void get() {
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }
}