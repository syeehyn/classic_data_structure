/*
 * NAME: Shuibenyang Yuan
 * PID: A14031016
 */
import java.util.LinkedList;
/**
 * This class contains the Hash sort.
 *
 * @author Shuibenyang Yuan
 * @Since {May 4 2018}
 */
public class HashSort {
    /**
     * The sort method
     * @param input The array to be sorted.
     * @param min The min value in the array.
     * @param max The max value in the array.
     * @return The sorted array.
     */

    public static int[] sort(int[] input, int min, int max) {
        HashTable toStore = new HashTable(input.length, min, max);
        for (int i = 0; i < input.length; i++) {
            toStore.insert(input[i]);
        }

        int counter = 0;
        for (int i = 0; i < toStore.getLength(); i++) {
            if (!toStore.table[i].isEmpty()) {
                for (int j = 0; j < toStore.table[i].size(); j++) {
                    input[counter] = toStore.table[i].get(j);
                    counter++;
                }
            }
        }
        return input;
    }

    /**
     * The hash table implement.
     */
    protected static class HashTable {
        private int nelems;  //Number of element stored in the hash table
        private LinkedList<Integer>[] table;
        private int size;
        private int max;
        private int min;
        //You are allowed to add more :)
        //Assigning default setting and magic number.
        public static final int DOUBLEFACTOR = 2;

        /**
         * Constructor for hash table
         *
         * @param size Initial size of the hash table
         */
        public HashTable(int size, int min, int max) {
            this.size = size;
            this.max = max;
            this.min = min;
            int range = this.max - this.min + 1;
            int bucketSize = (range + this.size - 1) / this.size;
            //Initialize
            if (this.size == 0) {
                throw new NullPointerException("the size should be positive");
            }
            this.table = new LinkedList[this.size];
            for (int i = 0; i < table.length; i++) {
                table[i] = new LinkedList<Integer>();
            }
            this.nelems = 0;
        }


        /**
         * method to insert value into the hash table.
         *
         * @param value the value of Integer to insert.
         * @return true if it is successful inserted, false if not.
         */
        public boolean insert(Integer value) {

            //checking if the inserting value is null.
            if (value == null) {
                throw new NullPointerException("The value is null");
            }
            //checking if the value is already in the hash table.
            this.nelems++;
            //checking if rehash necessary before executing insert.
            if (rehashCondition()) {
                this.rehash();
            }
            //executing insert.
            if (this.table[hashFunction(value)].isEmpty()) {
                this.table[hashFunction(value)].add(value);
            } else {
                if (this.table[hashFunction(value)].size() == 1) {
                    if (value < this.table[hashFunction(value)].get(0)) {
                        this.table[hashFunction(value)].add(0, value);
                    } else {
                        this.table[hashFunction(value)].add(1, value);
                    }
                } else {
                    for (int i = 1; i < this.table[hashFunction(value)].size(); i++) {
                        if (value < this.table[hashFunction(value)].get(i)) {
                            this.table[hashFunction(value)].add(i - 1, value);
                            break;
                        }
                        if (value < this.table[hashFunction(value)].get(i) && value > this
                                .table[hashFunction(value)].get(i - 1)) {
                            this.table[hashFunction(value)].add(i, value);
                            break;
                        }
                        if (value > this.table[hashFunction(value)].get(this.table[hashFunction
                                (value)].size() - 1)) {
                            this.table[hashFunction(value)].add(value);
                            break;
                        }
                    }
                }
            }

            return true;
        }

        /**
         * method to delete value from a hash table.
         *
         * @param value value to delete
         * @return true if the value is successfully deleted, false otherwise.
         */
        public boolean delete(Integer value) {
            //checking if the inserting value is null.
            if (value == null) {
                throw new NullPointerException("The value is null");
            }
            //executing deleting if the value is in the hash table.
            if (this.contains(value)) {
                this.table[hashFunction(value)].remove(value);
                this.nelems--;
                return true;
            } else {
                return false;
            }
        }

        /**
         * the method to check if the value is in the hash table.
         *
         * @param value value to look up
         * @return true if the value is in the hash table, false otherwise.
         */
        public boolean contains(Integer value) {
            //checking if the inserting value is null.
            if (value == null) {
                throw new NullPointerException("The value is null");
            }
            return this.table[hashFunction(value)].contains(value);
        }

        /**
         * method to visualize the hash table.
         */
        public void printTable() {
            for (int i = 0; i < this.getLength(); i++) {
                String toPrint = "";
                if (this.table[i].isEmpty()) {
                    toPrint = "";
                } else {
                    toPrint = ": " + this.table[i].get(0);
                    for (int j = 1; j < this.table[i].size(); j++) {
                        toPrint = toPrint + ", " + this.table[i].get(j);
                    }
                }
                toPrint = i + toPrint;
                System.out.println(toPrint);
            }
        }
        /**
         * method to find the how many elements in the hash table.
         *
         * @return size of hash table.
         */
        public int getSize() {
            return this.nelems;
        }
        /**
         * method to find the size of hash table.
         *
         * @return size of hash table.
         */
        private int getLength() {
            return this.table.length;
        }
        public LinkedList<Integer> getData(int key) {
            return this.table[key];
        }
        /**
         * helper method to check if rehash necessary before inserting.
         *
         * @return true if need to rehash, false otherwise.
         */
        private boolean rehashCondition() {
            double toCompare = (double) this.getSize() / (double) this.getLength();
            return toCompare > 1;
        }

        /**
         * helper method to rehash the hash table.
         */
        private void rehash() {
            // initializing a new linked list array with double size.
            this.size = this.size * DOUBLEFACTOR;
            LinkedList<Integer>[] temp = new LinkedList[this.size * DOUBLEFACTOR];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = new LinkedList<>();
            }
            // inserting value from the old hash table to the array.
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.table[i].size(); j++) {
                    temp[hashFunction(this.table[i].get(j))].add(this.table[i].get(j));
                }
            }
            // replace the data in old hash table with new array.
            this.table = temp;
        }


        /**
         * the hash function to execute hash index.
         *
         * @param key  the integer to check.
         * @return the index to process the hash.
         */
        private int hashFunction(int key) {
            int range = this.max - this.min + 1;
            int bucketSize = (range + this.size - 1) / this.size;
            int hashVal = (key - this.min) / bucketSize;
            return hashVal;
        }

    }
}
