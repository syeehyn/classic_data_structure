/*
 * NAME: Jackie Yuan
 */
import java.util.Arrays;
/**
 *  The Startup class contains five methods to an array within certain demands:
 *  Find minimum value, second max value, median, rearrange the array, and determine mirror image.
 *  @author Shuibenyang Yuan
 *  @since ${April 6 2018}
 */
public class Startup {
    // Declare constants and magic numbers.
    private static final int LEN_BOUND = 2;
    private static final int BREAK_POINT = 2;
    private static int first, second;
    private static double mid = Integer.MIN_VALUE;
    private static int length, indx;
    /**
     * The method to find the minimum integer of an int array.
     * @param a Original int array.
     * @return The minimum integer of the array.
     */
    public static int min(int[] a) {
        // Initialize the process by selecting the first integer of the array.
        int min = a[0];
        // If the length of the array is empty, return the JAVA built-in minimum integer.
        if (a.length == 0) {
            return Integer.MIN_VALUE;
        }
        // If the next value is smaller than current value, replace the current value.
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        // After checking all value in the array, return the minimum.
        return min;
    }

    /**
     * The method to find the second maximum integer of an int array.
     * @param a Original int array.
     * @return The second minimum integer of the array.
     */
    public static int secondMax(int[] a) {
        // If the length of array is less than two, there is no second max.
        if (a.length < LEN_BOUND) {
            return Integer.MIN_VALUE;
        }
        // Initialize a max value and a second max value by making them the first and the second
        // of the array.
        if (a[0] > a[1]) {
            first = a[0];
            second = a[1];
        } else {
            first = a[1];
            second = a[0];
        }
        /* Replace the max value if found the number larger than the current max in the array.
         * Replace the second max within the current max if Another larger number is found.
         */
        for (int i = 2; i < a.length; i++) {
            if (a[i] > first) {
                second = first;
                first = a[i];
            } else if (a[i] == first) {
                continue;
            } else if (first == second || a[i] > second) {
                second = a[i];
            } else {
                continue;
            }
        }
        // After checking all numbers in the int array, return the second max value.
        return second;
    }

    /**
     * The method to find the median value among the int array.
     * @param a Original int array.
     * @return The median value (a double) of the int array.
     */
    public static double median(int[] a) {
        length = a.length;
        // Define the indx to the index of median.
        indx = length / BREAK_POINT;
        // If the int array is empty, return the smallest integer.
        if (length == 0) {
            return mid;
        }
        // Sort the array into ascending order.
        int [] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        Arrays.sort(b);
        /* If the length of array is odd, return the mid index of the sorted array.
         * If the length of array is even, return the mean value of the mid index two numbers.
         */
        if (length % BREAK_POINT != 0) {
            mid = b[indx];
        } else if (length % BREAK_POINT == 0) {
            mid = (b[indx - 1] + b[indx]);
            mid = mid / BREAK_POINT;
        }
        // Return the median value.
        return mid;
    }

    /**
     * The method to rearrange an int array to all even number in the front.
     * @param a Original Int array.
     * @return The rearranged int array.
     */
    public static int [] rearrange(int[] a) {
        // Declare constants and magic numbers.
        int even = 2;
        int count = 0;
        int inv = a.length - 1;
        // Creating an new empty int array to store the rearranged array.
        int[] b = new int[a.length];
        /* If the element of array is even, put it into the new array from the beginning.
           If the element of array is odd, put it into the new array from the end.
         */
        for (int i = 0; i < a.length; i++) {
            if (a[i] % even == 0) {
                b[count] = a[i];
                count++;
            } else {
                b[inv] = a[i];
                inv--;
            }
        }
        // return the new created int array.
        return b;
    }

    /**
     * The method to check if the array's first n elements and the array's last n elements are
     * the same.
     * @param a Original int array.
     * @param range The specified range of first and last elements of the array.
     * @return The boolean whether they are the same or not.
     */
    public static boolean brokenMirror(int[] a, int range) {
        // Creating two empty int array.
        int[] b = new int[range];
        int[] c = new int[range];
        int count = 0;
        // Duplicating the elements of the array within range from the start of the original array.
        for (int i = 0; i < range; i++) {
            b[i] = a[i];
        }
        // Duplicating the elements of the array within range from the end of the original array.
        for (int j = range; j > 0; j--) {
            c[count] = a[a.length - j];
            count++;
        }
        // Return if two arrays are the same.
        return Arrays.equals(b, c);
    }

    /**
     * Executing the five methods with given int array a,b,c to test the methods.
     * @param args
     */
    public static void main(String[] args) {
        // Define new int array we want to test.
        int[] a = {0, 1, 2, 3, 4};
        int[] b = {1, 3, 5, 7, 8, 9, 10};
        int[] c = {1, 2, 2, 1, 2, 2, 1};
        int[] d = {10, 10, 10, 10, 9};
        int[] e = {10, 10, 10};
        int[] f = {Integer.MIN_VALUE, 10, Integer.MIN_VALUE};
        int[] g = {10, Integer.MIN_VALUE, Integer.MIN_VALUE};
        // Execute to test method.
        // Test int array a.
        System.out.println(brokenMirror(a, 4));
        System.out.println(min(a));
        System.out.println(secondMax(a));
        System.out.println(median(a));
        System.out.println(Arrays.toString(rearrange(a)));
        // Test int array b.
        System.out.println(brokenMirror(b, 4));
        System.out.println(min(b));
        System.out.println(secondMax(b));
        System.out.println(median(b));
        System.out.println(Arrays.toString(rearrange(b)));
        // Test int array c.
        System.out.println(brokenMirror(c, 7));
        System.out.println(min(c));
        System.out.println(secondMax(c));
        System.out.println(median(c));
        System.out.println(Arrays.toString(rearrange(c)));
        // Test secondMax method's edge case.
        System.out.println(secondMax(d));
        System.out.println(secondMax(e));
        System.out.println(secondMax(f));
        System.out.println(secondMax(g));

    }

}
