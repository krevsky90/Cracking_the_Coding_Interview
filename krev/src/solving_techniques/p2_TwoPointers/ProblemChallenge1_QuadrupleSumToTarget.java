package solving_techniques.p2_TwoPointers;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638f9cab70b3327d7a210d9d
 * OR
 * 18. 4Sum
 * https://leetcode.com/problems/4sum/
 * <p>
 * Given an array of unsorted numbers and a target number, find all unique quadruplets in it, whose sum is equal to the target number.
 * <p>
 * Example 1:
 * <p>
 * Input: [4, 1, 2, -1, 1, -3], target=1
 * Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
 * Explanation: Both the quadruplets add up to the target.
 * Example 2:
 * <p>
 * Input: [2, 0, -1, 1, -2, 2], target=2
 * Output: [-2, 0, 2, 2], [-1, 0, 1, 2]
 * Explanation: Both the quadruplets add up to the target.
 */
public class ProblemChallenge1_QuadrupleSumToTarget {
    // [1,0,-1,0,-2,2], target = 0
    // length = 6
    // -2,-1,0,0,1,2
    // i1 = 1
    // i2 = 2
    // left = 4
    // right = 3
    // tempSum = 1
    // result = [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 12 mins
     * time to debug ~ 7 mins
     * idea is the same as Cracking_the_Coding_Interview\krev\src\solving_techniques\p2_TwoPointers\TripletSumToZero,
     * but +one variable (=> +one loop)
     * <p>
     * +improvements regarding duplicates from https://leetcode.com/problems/4sum/solutions/4277837/best-solution-in-java-pointer-approach-tc-o-n-3-sc-o-1/
     * <p>
     * time ~ O(n^3)
     * <p>
     * 3 attempts (due to int => long converting, I thought that max_int = 2^32m rather than 2^31 - 1)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
//        Set<List<Integer>> result = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i1 = 0; i1 < nums.length - 3; i1++) {
            //avoid duplicates
            if (i1 > 0 && nums[i1] == nums[i1 - 1]) continue;

            for (int i2 = i1 + 1; i2 < nums.length - 2; i2++) {
                //avoid duplicates
                if (i2 > i1 + 1 && nums[i2] == nums[i2 - 1]) continue;

                int left = i2 + 1;
                int right = nums.length - 1;
                while (left < right) {
                    //NOTE: we canNOT use int type for tempSum, nums[i] <= 10^9. Then sum of 4 nums <= 4*10^9.
                    // BUT max int value = 2^31 - 1 = 2.14*10^9 => this is less than 4*10^9
                    // that's why we have to use long
                    long tempSum = (long) nums[i1] + (long) nums[i2] + (long) nums[left] + (long) nums[right];
                    if (tempSum == target) {
                        result.add(Arrays.asList(nums[i1], nums[i2], nums[left], nums[right]));
                        left++;
                        right--;

                        // skip the duplicates:
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (tempSum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }

//        return new ArrayList<>(result);
        return result;
    }
}