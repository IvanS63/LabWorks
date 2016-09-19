package com.mypackage.lab6.task1.consumers;

import com.mypackage.lab6.task1.annotations.InjectCache;
import com.mypackage.lab6.task1.cache.ICache;

public class Consumer {
    @InjectCache(name = "HashMapCache")
    private ICache firstTypeCache;

    @InjectCache(name = "HashTableCache")
    private ICache secondTypeCache;

    private String noCacheField;

    public Consumer() {
        secondTypeCache = null;
        firstTypeCache = null;
    }

    public void getCache() {
        System.out.println("ParentConsumer cache:");
        if (firstTypeCache != null)
            firstTypeCache.printAllElements();
        if (secondTypeCache != null)
            secondTypeCache.printAllElements();
        if (firstTypeCache == null && secondTypeCache == null)
            System.out.println("Cache is empty");

    }
}
