package data_structures.chapter5_bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * p.128
 * 5.3 Flip Bit to Win:
 * You have an integer and you can flip exactly one bit from a 13 to a 1.
 * Write code to find the length of the longest sequence of ls you could create.
 * EXAMPLE
 * Input: 1775 (or: 1110111101111)
 * Output: 8
 * Hints: #159, #226, #314, #352
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem5_3 {
    public static void main(String[] args) {
        int n = 1775;
        System.out.println("n = " + n + ", binary = " + Integer.toBinaryString(n));
        int result = longestSequenceOptimized(n);
        System.out.println("result = " + result);
    }

    /**
     * ORIGINAL OPTIMIZED SOLUTION - START
     * time ~ O(b), space ~ O(1), where b - amount of bytes of n number
     */
    public static int longestSequenceOptimized(int n) {
        if (n == -1) return Integer.BYTES * 8;  //return 111...11

        int previousLength = 0;
        int currentLength = 0;
        int maxLength = 1;  //because n has at least one '1' symbol
        while(n != 0) {
            //check right byte
            if ((n & 1) == 1) {     //current bit is 1
                currentLength++;
            } else if ((n & 1) == 0) {  //current bit is 0
                if ((n & 2) == 0) {     //check whether the next byte is 0
                    previousLength = 0;
                } else {
                    previousLength = currentLength;
                }
                currentLength = 0;
            }
            maxLength = Math.max(maxLength, previousLength + currentLength + 1);

            n >>>= 1;   //to check the next byte
        }
        return maxLength;
    }

    /**
     * ORIGINAL OPTIMIZED SOLUTION - END
     */

    /**
     * ORIGINAL NON-OPTIMIZED SOLUTION - START
     * time ~ O(b), space ~ O(b), where b - amount of bytes of n number
     */
    public static int longestSequence(int n) {
        if (n == -1) return Integer.BYTES * 8;
        List<Integer> sequences = getSequences(n);
        return findLongestSequencePair(sequences);
    }

    /**
     * KREVSKY's PART
     */
    protected static int findLongestSequencePair(List<Integer> sequences) {
        int maxSeqLen = 0;
        for (int i = 0; i < sequences.size(); i += 2) {
            int zeroSeq = sequences.get(i);
            int leftSeq = i-1 > 0 ? sequences.get(i-1) : 0;
            int rightSeq = i+1 < sequences.size() ? sequences.get(i+1) : 0;
            if (zeroSeq == 1) {
                maxSeqLen = Math.max(maxSeqLen, leftSeq + rightSeq + 1);
            } else if (zeroSeq > 1) {
                maxSeqLen = Math.max(maxSeqLen, 1 + Math.max(leftSeq, rightSeq));
            } else {
                maxSeqLen = Math.max(maxSeqLen, rightSeq);
            }
        }
        return maxSeqLen;
    }

    /**
     * ORIGINAL:
     * target: to get array like [0(0), 4(1), 1(0), ..]
     * were X(Y) - X - amount of Y
     * ВНМАНИЕ! мы ОБЯЗАТЕЛЬНО должны сеттить число нулей в младших разрядах,
     * даже если оно равно нулю!
     * т.е. для 11011101111 будет [0(0), 4(1), 1(0), 3(1), 1(0), 2(1), 21(0)]
     * <p>
     * time ~ O(b), where b - amount of bits
     */
    protected static List<Integer> getSequences(int n) {
        List<Integer> sequences = new ArrayList<>();

        int searchingFor = 0;
        int counter = 0;

        for (int i = 0; i < Integer.BYTES * 8; i++) {   //BYTES = 4, i.e. i < 32
            int rightByte = n & 1;
            if (rightByte != searchingFor) {
                sequences.add(counter);
                searchingFor = rightByte;  //flip 1 to 0 or 0 to 1
                counter = 0;
            }
            counter++;
            n >>>= 1;   //to check the next byte of n
        }
        sequences.add(counter);

        return sequences;
    }
    /**
     * ORIGINAL NON-OPTIMIZED SOLUTION - END
     */
}
