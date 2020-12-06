/*
 * NAME: Jackie Yuan
 */

import java.util.NoSuchElementException;

/**
 * The class contains the implement of priority queue.
 * @param <T> the data stores in the priority queue.
 * @author Shuibenyang Yuan
 * @since 05.27.2018
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private final int TRIPLE = 3;
    private dHeap<T> heap;

    public MyPriorityQueue(int initialSize) {
        heap = new dHeap<>(TRIPLE, initialSize, true);
    }

    /**
     * Inserts an element into the Priority Queue. The element received cannot
     * be null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        heap.add(element);
        dHeap temp = new dHeap(TRIPLE, this.heap.size(), true);
        while (this.heap.size() != 0) {
            temp.add(this.heap.remove());
        }
        heap = temp;
        return true;
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if
     * the queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is
     * empty.
     */
    public T poll() {
        try {
            return this.heap.remove();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() {
        this.heap.clear();
        return;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null
     * if this queue is empty.
     *
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
        try {
            return this.heap.element();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
