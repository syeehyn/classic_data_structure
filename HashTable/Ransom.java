/*
 * NAME: Jackie Yuan
 */
import java.util.*;
/**
 * This class contains the implement EC1.
 *
 * @author Shuibenyang Yuan
 * @Since {May 4 2018}
 */

public class Ransom {
    Map<String, Integer> magazineMap;
    Map<String, Integer> noteMap;

    /**
     * To store magazine and the ransom into Hashmap.
     * @param mz The magazine input.
     * @param rs The ransom supposed to be.
     */
    public Ransom(String mz, String rs) {
        magazineMap = new HashMap();
        noteMap = new HashMap();

        magazineMap = setMap(mz);
        noteMap = setMap(rs);
    }

    /**
     * The helper method to set element into the hash map.
     * @param value the string input indicates the magazine or ransom.
     * @return The hashmap stores the input.
     */
    public HashMap setMap(String value) {
        HashMap toReturn = new HashMap<String, Integer>();
        if (value == null) {
            return toReturn;
        }

        String[] words = value.split("\\s+");

        for (String word : words) {
            if (!toReturn.containsKey(word)) {
                toReturn.put(word, 1);
            } else {
                Integer current = (Integer)toReturn.get(word);
                if (current == null) current = 0;
                toReturn.put(word, current + 1);
            }
        }
        return toReturn;
    }

    /**
     * The helper method to see if we can make ransom by the magazine.
     * @return true if a ransom can be made, false otherwise.
     */
    public boolean solvehelper() {
        for (String key : noteMap.keySet()) {
            // check if the magazine has the word or not.
            if (!magazineMap.containsKey(key)){
                return false;
            }

            // check if the order consistent.
            Integer magazineCount = magazineMap.get(key);
            Integer noteCount = noteMap.get(key);
            if (magazineCount < noteCount) {
                return false;
            }
        }

        return true;
    }

    /**
     * the main method to print out yes or no.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        scanner.nextLine();

        Ransom s = new Ransom(scanner.nextLine(), scanner.nextLine());
        scanner.close();

        boolean answer = s.solvehelper();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");
    }
}
