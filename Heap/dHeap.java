/*
 * NAME: Jackie Yuan
 */
import java.util.*;

/**
 * The class contains the implement of dHeap.
 * @param <T> The data stored in the dHeap.
 * @author Shuibenyang Yuan
 * @since 5.27.2018
 */

public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {
    // declare variables and magic numbers.
    private T[] heap; //heap array
    private int d; //branching factor
    private int nelems;
    private boolean isMaxHeap; //boolean to indicate whether heap is max or min
    private final int BINARY = 2;
    private final int DCAPA = 7;
    private final int DOUBLESIZE = 2;
    private final int SECONDCHILD = 2;

    /**
     * Initializes a binary max heap with capacity = 7
     */
    public dHeap() {
        this.nelems = 0;
        this.heap = (T[]) new Comparable[DCAPA];
        this.isMaxHeap = true;
        this.d = BINARY;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    public dHeap(int heapSize) {
        this.nelems = 0;
        this.heap = (T[]) new Comparable[heapSize];
        this.isMaxHeap = true;
        this.d = BINARY;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d The number of child nodes each node in the heap should have.
     * @param heapSize The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap)
            throws IllegalArgumentException {
        this.nelems = 0;
        this.heap = (T[]) new Comparable[heapSize];
        this.isMaxHeap = isMaxHeap;
        this.d = d;
    }

    @Override
    /**
     * method to get size of the dHeap.
     */
    public int size() {
        return this.nelems;

    }

    /**
     * Method to add T data into dHeap.
     * @param data the data to be added.
     * @throws NullPointerException
     */
    @Override
    public void add(T data) throws NullPointerException {
        //TODO
        if (data == null) {
            throw new NullPointerException();
        }
        // is full condition.
        if (isFull()) {
            resize();
        }
        heap[nelems++] = data;
        if (!isMaxHeap) {
            bubbleUp(nelems - 1);
        } else {
            bubbleUpmax(nelems - 1);
        }
    }

    /**
     * method to remove the root of the heap.
     * @return the removed value.
     * @throws NoSuchElementException
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T toRemove = element();
        heap[0] = heap[this.size() - 1];
        this.nelems--;
        if (!isMaxHeap) { // min heap case.
            trickleDown(0);
        } else { // max heap case.
            trickleDownmax(0);
        }
        return toRemove;
    }

    /**
     * method to clear the heap.
     */
    @Override
    public void clear() {
        this.nelems = 0;
    }

    /**
     * method to get the max or min value in the heap.
     * @return the max or min value of the heap.
     */
    @Override
    public T element() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.heap[0];
    }


    /**
     * Helper method to bubble up the element.
     * @param childInd the index of the element to be bubbled up.
     */
    private void bubbleUp(int childInd) {
        T tmp = heap[childInd];
        // Swaping until in the right spot.
        while (childInd > 0 && tmp.compareTo(heap[parent(childInd)]) < 0) {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    /**
     * Helper method to trickledown the element.
     * @param index the index of the element to be trickled down.
     */
    private void trickleDown(int index) {
        int child;
        T tmp = heap[index];
        // Going down until in the right spot.
        while (kthChild(index, 1) < this.size()) {
            child = minChild(index);
            if (heap[child].compareTo(tmp) < 0) {
                heap[index] = heap[child];
            } else {
                break;
            }
            index = child;
        }
        heap[index] = tmp;
    }

    /**
     * the max heap version of bubble up.
     * @param childInd the index of the element to be bubbled up.
     */
    private void bubbleUpmax(int childInd) {
        T tmp = heap[childInd];
        // Swapping until in the right spot.
        while (childInd > 0 && tmp.compareTo(heap[parent(childInd)]) > 0) {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    /**
     * the max heap version of trickle down.
     * @param index the index of the element to be trickled down.
     */
    private void trickleDownmax(int index) {
        int child;
        T tmp = heap[index];
        // going down until in the right spot.
        while (kthChild(index, 1) < this.size()) {
            child = maxChild(index);
            if (heap[child].compareTo(tmp) > 0) {
                heap[index] = heap[child];
            } else {
                break;
            }
            index = child;
        }
        heap[index] = tmp;
    }

    /**
     * Find the minimum child's index.
     * @param index parent's index.
     * @return minimum child's index.
     */
    private int minChild(int index) {
        int bestChild = kthChild(index, 1);
        int k = SECONDCHILD;
        int pos = kthChild(index, k);
        // compare each element to get the minimum child.
        while ((k <= d) && (pos < this.size())) {
            if (heap[pos].compareTo(heap[bestChild]) < 0) {
                bestChild = pos;
            }
            k++;
            pos = kthChild(index, k);
        }
        return bestChild;
    }

    /**
     * Find the max child's index.
     * @param index parent's index.
     * @return max child's index.
     */
    private int maxChild(int index) {
        int bestChild = kthChild(index, 1);
        int k = SECONDCHILD;
        int pos = kthChild(index, k);
        // compare each element to get the maximum child.
        while ((k <= d) && (pos < this.size())) {
            if (heap[pos].compareTo(heap[bestChild]) > 0) {
                bestChild = pos;
            }
            k++;
            pos = kthChild(index, k);
        }
        return bestChild;
    }

    /**
     * helper method to resize the heap by double the size.
     */
    private void resize() {
        T[] temp = this.heap;
        // create a new array, and copy the array to it.
        this.heap = (T[]) new Comparable[temp.length * DOUBLESIZE];
        for (int i = 0; i < temp.length; i++) {
            this.heap[i] = temp[i];
        }
    }

    // deep level helper method.

    /**
     Function to check if heap is empty
     **/
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     Check if heap is full
     **/
    public boolean isFull() {
        return this.size() == heap.length;
    }

    /**
     * Function to  get index parent of index.
     * @param index the index of the child.
     **/
    private int parent(int index) {
        return (index - 1) / d;
    }
    /**
     * Function to get index of k th child of i
     * @param i the i in the heap.
     * @param k the kth element in the heap.
     **/
    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /**
     * method that returns all the occurrences of the values greater
     * than a parameter 'k' in a max-heap.
     * @param k the number to be compared.
     * @return All the numbers greater than k.
     */

    public LinkedList findGreaterThanK(T k) {
        LinkedList<T> toReturn = new LinkedList<>();
        T toAdd;
        try {
            toAdd = this.remove();
        } catch (NoSuchElementException e) {
            return toReturn;
        }
        while (toAdd.compareTo(k) > 0) {
            toReturn.add(toAdd);
            try {
                toAdd = this.remove();
            } catch (NoSuchElementException e) {
                return toReturn;
            }
        }
        return toReturn;
    }

    /**
     * method which takes an array of integers and two numbers num1 and num2, and finds the
     * sum of all elements between given two num1 and num2 smallest elements of array.
     * @param a the array to execute the process.
     * @param num1 the first number of range.
     * @param num2 the second number of range.
     * @return sum of all elements between given two num1 and num2 smallest elements of array.
     */
    public int findSum(int[] a, int num1, int num2) {
        dHeap<Integer> tocalc = new dHeap(BINARY, a.length, false);
        for (int i = 0; i < a.length; i++) {
            tocalc.add(a[i]);
        }
        for (int i = 0; i < num1; i++) {
            tocalc.remove();
        }
        int finalsum = 0;
        for (int i = 0; i < num2 - num1 - 1; i++) {
            finalsum = finalsum + tocalc.remove();
        }
        return finalsum;
    }



}
