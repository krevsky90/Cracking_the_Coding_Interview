package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

import java.util.TreeSet;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/third-maximum-number/description/
 * <p>
 * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.
 */
public class Problem1_3_ThirdLargestNumber {
    public int thirdMaxKREV(int[] nums) {
        // 1 2 5 3 7 4
        // m m m
        // 1 0 0
        // 2 1 0
        // 5 2 1
        // 5 3 2
        // 7 5 3
        // 7 5 4

        // 2 2 3 1
        // m m m
        // 2 m m
        // 3 2 m
        // 3 2 1

        Integer m1 = null;
        Integer m2 = null;
        Integer m3 = null;
        for (int i = 0; i < nums.length; i++) {
            if (
                    (m1 != null && m1 == nums[i]) ||
                            (m2 != null && m2 == nums[i]) ||
                            (m3 != null && m3 == nums[i])) {
                continue;
            }

            if (m1 == null || nums[i] > m1) {
                m3 = m2;
                m2 = m1;
                m1 = nums[i];
            } else if (m2 == null || nums[i] > m2) {
                m3 = m2;
                m2 = nums[i];
            } else if (m3 == null || nums[i] > m3) {
                m3 = nums[i];
            }
        }

        if (m3 == null) {
            return m1;
        } else {
            return m3;
        }
    }

    /**
     * https://leetcode.com/problems/third-maximum-number/solutions/2614958/third-maximum-number/
     */
    public int thirdMax(int[] nums) {
        // Sorted set to keep elements in sorted order.
        TreeSet<Integer> sortedNums = new TreeSet<Integer>();
        // Iterate on all elements of 'nums' array.
        for (int num : nums) {
            // Do not insert same element again.
            if (sortedNums.contains(num)) {
                continue;
            }

            // If sorted set has 3 elements.
            if (sortedNums.size() == 3) {
                // And the smallest element is smaller than current element.
                if (sortedNums.first() < num) {
                    // Then remove the smallest element and push the current element.
                    sortedNums.pollFirst();
                    sortedNums.add(num);
                }
            }
            // Otherwise push the current element of nums array.
            else {
                sortedNums.add(num);
            }
        }

        // If sorted set has three elements return the smallest among those 3.
        if (sortedNums.size() == 3) {
            return sortedNums.first();
        }

        // Otherwise return the biggest element of nums array.
        return sortedNums.last();
    }
}
