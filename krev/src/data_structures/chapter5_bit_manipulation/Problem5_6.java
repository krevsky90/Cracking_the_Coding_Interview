package data_structures.chapter5_bit_manipulation;

/**
 * p.128
 * 5.6 Conversion:
 * Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
 * EXAMPLE
 * Input: 29 (or: 111131), 15 (or: 131111)
 * Output: 2
 * Hints: #336, #369
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem5_6 {
    /**
     * ORIGINAL SOLUTION:
     * Each 1 in the XOR represents a bit that is different between A and B. Therefore, to check the number of bits
     * that are different between A and B, we simply need shift >> and count the number of bits in A^B that are l.
     */
    public static int bitSwapRequired(int a, int b) {
        int count = 0;
        for (int c = a^b; c != 0; c >>= 1) {
            count += c & 1; //i.e. increment counter only if rightmost bit = 1
        }
        return count;
    }

    /**
     * ORIGINAL SOLUTION:
     * we can continuously flip the least significant bit and count how long it takes c to
     * reach 0. The operation c = c & (c - 1) will clear the least significant bit in c.
     */
    public static int bitSwapRequiredOptimized(int a, int b) {
        int count = 0;
        for (int c = a^b; c != 0; c = c & (c-1)) {
            count++;
        }
        return count;
    }
}
