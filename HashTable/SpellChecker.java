/*
 * NAME: Jackie Yuan
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class contains the implement of SpellChecker.
 *
 * @author Shuibenyang Yuan
 * @Since {May 4 2018}
 */
public class SpellChecker {
    private static final int DEFAULTSIZE = 128;
    private static final int PIVOT = 2;
    public static void main(String [] args) {
        String longdict = args[0];
        String input = args[1];

        SpellChecker one = new SpellChecker(input, longdict);
    }
    /**
     * Helper method to read file into array list.
     * @param toRead The directory to read file.
     * @return The array list stores the files.
     */
    private static ArrayList readFile(String toRead) {
        ArrayList toReturn = new ArrayList();
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File(toRead));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc2.hasNext()) {
            toReturn.add(sc2.next());
        }
        return toReturn;
    }

    /**
     * Helper method to store dictionary into a hash table.
     * @param toRead The directory of dictionary.
     * @return The hash table stores the dictionary.
     */
    private static HashTable readFileh(String toRead) {
        HashTable toReturn = new HashTable(DEFAULTSIZE);
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File(toRead));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc2.hasNext()) {
            toReturn.insert(sc2.next());
        }
        return toReturn;
    }

    /**
     * Constructor to print the spell Checker.
     * @param input The input to be tested.
     * @param dictionary The dictionary to be referred.
     */
    public SpellChecker(String input, String dictionary) {
        ArrayList in = readFile(input);
        HashTable dict = readFileh(dictionary);
        for (int i = 0; i < in.size(); i++) {
            String toRead = in.get(i) + ": ";
            ArrayList toReturn = new ArrayList();
            toReturn = spell((String) in.get(i), dict);
            if (toReturn.isEmpty()) {
                toRead = toRead + " not found";
            } else {
                toRead = toRead + toReturn.get(0);
                for (int j = 1; j < toReturn.size(); j++) {
                    toRead = toRead + ", " + toReturn.get(j);
                }
            }
            System.out.println(toRead);
        }

    }

    /**
     * The method to store the possible words of suggestions.
     * @param toCheck The words to checked
     * @param dictionary The hash table dictionary to be referred.
     * @return The array list of possible words
     */
    private static ArrayList spell(String toCheck, HashTable dictionary) {
        ArrayList toReturn = new ArrayList();
        String word = toCheck.toLowerCase();
        // if the input is found or empty.
        if (dictionary.contains(word) || word.length() == 0) {
            toReturn.add("ok");
            return toReturn;
        }
        // suggestion of misplacing a letter.
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String checking = word.substring(0, i) + c + word.substring(i + 1);
                if (dictionary.contains(checking) && !toReturn.contains(checking)) {
                    toReturn.add(checking);
                }
            }
        }
        // suggestion of having a letter.
        for (int i = 0; i < word.length(); i++) {
            String checking = word.substring(0, i) + word.substring(i + 1);
            if (dictionary.contains(checking) && !toReturn.contains(checking)) {
                toReturn.add(checking);
            }
        }
        // suggestion of missing a letter.
        for (int i = 0; i < word.length() + 1; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String checking = word.substring(0, i) + c + word.substring(i);
                if (dictionary.contains(checking) && !toReturn.contains(checking)) {
                    toReturn.add(checking);
                }
            }
        }
        // suggestion of transposing two letters.
        for (int i = 0; i < word.length() - 1; i++) {
            String checking = word.substring(0, i) + word.substring(i + 1, i + PIVOT) + toCheck
                    .substring(i, i + 1) + toCheck.substring(i + PIVOT);
            // magic number and logic.
            if (dictionary.contains(checking) && !toReturn.contains(checking)) {
                toReturn.add(checking);
            }
        }
        // suggestion of two words combine.
        for (int i = 0; i < word.length(); i++) {
            String checking1 = word.substring(0, i);
            String checking2 = word.substring(i);
            String toAdd = checking1 + " " + checking2;
            if (dictionary.contains(checking1) && dictionary.contains(checking2) && !toReturn
                    .contains(toAdd)) {
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }
}

/*
1.  a wrong letter ("pod"),
In general, you have (26*n) possibilities, where n is the length of the word.
2.  an inserted letter ("pogl"),
In general, you have (26*(n+1)) possibilities.
3.  a deleted letter ("po"),
In general, you have n possibilities, where n is the length of the word
4.  a pair of adjacent transposed letters ("plo").
In general, you have (n-1) possibilities.
5.  every pair of strings that can be made by inserting a space into the word.
In general, you have (n-1) pairs of words; make sure to check separately
in the dictionary for each word in the pair.

 */
