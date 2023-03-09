package com.clevertec.CheckRunner.services;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    V getSilent (K key );

    void remove ( K key );

    int size ();
}
