package data_structures.chapter10_sorting_n_searching;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class Problem10_7_follow_up {
    public static void main(String[] args) throws FileNotFoundException {
        int result = findMissedInt(filename);
        if (result == -1) {
            System.out.println("There are no missed integers. Return -1");
        } else {
            System.out.println(result);
        }
    }
    /**
     * ORIGINAL SOLUTION:
     * idea 1: split input into blocks. We will store array of integers, where each integer shows how many integers of the input file belong to this block (i.e. range)
     * For example: if input is 1,4,55,199,65 and the ranges are 0-99, 100-199 etc, then we store blocks[0] = 4 (because 1,4,55,65 belong to the range 0-99)
     * What is the rangeSize should be?
     * We have < 1 billion non-negative integers, i.e. < 2^31
     * 10 Mb > 8 Mb = 2^23 bytes = 2^21 integers (since 1 integer requires 4 bytes).
     * So we can blocks.size() = (2^31)/rangeSize <= 2^21
     * rangeSize >= 2^10
     *
     * idea 2: apply idea of bit vector to found block with missing integer
     * bit vector should be also < 10 Mbs. i.e. its length < 2^23 bytes = 2^26 bits
     *
     * To sum up: 2^10 <= rangeSize <= 2^26
     */
    /**
     * NOTE: boolean type in Java may require 4 bytes of memory (as int).
     * That's why we need to use array of bytes as bit vector!
     */
    public static final String filename = "krev/src/data_structures/chapter10_sorting_n_searching/Problem10_7.txt";

    public static int findMissedInt(String filename) throws FileNotFoundException {
        int rangeSize = 1 << 20;    //i.e. 2^17 bytes
        //fill blocks with counters
        int[] blocks = fillBlocks(filename, rangeSize);
        //find block with missing int
        int blockIndex = findBlockWithMissing(blocks, rangeSize);
        if (blockIndex == -1) {
            return -1;
        }

        //create bit vector for the numbers that are in block with blockIndex
        byte[] bitVector = createBitVector(blockIndex, rangeSize, filename);

        //find missing int into bit vector
        int intIntVector = findMissingIntIntoBitVectorKREV(bitVector);
//        int intIntVector = findZero(bitVector);
        if (intIntVector == -1) {
            return -1;
        }

        return rangeSize*blockIndex + intIntVector;
    }

    protected static int[] fillBlocks(String filename, int rangeSize) throws FileNotFoundException {
        int[] blocks = new int[rangeSize];
        Scanner scanner = new Scanner(new FileInputStream(filename));
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            blocks[value / rangeSize]++;
        }
        scanner.close();
        return blocks;
    }

    protected static int findBlockWithMissing(int[] blocks, int rangeSize) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] < rangeSize) {
                return i;
            }
        }
        return -1;
    }

    protected static byte[] createBitVector(int blockIndex, int rangeSize, String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(filename));
        byte[] bitVector = new byte[rangeSize / Byte.SIZE];
        int startIndex = blockIndex * rangeSize;
        int endIndex = startIndex + rangeSize;
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();
            if (startIndex <= value && value < endIndex) {
                int offset = value - startIndex;
                bitVector[offset / Byte.SIZE] |= 1 << offset % Byte.SIZE;
            }
        }
        scanner.close();
        return bitVector;
    }

    protected static int findMissingIntIntoBitVectorKREV(byte[] bitVector) {
        for (int i = 0; i < bitVector.length; i++) {
            //if not all 1s
            //NOTE! as for me it is small optimization to avoid checking the bits where all integers exist (like 11,12,13,14,15,16,17,18)
            if (bitVector[i] != ~0) {
                for (int j = 0; j < Byte.SIZE; j++) {
                    if ((bitVector[i] & (1 << j)) == 0) {
                        return i * Byte.SIZE + j;
                    }
                }
            }
        }
        return -1;
    }

    protected static int findZero(byte[] bitVector) {
        for (int i = 0; i < bitVector.length; i++) {
            if (bitVector[i] != ~0) {   //if not all 1s
                int bitIndex = findZero(bitVector[i]);
                return i * Byte.SIZE + bitIndex;
            }
        }
        return -1;
    }

    protected static int findZero(byte b) {
        for (int i = 0; i < Byte.SIZE; i++) {
            int mask = 1 << i;
            if ((b & mask) == 0) {
                return i;
            }
        }
        return -1;
    }
}
