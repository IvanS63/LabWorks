package com.mypackage.lab6.task1.cache;

public interface ICache<K, V> {
    void put(K key, V value);

    String get(K key);

    void printAllElements();
}
