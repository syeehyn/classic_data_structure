/*
 * NAME: Jackie Yuan
 */
import java.util.Arrays;
/**
 * Implementation of the Queue ADT using a Circular Array
 * @author Shuibenyang Yuan
 * @since {April 14 2018}
 */
public class CircularArrayQueue implements QueueADT {
    //  Declare constants and magic numbers.
    private static final int DEFAULT_SIZE = 5;
    private static final int GROWTH_FACTOR = 2;

    private int[] circularArray;
    private int length;
    private int head;
    private int tail;

    /**
     * Creates a new, empty queue of default capacity
     */
    public CircularArrayQueue() {
        this.circularArray = new int[DEFAULT_SIZE];
        this.head = 0;
        this.tail = 0;
        this.length = 0;
    }

    /**
     * Creates an empty queue of a specified capacity
     *
     * @param capacity the initial amount of space to reserve for the queue
     */
    public CircularArrayQueue(int capacity) {
        this.head = 0;
        this.tail = 0;
        this.length = 0;
        if (capacity < 1) {
            this.circularArray = new int[1];
        } else {
            this.circularArray = new int [capacity];
        }

    }
    /**
     * Adds a new element to the tail of the Queue
     *
     * @param elem integer to add
     * @return void
     */
    public void add(int elem) {
        int j = 0;
        // Empty array condition.
        if (this.length == 0) {
            this.circularArray[this.tail] = elem;
            // When the array is full, double the capacity.
        } else if (this.length == this.circularArray.length) {
            // Setup a new array with double size.
            int[] a = Arrays.copyOf(this.circularArray, this.circularArray.length);
            this.circularArray = new int[this.circularArray.length * GROWTH_FACTOR];
            // Copy the first part.
            for (int i = this.head; i < a.length; i++) {
                this.circularArray[j] = a[i];
                j++;
            }
            // Copy the second part.
            for (int k = 0; k < this.head; k++) {
                this.circularArray[j] = a[k];
                j++;
            }
            // Reset the pointers.
            this.head = 0;
            this.tail = this.length;
            this.circularArray[this.tail] = elem;
            // When the array is not fall.
        } else {
            // When the tail is at the end, add the element from the beginning.
            if (this.tail == this.circularArray.length - 1) {
                this.tail = 0;
                this.circularArray[this.tail] = elem;
                // When the tail is not at the end, add the element at the next.
            } else {
                this.tail++;
                this.circularArray[this.tail] = elem;
            }
        }
        this.length++;
    }

    /**
     * Determines if the Queue is empty
     *
     * @return true if the number of elements in the Queue is 0
     */
    public boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * Returns the head of the Queue
     *
     * @return head of the Queue, or 0 if the Queue is empty
     */
    public int peek() {
        // Edge condition when the queue is empty, return 0.
        if (this.length == 0) {
            return 0;
            // Normal condition, return the head element.
        } else {
            return this.circularArray[this.head];
        }
    }

    /**
     * Returns and removes the element at the head of the Queue
     *
     * @return head of the queue, or 0 if the Queue is empty
     */
    public int remove() {
        // When the queue is empty, remove nothing and return 0.
        if (this.length == 0) {
            return 0;
            // When the queue is not empty, remove the head element and return the value.
        } else {
            int toreturn = this.circularArray[this.head];
            // If the removing element is at the end, set the head pointer at the beginning.
            if (this.head == this.circularArray.length - 1) {
                this.head = 0;
                // If the removing element is not at the end, set the head pointer to the next.
            } else {
                // Eliminating the edge case when the head equals tail pointer.
                if (this.head != this.tail) {
                    this.head++;
                }
            }
            // Reducing the size.
            this.length--;
            return toreturn;
        }
    }

    /**
     * Removes all elements from the Queue
     */
    public void clear() {
        this.circularArray = new int[this.circularArray.length];
        this.head = 0;
        this.tail = 0;
        this.length = 0;
    }

    /**
     * Determines the number of elements in the Queue
     *
     * @return number of elements in the Queue
     */
    public int size() {
        return this.length;
    }
}
