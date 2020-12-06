/*
 * NAME: Jackie Yuan
 */
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 *  The class contains test case that JUnit will test method in QueueADT.
 *  @author Shuibenyang Yuan
 *  @since ${April 13 2018}
 */
public class QueueADTTest {
    /**
     * Initial the QueueADT test.
     */
    private static QueueADT test;
    @Before
    /**
     *  Initialize the test with a CircularArrayQueue.
     */
    public void setup() {
        test = new CircularArrayQueue();
    }

    @Test
    /**
     *  Test the adding method.
     */
    public void add() {
        test.add(1);
        assertEquals(1, test.peek());
        test.add(2);
        test.remove();
        assertEquals(2, test.peek());
        test.remove();
        assertEquals(0, test.peek());
        assertEquals(0, test.size());
        assertEquals(0, test.peek());
        test.add(1);
        assertEquals(1, test.peek());
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        assertEquals(5, test.size());
        assertEquals(1, test.peek());
        test.add(6);
        assertEquals(1, test.peek());
        assertEquals(6, test.size());
        test.add(7);
        test.add(8);
        test.add(9);
        test.add(10);
        test.add(11);
        assertEquals(11, test.size());
    }

    @Test
    /**
     *  Test if the Queue is empty.
     */
    public void isEmpty() {
        assertTrue(test.isEmpty());
        test.add(1);
        assertFalse(test.isEmpty());
        test.remove();
        assertTrue(test.isEmpty());
        test.remove();
        assertTrue(test.isEmpty());
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(7);
        test.add(8);
        test.add(9);
        test.add(10);
        test.add(11);
        assertFalse(test.isEmpty());
        test.remove();
        assertFalse(test.isEmpty());
        test.clear();
        assertTrue(test.isEmpty());
    }

    @Test
    /**
     *  Test the peek method.
     */
    public void peek() {
        assertEquals(0, test.peek());
        test.add(1);
        assertEquals(1, test.peek());
        test.add(2);
        assertEquals(1, test.peek());
        test.add(3);
        test.add(4);
        test.add(5);
        test.remove();
        assertEquals(2, test.peek());
        test.remove();
        assertEquals(3, test.peek());
        test.remove();
        assertEquals(4, test.peek());
        test.remove();
        assertEquals(5, test.peek());
        test.remove();
        assertEquals("The array is empty.", 0, test.peek());
    }

    @Test
    /**
     *  Test the remove method.
     */
    public void remove() {
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(7);
        test.add(8);
        test.add(9);
        test.add(10);
        test.add(11);
        test.remove();
        assertEquals(2, test.peek());
        test.remove();
        assertEquals(3, test.peek());
        test.remove();
        assertEquals(4, test.peek());
        test.remove();
        assertEquals(5, test.peek());
        test.remove();
        assertEquals(6, test.peek());
        test.remove();
        assertEquals(7, test.peek());
        test.remove();
        assertEquals(8, test.peek());
        test.remove();
        assertEquals(9, test.peek());
        test.remove();
        assertEquals(10, test.peek());
        test.remove();
        assertEquals(11, test.peek());
        test.remove();
        assertEquals("The array is empty.", 0, test.peek());
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        assertEquals(1, test.remove());
        assertEquals(2, test.remove());
        assertEquals(3, test.remove());
        assertEquals(4, test.remove());
        assertEquals(5, test.remove());
        assertEquals(0, test.remove());
    }

    @Test
    /**
     *  Test the clear method.
     */
    public void clear() {
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        test.add(5);
        test.add(6);
        test.add(7);
        test.add(8);
        test.add(9);
        test.add(10);
        test.add(11);
        test.clear();
        assertEquals(0, test.size());
        assertEquals(0, test.peek());
        test.clear();
        assertEquals(0, test.size());
        assertEquals(0, test.peek());
        test.add(1);
        test.clear();
        assertEquals(0, test.size());
        assertEquals(0, test.peek());
    }

    @Test
    /**
     *  Test the size method.
     */
    public void size() {
        assertEquals(0, test.size());
        test.add(1);
        assertEquals(1, test.size());
        test.add(2);
        assertEquals(2, test.size());
        test.add(3);
        assertEquals(3, test.size());
        test.add(4);
        assertEquals(4, test.size());
        test.add(5);
        assertEquals(5, test.size());
        test.add(6);
        assertEquals(6, test.size());
        test.add(7);
        assertEquals(7, test.size());
        test.add(8);
        assertEquals(8, test.size());
        test.add(9);
        assertEquals(9, test.size());
        test.add(10);
        assertEquals(10, test.size());
    }
}
