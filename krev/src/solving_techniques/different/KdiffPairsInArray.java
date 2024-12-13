package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 532. K-diff Pairs in an Array (medium)
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 * <p>
 * #Company: 0 - 3 months Salesforce 6 tcs 2 0 - 6 months Amazon 3 Google 2 6 months ago Bloomberg 4 Adobe 3 Apple 3 Uber 2 Yahoo 2 Capital One 2
 * <p>
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 * <p>
 * Example 1:
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).\
 * <p>
 * Example 3:
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 */
public class KdiffPairsInArray {
    /**
     * NOT SOLVED by myself
     * idea:
     * 1) store freq Map for each element
     * 2) traverse through keys: if key + k is part of keySet => result++
     * 2.2) edge case: if k == 0 => if freq >= 2 => result++
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * BEATS ~ 62
     */
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        int result = 0;

        if (k == 0) {
            for (int key : freqMap.keySet()) {
                if (freqMap.get(key) >= 2) result++;
            }
            return result;
        }

        for (int key : freqMap.keySet()) {
            int y = key + k;
            if (freqMap.keySet().contains(y)) {
                result++;
            }
        }

        return result;
    }
}
