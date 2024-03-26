package solving_techniques.p17_FibonacciNumbers;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f4de6273008407d8d141e
 * OR
 * 746. Min Cost Climbing Stairs (easy)
 * https://leetcode.com/problems/min-cost-climbing-stairs
 * <p>
 * Let's solve the problem from leetcode:
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 * <p>
 * Example 1:
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * <p>
 * Example 2:
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 * <p>
 * Constraints:
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class MinimumJumpsWithFee {
    /**
     * KREVSKY SOLUTION:
     * idea: res[i] = Math.min(res[i-2] + cost[i-2], res[i-1] + cost[i-1])
     * base case: res[0] = res[1] = 0
     * <p>
     * time to solve ~ 21 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n), where n = cost.length
     * <p>
     * 2 attempts:
     * - syntax typo (forgot 'new' when created in array)
     * <p>
     * BEATS = 100%
     */

    //cost = [10,15,20]
    //res[0] = 0
    //res[1] = 0
    //res[2] = min(res[0] + cost[0], res[1] + cost[1]) = min(0+10,0+15) = 10
    //res[3] = min(res[1] + cost[1], res[2] + cost[2]) = min(0+15,10+20) = 15
    public int minCostClimbingStairs(int[] cost) {
        int[] res = new int[cost.length + 1];
        res[0] = 0;
        res[1] = 0;
        for (int i = 2; i < res.length; i++) {
            res[i] = Math.min(res[i - 2] + cost[i - 2], res[i - 1] + cost[i - 1]);
        }
        return res[cost.length];
    }

    /**
     * KREVSKY SOLUTION #1.2:
     * optimize space usage since res[i] depends from res[i-1] and res[i-2] elements
     * => we don't need to store all calculates values in 'res'
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 100%
     */
    public int minCostClimbingStairsOptimized(int[] cost) {
        int prev = 0;
        int prevprev = 0;
        int res = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            res = Math.min(prevprev + cost[i - 2], prev + cost[i - 1]);
            prevprev = prev;
            prev = res;
        }
        return res;
    }
}
