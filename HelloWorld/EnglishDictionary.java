/*
 * NAME: Jackie Yuan
 */

/**
 * The EnglishDictionary class contains method to create new words within current given words in
 * a string array.
 * @author Shuibenyang Yuan
 * @since ${April 6 2018}
 */
public class EnglishDictionary {
    /**
     * The method to make new words within a string array of words, the words start with z and x
     * will be automatically skipped and be replaced by empty string.
     * @param letters The base-letter words we want to compose.
     * @param n How many base-letters words we want to compose with at first place.
     * @return The string array of new words created by order.
     */
    public static String[] newWords(String[] letters, int n) {
        /** Your code is here */
        // Creating an empty string array to store the new words.
        String[] a = new String[letters.length];
        // Combining words process.
        for (int i = 0; i < letters.length; i++) {
            String b = "";
            int counter = 0;
            for (int j = i; j < letters.length; j++) {
                if (counter <= n) {
                    b = b + letters[j];
                    counter = counter + 1;
                } else {
                    break;
                }
            }
            // if the word starts with z and x, replace it to empty word.
            if (b.charAt(0) == 'z') {
                b = "%empty%";
            } else if (b.charAt(0) == 'x') {
                b = "%empty%";
            }
            // Putting the created words into the string array.
            a[i] = b;
        }
        return a;
    }

    /**
     * Execute the dictionary method above to test the method.
     * @param args
     */
    public static void main(String[]args) {
        String[] test = {"xoxo", "yum", "lol", "ypop"};
        int n = 2;
        String[] output;
        output = newWords(test, n);

        // Should print %empty%, yumlolypop, lolypop, ypop
        System.out.println(java.util.Arrays.toString(output));
    }
}
