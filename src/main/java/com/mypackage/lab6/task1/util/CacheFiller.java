package com.mypackage.lab6.task1.util;

import com.mypackage.lab6.task1.cache.ICache;

/**
 * Fill cache with some values
 */
public class CacheFiller {
    public static void fill(ICache cache) {
        for (int i = 0; i < 5; i++)
            cache.put(i, cache.getClass().getSimpleName() + "_value" + i);
    }
}
