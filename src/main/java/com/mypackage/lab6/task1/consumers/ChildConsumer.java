package com.mypackage.lab6.task1.consumers;

import com.mypackage.lab6.task1.annotations.InjectCache;
import com.mypackage.lab6.task1.cache.ICache;

public class ChildConsumer extends Consumer {
    @InjectCache(name = "HashMapCache")
    private ICache childCacheField;

    private int noCacheField;

    public ChildConsumer() {
        childCacheField = null;
    }

    @Override
    public void getCache() {
        super.getCache();
        System.out.println("ChildConsumer cache:");
        if (childCacheField != null) {
            childCacheField.printAllElements();
        } else System.out.println("Cache is empty");
    }
}
