package com.mypackage.lab6.task1.cache;

import com.mypackage.lab6.task1.annotations.Cache;

import java.util.Hashtable;
import java.util.Map;

@Cache(name = "HashTableCache")
public class HashTableCache implements ICache<Integer, String> {
    private Hashtable<Integer, String> hashtable = new Hashtable<Integer, String>();

    @Override
    public void put(Integer key, String value) {
        hashtable.put(key, value);
    }

    @Override
    public String get(Integer key) {
        return hashtable.get(key);
    }

    @Override
    public void printAllElements() {
        for (Map.Entry<Integer, String> e : hashtable.entrySet()) {
            System.out.println("(" + e.getKey() + ";" + e.getValue() + ") ");
        }
    }

    public HashTableCache() {
        for (int i = 0; i < 5; i++) {
            put(i, "HashTableCacheValue" + i);
        }
    }
}
