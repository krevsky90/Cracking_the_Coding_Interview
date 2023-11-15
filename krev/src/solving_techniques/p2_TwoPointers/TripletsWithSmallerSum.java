package solving_techniques.p2_TwoPointers;

import java.util.Arrays;

/**
 * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638f7a0a4544c65f117ba260
 * <p>
 * OR
 * LOCKED!
 * https://leetcode.com/problems/3sum-smaller/
 * <p>
 * ============================================================
 * Given an array arr of unsorted numbers and a target sum,
 * count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
 * Write a function to return the count of such triplets.
 * <p>
 * Example 1:
 * Input: [-1, 0, 2, 3], target=3
 * Output: 2
 * Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]
 * <p>
 * Example 2:
 * Input: [-1, 4, 2, 1, 3], target=5
 * Output: 4
 * Explanation: There are four triplets whose sum is less than the target:
 * [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 **/

public class TripletsWithSmallerSum {
    /**
     * solution: https://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/
     * time complexity ~ O(n^2)
     */
    public static int searchTriplets(int[] arr, int target) {
        if (arr == null || arr.length < 3) return -100500; //means invalid argument

        Arrays.sort(arr);    //O(n*logn)

        int result = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int j = i + 1;
            int k = arr.length - 1;

            while (j < k) {
                if (arr[i] + arr[j] + arr[k] < target) {
                    result += (k - j);
                    j++;
                } else {
                    k--;
                }
            }
        }

        return result;
    }
}
