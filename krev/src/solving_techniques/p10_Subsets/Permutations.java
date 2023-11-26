package solving_techniques.p10_Subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639cae5754a43e1fc3c40cb6
 * OR
 * 46. Permutations
 * https://leetcode.com/problems/permutations/
 *
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 *
 * Constraints:
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutations {
    /**
     * https://leetcode.com/problems/permutations/solutions/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning/
     * NOT SOLVED!
     * time to solve ~ 35 mins
     *
     * Idea (#1): use backtracking, BUT skip elements that are already exist in tempList
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, result, new ArrayList<>());

        return result;
    }

    private void permute(int[] nums, List<List<Integer>> result, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue;   //idea #1: skip since all elements should be unique!

                tempList.add(nums[i]);
                permute(nums, result, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
     * SOLUTION #2:
     * see Cracking_the_Coding_Interview\krev\src\data_structures\chapter8_recursion_and_dynamic_programming\Problem8_7 # computeAllPermutations
     */
}