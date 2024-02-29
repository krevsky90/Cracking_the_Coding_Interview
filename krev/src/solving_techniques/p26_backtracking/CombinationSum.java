package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63d3bfa4f81b8e2fe5ded9c4
 * OR
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum
 * <p>
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the  frequency of at least one of the chosen numbers is different.
 * <p>
 * The test cases are generated such that the number of unique combinations
 * that sum up to target is less than 150 combinations for the given input.
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 * <p>
 * Constraints:
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * All elements of candidates are distinct.
 * 1 <= target <= 40
 */
public class CombinationSum {
    /**
     * https://leetcode.com/problems/combination-sum/solutions/16502/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning
     *
     * common idea: collect combinations as in case of general 'get all permutations' problem
     * idea #1: sort initial array
     * idea #2: use 'start' parameter to consider only the elements that are more right than 'start' element.
     *  otherwise for arr = [2,3] and target = 5 we will have {2,3} and {3,2} combinations - i.e. duplicates
     *
     *  time to solve ~ 22 mins
     *
     *  3 attempts
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();
        //idea #1: is to SORT before to avoid duplicate combinations
        Arrays.sort(candidates);
        combinationSumHelper(candidates, target, tempResult, result, 0);
        return result;
    }

    //'start' is necessary to avoid the situation when you already got [2,3], i-th element is 3 and you start considering 2 (i.e. i-1-th element)
    private void combinationSumHelper(int[] candidates, int target, List<Integer> tempResult, List<List<Integer>> result, int start) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            tempResult.add(candidates[i]);
            combinationSumHelper(candidates, target - candidates[i], tempResult, result, i);
            tempResult.remove(tempResult.size() - 1);
        }
    }
}
