package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 465. Optimal Account Balancing (hard)
 * https://leetcode.com/problems/optimal-account-balancing
 * <p>
 * #Company: (22.12.2024) 0 - 3 months Pinterest 5 ZScaler 4 Uber 3 0 - 6 months Google 3 Rippling 2 6 months ago TikTok 9 Microsoft 8 Amazon 4 Stripe 3 Bloomberg 2 Zomato 2
 * <p>
 * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
 * <p>
 * Return the minimum number of transactions required to settle the debt.
 * <p>
 * Example 1:
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * <p>
 * Example 2:
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 * <p>
 * Constraints:
 * 1 <= transactions.length <= 8
 * transactions[i].length == 3
 * 0 <= fromi, toi < 12
 * fromi != toi
 * 1 <= amounti <= 100
 */
public class OptimalAccountBalancing {
    /**
     * NOT SOLVED by myself
     * idea:
     * use backtracking OR DP
     * I will use backtracking
     * NOTE: it is NOT graph problem since when all transactions are done, each account can transfer the moeny to any other account directly!
     * 1) build map: accountNum -> balance
     * 2) form list of nonZeroBalances
     * 3) set dfs function that returns the answer to the initial question for subList [i, end of list]
     * if i-th balance is zero - it does not require any transactions => dfs(i) = dfs(i+1)
     * else - for each further element (i+1, end of list) we try to move money from i-th to j-th account and call dfs(j+1)
     * i.e. dfs(i) = min(1 + dfs(j)).where j = i+1 ... n-1
     * 3.2) optimization: there is sense to transfer money only between the balances that have different signs
     * 4) edge case: if i == n => return 0, we are out of list
     * <p>
     * time to implement ~ 24 mins
     * <p>
     * time ~ O((N - 1)!)
     * space ~ O(N)
     * <p>
     * 3 attempts:
     * - forgot edge case 4
     * - forgot optimization 3.2
     * <p>
     * BEATS ~ 42%
     */
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> accountToBalance = new HashMap<>();
        for (int[] t : transactions) {
            accountToBalance.put(t[0], accountToBalance.getOrDefault(t[0], 0) - t[2]);
            accountToBalance.put(t[1], accountToBalance.getOrDefault(t[1], 0) + t[2]);
        }

        List<Integer> nonZeroBalances = new ArrayList<>();
        for (int v : accountToBalance.values()) {
            if (v != 0) {
                nonZeroBalances.add(v);
            }
        }

        return dfs(nonZeroBalances, 0);
    }

    //returns min amount of transactions that is required for subList [i, end of list]
    private int dfs(List<Integer> nonZeroBalances, int i) {
        if (i == nonZeroBalances.size()) return 0;

        int cur = nonZeroBalances.get(i);

        if (cur == 0) {
            return dfs(nonZeroBalances, i + 1);
        } else {
            int result = Integer.MAX_VALUE;
            for (int j = i + 1; j < nonZeroBalances.size(); j++) {
                //try to transfer cur to j-th account and see what will be the result
                //NOTE: optimization: will transfer only if i-th and j-th balances have different signs
                int oldValue = nonZeroBalances.get(j);
                if (cur * oldValue < 0) {
                    nonZeroBalances.set(j, oldValue + cur);
                    int tempResult = 1 + dfs(nonZeroBalances, i + 1);
                    result = Math.min(result, tempResult);

                    //backtrack the changes
                    nonZeroBalances.set(j, oldValue);
                }
            }

            return result;
        }
    }
}
