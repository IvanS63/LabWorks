package com.mypackage.lab7.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DiningTable class using java.util.concurrent
 */
public class DiningTable {
    private static final int PHILOSOPHERS_NUMBER = 5;
    private static final int FORKS_NUMBER = 5;
    private List<Fork> listOfForks;
    private List<Philosopher> listOfPhilosophers;

    private void init() {
        listOfForks = new ArrayList<>();
        listOfPhilosophers = new ArrayList<>();
        for (int i = 0; i < FORKS_NUMBER; i++) {
            listOfForks.add(new Fork(i + 1));
        }
        for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
            listOfPhilosophers.add(new Philosopher(i + 1, listOfForks.get((i + 5) % 5), listOfForks.get((i + 6) % 5)));
        }
    }

    private void startDinner() {
        init();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
            executorService.execute(listOfPhilosophers.get(i));
        }
    }

    public void test() {
        startDinner();
    }

}
