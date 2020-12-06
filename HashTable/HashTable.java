/*
 * NAME: Jackie Yuan
 */
import java.util.LinkedList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class contains the implement of Hash table.
 *
 * @author Shuibenyang Yuan
 * @Since {May 4 2018}
 */
public class HashTable implements IHashTable {
    private int nelems;  //Number of element stored in the hash table
    private int expand;  //Number of times that the table has been expanded
    private int collision;  //Number of collisions since last expansion
    private String statsFileName;     //FilePath for the file to write statistics upon every
    // rehash
    private boolean printStats = false;   //Boolean to decide whether to write statistics to
    // file or not after rehashing

    //You are allowed to add more :)
    //Assigning default setting and magic number.
    public static final int DESIMAL = 2;
    public static final int DEFAULTSIZE = 32;
    public static final int NUMLETTERS = 27;
    public static final int DOUBLEFACTOR = 2;
    public static final double LOADFACTORLIMIT = Double.valueOf(2d / 3d);
    private LinkedList<String>[] table;

    /**
     * Constructor for hash table
     *
     * @param size Initial size of the hash table
     */
    public HashTable(int size) {

        //Initialize
        if (size == 0) {
            throw new NullPointerException("the size should be positive");
        }
        this.table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        this.nelems = 0;
        this.collision = 0;
        this.expand = 1;
    }

    /**
     * Constructor for hash table
     *
     * @param size     Initial size of the hash table
     * @param fileName File path to write statistics
     */
    public HashTable(int size, String fileName) {

        // Set printStats to true and statsFileName to fileName
        if (size == 0) {
            throw new NullPointerException("the size should be positive");
        }
        this.table = new LinkedList[size];
        this.statsFileName = fileName;
        for (int i = 0; i < this.table.length; i++) {
            table[i] = new LinkedList<>();
        }
        this.nelems = 0;
        this.collision = 0;
        this.expand = 1;
        this.printStats = true;
    }

    /**
     * method to insert value into the hash table.
     *
     * @param value the value of string to insert.
     * @return true if it is successful inserted, false if not.
     */
    @Override
    public boolean insert(String value) {

        //checking if the inserting value is null.
        if (value == null) {
            throw new NullPointerException("The value is null");
        }
        //checking if the value is already in the hash table.
        if (this.contains(value)) {
            return false;
        }
        this.nelems++;
        //checking if rehash necessary before executing insert.
        if (rehashCondition()) {
            this.rehash();
        }
        //checking if collision happens.
        if (!this.table[hashFunction(value, this.getLength())].isEmpty()) {
            this.collision++;
        }
        //executing insert.
        this.table[hashFunction(value, this.getLength())].add(value);
        return true;
    }

    /**
     * method to delete value from a hash table.
     *
     * @param value value to delete
     * @return true if the value is successfully deleted, false otherwise.
     */
    @Override
    public boolean delete(String value) {
        //checking if the inserting value is null.
        if (value == null) {
            throw new NullPointerException("The value is null");
        }
        //executing deleting if the value is in the hash table.
        if (this.contains(value)) {
            this.table[hashFunction(value, this.getLength())].remove(value);
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
    @Override
    public boolean contains(String value) {
        //checking if the inserting value is null.
        if (value == null) {
            throw new NullPointerException("The value is null");
        }
        return this.table[hashFunction(value, this.getLength())].contains(value);
    }

    /**
     * method to visualize the hash table.
     */
    @Override
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
    @Override
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

    /**
     * helper method to check if rehash necessary before inserting.
     *
     * @return true if need to rehash, false otherwise.
     */
    private boolean rehashCondition() {
        double toCompare = (double) this.getSize() / (double) this.getLength();
        return toCompare > LOADFACTORLIMIT;
    }

    /**
     * helper method to rehash the hash table.
     */
    private void rehash() {
        // print stats if needed.
        if (this.printStats) {
            printStatics();
        }
        // initializing a new linked list array with double size.
        LinkedList<String>[] temp = new LinkedList[this.getLength() * DOUBLEFACTOR];
        this.collision = 0;
        this.expand++;
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new LinkedList<>();
        }
        // inserting value from the old hash table to the array.
        for (int i = 0; i < this.getLength(); i++) {
            for (int j = 0; j < this.table[i].size(); j++) {
                if (!temp[hashFunction(this.table[i].get(j), temp.length)].isEmpty()) {
                    this.collision++;
                }
                temp[hashFunction(this.table[i].get(j), temp.length)].add(this.table[i].get(j));
            }
        }
        // replace the data in old hash table with new array.
        this.table = temp;
    }

    /**
     * helper method to printStatics before rehash.
     */
    private void printStatics() {
        double loadFactor = (double) (this.getSize() - 1) / (double) this.getLength();
        BigDecimal bd = new BigDecimal(loadFactor).setScale(DESIMAL, RoundingMode.HALF_EVEN);
        String toPint = (this.expand + " resizes, load factor " + bd + ", " + this
                .collision + " collisions, " + this.longestChain() + " longest chain");
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            String content = toPint;

            fw = new FileWriter(this.statsFileName, true);
            bw = new BufferedWriter(fw);
            bw.append(content);

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.newLine();
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    /**
     * helper method to return the length of the longest chain before rehash.
     *
     * @return the length of the longest chain.
     */
    private int longestChain() {
        int[] temp = new int[this.getLength()];
        for (int i = 0; i < this.table.length; i++) {
            temp[i] = this.table[i].size();
        }
        int max = temp[0];
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }

    /**
     * the hash function to execute hash index.
     *
     * @param key  the string to check.
     * @param size the size of the hash table.
     * @return the index to process the hash.
     */
    private int hashFunction(String key, int size) {
        int hashVal = 0;
        for (int j = 0; j < key.length(); j++) {
            int letter = key.charAt(j);
            hashVal = (hashVal * NUMLETTERS + letter) % size;
        }
        return hashVal;
    }

}

