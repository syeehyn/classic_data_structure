/*
 * NAME: Jackie Yuan
 */
//import java.io.*;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * The Huffman Coding Tree
 * @author Shuibenyang Yuan
 * @date May 20 2018
 */
public class HCTree {

    private static final int NUM_CHARS = 256; // alphabet size of extended ASCII
    private static final int BYTE_BITS = 8; // number of bits in a byte

    private HCNode root; // the root of HCTree
    private HCNode [] leaves = new HCNode[NUM_CHARS]; // the leaves of HCTree
    // that contain all the symbols


    /**
     * The Huffman Coding Node
     *
     * DO NOT MODIFY THIS CLASS!!!
     */
    public class HCNode implements Comparable<HCNode> {

        private char symbol; // the symbol contained in this HCNode
        private int freq; // the frequency of this symbol
        private HCNode c0, c1, parent; // c0 is the '0' child, c1 is the '1' child

        /**
         * Initialize a HCNode with given parameters
         * @param symbol the symbol contained in this HCNode
         * @param freq the frequency of this symbol
         * @param c0 the '0' child
         * @param c1 the '1' child
         * @param parent the parent of this HCNode
         */
        HCNode(char symbol, int freq, HCNode c0, HCNode c1, HCNode parent) {
            this.symbol = symbol;
            this.freq = freq;
            this.c0 = c0;
            this.c1 = c1;
            this.parent = parent;
        }


        /**
         * Getter for symbol
         * @return the symbol contained in this HCNode
         */
        char getSymbol() {
            return this.symbol;
        }

        /**
         * Setter for symbol
         * @param symbol the given symbol
         */
        void setSymbol(char symbol) {
            this.symbol = symbol;
        }

        /**
         * Getter for freq
         * @return the frequency of this symbol
         */
        int getFreq() {
            return this.freq;
        }

        /**
         * Setter for freq
         * @param freq the given frequency
         */
        void setFreq(int freq) {
            this.freq = freq;
        }

        /**
         * Getter for '0' child of this HCNode
         * @return '0' child of this HCNode
         */
        HCNode getC0() {
            return c0;
        }

        /**
         * Setter for '0' child of this HCNode
         * @param c0 the given '0' child HCNode
         */
        void setC0(HCNode c0) {
            this.c0 = c0;
        }

        /**
         * Getter for '1' child of this HCNode
         * @return '1' child of this HCNode
         */
        HCNode getC1() {
            return c1;
        }

        /**
         * Setter for '1' child of this HCNode
         * @param c1 the given '1' child HCNode
         */
        void setC1(HCNode c1) {
            this.c1 = c1;
        }

        /**
         * Getter for parent of this HCNode
         * @return parent of this HCNode
         */
        HCNode getParent() {
            return parent;
        }

        /**
         * Setter for parent of this HCNode
         * @param parent the given parent HCNode
         */
        void setParent(HCNode parent) {
            this.parent = parent;
        }

        /**
         * Check if the HCNode is leaf
         * @return if it's leaf, return 1. Otherwise, return 0.
         */
        boolean isLeaf() {
            return (c0 == null) && (c1 == null);
        }

        /**
         * Compare the current HCNode to the given HCNode by the freq.
         * If freq of both node are the same, compare the symbol deterministically.
         * This method would be used in Priority Queue to determine the priority of
         * HCNode.
         * @param o the given HCNode
         * @return positive number if current HCNode has higher freq; negative number
         *          if current HCNode has lower freq. If freq of both node are the same,
         *          compare the symbol deterministically.
         */
        public int compareTo(HCNode o) {
            if (this.freq != o.getFreq()) {
                return this.freq - o.getFreq();
            } else {
                return this.symbol - o.getSymbol();
            }
        }
    }

    /**
     * Initialize an empty HCTree
     */
    public HCTree() { }

    /**
     * Getter for the root of HCTree
     * @return the root of HCTree
     */
    public HCNode getRoot() {
        return root;
    }

    /**
     * Setter for the root of HCTree
     * @param root the given root
     */
    public void setRoot(HCNode root) {
        this.root = root;
    }

