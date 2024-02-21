package solving_techniques.p16_UnboundedKnapsack;

import java.util.HashSet;
import java.util.Set;

/**
 * 983. Minimum Cost For Tickets
 * https://leetcode.com/problems/minimum-cost-for-tickets
 *
 * You have planned some train traveling one year in advance.
 * The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 * Example 1:
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 *
 * Example 2:
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 *
 * Constraints:
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class MinimumCostForTickets {
    /**
     * KREVSKY SOLUTION:
     * idea: usual problem with weights = costs and prices = durations = {1,7,30) days
     * EXCEPT the fact, that some of days can be omitted => we have to find the next day of travelling by findIdx method
     *
     * time to solve ~ 27 mins
     *
     * 3 attempts:
     * - set memo = new int[days.length] instead of int[366] (or even int[365]
     * - firstly we have to find idx and if it is not -1, then check memo[idx]
     */
    public int mincostTickets(int[] days, int[] costs) {
        int[] durations = {1,7,30};
        int[] memo = new int[366];
        return mincostTickets(days, 1, costs, durations, memo);
    }

    private int mincostTickets(int[] days, int dayNum, int[] costs, int[] durations, int[] memo) {
        int idx = findIdx(days, dayNum);
        if (idx == -1) {
            return 0;
        }

        if (memo[dayNum] != 0) {
            return memo[dayNum];
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            result = Math.min(result, costs[i] + mincostTickets(days, days[idx] + durations[i], costs, durations, memo));
        }

        return memo[dayNum] = result;
    }

    private int findIdx(int[] days, int dayNum) {
        //we can use binary search of just linear search since days.length <= 365, i.e. very short
        for (int i = 0; i < days.length; i++) {
            if (days[i] >= dayNum) {
                return i;
            }
        }

        return -1;  //i.e. dayNum > max element of days => we cover all travel days
    }

    /**
     * Alternative solution:
     * idea is to increment dayNum if it is not in days array, rather then find it by findIdx
     * https://leetcode.com/problems/minimum-cost-for-tickets/solutions/4680062/recursive-solution-with-dp-easy-to-understand-clean-code/
     */
    public int mincostTicketsLeetcode(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        Integer[] dp = new Integer[366];
        for(int n : days) {
            set.add(n);
        }
        return calculateMinCost(days, costs, 1, set, dp);
    }

    public int calculateMinCost(int[] days, int[] costs, int curr, Set<Integer> set, Integer[] dp) {
        if (curr > 365) {
            return 0;
        }

        if(dp[curr] != null)
            return dp[curr];

        int cost = Integer.MAX_VALUE;
        if (!set.contains(curr)) {
            return calculateMinCost(days, costs, curr + 1, set, dp);
        } else {
            int oDay = costs[0] + calculateMinCost(days, costs, curr + 1, set, dp);
            int sDay = costs[1] + calculateMinCost(days, costs, curr + 7, set, dp);
            int tDay = costs[2] + calculateMinCost(days, costs, curr + 30, set, dp);
            cost = Math.min(oDay, Math.min(sDay, tDay));
        }

        dp[curr] = cost;
        return dp[curr];
    }
}
