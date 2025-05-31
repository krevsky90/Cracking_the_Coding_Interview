package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 1679. Max Number of K-Sum Pairs (medium)
 * https://leetcode.com/problems/max-number-of-k-sum-pairs
 * <p>
 * #Company (31.05.2025): 0 - 3 months Meta 3 0 - 6 months Google 3 Amazon 2 6 months ago Bloomberg 7 Microsoft 6 Apple 3 DE Shaw 3 Yandex 3
 * <p>
 * You are given an integer array nums and an integer k.
 * <p>
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * <p>
 * Return the maximum number of operations you can perform on the array.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * <p>
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 */
public class MaxNumberOfKSumPairs {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 18 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - removed elements from map during iterative traversal
     * BEATS ~ 5%
     */
    public int maxOperations(int[] nums, int k) {
        int cnt = 0;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        boolean removed = true;
        while (removed) {
            removed = false;

            for (int key : freq.keySet()) {
                if (freq.get(key) > 0 && freq.getOrDefault(k - key, 0) > 0) {
                    if (k - key != key) {
                        freq.put(key, freq.get(key) - 1);
                        freq.put(k - key, freq.get(k - key) - 1);

                        removed = true;
                        cnt++;
                    } else {
                        if (freq.get(key) >= 2) {
                            freq.put(key, freq.get(key) - 2);
                            removed = true;
                            cnt++;
                        }
                    }
                }
            }
        }

        return cnt;
    }

    /**
     * Official:
     * a little bit optimized from code beauty perspective
     */
    public int maxOperationsOfficial(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        // build the hashmap with count of occurence of every element in array
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = k - nums[i];
            if (map.getOrDefault(current, 0) > 0
                    && map.getOrDefault(complement, 0) > 0) {
                if ((current == complement) && map.get(current) < 2)
                    continue;
                map.put(current, map.get(current) - 1);
                map.put(complement, map.get(complement) - 1);
                count++;
            }
        }
        return count;
    }
}
