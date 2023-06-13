package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/maximum-product-subarray/description/
 * <p>
 * Given an integer array nums, find a subarray that has the largest product, and return the product
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * <p>
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class Problem2_6_MaxProductSubArray_DynP {
    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, -2, 4};
        int[] arr2 = new int[]{-2, 0, -1};
        System.out.println(maxProduct(arr1));
        System.out.println(maxProduct(arr2));
    }

    /**
     * BEST EXPLANATION but I solved without special case 'nums[i] = 0'
     * https://www.youtube.com/watch?v=lXVy6YWFcRM
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;


        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, nums[i]);
        }

        int minSubProduct = 1;
        int maxSubProduct = 1;

        for (int i = 0; i < nums.length; i++) {
            int tempMin = Math.min(minSubProduct * nums[i], maxSubProduct * nums[i]);
            int tempMax = Math.max(minSubProduct * nums[i], maxSubProduct * nums[i]);
            minSubProduct = Math.min(nums[i], tempMin); //since we need to get min of three values
            maxSubProduct = Math.max(nums[i], tempMax); //since we need to get max of three values

            result = Math.max(result, maxSubProduct);
        }

        return result;
    }
}
