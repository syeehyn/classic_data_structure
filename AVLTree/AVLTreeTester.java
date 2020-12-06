/*
 * NAME: Jackie Yuan
 */
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test for AVL Tree.
 * The tree holds Node objects that have key,value structure.
 */

public class AVLTreeTester {
    /**
     * Example Test
     */
    @Test
    public void exampleTest() {
        AVLTree<String> t = new AVLTree<>();

        t.insert(5, "A");
        t.insert(2, "B");
        t.insert(3, "C"); //Should cause a right rotation
        //t.insert(4, "A");

        //Create a BST(NOT AVL) of the correct structure
        BSTree<String> sol = new BSTree<>();
        sol.insert(3, "C");
        sol.insert(2, "B");
        sol.insert(5, "A");

        //Use the treeCompare function to check whether your AVL rotated correctly
        assertTrue(t.treeCompare(sol));

    }

    /**
     * My test over avl.
     */
    @Test
    public void myTest() {
        AVLTree<String> test = new AVLTree<>();
        test.insert(15, "A");
        test.insert(20, "A");
        test.insert(24, "A");
        test.insert(10, "A");
        test.insert(13, "A");
        test.insert(7, "A");
        test.insert(30, "A");
        test.insert(36, "A");
        test.insert(25, "A");

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

        //assertTrue(test.treeCompare(bst));
    }

    /**
     * My another test over avl.
     */
    @Test
    public void myTest1() {
        //14, 17, 11, 7, 53, 4, 13
        AVLTree<String> test = new AVLTree<>();
        test.insert(14, "A");
        test.insert(17, "A");
        test.insert(11, "A");
        test.insert(7, "A");
        test.insert(53, "A");
        test.insert(4, "A");
        test.insert(13, "A");
        test.insert(12, "A");
        test.insert(8, "A");

        BSTree<String> bst = new BSTree<>();
        bst.insert(14, "A");
        bst.insert(11, "A");
        bst.insert(17, "A");
        bst.insert(7, "A");
        bst.insert(12, "A");
        bst.insert(53, "A");
        bst.insert(4, "A");
        bst.insert(8, "A");
        bst.insert(13, "A");

        assertTrue(test.treeCompare(bst));

    }
}
