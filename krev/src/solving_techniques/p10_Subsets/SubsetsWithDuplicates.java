package solving_techniques.p10_Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639ca98e585e4a974dfff9bf
 * OR
 * 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/description/
 *
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class SubsetsWithDuplicates {
    /**
     * KREVSKY SOLUTION:
     * ideas:
     * 1) to sort initial array
     * 2) to skip 'branches' that start with duplicate element
     * time to solve ~ 60 mins
     *
     * MY estimations:
     * time ~ O(2^n) or less, where n - length of array
     * space ~ O(n*2^n) - worst case
     *
     * 3 attempts (forgot idea #1, wrongly set (start + 1) instead of (i + 1))
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);      //idea #1

        List<List<Integer>> result = new ArrayList<>();
        int start = 0;

        subsetsWithDupHelper(nums, result, new ArrayList<>(), start);
        return result;
    }

    private void subsetsWithDupHelper(int[] nums, List<List<Integer>> result, List<Integer> tempList, int start) {
        result.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;    //idea #2

            tempList.add(nums[i]);
            subsetsWithDupHelper(nums, result, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}