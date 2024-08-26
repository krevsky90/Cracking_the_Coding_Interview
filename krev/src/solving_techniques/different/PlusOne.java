package solving_techniques.different;

/**
 * 66. Plus One (easy)
 * https://leetcode.com/problems/plus-one
 * <p>
 * #Company: Amazon Bloomberg Facebook Google Microsoft Spotify Uber
 * <p>
 * You are given a large integer represented as an integer array digits,
 * where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 * <p>
 * Increment the large integer by one and return the resulting array of digits.
 * <p>
 * Example 1:
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 * <p>
 * Example 2:
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * Incrementing by one gives 4321 + 1 = 4322.
 * Thus, the result should be [4,3,2,2].
 * <p>
 * Example 3:
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9.
 * Incrementing by one gives 9 + 1 = 10.
 * Thus, the result should be [1,0].
 * <p>
 * Constraints:
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits does not contain any leading 0's.
 */
public class PlusOne {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 7 mins
     * time ~ O(n)
     * space ~ O(n) - worst case
     * <p>
     * 2 attempts:
     * - commented "delta = 0;" by mistale, but it is used for creation of new array
     * <p>
     * BEATS ~ 100%
     */
    public int[] plusOne(int[] digits) {
        int delta = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + delta >= 10) {
                digits[i] = 0;
                // delta = 1;
            } else {
                digits[i] += delta;
                delta = 0;
                break;
            }
        }

        if (delta == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = delta;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        } else {
            return digits;
        }
    }

    /**
     * info:
     * https://leetcode.com/problems/plus-one/solutions/2706861/java-fastest-0ms-runtime-easy-and-elegant-solution/
     */
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        /**
         * Damn! So you thought of every digit is 1 to 9. And this line:
         * digits = new int[digits.length + 1]; digits[0] = 1; return digits;
         * freaking genius! Other digits were zero at this point so just change the first index. Marvelous!
         */
        digits = new int[digits.length + 1];

        digits[0] = 1;
        return digits;
    }
}