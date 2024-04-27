package solving_techniques.p25_UniquePaths;

/**
 * 375. Guess Number Higher or Lower II
 * https://leetcode.com/problems/guess-number-higher-or-lower-ii
 * <p>
 * We are playing the Guessing Game. The game will work as follows:
 * <p>
 * I pick a number between 1 and n.
 * You guess a number.
 * If you guess the right number, you win the game.
 * If you guess the wrong number, then I will tell you whether the number I picked is higher or lower, and you will continue guessing.
 * Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 * Given a particular n, return the minimum amount of money you need to guarantee a win regardless of what number I pick.
 * <p>
 * Example 1:
 * Input: n = 10
 * Output: 16
 * Explanation: The winning strategy is as follows:
 * - The range is [1,10]. Guess 7.
 * - If this is my number, your total is $0. Otherwise, you pay $7.
 * - If my number is higher, the range is [8,10]. Guess 9.
 * - If this is my number, your total is $7. Otherwise, you pay $9.
 * - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
 * - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
 * - If my number is lower, the range is [1,6]. Guess 3.
 * - If this is my number, your total is $7. Otherwise, you pay $3.
 * - If my number is higher, the range is [4,6]. Guess 5.
 * - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
 * - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
 * - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
 * - If my number is lower, the range is [1,2]. Guess 1.
 * - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
 * - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
 * The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 0
 * Explanation: There is only one possible number, so you can guess 1 and not have to pay anything.
 * Example 3:
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: There are two possible numbers, 1 and 2.
 * - Guess 1.
 * - If this is my number, your total is $0. Otherwise, you pay $1.
 * - If my number is higher, it must be 2. Guess 2. Your total is $1.
 * The worst case is that you pay $1.
 * <p>
 * <p>
 * Constraints:
 * 1 <= n <= 200
 */
public class GuessNumberHigherOrLower2 {
    /**
     * KREVSKY SOLUTION:
     * idea: the process is the same as building of BST
     * so we need to build all possible BSTs from nodes that are in range [1,n]
     * and find maximum path from root to leaf (except the value of this leaf)
     * <p>
     * +memoization, since we consider ranges [start,end] that is subrange of [1,n]
     * <p>
     * time to solve ~ 50 mins
     * <p>
     * 4 attempts:
     * - incorrect formula "result = Math.max(result, i + leftMax + rightMax)" - we need to find min, not max
     * - incorrect formula "result = Math.min(result, i + leftMax + rightMax)" - we do not need to add left and right, we need to find their max value!
     * - add memo
     * <p>
     * BEATS = 6%
     * <p>
     * NOTE: it is not very optimal since we do not need to consider the trees with root <= n/2,
     * because in this case right subtree will have longer paths than left subtree
     */
    public int getMoneyAmount(int n) {
        Integer[][] memo = new Integer[n + 1][n + 1];
        return getMoneyAmount(1, n, memo);
    }

    private int getMoneyAmount(int start, int end, Integer[][] memo) {
        if (start > end) return 0;  //nothing to check
        if (start == end) return 0; //since we knew this number before calling the method with such parameters

        if (memo[start][end] != null) return memo[start][end];

        int result = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int leftMax = getMoneyAmount(start, i - 1, memo);
            int rightMax = getMoneyAmount(i + 1, end, memo);
            result = Math.min(result, i + Math.max(leftMax, rightMax));
        }

        return memo[start][end] = result;
    }
}
