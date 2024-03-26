package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. Combination Sum III
 * https://leetcode.com/problems/combination-sum-iii
 *
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations.
 * The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 *
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1,
 * there are no valid combination.
 *
 * Constraints:
 * 2 <= k <= 9
 * 1 <= n <= 60
 */
public class CombinationSum3 {
    public static void main(String[] args) {
        CombinationSum3 obj = new CombinationSum3();

        int k1 = 3;
        int n1 = 7;
        List<List<Integer>> result1 = obj.combinationSum3(k1, n1);  //expected {{1,2,4}}

        System.out.println();
    }

    /**
     * KREVSKY SOLUTION:
     * idea: array 1..9 - already sorted and does not contain duplicates
     *
     * time to solve ~ 24 mins
     *
     * 4 attempts:
     * - forgot "&& tempResult.size() == k"
     * - forgot  "tempResult.size() == k"
     * - incorrect condition in for-loop "i < k"
     *
     * BEATS = 100%
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();
        helper(arr, k, n, tempResult, result, 0);

        return result;
    }

    private void helper(int[] arr, int k, int target, List<Integer> tempResult, List<List<Integer>> result, int startIdx) {
        if (target == 0 && tempResult.size() == k) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        if (tempResult.size() == k) return;

        if (target < 0) return; //not necessary?

        for (int i = startIdx; i < arr.length; i++) {
            tempResult.add(arr[i]);
            helper(arr, k, target - arr[i], tempResult, result, i + 1);
            tempResult.remove(tempResult.size() - 1);
        }
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/combination-sum-ii/solutions/16878/combination-sum-i-ii-and-iii-java-solution-see-the-similarities-yourself/
     *
     * similar to min solution, BUT with optimizations:
     * - avoid 'arr'
     * - avoid "target < 0" validation
     *
     */
    public List<List<Integer>> combinationSum3_2(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), k, n, 1);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int k, int remain, int start) {
        if(tempList.size() > k) return; /** no solution */
        else if(tempList.size() == k && remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for (int i = start; i <= 9; i++) {
                tempList.add(i);
                backtrack(list, tempList, k, remain-i, i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
