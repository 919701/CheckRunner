package com.clevertec.CheckRunner.cache;

import com.clevertec.CheckRunner.cache.implCache.LFUCache;
import com.clevertec.CheckRunner.cache.implCache.LRUCache;

/**
 *This interface implements a cache for objects that are often used
 * during program execution to reduce the number of calls to a resource source.
 * <p>This interface allows the use of various cache generation methods.
 * @see LRUCache
 * @see LFUCache
 * @param <K> the type of keys maintained by this map
 * @param <V>the type of mapped values
 *
 * @author Sergeev Sergey
 */
public interface Cache<K, V> {

    /**
     * The method of adding an element to the cache,
     * from which entities will be retrieved later.
     * @param key associative key by which the value of the object will be obtained
     * @param value The object that is required to be provided in cases of key access
     */
    void put(K key, V value);

    /**
     * This method allows you to retrieve an object by its bound key
     * @param key The key by which you want to retrieve the object
     * @return Returning the Object to which the key is bound
     */
    V get(K key);

    Boolean remove(K key);

    /**
     * You can use this method to get the size of the cache that you are using
     * @return
     */
    int size();
}
