/*
 * NAME: Jackie Yuan
 */
public class SunshineMarket {
    // Customer lists
    private static int[] customers1 = {3, 7, 20};
    private static int[] customers2 = {1, 3, 5, 4, 16, 8};
    private static int[] customers3 = {1, 1, 2, 3, 5, 7};

    private static QueueADT customersQueue;
    private static QueueADT[] lines;

    /**
     * This is the program entry where we will run our simulation
     *
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        System.out.println(lines(customers1, 1));
        System.out.println(lines(customers1, 2));
        System.out.println(lines(customers1, 3));
        System.out.println(lines(customers1, 4));
        System.out.println(lines(customers2, 1));
        System.out.println(lines(customers2, 2));
        System.out.println(lines(customers2, 3));
        System.out.println(lines(customers2, 4));
    }

    /**
     * Method to estimate the time of input customers's item, and how many cashiers are available.
     * @param customers
     * @param numberOfLines
     * @return The message indicates the totalTime and emptyQueueTime within certain number of
     * lines.
     */
    public static String lines(int[] customers, int numberOfLines) {
        // Initialize the total time and empty queue time within 0.
        int totalTime = 0;
        int emptyQueueTime = 0;
        // Initialize the customer Queue and the lines of cashier.
        customersQueue = new CircularArrayQueue(customers.length);
        lines = new QueueADT[numberOfLines];
        // Adding the customers into the QueueADT
        for (int i = 0; i < customers.length; i++) {
            customersQueue.add(customers[i]);
        }
        // Converting Curstomers into QueueADT array.
        for (int j = 0; j < numberOfLines; j++) {
            QueueADT aLine = new CircularArrayQueue();
            lines[j] = aLine;
        }
        // Starting the line process.
        for (int k = 0; k < customers.length; k++) {
            // updating the shortest line every time.
            QueueADT sLine = getShortestLine();
            int remove = customersQueue.remove();
            // converting to time unit array adt.
            for (int n = 0; n < remove; n++) {
                sLine.add(1);
            }
            sLine.add(1);
            sLine.add(1);
            // Counting the time from the beginning.
            for (int e = 0; e < lines.length; e++) {
                if (lines[e].size() == 0) {
                    emptyQueueTime++;
                }
                lines[e].remove();
            }
            totalTime++;
        }
        // Counting the time in the checkout process.
        while (!allQueuesEmpty()) {
            for (int e = 0; e < lines.length; e++) {
                if (lines[e].size() == 0) {
                    emptyQueueTime++;
                }
                lines[e].remove();
            }
            totalTime++;
        }





        return "With " + numberOfLines + " lines, the total wait time was "
                + totalTime + " time units, and registers were idle for a total of "
                + emptyQueueTime + " time units.\n";
    }
    /**
     * Helper method to determine the state of all Queues
     *
     * @return true if all Queues are empty
     */
    private static boolean allQueuesEmpty() {
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].size() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to choose the shortest line
     *
     * @return Queue with the shortest line
     */
    private static QueueADT getShortestLine() {
        int minLineIndex = 0;
        // similar to sorting process to find the shortestline.
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].size() < lines[minLineIndex].size()) {
                minLineIndex = i;
            }
        }
        return lines[minLineIndex];
    }
}
