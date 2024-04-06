package solving_techniques.p28_MonotonicStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. Next Greater Element I
 * https://leetcode.com/problems/next-greater-element-i
 * <p>
 * The next greater element of some element x in an array is the first greater element
 * that is to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
 * and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 * <p>
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * <p>
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 * <p>
 * Constraints:
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 * <p>
 * <p>
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 */
public class NextGreaterElement1 {
    /**
     * KREVSKY SOLUTION:
     * idea #1: use map 'value from nums1 to index of this value in nums1'
     * idea #2: (for follow-up section) use monotonic stack!!
     * <p>
     * time to solve ~ 12 mins
     * <p>
     * time ~ O(n + m), where n = nums1.length, m = nums2.length
     * space ~ O(n)
     *
     * 2 attempts:
     * - forgot ";"
     * <p>
     * BEATS = 46%
     *
     */
    // {{4,0},{1,1},{2,2}}
    // nums2 = 1 3 4 2
    // stack = 4 3
    // idxNums1 = 1
    // result = -1 3 -1
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            //since all values are unique, we can use nums1[i] as key
            map1.put(nums1[i], i);
        }

        Stack<Integer> stack = new Stack<>();
        for (int j = nums2.length - 1; j >= 0; j--) {
            if (map1.containsKey(nums2[j])) {
                while (!stack.isEmpty() && stack.peek() <= nums2[j]) {
                    stack.pop();
                }

                int idxNums1 = map1.get(nums2[j]);
                if (stack.isEmpty()) {
                    result[idxNums1] = -1;
                } else {
                    result[idxNums1] = stack.peek();
                }
            }

            //add j-th element to the stack anyway
            stack.add(nums2[j]);
        }

        return result;
    }
}
