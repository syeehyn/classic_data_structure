/**
 *  NAME: Shuibenyang Yuan
 *  PID: A14031016
 */

import java.util.ArrayList;

public class Sorts {
    /**
     * Quik sort implement.
     * @param list waited to be sorted
     * @return sorted list.
     */
    private static int breakpoint = 2;
    public static ArrayList<Pair> quickSort(ArrayList<Pair> list) {
        //Implement quick sort
        // Check for empty list.
        if (list.size() == 0) {
            return list;
        }
        // Initialize the starting condition.
        int low = 0;
        int high = list.size() - 1;
        quickSorthelper(list, low, high);
        return list;
    }


    /**
     * Helper function to do quick sort
     * @param list list to be sorted.
     * @param low the lower bound unsorted area.
     * @param high the upper bound unsorted area.
     */
    private static void quickSorthelper(ArrayList<Pair> list, int low, int high) {
        // Initialize the starting points and pivot.
        int i = low;
        int j = high;
        int mid = low + (high - low) / breakpoint;
        Pair pivot = list.get(mid);
        // swaping to get the smaller value of pivot to one side, and larger to the other.
        while (i <= j) {
            while (list.get(i).getCount() < pivot.getCount()) {
                i++;
            }
            while (list.get(j).getCount() > pivot.getCount()) {
                j--;
            }
            if (i <= j) {
                Pair toStore = list.get(i);
                list.set(i, list.get(j));
                list.set(j, toStore);
                i++;
                j--;
            }
        }
        // recursion to keep swap.
        if (low < j) {
            quickSorthelper(list, low, j);
        }
        if (i < high) {
            quickSorthelper(list, i, high);
        }
    }

    public static ArrayList<Pair> insertionSort(ArrayList<Pair> list) {
        //Implement insertion sort
        // starting from the second element.
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            // if the element is smaller than the previous one, swapping until it is not.
            while (j > 0 && list.get(j).getCount() < list.get(j - 1).getCount()) {
                Pair toStore = list.get(j);
                list.set(j, list.get(j - 1));
                list.set(j - 1, toStore);
                j--;
            }
        }
        return list;
    }
}
