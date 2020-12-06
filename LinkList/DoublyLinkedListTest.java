/*
 * NAME: Jackie Yuan
 */
//import java.lang.reflect.Array;

import java.util.AbstractList;
import java.util.LinkedList;
//import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;

/**
 * Test harness for the DoublyLinkedList class
 *
 * @author Shuibenyang Yuan
 * @since {April 18 2018}
 */
public class DoublyLinkedListTest {

    AbstractList<Character> alphabet;
    AbstractList<Integer> empty;
    AbstractList<Integer> splicing;
    DoublyLinkedList mytest;
    DoublyLinkedList alphabetc;
    DoublyLinkedList splicingc;
    DoublyLinkedList subsequence;

    /**
     * Performs necessary setup before each test is run
     */
    @Before
    public void setup() {
        alphabet = new LinkedList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }
        alphabetc = new DoublyLinkedList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabetc.add(c);
        }


        empty = new LinkedList<>();
        mytest = new DoublyLinkedList<>();
    }

    //TODO your test cases will go here
    @Test
/**
 * To test the add method.
 */
    public void add() {
        empty.add(1);
        empty.add(2);
        empty.add(3);
        mytest.add(1);
        assertEquals(1, mytest.size());
        mytest.add(2);
        assertEquals(2, mytest.size());
        mytest.add(3);
        assertEquals(3, mytest.size());
        assertEquals(1, mytest.get(0));
        assertEquals(2, mytest.get(1));
        assertEquals(3, mytest.get(2));
        mytest.add('c');
        assertEquals('c', mytest.get(3));
    }

    /**
     * To test the add function with index.
     */
    @Test
    public void add1() {
        for (int i = 0; i < 5; i++) {
            empty.add(1);
        }
        empty.add(1, 2);
        assertTrue(2 == empty.get(1));
        empty.add(null);

        for (int i = 0; i < 5; i++) {
            mytest.add(1);
        }
        mytest.add(1, 2);
        assertEquals(2, mytest.get(1));
    }

    /**
     * To test the clear method.
     */
    @Test
    public void clear() {
        empty.add(1);
        empty.add(2);
        empty.add(3);
        empty.clear();
        assertEquals(0, empty.size());
        mytest.add(1);
        mytest.add(2);
        mytest.add(3);
        mytest.clear();
        assertEquals(0, mytest.size());
        assertTrue(mytest.isEmpty());
    }

    /**
     * To test contains method.
     */
    @Test
    public void contains() {
        empty.add(1);
        assertTrue(empty.contains(1));
        assertFalse(empty.contains(2));
        mytest.add('c');
        assertTrue(mytest.contains('c'));
        assertFalse(mytest.contains('v'));
        mytest.clear();
        mytest.add(9999);
        assertTrue(mytest.contains(9999));
    }

    /**
     * To test get method.
     */
    @Test
    public void get() {
        empty.add(1);
        empty.add(2);
        empty.add(3);
        assertTrue(1 == empty.get(0));
        assertTrue(2 == empty.get(1));
        assertTrue(3 == empty.get(2));
        empty.clear();
        for (int i = 0; i < 100; i++) {
            empty.add(i);
            assertTrue(i == (empty.get(empty.size() - 1)));
        }

        mytest.add(1);
        mytest.add(2);
        mytest.add(3);
        assertTrue(1 == (int) mytest.get(0));
        assertTrue(2 == (int) mytest.get(1));
        assertTrue(3 == (int) mytest.get(2));
        empty.clear();
        for (int i = 0; i < 100; i++) {
            mytest.add(i);
            assertTrue(i == (int) (mytest.get(mytest.size() - 1)));
        }
        mytest.clear();
        for (int i = 0; i < 366; i++) {
            mytest.add(i);
            assertEquals(i, mytest.get(i));
        }
    }

    /**
     * To test isEmpty method.
     */

    @Test
    public void isEmpty() {
        assertTrue(empty.isEmpty());
        for (int i = 0; i < 100; i++) {
            empty.add(i);
            assertTrue(i == (empty.get(empty.size() - 1)));
        }
        assertEquals(100, empty.size());
        assertFalse(empty.isEmpty());
        empty.clear();
        assertTrue(empty.isEmpty());
        assertTrue(mytest.isEmpty());
        for (int i = 0; i < 100; i++) {
            mytest.add(i);
            assertEquals(i, mytest.get(mytest.size() - 1));
        }
        assertEquals(100, mytest.size());
        assertFalse(mytest.isEmpty());
        mytest.clear();
        assertTrue(mytest.isEmpty());
    }

    /**
     * To test remove method.
     */
    @Test
    public void remove() {
        assertEquals(0, empty.size());
        assertEquals(0, mytest.size());
        empty.add(1);
        empty.add(2);
        empty.add(3);
        assertEquals(3, empty.size());
        assertTrue(1 == empty.get(0));
        empty.remove(0);
        assertEquals(2, empty.size());
        assertTrue(2 == empty.get(0));
        empty.remove(0);
        assertEquals(1, empty.size());
        assertTrue(3 == empty.get(0));
        empty.remove(0);
        assertEquals(0, empty.size());
        mytest.add(1);
        mytest.add(2);
        mytest.add(3);
        assertEquals(3, mytest.size());
        assertEquals(1, mytest.get(0));
        mytest.remove(0);
        assertEquals(2, mytest.size());
        assertEquals(2, mytest.get(0));
        mytest.remove(0);
        assertEquals(1, mytest.size());
        assertEquals(3, mytest.get(0));
        mytest.remove(0);
        assertEquals(0, mytest.size());

    }

    /**
     * To test set method.
     */
    @Test
    public void set() {
        empty.add(1);
        empty.set(0, null); //Different than double.
        empty.clear();
        for (int i = 0; i < 10; i++) {
            empty.add(i);
        }
        assertTrue(4 == empty.get(4));
        empty.set(4, 100);
        assertTrue(100 == empty.get(4));

        for (int i = 0; i < 10; i++) {
            mytest.add(i);
        }
        assertEquals(4, mytest.get(4));
        mytest.set(4, 100);
        assertEquals(100, mytest.get(4));
    }

    /**
     * To test size method.
     */
    @Test
    public void size() {
        assertEquals(26, alphabet.size());
        assertEquals(26, alphabetc.size());
    }

    /**
     * To test splize method.
     */
    @Test
    public void splice() {
        splicingc = new DoublyLinkedList();
        for (int i = 0; i < 10; i++) {
            mytest.add(i);
        }
        for (int i = 10; i < 20; i++) {
            splicingc.add(i);
        }
        mytest.splice(1, splicingc);
        assertEquals(20, mytest.size());
        for (int j = 10; j < 20; j++) {
            assertEquals(j, mytest.get(j - 9));
        }
        assertEquals(0, mytest.get(0));
        assertEquals(1, mytest.get(11));
        assertEquals(20, mytest.size());
        splicingc.clear();
        mytest.clear();
        mytest.splice(0, splicingc);
        assertTrue(mytest.isEmpty());

        for (int i = 0; i < 10; i++) {
            splicingc.add(i);
        }
        mytest.splice(0, splicingc);
        for (int j = 0; j < 10; j++) {
            assertEquals(j, mytest.get(j));
        }

        splicingc.clear();
        for (int i = 0; i < 10; i++) {
            mytest.add(i);
        }
        mytest.splice(0, splicingc);
        for (int j = 0; j < 10; j++) {
            assertEquals(j, mytest.get(j));
        }
    }

    /**
     * To test match method.
     */
    @Test
    public void match() {
        subsequence = new DoublyLinkedList();
        subsequence.add('a');
        subsequence.add('b');
        subsequence.add('a');
        mytest.add('a');
        mytest.add('c');
        mytest.add('a');
        mytest.add('b');
        mytest.add('a');
        mytest.add('c');
        mytest.add('a');
        mytest.add('b');
        mytest.add('a');
        mytest.match(subsequence);
        int[] firstTest = {2, 6};
        int[] firstCompare = mytest.match(subsequence);
        assertTrue(Arrays.equals(firstCompare, firstTest));

        mytest.clear();
        mytest.add('a');
        mytest.add('c');
        mytest.add('a');
        mytest.add('a');
        mytest.add('b');
        mytest.add('a');
        int[] secondTest = {3};
        int[] secondCompare = mytest.match(subsequence);
        assertTrue(Arrays.equals(secondCompare, secondTest));

        mytest.clear();
        mytest.add('a');
        mytest.add('c');
        mytest.add('a');
        int[] thirdTest = {};
        int[] thirdCompare = mytest.match(subsequence);
        assertTrue(Arrays.equals(thirdCompare, thirdTest));

        mytest.clear();
        int[] fourthTest = {};
        int[] fourthCompare = mytest.match(subsequence);
        assertTrue(Arrays.equals(fourthCompare, fourthTest));

        mytest.clear();
        subsequence.clear();
        mytest.clear();
        mytest.add('a');
        mytest.add('c');
        mytest.add('a');
        int[] fifthTest = {};
        int[] fifthCompare = mytest.match(subsequence);
        assertTrue(Arrays.equals(fifthCompare, fifthTest));


    }

    /**
     * To test throw exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        empty.remove(0);
        empty.add(1, 1);
        empty.get(0);
        empty.set(1, 1);


        mytest.remove(0);
        mytest.add(1, 1);
        mytest.get(0);
        mytest.set(1, 1);
        splicingc = new DoublyLinkedList();
        for (int i = 0; i < 10; i++) {
            mytest.add(i);
        }
        for (int i = 10; i < 20; i++) {
            splicingc.add(i);
        }
        mytest.splice(11, splicingc);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        mytest.add(null);
        mytest.add(1);
        mytest.set(0, null);
        mytest.add(0, null);
    }


}
