/**
 *  NAME: Shuibenyang Yuan
 *  PID: A14031016
 */
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


import static org.junit.Assert.*;
import java.util.ArrayList;
/**
 *  The class contains the tester of sorts.
 *  @author Shuibenyang Yuan
 *  @since ${April 29 2018}
 */
public class SortsTester {
    private static Sorts st;
    ArrayList<Pair> sorted;
    ArrayList<Pair> reversed;
    ArrayList<Pair> almost;
    ArrayList<Pair> expect;
    ArrayList<Pair> random;


    @Before
    /**
     * To set up conditions.
     */
    public void setUp() throws Exception {
        Pair one = new Pair("one", 1);
        Pair two = new Pair("two", 2);
        Pair three = new Pair("three", 3);
        Pair four = new Pair("four", 4);
        Pair five = new Pair("five", 5);
        Pair six = new Pair("six", 6);
        expect = new ArrayList<Pair>(Arrays.asList(one, two, three, four, five, six));
        sorted = new ArrayList<Pair>(Arrays.asList(one, two, three, four, five, six));
        reversed = new ArrayList<Pair>(Arrays.asList(six, five, four, three, two, one));
        almost = new ArrayList<Pair>(Arrays.asList(one, two, four, three, five, six));
        random = new ArrayList<Pair>(Arrays.asList(four, three, five, one, six, two));

    }

    @Test
    /**
     * To test quick sort.
     */
    public void quickSort() {
        st.quickSort(sorted);
        st.quickSort(reversed);
        st.quickSort(almost);
        st.quickSort(random);
        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), sorted.get(i));
            assertEquals(expect.get(i), reversed.get(i));
            assertEquals(expect.get(i), almost.get(i));
            assertEquals(expect.get(i), random.get(i));
        }


    }

    @Test
    /**
     * To test insertions sort.
     */
    public void insertionSort() {
        st.insertionSort(sorted);
        st.insertionSort(reversed);
        st.insertionSort(almost);
        st.insertionSort(random);
        for (int i = 0; i < expect.size(); i++) {
            assertEquals(expect.get(i), sorted.get(i));
            assertEquals(expect.get(i), reversed.get(i));
            assertEquals(expect.get(i), almost.get(i));
            assertEquals(expect.get(i), random.get(i));
        }
    }
}
