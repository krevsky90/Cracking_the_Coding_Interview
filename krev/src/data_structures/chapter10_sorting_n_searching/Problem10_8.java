package data_structures.chapter10_sorting_n_searching;

import java.util.BitSet;

/**
 * p.163
 * 10.8 Find Duplicates:
 * You have an array with all the numbers from 1 to N, where N is at most 32,000.
 * The array may have duplicate entries and you do not know what N is.
 * With only 4 kilobytes of memory available, how would you print all duplicate elements in the array?
 * Hints: #289, #315
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_8 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 6, 2, 9, 7, 15, 3, 78, 5, 46, 6, 76};
        checkDuplicates(arr);
        System.out.println("---------");
        printDups(arr);
    }

    /**
     * KREVSKY SOLUTION:
     * since 4 Kb > 32000 bits, we can create bit vector with such length and set 1 to i-th bit if number i belongs to the array.
     * If we have already set i-th bit to 1, then we have found duplicate number and we should print number i.
     */
    public static final int vectorSize = 4096;

    public static void printDups(int[] arr) {
        byte[] v = new byte[vectorSize];
        for (int i = 0; i < arr.length; i++) {
            if ((v[arr[i] / 8] & (1 << (arr[i] % 8))) > 0) {
                System.out.println(arr[i]);
            } else {
                v[arr[i] / 8] |= 1 << (arr[i] % 8);
            }
        }
    }

    /**
     * ORIGINAL SOLUTION:
     */
    public static void checkDuplicates(int[] array) {
        BitSet bs = new BitSet(32000);
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            int num0 = num - 1; //bitSet starts at 0, numbers start at 1
            if (bs.get(num0)) {
                System.out.println(num);
            } else {
                bs.set(num0);
            }
        }
    }

    static class BitSet {
        int[] bitSet;

        public BitSet(int size) {
            bitSet = new int[(size >> 5) + 1]; // divide by 32
        }

        boolean get(int pos) {
            int wordNumber = (pos >> 5); // divide by 32
            int bitNumber = (pos & 0x1F); // mod 32
            return (bitSet[wordNumber] & (1 << bitNumber)) != 0;
        }

        void set(int pos) {
            int wordNumber = (pos >> 5); //divide by 32
            int bitNumber = (pos & 0x1F); // mod 32
            bitSet[wordNumber] |= 1 << bitNumber;
        }
    }
}
