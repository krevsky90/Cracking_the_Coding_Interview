package solving_techniques.p2_TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 4Sum II
 * https://leetcode.com/problems/4sum-ii/description/
 *
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the ? of tuples (i, j, k, l) such that:
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * Example 1:?
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * Example 2:
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 *
 * Constraints:
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 */
public class FourSums_II {
    /**
     * NOT SOLVED by myself!
     * key distinctions from FourSums:
     * 1) several arrays
     * 2) do not exclude duplicates
     * 3) DO NOT APPLY TWO POINTERS method!!!
     *
     * idea:
     * 1) store all possible sums of the elements from nums1 and nums2, and its amount
     * 2) count all possible sums of the elements from nums3 and nums3 and check if -<tempSum34> exists in map
     * if yes - then increase the result by the value from map
     *
     * time to solve ~ 50 mins
     * tine ~ O(n^2)
     *
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sums12 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int tempSum12 = nums1[i] + nums2[j];
                //store how many time the sum = tempSum12 occures
                sums12.put(tempSum12, sums12.getOrDefault(tempSum12, 0) + 1);
            }
        }

        int result = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int tempSum34 = nums3[i] + nums4[j];
                //check if the map sums12 contains key = sum = -tempSum34
                //in this case means that tempSum12 + tempSum34 = nums1[..] + nums2[..] + nums3[..] + nums4[..] = 0 for some indexes,
                //and it happened sums12.get(-tempSum34) times!
                if (sums12.containsKey(-tempSum34)) {
                    result += sums12.get(-tempSum34);
                }
            }
        }

        return result;
    }
}
