package solving_techniques.p10_Subsets;

import java.util.*;

/**
 * the same as Cracking_the_Coding_Interview\krev\src\data_structures\chapter8_recursion_and_dynamic_programming\Problem8_8.java
 * OR
 * 47. Permutations II
 * https://leetcode.com/problems/permutations-ii/description/
 * <p>
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class Permutations_II {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 8 mins
     * time to debug ~ 10 mins
     * <p>
     * Time complexity: O(N! * N)
     * Space complexity:* O(N)*
     * <p>
     * 2 attempts: forgot new ArrayList<>(..) in the row "result.add(new ArrayList<>(tempList));"
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        permuteUnique(map, result, new ArrayList<>(), nums.length);
        return result;
    }

    // [112] => {1->2, 2->1}
    // permuteUnique( {1->2, 2->1},[[]],[],3)
    //     key = 1
    //         {1->1, 2->1}
    //         tempList = [1]
    //         permuteUnique( {1->1, 2->1},[[]],[1],3)
    //             key = 1
    //                 {1->0, 2->1}
    //                 tempList = [11]
    //                 permuteUnique( {1->0, 2->1},[[]],[11],3)
    //                     key = 1 - do nothing
    //                     key = 2
    //                         {1->0, 2->0}
    //                         tempList = [112]
    //                             permuteUnique( {1->0, 2->0},[[]],[112],3)
    //                                 result = [112]
    //                         tempList = [11]
    //                         {1->0, 2->1}
    //                 tempList = [1]
    //                 {1->1, 2->1}
    //             key = 2
    //                 {1->1, 2->0}
    //                 tempList = [12]
    //                 permuteUnique( {1->1, 2->0},[[112]],[12],3)
    //                     key = 1
    //                         {1->0, 2->0}
    //                         tempList = [121]
    //                             permuteUnique( {1->0, 2->0},[[112]],[121],3)
    //                                 result = [112][121]
    //                         tempList = [12]
    //                         {1->1, 2->0}

    //                     key = 2 - do nothing
    //                 tempList = [1]
    //                 {1->1, 2->1}

    //         {1->2, 2->1}
    //     key = 2
    //         {1->2, 2->0}
    //         tempList = [2]
    //            ... etc
    //         {1->2, 2->1}


    private void permuteUnique(Map<Integer, Integer> map, List<List<Integer>> result, List<Integer> tempList, int n) {
        if (tempList.size() == n) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) > 0) {
                map.put(key, map.get(key) - 1);
                tempList.add(key);
                permuteUnique(map, result, tempList, n);
                tempList.remove(tempList.size() - 1);
                map.put(key, map.get(key) + 1);
            }
        }
    }

    /**
     * SOLUTION of similar problem (with strings)
     * https://www.geeksforgeeks.org/print-all-permutations-of-a-string-in-java/ - see the second problem "When the permutations need to be distinct."
     */

    /**
     * SOLUTION #2: faster than mine
     * https://leetcode.com/problems/permutations-ii/solutions/4093616/java-very-easy-solution-check-comments-o-n-n/
     * the idea is similar, BUT we use array and ignore duplicates
     *
     * Time complexity: O(N! * N)
     * Space complexity:* O(N)*
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    private void helper(int[] nums, List<List<Integer>> result, List<Integer> current, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            current.add(nums[i]);
            helper(nums, result, current, used);

            current.remove(current.size() - 1);
            used[i] = false;

            // Check for duplicates & skip it
            // index + 1 < nums.length to avoid array out of bound error
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}
