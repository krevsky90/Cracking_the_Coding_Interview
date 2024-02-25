package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence (hard)
 * https://leetcode.com/problems/permutation-sequence
 *
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 *
 * Example 3:
 * Input: n = 3, k = 1
 * Output: "123"
 *
 * Constraints:
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class PermutationSequence {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     * 1 attempt
     * <p>
     * BUT Time Limit Exceeded
     *
     * time to solve + 15 mins (optimization)
     * PROBLEM: tempResult.contains(arr[i]) takes O(n)
     * SOLUTION: use boolean array to check the same, it will take time ~ O(1)
     */
    public String getPermutation(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        boolean[] path = new boolean[n];

        return getKthPermutation(arr, path, new int[1], new ArrayList<>(), k);
    }

    private String getKthPermutation(int[] arr, boolean[] path, int[] pos, List<Integer> tempResult, int k) {
        if (tempResult.size() == arr.length) {
            pos[0]++;
            if (pos[0] == k) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : tempResult) {
                    sb.append(i);
                }
                return sb.toString();
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
//                if (!tempResult.contains(arr[i])) { <==== this is too long - O(n)
                if (path[i] == false) {             // this is fast - O(1)
                    path[i] = true;
                    tempResult.add(arr[i]);

                    String result = getKthPermutation(arr, path, pos, tempResult, k);
                    if (!"".equals(result)) {
                        return result;
                    }
                    //backtracking
                    tempResult.remove(tempResult.size() - 1);
                    path[i] = false;
                }
            }
        }

        return "";
    }

    /**
     * SOLUTION #2: Math
     * https://leetcode.com/problems/permutation-sequence/solutions/3807334/2-ms-java-explained/
     *
     */
}

