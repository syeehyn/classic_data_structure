/*
 * NAME: Jackie Yuan
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
/**
 * Test for Binary Search Tree.
 * The tree holds Node objects that have key,value structure.
 */

public class BSTreeTester {

    private static BSTree test;
    private static BSTree solution;

    @Before
    public void setUp() {
        test = new BSTree();
        solution = new BSTree();
    }

    /**
     * example test
     */
    @Test
    public void exampleTest() {
        test = new BSTree<>();

        test.insert(5, "A");
        test.insert(3, "B");
        test.insert(10, "C");
        test.insert(8, "D");

        test.remove(10); //This will cause your tree to change in structure

        //Create a BST of the correct structure
        solution = new BSTree<>();
        solution.insert(5, "A");

        solution.insert(3, "B");
        solution.insert(8, "D");

        //Use the treeCompare function to check whether your AVL rotated correctly
        assertTrue(solution.treeCompare(test));

    }

    /**
     * my own test.
     */
    @Test
    public void myTest() {
        BSTree<String> bst = new BSTree<>();
        bst.insert(13, "A");
        bst.insert(10, "A");
        bst.insert(24, "A");
        bst.insert(7, "A");
        bst.insert(20, "A");
        bst.insert(30, "A");
        bst.insert(15, "A");
        bst.insert(25, "A");
        bst.insert(36, "A");

        bst.remove(20);
    }

    /**
     * test for getroot.
     */
    @Test
    public void testGetRoot() {
        BSTree<String> bst = new BSTree<>();
        bst.insert(13, "A");
        bst.insert(10, "B");
        bst.insert(24, "C");
        bst.insert(7, "D");
        bst.insert(20, "E");
        bst.insert(30, "F");
        bst.insert(15, "G");
        bst.insert(25, "H");
        bst.insert(36, "I");
        assertTrue(13 == bst.getRoot().getKey());
        bst.remove(13);
        assertTrue(10 == bst.getRoot().getKey());

    }

    /**
     * test for get size.
     */
    @Test
    public void testGetSize() {
        BSTree<String> bst = new BSTree<>();
        bst.insert(13, "A");
        bst.insert(10, "B");
        bst.insert(24, "C");
        bst.insert(7, "D");
        bst.insert(20, "E");
        bst.insert(30, "F");
        bst.insert(15, "G");
        bst.insert(25, "H");
        bst.insert(36, "I");
        assertTrue(9 == bst.getSize());
        bst.remove(10);
        assertTrue(8 == bst.getSize());
        bst.insert(13, "A");
        assertTrue(8 == bst.getSize());
    }

    /**
     * test for contains kay.
     */
    @Test
    public void testContainsKey() {
        BSTree<String> bst = new BSTree<>();
        bst.insert(13, "A");
        bst.insert(10, "B");
        bst.insert(24, "C");
        assertTrue(bst.containsKey(13));
        assertTrue(bst.containsKey(10));
        assertTrue(bst.containsKey(24));
        assertFalse(bst.containsKey(11));
    }

    /**
     * test for test getdata.
     */
    @Test
    public void testGetData() {
        test.insert(1, "kk");
        test.insert(1, 'l');
        assertEquals("kk", test.getData(1).get(0));
        assertEquals('l', test.getData(1).get(1));
        test.insert(0, 'k');
        assertEquals('k', test.getData(0).get(0));
        test.insert(9, "peace");
        assertEquals("peace", test.getData(9).get(0));
    }

    /**
     * test for find pair.
     */
    @Test
    public void testFindPair() {
        test.insert(1, "kk");
        test.insert(1, 'l');
        assertTrue(test.findPair(1, 'l'));
        assertTrue(test.findPair(1, "kk"));
        test.insert(0, 'k');
        assertTrue(test.findPair(0, 'k'));
        assertFalse(test.findPair(0, 'l'));
        test.insert(9, "peace");
        assertTrue(test.findPair(9, "peace"));

    }

    /**
     * test for insert.
     */
    @Test
    public void testInsert() {
        test.insert(10, "first");
        assertEquals(1, test.getSize());
        test.insert(10, "second");
        assertEquals(1, test.getSize());
        test.insert(9, "nine");
        assertEquals(2, test.getSize());
        test.insert(12, "twelve");
        assertEquals(3, test.getSize());
        test.insert(11, "eleven");
        assertEquals(4, test.getSize());
        test.insert(15, "fifteen");
        assertEquals(5, test.getSize());
    }
    /**
     * test null pointer exception
     */
    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        test.containsKey(null);
        test.getData(null);
        test.insert(null, "1");
        test.insert(1, null);
        test.insert(null, null);
    }
    /**
     * test no such file exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElementException() {
        test.getData(1);
        test.insert(1, 'k');
        test.getData(2);
        test.remove(1);
        test.getData(1);
        test.remove(100);
        test.remove(20);
    }

    /**
     * test remove method.
     */
    @Test
    public void removeTest() {
        test = new BSTree<>();

        test.insert(5, "A");
        test.insert(3, "B");
        test.insert(10, "C");
        test.insert(8, "D");
        test.insert(9, "E");

        test.remove(10); //This will cause your tree to change in structure

        //Create a BST of the correct structure
        solution = new BSTree<>();
        solution.insert(5, "A");

        solution.insert(3, "B");
        solution.insert(8, "D");
        solution.insert(9, "E");


        assertTrue(solution.treeCompare(test));

    }
    @Test
    public void completetreeTest() {
        test = new BSTree<>();
        solution = new BSTree<>();
        test.insert(2, "A");
        test.insert(1, "A");
        test.insert(3, "A");
        assertTrue(test.isCompleteBT());
        assertTrue(solution.isCompleteBT());
    }
}
