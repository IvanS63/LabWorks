package com.mypackage.lab7.task1;

import com.mypackage.lab7.task1.util.Counter;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final int THREADS_COUNT = 100;

    private static void testCount() {
        System.out.println("First task: testing counter...");
        List<MyThread> listOfThreads = new ArrayList<>();
        for (int i = 0; i < THREADS_COUNT; i++) {
            listOfThreads.add(new MyThread("Thread #" + (i + 1)));
        }
        for (MyThread myThread : listOfThreads) {
            myThread.start();
        }
        for (MyThread myThread : listOfThreads) {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
        System.out.println(Counter.getCounter());
    }

    public static void doTest() {
        testCount();
    }
}
