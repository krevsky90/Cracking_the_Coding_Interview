package solving_techniques.p12_Bitwise_XOR;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a0a649ef4e913a2e2bf4cd
 * OR
 * 1009. Complement of Base 10 Integer (easy)
 * https://leetcode.com/problems/complement-of-base-10-integer/
 * <p>
 * The complement of an integer is the integer you get
 * when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.
 * <p>
 * For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
 * Given an integer n, return its complement.
 * <p>
 * Example 1:
 * Input: n = 5
 * Output: 2
 * Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
 * <p>
 * Example 2:
 * Input: n = 7
 * Output: 0
 * Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
 * <p>
 * Example 3:
 * Input: n = 10
 * Output: 5
 * Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.
 * <p>
 * Constraints:
 * 0 <= n < 10^9
 */
public class ComplementOfBase10Integer {
    /**
     * KREVSKY SOLUTION:
     * idea: n + complemented(n) + 1 = 2^degree, where degree - length of number n in binary representation
     * time to solve ~ 6 mins
     * 3 attempts:
     * - forgot to cast Math.pow(..) to int
     * - did not handle special case, when n = 0
     */
    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }

        int degree = 0;
        int temp = n;
        while (temp > 0) {
            degree++;
            temp = temp / 2;
        }

        return (int) Math.pow(2, degree) - n - 1;
    }

    /**
     * SOLUTION #2
     * improved my solution: https://leetcode.com/problems/complement-of-base-10-integer/solutions/3544067/solution/
     */
    public int bitwiseComplement2(int n) {
        int mask = 1;

        while (mask < n) {
            mask = (mask << 1) + 1;
        }

        return mask ^ n;
    }

}
