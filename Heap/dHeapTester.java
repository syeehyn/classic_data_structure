/*
 * NAME: Jackie Yuan
 */
import org.junit.Test;

import java.util.NoSuchElementException;
import static org.junit.Assert.*;

/**
 * The class contains the tester of dheap class.
 * @author Shuibenyang Yuan
 * @since 5.27.2018
 */

public class dHeapTester {
    // Initialize the test condition.
    dHeap test = new dHeap(2, 7, false);
    dHeap test1 = new dHeap(3, 7, true);
    dHeap test2 = new dHeap(2, 7, true);

    /**
     * Initialize the test condition.
     * @throws Exception
     */
    @org.junit.Before
    public void setUp() throws Exception {
        test.add(5);
        test.add(7);
        test.add(10);
        test.add(18);
        test.add(14);
        test.add(11);
        test.add(21);
        test.add(27);
        test.add(6);
        test.add(6);
        test.add(6);
        test.add(6);
        test.add(6);
        test.add(6);
        test.add(6);

        test1.add(95);
        test1.add(64);
        test1.add(66);
        test1.add(58);
        test1.add(56);
        test1.add(43);
        test1.add(54);
        test1.add(39);
        test1.add(23);
        test1.add(20);
        test1.add(35);
        test1.add(26);
        test1.add(99);

    }

    /**
     * Test for size method.
     */
    @org.junit.Test
    public void size() {
        assertEquals(15, test.size());
        assertEquals(13, test1.size());
        test.remove();
        test.remove();
        assertEquals(13, test.size());
        test.clear();
        assertEquals(0, test.size());

        for (int i = 0; i < 10; i++) {
            test1.remove();
        }
        assertEquals(3, test1.size());

    }

    /**
     * Test for add method.
     */
    @org.junit.Test
    public void add() {
        for (int i = 0; i < 100; i++) {
            test.add(i);
        }
        assertEquals(0, test.element());
        assertEquals(115, test.size());

        for (int i = 0; i < 1001; i++) {
            test1.add(i);
        }
        assertEquals(1014, test1.size());
        assertEquals(1000, test1.remove());
        assertEquals(999, test1.element());
        assertEquals(1013, test1.size());

        for (int i = 0; i < 10; i++) {
            test2.add(i);
        }
        test2.remove();
        test2.remove();
        test2.remove();
        test2.remove();

    }

    /**
     * Test for remove method.
     */
    @org.junit.Test
    public void remove() {
        test.clear();
        test1.clear();
        for (int i = 0; i < 100; i++) {
            test.add(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, test.remove());
        }
        assertEquals(0, test.size());

        for (int i = 0; i < 100; i++) {
            test1.add(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(99 - i, test1.remove());
        }


    }

    /**
     * Test for clear method.
     */
    @org.junit.Test
    public void clear() {
        for (int i = 0; i < 1000; i++) {
            test.add(i);
        }
        test.clear();
        assertEquals(0, test.size());

        for (int i = 0; i < 1000; i++) {
            test1.add(i);
        }
        test1.clear();
        assertEquals(0, test1.size());
    }

    /**
     * Test for element method.
     */
    @org.junit.Test
    public void element() {
        test.clear();
        test1.clear();
        for (int i = 99; i > 0; i--) {
            test1.add(i);
            assertEquals(99, test1.element());
        }
        for (int i = 0; i < 99; i++) {
            test.add(i);
            assertEquals(0, test.element());
        }
    }
    @org.junit.Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        test1.add(null);
        test.add(null);

    }

    /**
     * Test for Exception.
     */
    @org.junit.Test(expected = NoSuchElementException.class)
    public void testNoSuchElementException() {
        test.clear();
        test1.clear();
        test.remove();
        test1.remove();
    }
    /**
     * Test for EC1.
     */
    @org.junit.Test
    public void findGreaterThanK() {
        assertEquals(8, test1.findGreaterThanK(40).size());
    }
    /**
     * Test for EC2.
     */
    @org.junit.Test
    public void findSum() {
        int[] a = {60, 5, 27, 3, 12, 9, 14};
        assertEquals(26, test.findSum(a, 3, 6));
    }
}
