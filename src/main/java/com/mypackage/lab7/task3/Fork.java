package com.mypackage.lab7.task3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fork class using java.util.concurrent
 */
public class Fork {
    private Lock isTaken;
    private int id;

    public Fork(int id) {
        this.id = id;
        isTaken = new ReentrantLock(true);
    }

    public boolean take() {
        return isTaken.tryLock();
    }

    public void put() {
        isTaken.unlock();
    }
}
