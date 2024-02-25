package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        List<List<Integer>> result1 = new Permutations().permute(nums);
        for (List<Integer> list : result1) {
            list.stream().forEach(x -> System.out.print(x + " "));
            System.out.println();
        }
        System.out.println();

        List<List<Integer>> result3 = new Permutations().permute3(nums);
        for (List<Integer> list : result3) {
            list.stream().forEach(x -> System.out.print(x + " "));
            System.out.println();
        }

    }
    /**
     * https://leetcode.com/problems/permutations/solutions/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning/
     * NOT SOLVED!
     * time to solve ~ 35 mins
     *
     * NOTE: if we consider the combinations as numbers, then these numbers will be in increasing sequence!
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

    /**
     * SOLUTION #3:
     * https://www.geeksforgeeks.org/print-all-possible-permutations-of-an-array-vector-without-duplicates-using-backtracking/
     */
    public List<List<Integer>> permute3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        List<List<Integer>> result = new ArrayList<>();
        //NOTE: we temporary change the initial array 'nums'!
        permute3(nums, left, right, result);

        return result;
    }


    //permute3(123, 0, 2, [])
    //	i=0:
    //		swap: 123
    //		permute3(123, 1, 2, [..])
    //			i=1:
    //				swap: 123
    //				permute3(123, 2, 2, [..])
    //					result += [[123]]
    //				swap: 123
    //			i=2:
    //				swap: 132
    //				permute3(132, 2, 2, [..])
    //					result += [[132]]
    //				swap: 123
    //		swap: 123
    //	i=1:
    //		swap: 213
    //		permute3(213, 1, 2, [..])
    //			i=1:
    //				swap: 213
    //				permute3(213, 2, 2, [..])
    //					result += [[213]]
    //				swap: 213
    //			i=2:
    //				swap: 231
    //				permute3(231, 2, 2, [..])
    //					result += [[231]]
    //				swap: 213
    //		swap: 123
    //	i=2:
    //		swap: 321
    //		permute3(321, 1, 2, [..])
    //			i=1:
    //				swap: 321
    //				permute3(321, 2, 2, [..])
    //					result += [[321]]
    //				swap: 321
    //			i=2:
    //				swap: 312
    //				permute3(312, 2, 2, [..])
    //					result += [[312]]
    //				swap: 321
    //		swap: 123
    private void permute3(int[] nums, int left, int right, List<List<Integer>> result) {
        if (left == right) {
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        for (int i = left; i <= right; i++) {
            swap(nums, left, i);
            permute3(nums, left + 1, right, result);
            swap(nums, left, i);    //backtracking
        }
    }

    private void swap(int[] arr, int i, int j) {
        int val = arr[i];
        arr[i] = arr[j];
        arr[j] = val;
    }

}