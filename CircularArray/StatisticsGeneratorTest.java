/*
 * NAME: Jackie Yuan
 */
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
/**
 *  The class contains test case that JUnit will test method.
 *  @author Shuibenyang Yuan
 *  @since ${April 13 2018}
 */
public class StatisticsGeneratorTest {
    /**
     * Initial the variable.
     */
    private static StatisticsGenerator sg;

    private static double epsilon = 0.0001;
    private static int divisor = 2;

    int[] empty = {};
    int[] single = {7};
    int[] twoElements = {3, 4};
    int[] sequential = {0, 1, 2, 3};
    int[] mixed = {-7, 13, 5, 0, 4, 2, -8};
    int[] blackMirror = {0, 3, 7, -3, 123, 0, 3, 7, -3};

    /**
     * Setup a test before running
     */
    @Before
    public void setup() {
        sg = new StatisticsGenerator();
    }

    /**
     * Test for min method.
     */
    @Test
    public void testMin() {
        assertEquals("Default minimum value is incorrect", Integer.MIN_VALUE, sg.min(empty));
        assertEquals(7, sg.min(single));
        assertEquals(3, sg.min(twoElements));
        assertEquals(0, sg.min(sequential));
        assertEquals(-8, sg.min(mixed));
    }
    /**
     * Test for secondMax method.
     */
    @Test
    public void testSecondMax() {
        assertEquals("The second array could not found second max", Integer.MAX_VALUE, sg
                        .secondMax(empty));
        assertEquals("The second array could not found second max", Integer.MAX_VALUE, sg
                        .secondMax(single));
        assertEquals(3, sg.secondMax(twoElements));
        assertEquals(2, sg.secondMax(sequential));
        assertEquals(5, sg.secondMax(mixed));
        assertEquals(7, sg.secondMax(blackMirror));
    }
    /**
     * Test for median method.
     */
    @Test
    public void testMedian() {
        assertEquals("The second array could not found second max", Integer.MAX_VALUE, sg
                .median(empty), epsilon);
        assertEquals("The second array could not found second max", 7, sg
                .median(single), 0.01);
        assertEquals(3.5, sg.median(twoElements), epsilon);
        assertEquals(1.5, sg.median(sequential), epsilon);
        assertEquals(2.0, sg.median(mixed), epsilon);
        assertEquals(3.0, sg.median(blackMirror), epsilon);
    }

    /**
     * helper function to check rearrange method.
     * @param toCheck
     * @return boolean if rearrange method is correct
     */
    private boolean isProperlyRearranged(int[] toCheck) {
        // Initialize the arrays to check.
        int[] o = sg.rearrange(toCheck);
        int[] a = Arrays.copyOf(toCheck, toCheck.length);
        int[] b = Arrays.copyOf(o, o.length);
        int[] c = Arrays.copyOf(toCheck, toCheck.length);
        Arrays.sort(a);
        Arrays.sort(b);
        // Check if two array's data matches.
        if (!Arrays.equals(a, b)) {
            return false;
        }
        int even = 0;
        int even1 = 0;
        for (int i = 0; i < o.length; i++) {
            if (o[i] % divisor == 0) {
                even++;
            } else {
                break;
            }
        }
        for (int j = 0; j < c.length; j++) {
            if (c[j] % divisor == 0) {
                even1++;
            } else {
                continue;
            }
        }
        // Check if two array's even numbers match.
        return even == even1;
    }
    /**
     * Test for rearrange method.
     */
    @Test
    public void testRearrange() {
        int[] mirror = {1, 2, 3, 1, 2};
        assertTrue(isProperlyRearranged(single));
        assertTrue(isProperlyRearranged(twoElements));
        assertTrue(isProperlyRearranged(sequential));
        assertTrue(isProperlyRearranged(mixed));
        assertTrue(isProperlyRearranged(blackMirror));
        assertTrue(isProperlyRearranged(mirror));
    }
    /**
     * Test for brokenMirror method.
     */
    @Test
    public void testBrokenMirror() {
        int[] mirror = {1, 2, 3, 1, 2};
        assertTrue(sg.brokenMirror(blackMirror, 4));
        assertFalse(sg.brokenMirror(blackMirror, 3));
        assertFalse(sg.brokenMirror(blackMirror, 5));
        assertTrue(sg.brokenMirror(mirror, 2));
        assertFalse(sg.brokenMirror(mirror, 1));
        assertFalse(sg.brokenMirror(mirror, 3));

    }
}
