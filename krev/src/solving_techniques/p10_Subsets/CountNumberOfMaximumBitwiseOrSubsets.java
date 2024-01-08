package solving_techniques.p10_Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 2044. Count Number of Maximum Bitwise-OR Subsets
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
 *
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums
 * and return the number of different non-empty subsets with the maximum bitwise OR.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 * Two subsets are considered different if the indices of the elements chosen are different.
 *
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 *
 * Example 1:
 * Input: nums = [3,1]
 * Output: 2
 * Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
 * - [3]
 * - [3,1]
 *
 * Example 2:
 * Input: nums = [2,2,2]
 * Output: 7
 * Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
 *
 * Example 3:
 * Input: nums = [3,2,1,5]
 * Output: 6
 * Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 10^5
 */
public class CountNumberOfMaximumBitwiseOrSubsets {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5};
        System.out.println(countMaxOrSubsets(nums));    // 6
    }

    /**
     * KREVSKY SOLUTION
     * time to solve ~25 mins
     * 1 tip: maxOR = maxOR(whole nums)
     * idea: see the tip + generate all subsets, but count some of them instead of save them
     *
     * 3 attempts:
     * - could not convert int[] to List<Integer>
     * - typo: 'start + 1' instead of 'i + 1"
     */
    public static int countMaxOrSubsets(int[] nums) {
        //1) count Max bitwise OR. it equals OR of all array
        int maxOr = calculateOR(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        int[] count = new int[1];
        //find all subsets and count only that have OR = maxOr
        generateAllSubsets(nums, maxOr, count);
        return count[0];
    }

    private static void generateAllSubsets(int[] nums, int maxOr, int[] count) {
        List<Integer> tempList = new ArrayList<>();
        int start = 0;
        generateAllSubsets(nums, maxOr, count, tempList, start);
    }

    private static void generateAllSubsets(int[] nums, int maxOr, int[] count, List<Integer> tempList, int start) {
        if (calculateOR(tempList) == maxOr) {
            count[0]++;
        }

        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            generateAllSubsets(nums, maxOr, count, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }

    private static int calculateOR(List<Integer> arr) {
        int res = 0;
        for (Integer n : arr) {
            res = res | n;
        }
        return res;
    }
}
