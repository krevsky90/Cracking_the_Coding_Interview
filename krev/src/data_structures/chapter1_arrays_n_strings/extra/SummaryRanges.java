package data_structures.chapter1_arrays_n_strings.extra;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges (easy)
 * https://leetcode.com/problems/summary-ranges
 * <p>
 * #Company: Yandex
 * <p>
 * You are given a sorted unique integer array nums.
 * <p>
 * A range [a,b] is the set of all integers from a to b (inclusive).
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * <p>
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * <p>
 * Constraints:
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 */
public class SummaryRanges {
    /**
     * KREVSKY SOLUTION:
     * time ~ 10 mins
     * <p>
     * 1 attempt
     *
     * BEATS ~ 77%
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        int start = 0;
        int end = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                end = i;
            } else {
                if (start == end) {
                    result.add("" + nums[start]);
                } else {
                    result.add(nums[start] + "->" + nums[end]);
                }
                start = i;
                end = i;
            }
        }

        if (start == end) {
            result.add("" + nums[start]);
        } else {
            result.add(nums[start] + "->" + nums[end]);
        }

        return result;
    }
}
