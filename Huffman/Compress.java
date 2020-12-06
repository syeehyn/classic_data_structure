/*
 * NAME: Jackie Yuan
 */

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Compress the first given file to the second given file using Huffman coding
 * @author Shuibenyang Yuan
 * @date May 20 2018
 */
public class Compress {

    private static final int EXP_ARG = 2; // number of expected arguments
    private static final int BYTE_BITS = 8; // number of bits in a byte
    private static final int NUM_CHAR = 256; // alphabet size of extended ASCII

    /**
     * Main method that drives the program. The first command line argument should be
     * the path of the file to be compressed. The second should be the path of the file
     * that's compressed to.
     *
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // Check if the number of arguments is correct
        if (args.length != EXP_ARG) {
            System.out.println("Invalid number of arguments.\n"
                    + "Usage: ./compress <infile outfile>.\n");
            return;
        }

        // read all the bytes from the given file and make it to a byte array
        byte[] input = Files.readAllBytes(Paths.get(args[0]));
        // count the frequency of each byte in an array, pass it as input to buildTree()
        int[] freq = new int[NUM_CHAR];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = 0;
        }
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        HCTree atree = new HCTree();
        atree.buildTree(freq);


        FileOutputStream file = new FileOutputStream(args[1]);
        DataOutputStream out  = new DataOutputStream(file);
        BitOutputStream bitOut = new BitOutputStream(out);


        /* Suggestion: first using out.writeInt() to write the number of byte to outFile,
           so that during uncompressing we know when to stop. */

        if (input.length == 0) {
            out.close();
            file.close();
            return;
        }
        out.writeInt(input.length);
        // EC2: only single char in input file case
        if (atree.getRoot().isLeaf()) {
            out.writeInt(input.length);
            out.writeByte(input[0]);
            out.close();
            file.close();
            return;
        }


        // first encode the HCTree as "header", then encode all the byte in input array
        //bitOut.writeBit(input.length);
        atree.encodeHCTree(atree.getRoot(), bitOut);

        for (int i = 0; i < input.length; i++) {
            atree.encode(input[i], bitOut);
        }

        // There might be several bits in the bitOut that we haven't written, so flush it first.

        bitOut.flush();
        out.close();
        file.close();
    }
}
