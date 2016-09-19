package com.mypackage.lab2.algorithms;

/**
 * Class with realisation CocktailSort and InterpolationSearch algorithms
 */
public class CSortISearch extends SortSearch {
    @Override
    public void doSort(int leftIndex, int rightIndex) {
        if (array == null) {
            throw new NullPointerException("Array hasn't been set");
        }
        if (leftIndex > rightIndex) {
            throw new IllegalArgumentException("Right index should be greater than left");
        }
        if (leftIndex < rightIndex) {
            while (leftIndex < rightIndex) {
                for (int i = leftIndex; i < rightIndex; i++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                    }
                }
                rightIndex--;
                for (int i = rightIndex; i > leftIndex; i--) {
                    if (array[i] < array[i - 1]) {
                        int temp = array[i];
                        array[i] = array[i - 1];
                        array[i - 1] = temp;
                    }
                }
                leftIndex++;
            }
        }
    }

    @Override
    public int doSearch(int value, int leftIndex, int rightIndex) {
        if (array == null) {
            throw new NullPointerException("Array hasn't been set");
        }
        if (leftIndex > rightIndex) {
            throw new IllegalArgumentException("Right index should be greater than left");
        }
        int midIndex;
        while (array[rightIndex] != array[leftIndex] && value >= array[leftIndex] && value <= array[rightIndex]) {
            midIndex = leftIndex + ((value - array[leftIndex]) * (rightIndex - leftIndex) / (array[rightIndex] - array[leftIndex]));

            if (array[midIndex] < value)
                leftIndex = midIndex + 1;
            else if (value < array[midIndex])
                rightIndex = midIndex - 1;
            else {
                while (midIndex > 1 && array[midIndex - 1] == value) {
                    midIndex--;
                }
                return midIndex;
            }
        }
        if (value == array[leftIndex])
            return leftIndex;
        else
            return -1;
    }


}
