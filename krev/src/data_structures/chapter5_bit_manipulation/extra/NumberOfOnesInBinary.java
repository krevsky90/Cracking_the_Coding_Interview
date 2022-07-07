package data_structures.chapter5_bit_manipulation.extra;

/**
 * https://www.youtube.com/watch?v=8iWIpkFgZ64&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=4
 * Byte by Byte: Number of Ones in Binary
 * <p>
 * Find the number of 1s in the binary representation of a number.
 * <p>
 * Example 1:
 * ones(2) = 1
 * ones(3) = 2
 * ones(5) = 2
 * ones(7) = 3
 */
public class NumberOfOnesInBinary {
    public static void main(String[] args) {
        System.out.println(ones(5));
    }

    /**
     * KREVSKY = ORIGINAL SOLUTION
     */
    public static int ones(int n) {
        if (n < 0) {
            return -1;
        }

        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >> 1;
        }

        return count;
    }
}
