package solving_techniques.prefixSum;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self
 *
 * #Company: Apple
 *
 * Given an integer array nums, return an array answer
 * such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductExceptSelf {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4}; //expected [24,12,8,6]
        //prefixes1 = 1 1 2 6
        //postfixes1 = 24 12 4 1

        int[] arr2 = {-1,1,0,-3,3}; //expected [0,0,9,0,0]

        int[] res1 = new ProductExceptSelf().productExceptSelf1(arr1);
        int[] res2 = new ProductExceptSelf().productExceptSelf1(arr2);
        Arrays.stream(res1).forEach(i -> System.out.print(i + " "));
        System.out.println("");
        Arrays.stream(res2).forEach(i -> System.out.print(i + " "));
    }

    /**
     * KREVSKY SOLUTION:
     * pattern: prefix sums
     * idea: store products as prefix and suffix (postfix) for each i-th position. then - multiply them
     *
     * time to solve ~ 15-20 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS = 89%
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int[] prefixes = new int[nums.length];
        Arrays.fill(prefixes, 1);

        int[] postfixes = new int[nums.length];
        Arrays.fill(postfixes, 1);

        for (int i = 1; i < nums.length; i++) {
            prefixes[i] = prefixes[i-1]*nums[i-1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            postfixes[i] = postfixes[i+1]*nums[i+1];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefixes[i]*postfixes[i];
        }

        return result;
    }

    /**
     * idea: we don't actually need separate array to store prefix product and suffix products,
     *      we can do all the approach from productExceptSelf1 onto our final answer array.
     * https://leetcode.com/problems/product-of-array-except-self/solutions/1342916/3-minute-read-mimicking-an-interview/
     *
     * time ~ O(n)
     * space ~ O(1)
     */

    public int[] productExceptSelf2(int[] nums) {
        //initially fill 'result' as 'prefixes' array
        int[] result = new int[nums.length];

        //instead of
        //Arrays.fill(result, 1);
        //        for (int i = 1; i < nums.length; i++) {
        //            result[i] = result[i-1]*nums[i-1];
        //        }
        int currProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = currProduct;
            currProduct *= nums[i];
        }

        //now change 'result' array as if we fill of 'postfixes' array, BUT starting from length - 1 position
        currProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= currProduct;
            currProduct *= nums[i];
        }

        return result;
    }

}