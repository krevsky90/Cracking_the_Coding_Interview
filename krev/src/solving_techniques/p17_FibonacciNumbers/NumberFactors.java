package solving_techniques.p17_FibonacciNumbers;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f4adfe57c531cec39a230
 *
 * Given a number 'n',
 * implement a method to count how many possible ways there are to express 'n' as the sum of 1, 3, or 4.
 *
 * Example 1:
 * n : 4
 * Number of ways = 4
 * Explanation: Following are the four ways we can express 'n' : {1,1,1,1}, {1,3}, {3,1}, {4}
 *
 * Example 2:
 * n : 5
 * Number of ways = 6
 * Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1}, {1,1,3}, {1,3,1}, {3,1,1},
 * {1,4}, {4,1}
 */
public class NumberFactors {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(countNumberFactors(n));
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 8 mins
     * time to solve ~ O(n)
     * space ~ O(n) - NOT OPTIMIZED! we can use 3 variables to store the calculations for n-1, n-3, n-4
     * 2 attempts:
     * - case 'n < 0' should be handled BEFORE taking memo[n]
     */
    public static int countNumberFactors(int n) {
        int[] memo = new int[n + 1];
        return countNumberFactorsMemo(n, memo);
    }

    public static int countNumberFactorsMemo(int n, int[] memo) {
        if (n < 0) return 0;
        if (n == 0) return 1;   //for cases like 3 - 3 = 0, but it is path!
        if (n == 1) return 1;
        if (memo[n] > 0) return memo[n]; //note! case "n < 0" should be already handled before we take n-th element!

        memo[n] = countNumberFactorsMemo(n - 1, memo) + countNumberFactorsMemo(n - 3, memo) + countNumberFactorsMemo(n - 4, memo);
        return memo[n];
    }
}
