package data_structures.chapter5_bit_manipulation;

/**
 * p.127
 * 5.1 Insertion:
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to insert M into N such that M starts at bit j and ends at bit i.
 * You can assume that the bits j through i have enough space to fit all of M.
 * That is, if M = 10011, you can assume that there are at least 5 bits between j and i.
 * You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 * EXAMPLE
 * Input: N = 10000000000, M = 10011, i = 2, j = 6
 * Output: N 10001001100
 * Hints: # 137, #169, #215
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) j - i + 1 >= length of M
 */
public class Problem5_1 {
    public static void main(String[] args) {
        int N = 1024, M = 19, i = 2, j = 6;
        System.out.println("N = " + Integer.toBinaryString(N) + ", M = " + Integer.toBinaryString(M));
        int result = insertion(N, M, i, j);
        System.out.println("result = " + Integer.toBinaryString(result));
    }

    /**
     * Example:
     * Input:  N = 10000000000, M = 10011, i = 2, j = 6
     * Output: N = 10001001100
     * <p>
     * ORIGINAL SOLUTION = KREVSKY SOLUTION:
     * 1. Clear the bits j through i in N
     * 2. Shift M so that it lines up with bits j through i
     * 3. Merge M and N.
     **/
    public static int insertion(int N, int M, int i, int j) throws IllegalArgumentException {
        //validation
        if (j - i + 1 < Integer.toBinaryString(M).length() || N < M) {
            throw new IllegalArgumentException();
        }

        // 1) shift M to the right on i steps
        int shiftedM = M << i;    //1001100
        System.out.println("shiftedM = " + Integer.toBinaryString(shiftedM));

        // 2) set 0 for N from j to i bits
        int maskLeft = -1 << (j + 1);   //1s before position j, then as. left = 1..110000000
        System.out.println("maskLeft = " + Integer.toBinaryString(maskLeft));
        int maskRight = (1 << i) - 1;   //1s after position i. right 0..00011
        System.out.println("maskRight = " + Integer.toBinaryString(maskRight));
        int fullMask = maskLeft | maskRight;
        System.out.println("fullMask = " + Integer.toBinaryString(fullMask));
        int maskedN = N & fullMask;
        System.out.println("maskedN = " + Integer.toBinaryString(maskedN));

        // 3) set i to j bits of maskedN equal to shiftedM
        int result = maskedN | shiftedM;
        return result;
    }
}
