package solving_techniques.p2_TwoPointers;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638f6ff2ae53511bdc36490d
 * OR
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/description/
 * <p>
 * <p>
 * Problem Statement
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 * <p>
 * Example 1:
 * Input: [-3, 0, 1, 2, -1, 1, -2]
 * Output: [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]
 * Explanation: There are four unique triplets whose sum is equal to zero. smallest sum.
 * <p>
 * Example 2:
 * Input: [-5, 2, -1, -2, 3]
 * Output: [[-5, 2, 3], [-2, -1, 3]]
 * Explanation: There are two unique triplets whose sum is equal to zero.
 */
public class TripletSumToZero {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 18 mins
     * time ~ O(n^2)
     * 1 attempt
     */
    public List<List<Integer>> threeSumKREV(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();

        Arrays.sort(nums);  // O(n*logN)

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int tempSum = nums[i] + nums[left] + nums[right];
                if (tempSum == 0) {
                    resultSet.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (tempSum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(resultSet);
    }

    /**
     * https://www.nileshblog.tech/leet-code-three-3-sum-java-cpp-python-solution/#Java_Two_Pointer_Approach
     * the same idea but OPTIMIZED reg duplicates
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();

        if (nums.length < 3) {
            return answer;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                break;
            }

            //to exclude duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum > 0) {
                    high--;
                } else if (sum < 0) {
                    low++;
                } else {
                    answer.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    int lastLowOccurrence = nums[low];
                    int lastHighOccurrence = nums[high];

                    //to exclude duplicates (this cycle moves low at least one time, as in my solution)
                    while (low < high && nums[low] == lastLowOccurrence) {
                        low++;
                    }

                    //to exclude duplicates (this cycle moves high at least one time, as in my solution)
                    while (low < high && nums[high] == lastHighOccurrence) {
                        high--;
                    }
                }
            }
        }
        return answer;
    }
}
