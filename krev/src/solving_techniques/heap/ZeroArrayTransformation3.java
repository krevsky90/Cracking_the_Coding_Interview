package solving_techniques.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 3362. Zero Array Transformation III (medium)
 * https://leetcode.com/problems/zero-array-transformation-iii
 * <p>
 * #Company (21.06.2025): 0 - 3 months Meta 3 Microsoft 3 Google 2 Amazon 2 Bloomberg 2
 * <p>
 * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri].
 * Each queries[i] represents the following action on nums:
 * Decrement the value at each index in the range [li, ri] in nums by at most 1.
 * The amount by which the value is decremented can be chosen independently for each index.
 * A Zero Array is an array with all its elements equal to 0.
 * Return the maximum number of elements that can be removed from queries,
 * such that nums can still be converted to a zero array using the remaining queries.
 * If it is not possible to convert nums to a zero array, return -1.
 * <p>
 * Example 1:
 * Input: nums = [2,0,2], queries = [[0,2],[0,2],[1,1]]
 * Output: 1
 * Explanation:
 * After removing queries[2], nums can still be converted to a zero array.
 * Using queries[0], decrement nums[0] and nums[2] by 1 and nums[1] by 0.
 * Using queries[1], decrement nums[0] and nums[2] by 1 and nums[1] by 0.
 * <p>
 * Example 2:
 * Input: nums = [1,1,1,1], queries = [[1,3],[0,2],[1,3],[1,2]]
 * Output: 2
 * Explanation:
 * We can remove queries[2] and queries[3].
 * <p>
 * Example 3:
 * Input: nums = [1,2,3,4], queries = [[0,3]]
 * Output: -1
 * Explanation:
 * nums cannot be converted to a zero array even after using all the queries.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= li <= ri < nums.length
 */
public class ZeroArrayTransformation3 {
    /**
     * NOT SOLVED BY MYSELF:
     * time to implement ~ 15 mins
     * time to understand ~ 60+ mins
     * info:
     * 1) https://www.youtube.com/watch?v=7jNS2hoM8Yw
     * 2) https://leetcode.com/problems/zero-array-transformation-iii/solutions/6768535/priority-queues-in-depth-with-images-idea-behind-solution-c-python-java/?envType=problem-list-v2&envId=nhmbu69h
     * <p>
     * idea:
     * 0) the answer is queries - min amount of queries to cover nums
     * 1) sort queries by start
     * 2) longer query is better, than shorter => select query with bigger end => used max heap to store available queries
     * 3) use min heap to store used queries (to remove the queries with end < i)
     * 4) if the amount of used queries is not sufficient to cover nums[i], add extra queries from available
     *
     * q = queries.length, n = nums.length
     * time ~ O(q*logq (sort) + q*logq (poll from used) + q*logq (add to available) + q*logq (move from available to used) + n (because of loop)) ~ O(n + q*logq)
     * space ~ O(q)
     *
     * 2 attempts:
     * - forgot what should be returned (and returned cnt as is)
     */
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> available = new PriorityQueue<>(Collections.reverseOrder());  //max heap. stores end bounds. sort by max end bound to obtain the farthest interval
        PriorityQueue<Integer> used = new PriorityQueue<>();   //min heap. stores end bounds. to exclude intervals that are already not relevant (i.e. can't be used)
        int cnt = 0;    //number of intervals that we used
        int queryIdx = 0;

        for (int i = 0; i < nums.length; i++) {
            //1. remove not relevant intervals from used
            while (!used.isEmpty() && used.peek() < i) {
                used.poll();
            }

            //2. add new queries (that starts with nums[i])) to available PQ
            while (queryIdx < queries.length && queries[queryIdx][0] <= i) {
                available.add(queries[queryIdx][1]);
                queryIdx++;
            }

            nums[i] -= used.size(); //(re-)use intervals that covers i-th position
            //if used intervals do not fully cover nums[i], try to use extra intervals - from 'available' heap
            while (!available.isEmpty() && nums[i] > 0 && available.peek() >= i) {
                used.add(available.poll());
                cnt++;
                nums[i]--;
            }

            if (nums[i] > 0) return -1; //impossible to turn nums[i] to 0
        }

        return queries.length - cnt;
    }
}
