package com.mypackage.lab7.task3;

/**
 * Philosopher class using java.util.concurrent
 */
public class Philosopher implements Runnable {
    private static final long EATING_PERIOD = 500;
    private static final long THINKING_PERIOD = 1000;
    private Fork leftFork;
    private Fork rightFork;

    private enum State {
        EATING,
        THINKING
    }

    private int id;
    private State currentState;

    /**
     * Create philosopher with id and two forks
     *
     * @param id        philosopher number
     * @param leftFork
     * @param rightFork
     */
    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        currentState = State.THINKING;
    }


    private void eat() {
        if (leftFork.take()){
            if (rightFork.take()) {
                currentState = State.EATING;
                try {
                    System.out.println("Philosopher #" + id + " is eating...");
                    Thread.sleep(EATING_PERIOD);
                    rightFork.put();
                    leftFork.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                leftFork.put();
            }
        }
    }


    private void think() {
        if (!leftFork.take()) {
            if (!rightFork.take()) {
                currentState = State.THINKING;
                System.out.println("Philosopher #" + id + " is thinking...");
                try {
                    Thread.sleep(THINKING_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void run() {
        while (true) {
            if (currentState == State.THINKING) {
                eat();
            } else {
                think();
            }
        }
    }

}
