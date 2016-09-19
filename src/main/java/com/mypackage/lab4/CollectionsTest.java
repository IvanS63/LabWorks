package com.mypackage.lab4;

import com.mypackage.lab2.util.RandomInit;

import java.util.*;

/**
 * Class for ArrayList and LinkedList comparison
 */
public class CollectionsTest {
    private Random random = new Random();
    private List<Integer> list;
    private int randomIndex;

    /**
     * ArrayList and LinkedList comparison
     * <p>
     * Print time for sequential adding; adding, getting and removing element
     * by random index; iterating and sorting.
     */
    public void doTest() {
        int length = 10000000;
        int[] array = RandomInit.initRandomArray(length, -50, 50);
        randomIndex = random.nextInt(length);
        System.out.println("Random index:" + randomIndex);
        System.out.println("-------------------");
        list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        System.out.println("ArrayList results:");
        System.out.println("Sequential addition:" + getSecAddTime() + " ns");
        System.out.println("Random addition:" + setRandomElTime() + " ms");
        System.out.println("Random get:" + getRandomElTime() + " ms");
        System.out.println("Iterator:" + getIteratorTime() + " ms");
        System.out.println("Sorting:" + getSortTime() + " ms");
        System.out.println("Random remove:" + removeRandomElTime() + " ms");
        System.out.println("-------------------");
        list = new LinkedList<Integer>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        System.out.println("LinkedList results:");
        System.out.println("Sequential addition:" + getSecAddTime() + " ns");
        System.out.println("Random addition:" + setRandomElTime() + " ms");
        System.out.println("Random get:" + getRandomElTime() + " ms");
        System.out.println("Iterator:" + getIteratorTime() + " ms");
        System.out.println("Sorting:" + getSortTime() + " ms");
        System.out.println("Random remove:" + removeRandomElTime() + " ms");

    }

    /**
     * Sequential adding
     *
     * @return time, ns
     */
    private long getSecAddTime() {
        int randomValue = random.nextInt(list.size());
        long sTime = System.nanoTime();
        list.add(randomValue);
        long eTime = System.nanoTime();
        return eTime - sTime;
    }

    /**
     * Sorting
     *
     * @return time, ms
     */
    private long getSortTime() {
        long sTime = System.currentTimeMillis();
        Collections.sort(list);
        long eTime = System.currentTimeMillis();
        return eTime - sTime;
    }

    /**
     * get element by random index
     *
     * @return time, ms
     */
    private long getRandomElTime() {
        long sTime = System.currentTimeMillis();
        list.get(randomIndex);
        long eTime = System.currentTimeMillis();
        return eTime - sTime;
    }

    /**
     * set element by random index
     *
     * @return time, ms
     */
    private long setRandomElTime() {
        long sTime = System.currentTimeMillis();
        list.add(randomIndex, randomIndex);
        long eTime = System.currentTimeMillis();
        return eTime - sTime;
    }

    /**
     * remove element by random index
     *
     * @return time, ms
     */
    private long removeRandomElTime() {
        long sTime = System.currentTimeMillis();
        list.remove(randomIndex);
        long eTime = System.currentTimeMillis();
        return eTime - sTime;
    }

    /**
     * Using iterator
     *
     * @return time, ms
     */
    private long getIteratorTime() {
        long sTime = System.currentTimeMillis();
        for (Integer i : list) {
        }
        long eTime = System.currentTimeMillis();
        return eTime - sTime;

    }
}
