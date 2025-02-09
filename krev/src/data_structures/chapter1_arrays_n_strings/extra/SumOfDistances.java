package data_structures.chapter1_arrays_n_strings.extra;

import java.util.HashMap;
import java.util.Map;

/**
 * 2615. Sum of Distances
 * https://leetcode.com/problems/sum-of-distances/description/
 *
 * You are given a 0-indexed integer array nums.
 * There exists an array arr of length nums.length,
 * where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i.
 * If there is no such j, set arr[i] to be 0.
 *
 * Return the array arr.
 *
 * Example 1:
 * Input: nums = [1,3,1,1,2]
 * Output: [5,0,3,4,0]
 * Explanation:
 * When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
 * When i = 1, arr[1] = 0 because there is no other index with value 3.
 * When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
 * When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
 * When i = 4, arr[4] = 0 because there is no other index with value 2.
 *
 * Example 2:
 * Input: nums = [0,5,3]
 * Output: [0,0,0]
 * Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 */
public class SumOfDistances {
    /**
     * NOT SOLVED by myself
     * idea is NOT 100% correct (since leetcode showed 1066/1068): https://www.youtube.com/watch?v=HmHt59FRn60
     * VERY COMPLICATED IDEA !!!
     */
    public long[] distance(int[] nums) {
        Map<Integer, Long> valueToSumOfIndicesMap = new HashMap<>();
        Map<Integer, Integer> valueToCountOfOccurencesMap = new HashMap<>();

        long[] result = new long[nums.length];

        //impact the 'result' by the distances when i > j for each j
        for (int i = 0; i < nums.length; i++) {
            Long sumOfIndices = valueToSumOfIndicesMap.getOrDefault(nums[i], 0L);
            int countOfOccurences = valueToCountOfOccurencesMap.getOrDefault(nums[i], 0);
            result[i] += i*countOfOccurences - sumOfIndices;

            if (valueToSumOfIndicesMap.containsKey(nums[i])) {
                valueToSumOfIndicesMap.put(nums[i], valueToSumOfIndicesMap.get(nums[i]) + i);
            } else {
                valueToSumOfIndicesMap.put(nums[i], Long.valueOf(i));
            }

            if (valueToCountOfOccurencesMap.containsKey(nums[i])) {
                valueToCountOfOccurencesMap.put(nums[i], valueToCountOfOccurencesMap.get(nums[i]) + 1);
            } else {
                valueToCountOfOccurencesMap.put(nums[i], 1);
            }
        }

        valueToSumOfIndicesMap.clear();
        valueToCountOfOccurencesMap.clear();

        //now the same calculations that add the distance (between nums[i] and nums[j]. when the elements are equals and j > i) to result[i]
        for (int i = nums.length - 1; i >= 0; i--) {
            Long sumOfIndices = valueToSumOfIndicesMap.getOrDefault(nums[i], 0L);
            int countOfOccurences = valueToCountOfOccurencesMap.getOrDefault(nums[i], 0);
            result[i] += sumOfIndices - i*countOfOccurences;

            if (valueToSumOfIndicesMap.containsKey(nums[i])) {
                valueToSumOfIndicesMap.put(nums[i], valueToSumOfIndicesMap.get(nums[i]) + i);
            } else {
                valueToSumOfIndicesMap.put(nums[i], Long.valueOf(i));
            }

            if (valueToCountOfOccurencesMap.containsKey(nums[i])) {
                valueToCountOfOccurencesMap.put(nums[i], valueToCountOfOccurencesMap.get(nums[i]) + 1);
            } else {
                valueToCountOfOccurencesMap.put(nums[i], 1);
            }
        }

        return result;
    }

    // 0 1 2 3 4 <- j
    // 1 3 1 1 2 <- nums
    // arr[0] = 2 + 3
    // arr[1] = 0
    // arr[2] = (2 - 0) + (3 - 2) = 3
    public long[] distanceNaive(int[] nums) {
        long[] arr = new long[nums.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    arr[i] += Math.abs(i - j);
                }
            }
        }

        return arr;
    }
}

