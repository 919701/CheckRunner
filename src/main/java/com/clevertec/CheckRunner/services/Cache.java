package com.clevertec.CheckRunner.services;

import java.util.Optional;

public interface Cache<K, V> {

    Optional<V> get(K key);

    void put(K key, V value);

    Optional<V> getSilent (K key );

    void remove ( K key );

    int size ();
}
