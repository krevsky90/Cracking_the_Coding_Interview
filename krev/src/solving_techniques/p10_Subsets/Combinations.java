package solving_techniques.p10_Subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 * https://leetcode.com/problems/combinations
 *
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 *
 * Example 2:
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations {
    /**
     * KREVSKY SOLUTION:
     * idea: generate all combinations and save them if its length = k
     * time to solve ~ 9 mins
     * 3 attempts due to 2 typos
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        int start = 0;
        generateSubsets(arr, k, result, tempList, start);
        return result;
    }

    public void generateSubsets(int[] arr, int k, List<List<Integer>> result, List<Integer> tempList, int start) {
        if (tempList.size() == k) {
            result.add(new ArrayList<>(tempList));  //filter/save combination
        }

        for (int i = start; i < arr.length; i++) {
            tempList.add(arr[i]);
            generateSubsets(arr, k, result, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}