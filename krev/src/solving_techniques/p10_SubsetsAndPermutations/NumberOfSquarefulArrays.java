package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 996. Number of Squareful Arrays
 * https://leetcode.com/problems/number-of-squareful-arrays (hard)
 * #Company: Apple
 * <p>
 * An array is squareful if the sum of every pair of adjacent elements is a perfect square.
 * <p>
 * Given an integer array nums, return the number of permutations of nums that are squareful.
 * <p>
 * Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].
 * <p>
 * Example 1:
 * Input: nums = [1,17,8]
 * Output: 2
 * Explanation: [1,8,17] and [17,8,1] are the valid permutations.
 * <p>
 * Example 2:
 * Input: nums = [2,2,2]
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= nums.length <= 12
 * 0 <= nums[i] <= 10^9
 */
public class NumberOfSquarefulArrays {
    int res = 0;

    public static void main(String[] args) {
        NumberOfSquarefulArrays obj = new NumberOfSquarefulArrays();
        obj.numSquarefulPerms(new int[]{2, 2, 2});
        System.out.println(obj.res);    //expected 1

        obj.res = 0;

        obj.numSquarefulPerms(new int[]{1, 17, 8});
        System.out.println(obj.res);    //expected 2
    }

    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums);
        permutations(nums, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    private void permutations(int[] nums, List<Integer> tempList, boolean[] visited) {
        if (tempList.size() == nums.length) {
            res++;
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            //NOT OBVIOUS to check visited[i-1] when you start debugging the code with duplicates
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) continue;

            //optimization: there is no sense to continue work with this temp sequence it is already can't be perfect aquare sequence
            if (!tempList.isEmpty() && !isPerfectSquare(tempList.get(tempList.size() - 1) + nums[i])) continue;

            visited[i] = true;
            tempList.add(nums[i]);
            permutations(nums, tempList, visited);
            tempList.remove(tempList.size() - 1);
            visited[i] = false;
        }
    }

    private boolean isPerfectSquare(int n) {
        return Math.sqrt(n) % 1 == 0;
    }

    //finally unused
    private boolean isPerfectSquare(List<Integer> tempList) {
        if (tempList.size() == 1) {
            return Math.sqrt(tempList.get(0)) % 1 == 0;
        }

        for (int i = 0; i < tempList.size() - 1; i++) {
            if (Math.sqrt(tempList.get(i) + tempList.get(i + 1)) % 1 != 0) {
                return false;
            }
        }

        return true;
    }
}