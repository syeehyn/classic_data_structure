/*
 * NAME: Jackie Yuan
 */
import java.util.Arrays;

/**
 * Gene Splicing CRISPR Simulator
 *
 * @author Shuibenyang Yuan
 * @since {April 21 2018}
 */
public class CRISPR {

    /*Sequences to use/test your CRISPR functions on. Please add more as you test*/
    private static String simpleGenome = "ACATATA";

    private static String sequencedGenome = "AAATTCAAGCGAGGTGATTACAACAAATTTTGCTGATGGTTTAGGCGTA"
            + "CAATCCCCTAAAGAATATAATTAAGAAAATAGCATTCCTTGTCGCCTAGAATTACCTACCGGCGTCCACCATACCTTCG"
            + "ATATTCGCGCCCACTCTCCCATTAGTCGGCACAAGTGGATGTGTTGCGATTGCCCGCTAAGATATTCTAAGGCGTAACG"
            + "CAGATGAATATTCTACAGAGTTGCCGTACGCGTTGAACACTTCACGGATGATAGGAATTTGCGTATAGAGCGGGTCATT"
            + "GAAGGAGATTACACTCGTAGTTAACAACGGGCCCGGCTCTATCAGAACACGAGTGCCTTGAATAACATACTCATCACTA";

    private static String overlappingGuide = "UAU";
    private static String guideRNA = "CUAAUGU";
    private static String splicedGene = "TAGACAT";

    private static String overlappingGuideone = "UGU";
    private static String overlappingGuidetwo = "UA";

    private static String spliceGeneOne = "CAT";


    /**
     * Program Entry, this simply runs
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        /*Should print out ACATATA (unchanged)*/
        System.out.println(spliceDNA(simpleGenome, overlappingGuide, splicedGene));
        System.out.println(spliceDNA(simpleGenome, overlappingGuideone, splicedGene));
        System.out.println(spliceDNA(simpleGenome, overlappingGuidetwo, spliceGeneOne));
        System.out.println(spliceDNA(sequencedGenome, overlappingGuide, splicedGene));

    }

    /**
     *  Simulate gene splicing on a genome using CRISPR
     *
     * @param genomeSequence initial DNA encoding
     * @param guideSequence guideRNA encoding
     * @param splicedSequence target insertion gene encoding
     * @return modified genome
     */
    public static String spliceDNA(String genomeSequence, String guideSequence, String
            splicedSequence) {
        DoublyLinkedList<Character> genome = new DoublyLinkedList<>();
        DoublyLinkedList<Character> guideRna = new DoublyLinkedList<>();

        populateFromDNA(genome, genomeSequence);
        populateDNAFromRNA(guideRna, guideSequence);

        //Implement a splicing algorithm with will add the splicedSequence where
        //appropriate to genome
        DoublyLinkedList<Character> spliced = new DoublyLinkedList<>();
        populateFromDNA(spliced, splicedSequence);
        int[] overlapped = genome.match(guideRna);
        for (int i = 0; i < overlapped.length; i++) {
            overlapped[i] = overlapped[i] + guideRna.size();
        }
        DoublyLinkedList<Integer> storedIndx = new DoublyLinkedList<>();
        recurter(storedIndx, overlapped, guideSequence);

        DoublyLinkedList newList = splicingHelper(storedIndx, genome, spliced);
        return transcribeGeneticCode(newList);
        //return Arrays.toString(overlapped);
    }

    /**
     * Helper function to splice the two list by recursive call.
     * @param storedIndx index linked list to store the index needed to be added.
     * @param genomec initial DNA encoding.
     * @param spliced target insertion gene encoding.
     * @return
     */
    public static DoublyLinkedList<Character> splicingHelper(DoublyLinkedList<Integer> storedIndx,
                                                              DoublyLinkedList<Character>
            genomec, DoublyLinkedList<Character> spliced) {
        if (storedIndx.size() == 0) {
            // if the index list is empty;
            return genomec;
        }
        if (storedIndx.size() == 1) {
            // base case, the last splice process.
            DoublyLinkedList<Character> c = new DoublyLinkedList<>();
            for (int i = 0; i < genomec.size(); i++) {
                c.add(genomec.get(i));
            }
            c.splice(storedIndx.get(0), spliced);
            return c;
        } else {
            // splice the two lists.
            DoublyLinkedList<Character> c = new DoublyLinkedList<>();
            for (int i = 0; i < genomec.size(); i++) {
                c.add(genomec.get(i));
            }
            c.splice(storedIndx.get(0), spliced);
            storedIndx.remove(0);
            // update the index list.
            for (int i = 0; i < storedIndx.size(); i++) {
                int toRenew = storedIndx.get(i) + spliced.size();
                storedIndx.set(i, toRenew);
            }
            // recursive call to ge to the next index.
            return splicingHelper(storedIndx, c, spliced);
        }



    }
    /**
     * helper method to recursive call to eliminate overlapped elements.
     * @param b to store the elements not overlapped.
     * @param a original array.
     * @param guideSequence to determine where is overlapping point.
     */
    public static void recurter(DoublyLinkedList<Integer> b, int[] a, String guideSequence) {
        int restValue = 2;
        int startPoint = 2;
        if (a.length == 1) {
            b.add(a[0]);
        } else {
            if ((a[0] + guideSequence.length()) > a[1]) {
                if (a.length != restValue) {
                    recurter(b, Arrays.copyOfRange(a, startPoint, a.length), guideSequence);
                }
            } else {
                b.add(a[0]);
                recurter(b, Arrays.copyOfRange(a, 1, a.length), guideSequence);
            }
        }
    }

    /**
     * This is a direct encoding of the genetic code from the String to a LinkedList
     * @param dnaList to populate
     * @param dnaString DNA string encoding
     */
    public static void populateFromDNA(DoublyLinkedList<Character> dnaList, String dnaString) {
        //Populate dnaList with the characters in s
        char[] toStore = dnaString.toCharArray();
        for (int i = 0; i < toStore.length; i++) {
            dnaList.add(toStore[i]);
        }
    }

    /**
     * This is an encoding of of the DNA that binds with the RNA
     * Remember that DNA pairs up A-T C-G, and RNA pairs up A-U C-G
     * Thus the guide RNA AUCG would match with the DNA TAGC
     * @param dnaList to populate
     * @param rnaString RNA string encoding
     */
    public static void populateDNAFromRNA(DoublyLinkedList<Character> dnaList, String rnaString) {
        //Populate dnaList with the DNA representation of the RNA Sequence
        char[] rnaArray = rnaString.toCharArray();
        for (int i = 0; i < rnaArray.length; i++) {
            if (rnaArray[i] == 'A') {
                dnaList.add('T');
            } else if (rnaArray[i] == 'U') {
                dnaList.add('A');
            } else if (rnaArray[i] == 'C') {
                dnaList.add('G');
            } else if (rnaArray[i] == 'G') {
                dnaList.add('C');
            }
        }
    }

    /**
     * Recreate the original base sequence that was loaded into the list
     * @param geneticSequence list representation of the
     * @return base sequence of the genetic material
     */
    public static String transcribeGeneticCode(DoublyLinkedList<Character> geneticSequence) {
        String s = "";
        for (char c : geneticSequence) {
            s += c;
        }
        return s;
    }

}
