package solving_techniques.p15_0or1_Knapsack;

import java.util.Arrays;

/**
 * 1049. Last Stone Weight II
 * https://leetcode.com/problems/last-stone-weight-ii
 * <p>
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * <p>
 * We are playing a game with the stones. On each turn, we choose any two stones and smash them together.
 * Suppose the stones have weights x and y with x <= y. The result of this smash is:
 * <p>
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 * <p>
 * Example 1:
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
 * <p>
 * Example 2:
 * Input: stones = [31,26,33,21,40]
 * Output: 5
 * <p>
 * Constraints:
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
public class LastStoneWeight2 {
    /**
     * NOT SOLVED by myself
     * using hints
     * idea #1: the set of the ways of smashing the stones is a subset of the ways of how we can put '-' or '+' before each stone!
     * so we can find the combination of '-'s and '+'s that givesthe value that is the closest to 0
     *
     * time to solve ~ 25 mins
     * <p>
     * gives Time Limit Exceeded,
     * and IT IS IMPOSSIBLE TO USE MEMO for this solution!
     */
    public int lastStoneWeight(int[] stones) {
        return getMinWeight(stones, 0, 0);
    }

    public int getMinWeight(int[] stones, int idx, int sum) {
        if (idx == stones.length) {
            return sum;
        }

        //case 1: take stones[idx] with '+' sign
        int posResult = getMinWeight(stones, idx + 1, sum + stones[idx]);
        //case 2: take stones[idx] with '-' sign
        int negResult = getMinWeight(stones, idx + 1, sum - stones[idx]);

        return Math.min(Math.abs(posResult), Math.abs(negResult));
    }

    /**
     * SOLUTION #2
     * idea #2: split the stones into 2 knapsacks: with '-' and '+'.
     * idea #3: use memo[][]: stones.length and totalSum. And try to minimize Math.abs(sumPos - sumNeg)
     * btw, this part is similar to src/solving_techniques/p15_0or1_Knapsack/MinimumSubsetSumDifference.java
     *
     * time to implement ~ 10 mins
     *
     * 1 attempt
     */
    public int lastStoneWeight2(int[] stones) {
        int totalSum = 0;
        for (int s : stones) {
            totalSum += s;
        }

        int[][] memo = new int[stones.length + 1][totalSum + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return getMinWeight2(stones, 0, 0, 0, memo);

    }

    public int getMinWeight2(int[] stones, int idx, int sumPos, int sumNeg, int[][] memo) {
        if (idx == stones.length) {
            return Math.abs(sumPos - sumNeg);
        }

        if (memo[idx][sumPos] != -1) {
            return memo[idx][sumPos];
        }

        int posCase = getMinWeight2(stones, idx + 1, sumPos + stones[idx], sumNeg, memo);
        int negCase = getMinWeight2(stones, idx + 1, sumPos, sumNeg + stones[idx], memo);

        return memo[idx][sumPos] = Math.min(posCase, negCase);
    }

}
