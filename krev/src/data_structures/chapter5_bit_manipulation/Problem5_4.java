package data_structures.chapter5_bit_manipulation;

/**
 * p.128
 * 5.4 Next Number:
 * Given a positive integer, print the next smallest and the next largest number
 * that have the same number of 1 bits in their binary representation.
 * Hints: #147, #175, #242, #312, #339, #358, #375, #390
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) n > 0, n is integer
 */
public class Problem5_4 {
    /**
     * По сути надо найти наименьшее число, к-ое больше n
     * и наибольшее число, к-ое меньше n
     */
    public static void main(String[] args) {
        int nToTestNext = Integer.parseInt("11011001111100", 2);
        int nToTestPrev = Integer.parseInt("10011110000011", 2);
        System.out.println("next: " + nToTestNext);
        System.out.println("prev: " + nToTestPrev);
//        int next = getNext(nToTestNext);
        int prev = getPrev(nToTestPrev);
    }

    /**
     * KREVSKY/ORIGINAL SOLUTION
     * Idea:
     * 1. If we flip a zero to a one, we must flip a one to a zero.
     * 2. When we do that, the number will be bigger if and only if the zero-to-one bit was to the left of the one-to-zero bit
     * 3. We want to make the number bigger, but not unnecessarily bigger.
     * Therefore, we need to flip the rightmost zero which has ones on the right of it.
     */
    public static int getNext(int n) {
        System.out.println("getNext(" + Integer.toBinaryString(n) + ")");
        if (n <= 0) return -1;

        int c = n;
        int p = 0;
        while ((c & 1) == 0) {
            p++;
            c >>>= 1;
        }

        int c1 = 0;
        while ((c & 1) == 1) {
            c1++;
            p++;
            c >>>= 1;
        }

        // Error: if n == 11 .. 1100 ... 00,
        // then there is no bigger number with the same number of 1s
        if (p == 32) {
            return -1;
        }

        int set1IthMask = 1 << p;   //0..010..0
        System.out.println("p = " + p + ", set1IthMask = " + Integer.toBinaryString(set1IthMask));
        n = n | set1IthMask;
        System.out.println(Integer.toBinaryString(n));

        int cleanFirstIthBitsMask = -1 << p;    //1..10..0
        System.out.println("p = " + p + ", cleanFirstIthBitsMask = " + Integer.toBinaryString(cleanFirstIthBitsMask));
        n = n & cleanFirstIthBitsMask;
        System.out.println(Integer.toBinaryString(n));

        int setC1Minus1OnesMask = (1 << (c1 - 1)) - 1;  // 0..01..1
        System.out.println("c1 = " + c1 + ", setKMinus1Ones = " + Integer.toBinaryString(setC1Minus1OnesMask));

        n = n | setC1Minus1OnesMask;
        System.out.println(Integer.toBinaryString(n));

        return n;
    }

    /**
     * KREVSKY/ORIGINAL SOLUTION:
     * 1. Compute c0 and c1. Note that c1 is the number of trailing ones,
     * and c0 is the size of the block of zeros immediately to the left of the trailing ones.
     * 2. Flip the rightmost non-trailing one to a zero. This will be at position p = c1 + c0.
     * 3. Clear all bits to the right of bit p.
     * 4. Insert c1 + 1 ones immediately to the right of position p.
     *
     * NOTE: 2 and 3 can be merged!
     */
    public static int getPrev(int n) {
        System.out.println("getPrev(" + Integer.toBinaryString(n) + ")");
        if (n <= 0) return -1;    //it covers the case n = -1 -> n_binary = 11..1

        int c1 = 0; //amount of trailing (the rightmost) 1s if exists
        int c0 = 0; //amount of the rightmost block of 0s if exists
        int n_copy = n;
        while ((n_copy & 1) == 1) {
            c1++;
            n_copy >>= 1;
        }

        while ((n_copy & 1) == 0 && n_copy != 0) {
            c0++;
            n_copy >>= 1;
        }

        int p = c0 + c1;    //position of rightmost non-trailing 1
        // 1) set p-th bit to 0
        int mask1 = ~(1 << p);    //11..101..1
        System.out.println("p = " + p + ", mask1 = " + Integer.toBinaryString(mask1));
        n = n & mask1;

        // 2) set all bits that are right from p-bit to zero
        int mask2 = -1 << p;    //11..100..0
        System.out.println("p = " + p + ", mask2 = " + Integer.toBinaryString(mask2));
        n = n & mask2;

        //NOTE: we can merge 1 and 2 by using mask -1 << (p+1)

        // 3) set the bits [c0-1, p-1] to 1
        int mask31 = (1 << (c1 + 1)) - 1;     //sequence of (c1+1) ones
        System.out.println("p = " + p + ", mask31 = " + Integer.toBinaryString(mask31));
        int mask3 = mask31 << (c0 - 1);     //move 1s to left
        System.out.println("p = " + p + ", mask3 = " + Integer.toBinaryString(mask3));
        n = n | mask3;
        System.out.println(Integer.toBinaryString(n));

        return n;
    }
}
