/*
 * NAME: Jackie Yuan
 */

import java.util.AbstractList;
import java.util.Arrays;

/**
 * Doubly-Linked List Implementation
 *
 * @author Shuibenyang Yuan
 * @since {April 21 2018}
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            //Constructor for Node.
            data = element;
            next = null;
            prev = null;

        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            // Another constructor for Node.
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;

        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            // Set data into the Node.
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            // Get the element from the Node.
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            // Setting up the next Node.
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            // Access the next Node.
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            // Setting up the previous Node.
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            // Get access to the previous Node.
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes.
         */
        public void remove() {
            // Remove this Node from the list.
            this.prev.next = this.next;
            this.next.prev = this.prev;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        // Creates a new, empty doubly-linked list.
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
    }

    /**
     * Add an element to the end of the list.
     *
     * @param element data to be added.
     * @return whether or not the element was added.
     * @throws NullPointerException if data received is null.
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //Implementation for throwing exceptions followed by
        if (element == null) {
            throw new NullPointerException("The data recieved is null");
            //implementation of adding the new data
        } else {
            Node toStore = this.tail.getPrev();
            Node toAdd = new Node(element, tail, toStore);
            toStore.setNext(toAdd);
            this.tail.setPrev(toAdd);
            this.nelems++;
            return true;
        }
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index   Where in the list to execute this operation.
     * @param element element data to be added.
     * @throws IndexOutOfBoundsException if the index is not appropriate.
     * @throws NullPointerException      if data received is null.
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {

        //Implementation for throwing exceptions followed by
        if (this.size() < index) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size());
        }
        if (element == null) {
            throw new NullPointerException("The data recieved is null");
        }
        //implementation of adding the new data
        Node toAdd = new Node(element, getNth(index), getNth(index).getPrev());
        toAdd.getPrev().setNext(toAdd);
        toAdd.getNext().setPrev(toAdd);
        this.nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element the element to check if it is in the list.
     * @return whether the element is in the list.
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        Node toStart = this.head;
        // looking for the element until it found it.
        while (toStart != this.tail) {
            if (data.equals(toStart.getElement())) {
                return true;
            } else {
                toStart = toStart.getNext();
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index to the data want to get.
     * @return the data in the index
     * @throws IndexOutOfBoundsException if the index is not appropriate..
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        boolean outOfBoundsCondition = (index < 0 || index >= this.size());
        if (outOfBoundsCondition) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size());
        } else {
            return this.getNth(index).getElement();
        }

    }

    /**
     * Helper method to get the Nth node in our list.
     *
     * @param index the index to the data want to get.
     * @return the Node in the index.
     */
    private Node getNth(int index) {
        // iterating to get to the nth node.
        Node toReturn = head.getNext();
        for (int i = 0; i < index; i++) {
            toReturn = toReturn.getNext();
        }
        return toReturn;
    }

    /**
     * Determine if the list empty.
     *
     * @return whether the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * remove the element from position index in the list
     *
     * @param index the index of the data to remove
     * @return the removed data.
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        // throw out of bound exception.
        if (this.size() <= index || index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size());
        } else {
            // deleting item.
            Node toRemove = getNth(index);
            toRemove.remove();
            this.nelems--;
            return toRemove.getElement();
        }
    }

    /**
     * Sets the value of an element at a certain index in the list.
     *
     * @param index   the index of data to be set.
     * @param element the data to be set.
     * @return the original data in the index.
     * @throws IndexOutOfBoundsException if the index is not appropriate.
     * @throws NullPointerException      if data received is null.
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        // throwing the exceptions.
        if (this.size() <= index | index < 0) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size());
        } else if (element == null) {
            throw new NullPointerException("The data set is null");
        } else {
            // Setting the element in the index.
            T toReturn = getNth(index).getElement();
            getNth(index).setElement(element);
            return toReturn;
        }
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return how many elements in the list.
     */
    @Override
    public int size() {
        /* {Alternate method}
        Node to_measure = this.head.getNext();
        int counter = 0;
        while (to_measure != this.tail) {
            counter++;
            to_measure = to_measure.getNext();
        }

        return counter;
        */
        return this.nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     *
     * @param index     where to add the list in the original list.
     * @param otherList the list to want to add to the original list.
     * @return the added list of two list,
     * @throws IndexOutOfBoundsException if the index is not appropriate.
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        // index error.

        if (this.size() < index) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + this.size());
        }
        if (otherList.size() != 0) {
            Node current = this.getNth(index);
            Node toAdd = otherList.head.getNext();
            Node toAddTail = otherList.getNth(otherList.size() - 1);
            if (index == this.size()) {
                current = this.tail;
            }
            toAdd.setPrev(current.getPrev());
            toAddTail.setNext(current);
            current.getPrev().setNext(toAdd);
            current.setPrev(toAddTail);
            this.nelems = this.nelems + otherList.nelems;

        }
    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * @param subsequence the subset to find in the original list.
     * @return the array of index where the subset starts.
     */
    public int[] match(DoublyLinkedList<T> subsequence) {
        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();
        if (subsequence.size() != 0 && this.size() >= subsequence.size()) {
            T[] checked = (T[]) new Object[subsequence.size()];
            T[] toCheck = (T[]) new Object[subsequence.size()];

            // convert subsequence to array
            Node checking = subsequence.head.getNext();
            for (int i = 0; i < subsequence.size(); i++) {
                checked[i] = checking.getElement();
                checking = checking.getNext();
            }

            // checking process
            Node cur = this.head.getNext();
            for (int i = 0; i < this.size() - subsequence.size() + 1; i++) {
                Node next = cur;
                int count = 0;
                while (count < subsequence.size()) {
                    toCheck[count] = next.getElement();
                    count = count + 1;
                    next = next.getNext();
                }
                if (Arrays.equals(checked, toCheck)) {
                    indices.add(i);
                }
                cur = cur.getNext();
            }
        }
        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }
}







