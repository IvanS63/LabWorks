package com.mypackage.lab3_5;

import com.mypackage.lab3_5.list.DoubleLinkedList;
import com.mypackage.lab2.util.RandomInit;

public class Test {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> myList = new DoubleLinkedList<Integer>();
        int array[] = RandomInit.initRandomArray(10, -25, 25);
        for (int i = 0; i < array.length; i++) {
            myList.addElement(array[i]);
        }
        System.out.println("Creating List:");
        for (Integer i : myList) System.out.print(i + " ");
        System.out.println();
        System.out.println("Inserting with index=2");
        myList.addElementByIndex(2, -100);
        for (Integer i : myList) System.out.print(i + " ");
        System.out.println();
        System.out.println("Removing element at index=2");
        myList.removeElementByIndex(2);
        for (Integer i : myList) System.out.print(i + " ");
        System.out.println();
        System.out.println("Sorted list:");
        myList.doMergeSort();
        for (Integer i : myList) System.out.print(i + " ");
        System.out.println();
        System.out.println("Test iterator:");
        DoubleLinkedList.LinkedListIterator iterator = myList.new LinkedListIterator();
        System.out.println("Backwards:");
        for (int i = 0; i < myList.getSize(); i++) {
            if (iterator.hasPrev()) {
                System.out.print(iterator.prev() + " ");
            }
        }
        System.out.println();
        System.out.println("Forward:");
        for (int i = 0; i < myList.getSize(); i++) {
            if (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            //myList.addElement(12);//Testing concurrentModException
        }
        System.out.println();
        System.out.println("Testing map function:");
        DoubleLinkedList<Double> convertedList = myList.map(new TypeChangeFunction<Integer, Double>() {
            @Override
            public Double convert(Integer value) {
                return value.doubleValue();
            }
        });
        for (Double i : convertedList) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
