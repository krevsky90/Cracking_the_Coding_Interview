package solving_techniques.different;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 2542. Maximum Subsequence Score
 * https://leetcode.com/problems/maximum-subsequence-score
 * <p>
 * You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
 * You must choose a subsequence of indices from nums1 of length k.
 * <p>
 * For chosen indices i0, i1, ..., ik - 1, your score is defined as:
 * <p>
 * The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
 * It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
 * Return the maximum possible score.
 * <p>
 * A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.
 * <p>
 * Example 1:
 * Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
 * Output: 12
 * Explanation:
 * The four possible subsequence scores are:
 * - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
 * - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
 * - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
 * - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
 * Therefore, we return the max score, which is 12.
 * <p>
 * Example 2:
 * Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
 * Output: 30
 * Explanation:
 * Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.
 * <p>
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= n
 */
public class MaximumSubsequenceScore {
    // #priority queue
    // #sorting (pairs)
    public static void main(String[] args) {
        int[] nums1 = {1,3,3,2};
        int[] nums2 = {2,1,3,4};
        int k = 3;

        MaximumSubsequenceScore obj = new MaximumSubsequenceScore();
        System.out.println(obj.maxScore(nums1, nums2, k));
    }
    /**
     * NOT SOLVED by myself
     * can't prove that the multiplying by the value from nums2 gives maximum result
     * info: https://leetcode.com/problems/maximum-subsequence-score/solutions/3557410/java-solution-using-sorting-and-priorityqueue/
     *
     * time to spend ~ 80 mins
     *
     * BEATS ~ 94%
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        //idea #1: create pairs and sort based on nums2
        int[][] sortedArr = new int[n][];  //to store pair: nums1[i], nums2[i]
        for (int i = 0; i < n; i++) {
            sortedArr[i] = new int[]{nums1[i], nums2[i]};
        }
        Arrays.sort(sortedArr, (a, b) -> a[1] - b[1]);
        // 3[1] 1[2] 3[3] 2[4]

        PriorityQueue<Integer> q = new PriorityQueue<>(); //to store k largest numbers from nums1
        long sum = 0;   //NOTE: long!
        long result = 0;
        for (int i = n - 1; i >= 0; i--) {
            int num1 = sortedArr[i][0];
            if (i <= n - k) {
                //add num1, but it is not mandatory that it will be included into the queue! even if the result is formed by this num1.
                //example: 3[1] 1[2] 3[3] 2[4], where 1[2] will not be included into the queue
                long cur = (sum + num1) * sortedArr[i][1];
                result = Math.max(result, cur);
            }
            if (q.size() < k - 1) {
                q.add(num1);
                sum += num1;
            } else if (!q.isEmpty() && num1 > q.peek()) {
                sum -= q.poll();
                sum += num1;
                q.add(num1);
            }
        }

        return result;
    }
}
