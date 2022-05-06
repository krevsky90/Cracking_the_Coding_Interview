package data_structures.chapter5_bit_manipulation;

public class Problem5_7 {
    public static void main(String[] args) {
        int n = Integer.parseInt("1101001", 2);
        System.out.println("before = " + Integer.toBinaryString(n));
        int p1 = pairwiseSwapKrevsky(n);
        int p2 = pairwiseSwap(n);
        System.out.println("result2 = " + Integer.toBinaryString(p2));
//        System.out.println("after = " + Integer.toBinaryString(p));
    }

    /**
     * KREVSKY SOLUTION
     */
    public static int pairwiseSwapKrevsky(int n) {
        int c1 = 0;
        int c2 = 0;
        int i = 0;
        for (int n1 = n; n1 != 0; n1 >>>= 1) {
            if (i % 2 == 1) {
                c2 += (n1 & 1) * Math.pow(2, i);
            } else {
                c1 += (n1 & 1) * Math.pow(2, i);
            }
            i++;
        }

        System.out.println("i = " + i);
        //store leftmost '1' bit of c1 if the length of c1 is odd (for example, i = 7)
        int oddDelta = 0;
        if (i % 2 == 1) {
            oddDelta = 1 << (i-1);
            System.out.println("oddDelta = " + Integer.toBinaryString(oddDelta));
            c1 -= oddDelta;
        }
        System.out.println("c1 = " + Integer.toBinaryString(c1));
        System.out.println("c2 = " + Integer.toBinaryString(c2));

        c1 <<= 1;
        c2 >>>= 1;
        int c = c1 | c2 + 2*oddDelta;
        System.out.println("result = " + Integer.toBinaryString(c));

        return 0;
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static int pairwiseSwap(int x) {
        return ( ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1) );
    }
}
