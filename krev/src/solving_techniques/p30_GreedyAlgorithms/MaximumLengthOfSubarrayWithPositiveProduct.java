package solving_techniques.p30_GreedyAlgorithms;

/**
 * 1567. Maximum Length of Subarray With Positive Product (medium)
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product
 * <p>
 * #Company (3.02.2025): 6 months ago Amazon 9 Arcesium 3
 * <p>
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 * <p>
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 * <p>
 * Return the maximum length of a subarray with positive product.
 * <p>
 * Example 1:
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * <p>
 * Example 2:
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 * <p>
 * Example 3:
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {
    /**
     * NOT SOLVED by myself (BUT solved with hints)
     * idea:
     * 1) split array into subarray by 0
     * 2.1) if subarray has even amount of neg numbers => product of this subarray > 0 => update max length by the length of this subarray (if it is bigger)
     * 2.2) (did not reach this idea by myself): if amont of neg number is odd => check the length of its 2 subarrays:
     * -from the first neg number to subarray's end
     * -from subarray's beginning to the last neg number of it
     * <p>
     * time to solve ~ 25 mins
     * <p>
     * 2 attempts:
     * - forgot to clear temp variables in the end of if (nums[i] == 0)
     *
     * BEATS ~ 98%
     */
    public int getMaxLen(int[] nums) {
        int start = 0;
        int amountOfNeg = 0;
        int res = 0;
        int firstNegIdx = -1;
        int lastNegIdx = -1;
        for (int i = 0; i <= nums.length; i++) {
            if (i == nums.length || nums[i] == 0) {
                //handle subarray [start, i-1]:
                //example: -1 -1 1 -1 1 1 - odd amount of neg values
                if (amountOfNeg % 2 == 0) {
                    res = Math.max(res, i - start);
                } else {
                    //have two choices:
                    //1) to remove the prefix till the first negative element in this subarray,
                    //2) or to remove the suffix starting from the last negative element in this subarray.
                    if (lastNegIdx == -1) {
                        //i.e. we have the only one neg element in the interval
                        //example: 1 -1 1 1 0
                        res = Math.max(res, Math.max(firstNegIdx - start, i - 1 - firstNegIdx));
                    } else {
                        //i.e. there are at least 3 neg elements
                        //example: 1 -1 1 1 1 -1 1 -1 1 0
                        res = Math.max(res, Math.max(lastNegIdx - start, i - 1 - firstNegIdx));
                    }
                }

                start = i + 1;
                amountOfNeg = 0;
                firstNegIdx = -1;
                lastNegIdx = -1;
            } else if (nums[i] < 0) {
                amountOfNeg++;
                if (firstNegIdx == -1) {
                    firstNegIdx = i;
                } else {
                    //set/update lastNegIdx
                    lastNegIdx = i;
                }
            }
        }

        return res;
    }

}
