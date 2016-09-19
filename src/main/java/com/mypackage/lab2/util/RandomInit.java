package com.mypackage.lab2.util;

import java.util.Random;

/**
 * Class for random array initialization
 */
public class RandomInit {

    public static int[] initRandomArray(int size) {
        int[] array = new int[size];
        if (size < 1) {
            throw new IllegalArgumentException("Array size should be greater than 1");
        }
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt();
        }
        return array;
    }

    /**
     * Create and fill array with random elements from interval
     *
     * @param size     number of elements
     * @param minValue left border
     * @param maxValue right border
     */
    public static int[] initRandomArray(int size, int minValue, int maxValue) {
        int[] array = new int[size];
        Random random = new Random();
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("Minimum value should be less than maximum value");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Array size should be greater than 1");
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt((maxValue - minValue) + 1) + minValue;
        }
        return array;
    }


}
