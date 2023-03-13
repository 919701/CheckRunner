package com.clevertec.CheckRunner.cache.implCache;

import com.clevertec.CheckRunner.cache.Cache;
import com.clevertec.CheckRunner.cache.factoryCache.CacheFactory;
import com.clevertec.CheckRunner.cache.factoryCache.CacheTypeMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {
    private final int CAPACITY = 4;
    Cache<Integer, Integer> cache = new CacheFactory().getCacheMethod(CAPACITY, CacheTypeMethod.LFU);

    @BeforeEach
    void setUp() {

        cache.put(0, 0);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
    }
    @Test
    void checkPutShould4() {

        cache.put(4,4);
        assertEquals(4,4);
    }

    @Test
    void checkGetShould6AfterAdd2Element() {

        cache.put(5, 5);
        cache.put(6, 6);
        assertEquals(6, cache.get(6));
    }

    @Test
    void checkCacheSizeShouldCAPACITY() {
        cache.put(5, 5);
        cache.put(6, 6);
        cache.put(7, 7);
        assertEquals(CAPACITY, cache.size());
    }
}