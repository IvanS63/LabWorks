package com.mypackage.lab3_5.list;

import com.mypackage.lab3_5.TypeChangeFunction;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Cycle double linked list realization
 */
public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T> {
    /**
     * Number of elements in list
     */
    private int size;
    /**
     * Pointer to the head of linked list
     */
    private Node head;
    /**
     * Changes to the list
     */
    private int modCount;


    public DoubleLinkedList() {
        size = 0;
        modCount = 0;
        head = new Node();
        head.next = head;
        head.previous = head;
    }

    public int getSize() {
        return size;
    }

    /**
     * Returns LinkedList node by set index
     *
     * @param index
     * @return Node
     */
    public Node getElementByIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Incorrect index! List size is " + size);
        } else {
            int i = 0;
            Node temp = head.next;
            while (i < index) {
                temp = temp.next;
                i++;
            }
            return temp;
        }
    }

    /**
     * Add element at the end of the list
     *
     * @param value
     */
    public void addElement(T value) {
        Node newNode = new Node(value);
        head.previous.next = newNode;
        newNode.previous = head.previous;
        newNode.next = head;
        head.previous = newNode;
        size++;
        modCount++;
    }

    /**
     * Insert element with set value at set position
     *
     * @param index
     * @param value
     */
    public void addElementByIndex(int index, T value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Attempt to add value on " + index + " position while list size is " + size);
        } else if (index == size) {
            addElement(value);
        } else {
            Node temp = getElementByIndex(index);
            Node newNode = new Node(value);
            newNode.next = temp;
            newNode.previous = temp.previous;
            temp.previous.next = newNode;
            temp.previous = newNode;
            size++;
            modCount++;
        }
    }

    /**
     * Remove element at set position
     *
     * @param index
     */
    public void removeElementByIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Attempt to add value on " + index + " position while list size is " + size);

        } else {
            Node temp = getElementByIndex(index);
            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
            size--;
            modCount++;
        }
    }

    private Node merge(Node first, Node second, int length) {
        Node result = first.previous;
        int right = 0;
        for (int i = 0; i < length; i++) {
            if (second.value != null) {
                if (first.value.compareTo(second.value) <= 0) {
                    if (first.next == second) break;
                    first = first.next;
                } else {
                    if (right == (length + 1) / 2) break;
                    if (second == result) result = result.previous;
                    Node nextSecond = second.next;
                    second.previous.next = second.next;
                    second.next.previous = second.previous;
                    second.previous = first.previous;
                    first.previous.next = second;
                    second.next = first;
                    first.previous = second;
                    second = nextSecond;
                    right++;
                }
            }
        }
        return result.next;
    }

    private Node mergeSort(Node first, int length) {
        if (length > 1) {
            Node second = first;
            for (int i = 0; i < length / 2; i++) {
                second = second.next;
            }
            first = mergeSort(first, length / 2);
            second = mergeSort(second, (length + 1) / 2);
            return merge(first, second, length);
        } else return first;
    }

    /**
     * Merge sort algorithm for CycleDoubleLinkedList
     */
    public void doMergeSort() {
        this.head.next = mergeSort(this.head.next, size);
        modCount++;
    }

    /**
     * Convert list elements into the new type
     *
     * @return converted to the specified type list
     */
    public <OUT extends Comparable<OUT>> DoubleLinkedList<OUT> map(TypeChangeFunction<T, OUT> function) {
        DoubleLinkedList<OUT> resultList = new DoubleLinkedList<OUT>();
        Node node = head;
        while (node.next != head) {
            node = node.next;
            resultList.addElement(function.convert(node.value));
        }
        return resultList;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();

    }

    /**
     * LinkedList Node
     */
    private class Node {
        private Node next;
        private Node previous;
        private T value;

        public Node() {
        }


        public Node(T value) {
            this.value = value;
        }

        public Node(Node next, Node prev, T value) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }


    }

    /**
     * TwoWay iterator realization
     */
    public class LinkedListIterator implements Iterator<T> {
        private Node currentNode;
        private int expModCount = modCount;

        public LinkedListIterator() {
            currentNode = head;
        }

        /**
         * Check whether list has been modified while using iterator
         */
        private void checkForModification() {
            if (expModCount != modCount) {
                throw new ConcurrentModificationException("List structure has been changed while using iterator!");
            }
        }

        @Override
        public boolean hasNext() {
            checkForModification();
            return currentNode.next != head;
        }

        @Override
        public T next() {
            checkForModification();
            T temp = currentNode.next.value;
            currentNode = currentNode.next;
            return temp;
        }


        public boolean hasPrev() {
            checkForModification();
            return currentNode.previous != head;
        }

        public T prev() {
            checkForModification();
            T temp = currentNode.previous.value;
            currentNode = currentNode.previous;
            return temp;
        }

        @Override
        public void remove() {

        }
    }
}
