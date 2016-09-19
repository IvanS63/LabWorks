package com.mypackage.lab7.task1;

import com.mypackage.lab7.task1.util.Counter;

public class MyThread extends Thread {
    private static final int ITERATIONS_COUNT = 100000;

    public MyThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERATIONS_COUNT; i++) {
            Counter.increment();
        }
    }
}
