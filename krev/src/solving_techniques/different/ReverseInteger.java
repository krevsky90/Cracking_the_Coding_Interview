package solving_techniques.different;

/**
 * 7. Reverse Integer (medium)
 * https://leetcode.com/problems/reverse-integer/
 * <p>
 * #Company: Adobe Airbnb Alibaba Amazon Apple Barclays Bloomberg Cisco Meta Google caMorgan Lyft Microsoft NetEase Oracle Uber Yahoo
 * <p>
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * Example 1:
 * Input: x = 123
 * Output: 321
 * <p>
 * Example 2:
 * Input: x = -123
 * Output: -321
 * <p>
 * Example 3:
 * Input: x = 120
 * Output: 21
 * <p>
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 */
public class ReverseInteger {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 5 mins
     *
     * time ~ O(log_10_x)
     * space ~ O(1)
     *
     * 1 attempt
     *
     * BEATS ~ 86%
     */
    public int reverse(int x) {
        double result = 0.0;
        boolean neg = x < 0;
        if (neg) x = -x;

        while (x > 0) {
            int delta = x % 10;
            result = 10 * result + delta;
            if (result > Integer.MAX_VALUE) return 0;
            x = x / 10;
        }

        return neg ? -(int) result : (int) result;
    }
}
