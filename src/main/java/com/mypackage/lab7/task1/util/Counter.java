package com.mypackage.lab7.task1.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class for parallel incrementing counter
 */
public class Counter {
    private static AtomicInteger counter = new AtomicInteger(0);

    /**
     * Get counter value
     *
     * @return current counter value
     */
    public static int getCounter() {
        return counter.get();
    }

    /**
     * Incrementing counter
     */
    public static void increment() {
        counter.getAndIncrement();
    }

}
