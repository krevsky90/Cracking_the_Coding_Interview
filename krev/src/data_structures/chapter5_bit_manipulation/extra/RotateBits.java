package data_structures.chapter5_bit_manipulation.extra;
/**
 * https://www.youtube.com/watch?v=uDqUb50Bmvs&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=15
 * Byte by Byte: Rotate Bits
 * <p>
 * Given a number, write a function to rotate the bits (i.e. circular shift)
 * <p>
 * Example:
 * rotate(1111 1111 1111 1111 0000 0000 0000 0000, 8) = 0000 0000 1111 1111 1111 1111 0000 0000
 */
public class RotateBits {
    public static void main(String[] args) {
//        int num = Integer.parseInt("FF", 16);
        int num = -2;// Integer.parseInt("11001110", 2);
        System.out.println(num);
        System.out.println(Integer.toBinaryString(num));

        System.out.println("orig result " + Integer.toBinaryString(rotate(num, 3)));
        System.out.println("KREV result " + Integer.toBinaryString(rotateKREV(num, 3)));
    }
    /**
     * KREVSKY SOLUTION:
     *
     * <p>
     * simple example: (11001110, 3) = (01100111, 2) = (10110011, 1) = (11011001, 0) = 11011001
     * i.e. we should divide the initial number into two parts: 11001, 110 and swap them (as numbers or as strings).
     * Let's do it as numbers
     */
    public static int rotateKREV(int num, int shift) {
        int movedPart = num >>> shift;            //like 00011001
        int rotatedPart = num << (Integer.SIZE - shift);    //like 11000000

        return movedPart + rotatedPart;
    }

    /**
     * ORIGINAL SOLUTION:
     *
     * NOTE! I don't agree with orig solution since it doesn't take into account the case when num < 0 (check the example num = -2)
     */
    public static int rotate(int num, int shift) {
        return (num >> shift) | (num << (Integer.SIZE - shift));
    }

}
