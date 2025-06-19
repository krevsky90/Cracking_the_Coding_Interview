package solving_techniques.p28_MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. Next Greater Element II (medium)
 * https://leetcode.com/problems/next-greater-element-ii
 * <p>
 * #Copnany (19.06.2025): 0 - 3 months Amazon 9 Meta 4 Google 3 0 - 6 months Bloomberg 6 Microsoft 3 6 months ago Uber 6 TikTok 5 Apple 4 Zeta 3 Adobe 3 Yahoo 3 Walmart Labs 2
 * <p>
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 * <p>
 * Example 1:
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class NextGreaterElement2 {
    /**
     * KREVSKY SOLUTION:
     * idea: monotonic stack + circular loop implemented by i % length
     * <p>
     * time to solve ~ 20 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * attempts:
     * - res[i] instead of res[i % n]
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();   //keep indices of elements
        int n = nums.length;
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek() % n] <= nums[i % n]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                res[i % n] = nums[stack.peek() % n];
            }

            stack.add(i);
        }

        return res;
    }
}
