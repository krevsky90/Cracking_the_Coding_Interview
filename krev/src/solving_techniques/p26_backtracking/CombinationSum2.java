package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * Constraints:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSum2 {
    /**
     * My solution was based on Knapsack 0/1 => caused Time Limit Exceeded (and there was no way of apply memoization for it)
     * so.. consider as NOT SOLVED by myself
     *
     * idea: https://leetcode.com/problems/combination-sum-ii/solutions/16878/combination-sum-i-ii-and-iii-java-solution-see-the-similarities-yourself/
     * time to solve ~ 40 mins
     *
     * several attempts:
     * - forgot to sort the initial array (idea #1)
     * - did not skip duplicates (ides #2)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();
        //idea #1: is to SORT before to avoid duplicate combinations
        Arrays.sort(candidates);
        helper(candidates, target, tempResult, result, 0);
        return result;
    }

    private void helper(int[] candidates, int target, List<Integer> tempResult, List<List<Integer>> result, int startIdx) {
        if (target == 0) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        if (target < 0) return;

        for (int i = startIdx; i < candidates.length; i++) {
            //idea #2: skip duplicates, because otherwise we would do the same work as on previous iteration

            //example: 2,2,1; target = 3
            //path1:
            //i = 0; tempResult = {2}
            //helper(target = 1, startIdx = 1):
            //      i = 1; tempResult = {2,2}; helper(-1,2) => do nothing
            //      i = 2: tempResult = {2,1}; helper(0,3) => result = {{2,1}} - the same!
            //path2:
            //i = 1: tempResult = {2}
            //helper(target = 1, startIdx = 2):
            //      i = 2: tempResult = {2,1}; helper(0,3) => result = {{2,1}} - the same!
            //
            if (i > startIdx && candidates[i-1] == candidates[i]) {
                continue;
            }
            tempResult.add(candidates[i]);
            helper(candidates, target - candidates[i], tempResult, result, i + 1);  //idea #3: i + 1 (to use i-th element only once!)
            tempResult.remove(tempResult.size() - 1);
        }
    }
}