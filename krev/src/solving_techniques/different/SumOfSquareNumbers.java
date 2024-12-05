package solving_techniques.different;

/**
 * 633. Sum of Square Numbers (medium)
 * https://leetcode.com/problems/sum-of-square-numbers/
 * <p>
 * #Company: Meta Bloomberg LinkedIn
 * <p>
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 * <p>
 * Example 1:
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * <p>
 * Example 2:
 * Input: c = 3
 * Output: false
 * <p>
 * Constraints:
 * 0 <= c <= 2^31 - 1
 */
public class SumOfSquareNumbers {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 5 mins
     * <p>
     * time ~ O(sqrt(c))
     * space ~ O(1)
     * <p>
     * 2 attempts:
     * incorrect condition if (b*b == c - a*a) => if (b == (int) b)
     *
     * BEATS ~ 91%
     */
    public boolean judgeSquareSum(int c) {
        if (c == 0) return true;

        int max = 1 + (int) Math.sqrt(c);
        for (int a = 1; a < max; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) return true;
        }

        return false;
    }
}