    /**
     * Build the HCTree from the given frequency array.
     * @param freq the given frequency array.
     */
    public void buildTree(int[] freq) {

        // initialize a priority queue of HCNode
        PriorityQueue<HCNode> pq = new PriorityQueue<>();

        // create all the leaf nodes from the given freq array
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                HCNode toAdd = new HCNode((char) i, freq[i], null, null, null);
                pq.add(toAdd);
                this.leaves[i] = toAdd;
            }
        }
        while (pq.size() > 1) {
            HCNode min = pq.poll();
            HCNode secmin = pq.poll();
            HCNode newNode = new HCNode(min.getSymbol(), min.getFreq()
                    + secmin.getFreq(), min, secmin, null);
            min.setParent(newNode);
            secmin.setParent(newNode);
            pq.add(newNode);
        }
        this.setRoot(pq.poll());
    }
    /**
     * Encode the given one byte symbol and write to the given BitOutputStream
     *
     * @param symbol the given one byte symbol to be encoded
     * @param out the given BitOutputStream to be written
     * @throws IOException
     */
    public void encode(byte symbol, BitOutputStream out) throws IOException {

        /* Hint: To find the encode of given symbol, it's much faster to start from the leaf node
           containing the given symbol to the root, store all the bits in those edge in sequence,
           and then reverse those bits. (Think about which data structure you learned might be
           useful here) In this way, you don't have to start from the root and
           traverse possibly the whole tree to find encode of the given symbol.
         */

        // start from the leaf containing the symbol to the root to get the encoding bits
        HCNode getstart = leaves[symbol];
        Stack code = new Stack();
        while (getstart.getParent() != null) {
            if (getstart.getParent().getC0().equals(getstart)) {
                code.push(0);
            } else {
                code.push(1);
            }
            getstart = getstart.getParent();
        }

        // once you get the encoding bits, use out.writeBit(int i) to write those bits
        // to BitOutputStream
        while (!code.empty()) {
            out.writeBit((int) code.pop());
        }
    }

    /**
     * Decode the bits from BitInputStream and return a byte that represents the symbol
     *
     * @param in the given BitInputStream to be read
     * @return a byte that represents the symbol
     * @throws IOException
     */
    public byte decode(BitInputStream in) throws IOException {

        int nextBit;
        HCNode node = this.root;
        // find the leaf node containing the symbol using bits in BitInputStream

        while (!node.isLeaf()) {
            nextBit = in.readBit();
            if (nextBit == 0) {
                node = node.getC0();
            } else {
                node = node.getC1();
            }
        }


        // Hint: Use in.readBit() to read the next bit in BitInputStream

        // found the leaf node and return the symbol
        return (byte) node.getSymbol();
    }


    /**
     * Encode the whole HCTree. If current node is a leaf node, write bit 1 followed by its symbol. 
     * Else, write 0 and then recursively encode the left subHCTree, and then the right subHCTree
     *
     * @param node the given node
     * @param out the given BitOutputStream to be written
     * @throws IOException
     */
    public void encodeHCTree(HCNode node, BitOutputStream out) throws IOException {

        /* Here is how to write a byte (the symbol in a node) to BitOutputStream
           for (int i = 0; i < BYTE_BITS; i++) {
               int loc = BYTE_BITS - i - 1;
               out.writeBit((node.getSymbol() >> loc) & 1);
           }
        */

        // Encode the structure of the HCTree and write it to the BitOutputStream

        // Hint: read javadoc comment first
        if (node.isLeaf()) {
            out.writeBit(1);
            for (int i = 0; i < BYTE_BITS; i++) {
                int loc = BYTE_BITS - i - 1;
                out.writeBit((node.getSymbol() >> loc) & 1);
            }
        } else {
            out.writeBit(0);
            encodeHCTree(node.getC0(), out);
            encodeHCTree(node.getC1(), out);
        }

    }


    /**
     * Decode the HCTree. If next bit is 1, construct leaf node using followed byte as symbol.
     * Else, keep decoding until we get two nodes, then we construct the subHCTree, using these
     * two nodes as two children of a new parent node.
     *
     * @param in the given BitInputStream to be read
     * @return the HCNode with the decoded symbol
     * @throws IOException
     */
    public HCNode decodeHCTree(BitInputStream in) throws IOException {

        /* Here is how to read a byte and cast it to char (the symbol in a node) from BitInputStream
           for (int i = 0; i < BYTE_BITS; i++) {
                int loc = BYTE_BITS - i - 1;
                symbol = (char) (symbol | (in.readBit() << loc));
           }
        */
        if (in.readBit() == 1) {
            char symbol = '\u0000';
            for (int i = 0; i < BYTE_BITS; i++) {
                int loc = BYTE_BITS - i - 1;
                symbol = (char) (symbol | (in.readBit() << loc));
            }
            leaves[symbol] = new HCNode(symbol, 0, null, null, null);
            return leaves[symbol];
        } else {
            HCNode left = decodeHCTree(in);
            HCNode right = decodeHCTree(in);
            HCNode parent = new HCNode('0', 0, left, right, null);
            left.setParent(parent);
            right.setParent(parent);
            return parent;
        }

        // decode bits from the given BitInputStream and build the HCTree

        /* Hint: read the javadoc comment first. Notice that we only need to build the HCTree
           structure based on the given bits, so the freq of each node doesn't matter here (Although
           it is useful in buildTree() to determine the structure of the tree) */

    }
}
