package com.mypackage.lab2.algorithms;

/**
 * Abstract class for search and sort algorithms
 */
abstract class SortSearch {
    protected int[] array;


    abstract void doSort(int leftIndex, int rightIndex);

    abstract int doSearch(int value, int leftIndex, int rightIndex);

    public void printArray() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }
}
