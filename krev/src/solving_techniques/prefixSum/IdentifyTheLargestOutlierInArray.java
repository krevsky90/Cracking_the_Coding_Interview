package solving_techniques.prefixSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3371. Identify the Largest Outlier in an Array (medium)
 * https://leetcode.com/problems/identify-the-largest-outlier-in-an-array/
 * <p>
 * #Company (4.03.2025):
 * <p>
 * You are given an integer array nums. This array contains n elements, where exactly n - 2 elements are special numbers.
 * One of the remaining two elements is the sum of these special numbers, and the other is an outlier.
 * An outlier is defined as a number that is neither one of the original special numbers nor the element representing the sum of those numbers.
 * Note that special numbers, the sum element, and the outlier must have distinct indices, but may share the same value.
 * Return the largest potential outlier in nums.
 * <p>
 * Example 1:
 * Input: nums = [2,3,5,10]
 * Output: 10
 * Explanation:
 * The special numbers could be 2 and 3, thus making their sum 5 and the outlier 10.
 * <p>
 * Example 2:
 * Input: nums = [-2,-1,-3,-6,4]
 * Output: 4
 * Explanation:
 * The special numbers could be -2, -1, and -3, thus making their sum -6 and the outlier 4.
 * <p>
 * Example 3:
 * Input: nums = [1,1,1,1,1,5,5]
 * Output: 5
 * <p>
 * Explanation:
 * The special numbers could be 1, 1, 1, 1, and 1, thus making their sum 5 and the other 5 as the outlier.
 * <p>
 * Constraints:
 * 3 <= nums.length <= 10^5
 * -1000 <= nums[i] <= 1000
 * The input is generated such that at least one potential outlier exists in nums
 */
public class IdentifyTheLargestOutlierInArray {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) prefix sum
     * 2) hashMap: elementn -> list of its places (i.e. indices)
     * time to solve ~ 20 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * -did not check that outlier has the same index as sum element
     * <p>
     * BEATS ~ 25%
     */
    public int getLargestOutlier(int[] nums) {
        //time ~ O(n)
        //space ~ O(n)
        int totalSum = 0;
        Map<Integer, List<Integer>> elements = new HashMap<>();
        int outlier = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            elements.putIfAbsent(nums[i], new ArrayList<>());
            elements.get(nums[i]).add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            int partSum = totalSum - nums[i];
            if (partSum % 2 == 0 && elements.containsKey(partSum / 2)) {
                //check that partSum/2 is NOT the same elements that is potential outlier
                //i.e. 'partSum/2' element is placed at least at any other index that != i
                List<Integer> indices = elements.get(partSum / 2);
                if (indices.size() > 1 || indices.get(0) != i) {
                    if (outlier < nums[i]) outlier = nums[i];
                }
            }
        }

        return outlier;
    }

    /**
     * Optimized solution:
     * idea: we don;t need to store all indices in map!
     * it can be just freq map
     * and we can temporarily decrease freq of 'partSum' element
     * if then 'partSum' is outlier => freq becomes 0 => we will skip such potential outlier
     * else update outlier's value (if potential candidate has greater value)
     *
     * BEATS ~ 20%
     */
    public int getLargestOutlierOptimized(int[] nums) {
        //time ~ O(n)
        //space ~ O(n)
        int totalSum = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        int outlier = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            if ((totalSum - nums[i]) % 2 == 0) {
                int halfSum = (totalSum - nums[i]) / 2;
                if (freqMap.containsKey(halfSum)) {
                    //temporarily decrease freq
                    freqMap.put(halfSum, freqMap.getOrDefault(halfSum, 0) - 1);

                    //if potential outlier still has freq > 0 => outlier and target - are different elements of 'nums' array
                    if (freqMap.get(nums[i]) > 0) {
                        if (outlier < nums[i]) outlier = nums[i];
                    }

                    //restore freq of halfSum
                    freqMap.put(halfSum, freqMap.getOrDefault(halfSum, 0) + 1);
                }
            }
        }

        return outlier;
    }
}
