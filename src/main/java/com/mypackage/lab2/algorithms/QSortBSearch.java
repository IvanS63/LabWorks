package com.mypackage.lab2.algorithms;

/**
 * Class with realisation QuickSort and BinarySearch algorithms
 */
public class QSortBSearch extends SortSearch {
    @Override
    public void doSort(int firstIndex, int secondIndex) {
        if (array == null) {
            throw new NullPointerException("Array hasn't been set");
        }
        if (firstIndex > secondIndex) {
            throw new IllegalArgumentException("Right index should be greater than left");
        }
        int i = firstIndex;
        int j = secondIndex;
        int midElement = array[firstIndex + (secondIndex - firstIndex) / 2];
        while (i <= j) {
            while (array[i] < midElement) {
                i++;
            }
            while (array[j] > midElement) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (firstIndex < j)
            doSort(firstIndex, j);
        if (i < secondIndex)
            doSort(i, secondIndex);
    }

    @Override
    public int doSearch(int value, int leftIndex, int rightIndex) {
        if (array == null) {
            throw new NullPointerException("Array hasn't been set");
        }
        if (leftIndex > rightIndex) {
            throw new IllegalArgumentException("Right index should be greater than left");
        }
        if (leftIndex > rightIndex)
            return -1;
        int midIndex = (leftIndex + rightIndex) / 2;
        if (array[midIndex] > value)
            return doSearch(value, leftIndex, midIndex - 1);
        else if (array[midIndex] < value)
            return doSearch(value, midIndex + 1, rightIndex);
        else if (leftIndex != midIndex)
            return doSearch(value, leftIndex, midIndex);
        else
            return midIndex;
    }

}
