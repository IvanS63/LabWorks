package com.mypackage.lab6.task1.cache;

import java.util.HashMap;
import java.util.Map;

import com.mypackage.lab6.task1.annotations.Cache;

@Cache(name = "HashMapCache")
public class HashMapCache implements ICache<Integer, String> {
    private HashMap<Integer, String> cache = new HashMap<Integer, String>();

    @Override
    public void put(Integer key, String value) {
        cache.put(key, value);
    }

    @Override
    public String get(Integer key) {
        return cache.get(key);
    }

    @Override
    public void printAllElements() {
        for (Map.Entry<Integer, String> entry : cache.entrySet()) {
            System.out.println("(" + entry.getKey() + ";" + entry.getValue() + ") ");
        }
    }

    public HashMapCache() {
        for (int i = 0; i < 5; i++) {
            put(i, "HashMapCacheValue" + i);
        }
    }

}
