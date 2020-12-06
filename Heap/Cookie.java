/*
 * NAME: Jackie Yuan
 */
import java.util.*;

/**
 * the class contains the implement of Cookie.
 */

public class Cookie {

    public static void main(String[] args) {
        // input environment set up.
        Scanner s = new Scanner(System.in);
        final int cookies = s.nextInt();
        final int K = s.nextInt();
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        // add to the priority queue.
        for (int i = 0; i < cookies; ++i) {
            minHeap.add(s.nextInt());
        }

        System.out.println(getNumOfOperation(minHeap, cookies, K));
        s.close();
    }

    private static int getNumOfOperation(PriorityQueue<Integer> cookieQ, int cookies, int
            K) {
        int count = 0;
        while (cookies >= 2 && cookieQ.peek() < K) {
            int newVal = cookieQ.poll().intValue() + 2 * cookieQ.poll().intValue();
            cookieQ.add(newVal);
            --cookies;
            ++count;
        }
        return (cookieQ.peek() >= K) ? count : -1;
    }
}
