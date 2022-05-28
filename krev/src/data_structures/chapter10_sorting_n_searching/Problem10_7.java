package data_structures.chapter10_sorting_n_searching;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * p.162
 * 10.7 Missing Int:
 * Given an input file with four billion non-negative integers,
 * provide an algorithm to generate an integer that is not contained in the file.
 * Assume you have 1 GB of memory available for this task.
 * FOLLOW UP
 * What if you have only 10MB of memory?
 * Assume that all the values are distinct and we now have no more than one billion non-negative integers.
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_7 {
    public static void main(String[] args) throws FileNotFoundException {
        findMissedInt(filename);
    }
    /**
     * ORIGINAL SOLUTION:
     * idea: bit vector
     * 1) There are ~ 4 billion different int values. If we have 4 non-negative -> we have duplicates
     * 2) 1 Gb of memory ~ 1 billion of bytes ~ 8 billions of bits.
     * It means that if we create bit vector that consumes 1 Gb of memory, it would store all non-negative integers
     * Definition: bit vector is array of bits, where i-th bit = 1 if we have i number in the input (and = 0 if we don't have)
     * 3) go through filled bit vector and search first bit that is 0. Return its position (it equals missed int value)
     */
    /**
     * NOTE: boolean type in Java may require 4 bytes of memory (as int).
     * That's why we need to use array of bytes as bit vector!
     */
    public static final String filename = "krev/src/data_structures/chapter10_sorting_n_searching/Problem10_7.txt";

    public static int findMissedInt(String filename) throws FileNotFoundException {
        long numberOfBits = (long) Integer.MAX_VALUE + 1;
        int vectorSize = (int) (numberOfBits / Byte.SIZE);
        byte[] vector = new byte[vectorSize];
        //read file and fill vector
        Scanner scanner = new Scanner(new FileReader(filename));
        while (scanner.hasNextInt()) {
            int temp = scanner.nextInt();
            /* Finds the corresponding number in the bitfield by using the OR operator to set the nth bit of a byte
            (e.g., 10 would correspond to the 2nd bit of index 2 in the byte array). */
            int byteIndex = temp / Byte.SIZE;
            int bitIndex = 1 << temp % Byte.SIZE;
            vector[byteIndex] |= bitIndex;
        }
        scanner.close();

        //go through filled bit vector and search first bit that is 0. Return its position (it equals missed int value)
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                if ((vector[i] & (1 << j)) == 0) {
                    int result = i * Byte.SIZE + j;
                    System.out.println("Missed integer: " + result);
                    return result;
                }
            }
        }

        System.out.println("There are no missed integers. Return -1");
        return -1;
    }
}