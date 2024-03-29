package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639ca6d8585e4a974dfff649
 * OR
 * 78. Subsets
 * https://leetcode.com/problems/subsets/
 *
 * Given a set with distinct elements, find all of its distinct subsets.
 *
 * Example 1:
 * Input: [1, 3]
 * Output: [], [1], [3], [1,3]
 *
 * Example 2:
 * Input: [1, 5, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 */
public class Subsets {
    /**
     * KREVSKY SOLUTION:
     * https://www.youtube.com/watch?v=VKTKKPKX_BU
     * time to solve ~ 10 mins
     * 2 attempts: forget to create new list based on tempList
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int start = 0;

        generateSubsets(nums, result, new ArrayList<>(), start);
        return result;
    }

    private void generateSubsets(int[] nums, List<List<Integer>> result, List<Integer> tempList, int start) {
        result.add(new ArrayList(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            generateSubsets(nums, result, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * SOLUTION #2:
     * see Cracking_the_Coding_Interview\krev\src\data_structures\chapter8_recursion_and_dynamic_programming\Problem8_4 # getSubSets
     */
}