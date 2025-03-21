package solving_techniques.different.difference_array;

import java.util.HashSet;
import java.util.Set;

/**
 * 3356. Zero Array Transformation II (medium)
 * https://leetcode.com/problems/zero-array-transformation-ii
 * <p>
 * #Company (21.03.2025): 0 - 3 months Google 8 Meta 3 Amazon 3 Bloomberg 2
 * <p>
 * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
 * <p>
 * Each queries[i] represents the following action on nums:
 * <p>
 * Decrement the value at each index in the range [li, ri] in nums by at most vali.
 * The amount by which each value is decremented can be chosen independently for each index.
 * A Zero Array is an array with all its elements equal to 0.
 * <p>
 * Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.
 * <p>
 * Example 1:
 * Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
 * Output: 2
 * Explanation:
 * For i = 0 (l = 0, r = 2, val = 1):
 * Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
 * The array will become [1, 0, 1].
 * For i = 1 (l = 0, r = 2, val = 1):
 * Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
 * The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.
 * <p>
 * Example 2:
 * Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
 * Output: -1
 * Explanation:
 * For i = 0 (l = 1, r = 3, val = 2):
 * Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively.
 * The array will become [4, 1, 0, 0].
 * For i = 1 (l = 0, r = 2, val = 1):
 * Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively.
 * The array will become [3, 0, 0, 0], which is not a Zero Array.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 5 * 105
 * 1 <= queries.length <= 105
 * queries[i].length == 3
 * 0 <= li <= ri < nums.length
 * 1 <= vali <= 5
 */
public class ZeroArrayTransformation2 {
    /**
     * NOT reached by myself
     * idea:
     * 1) binary search to find k
     * 2) in each iteration of BS we check if it is possible to turn all elements to 0s, using the method that takes O(N+Q)
     * because of 'difference array' approach
     *
     * time to implement ~ 13 mins
     * <p>
     * time ~ O((N + Q)*logQ)
     * space ~ O(N) - to keep difference array
     *
     * BEATS ~ 61%
     */
    public int minZeroArrayBS(int[] nums, int[][] queries) {
        int low = 0;
        int high = queries.length;

        if (!canBeZeros(nums, queries, high)) return -1;

        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canBeZeros(nums, queries, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private boolean canBeZeros(int[] nums, int[][] queries, int k) {
        int[] diffArr = new int[nums.length + 1];
        for (int i = 0; i < k; i++) {
            int[] q = queries[i];
            diffArr[q[0]] += q[2];  //increase value on left side
            diffArr[q[1] + 1] -= q[2];  //decrease value on right side
        }

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += diffArr[i];
            if (prefixSum < nums[i]) return false;
        }

        return true;
    }

    /**
     * NOT SOLVED by myself
     * approach 2: Line Sweep
     * idea:
     * - go through nums and save the number of applied queries that's total effect (prefixSum) >= nums[i]
     * - keep difference array and use it to calculate prefixSum
     *
     * time to implement ~ 15 mins
     * time ~ O(Q + N)
     * space ~ O(N)
     *
     * BEATS ~ 100%
     */
    public int minZeroArrayLineSweep(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diffArr = new int[n + 1];
        int prefixSum = 0;

        int k = 0;
        for (int i = 0; i < n; i++) {
            while (prefixSum + diffArr[i] < nums[i]) {
                k++;

                if (k > queries.length) return -1;

                int[] q = queries[k - 1];
                int left = q[0];
                int right = q[1];
                int val = q[2];

                if (i <= right) {
                    diffArr[Math.max(left, i)] += val;
                    diffArr[right + 1] -= val;
                }
            }
            prefixSum += diffArr[i];
        }

        return k;
    }

    /**
     * KREVSKY SOLUTION:
     * naive solution gives TLE
     * time ~ O(N*Q) in the worst case when each query has range [0,n-1], where Q = queries.length
     */
    public int minZeroArray(int[] nums, int[][] queries) {
        int k = 0;
        Set<Integer> zeroIndices = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIndices.add(i);
            }
        }

        //stop condition
        if (zeroIndices.size() == nums.length) return k;

        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                if (zeroIndices.contains(j)) continue;

                if (nums[j] > 0) {
                    nums[j] -= Math.min(nums[j], queries[i][2]);
                }

                if (nums[j] == 0) {
                    zeroIndices.add(j);
                }
            }

            k++;

            //stop condition
            if (zeroIndices.size() == nums.length) return k;
        }

        return -1;
    }
}
