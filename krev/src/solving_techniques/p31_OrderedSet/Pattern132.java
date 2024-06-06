package solving_techniques.p31_OrderedSet;

import java.util.TreeSet;

/**
 * 456. 132 Pattern
 * https://leetcode.com/problems/132-pattern (medium)
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
 * such that i < j < k and nums[i] < nums[k] < nums[j].
 *
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 *
 * Example 2:
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 * Example 3:
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Pattern132 {
    /**
     * NOT SOLVED by myself
     */

    /**
     * SOLUTION #1: ordered set approach
     * info: https://leetcode.com/problems/132-pattern/solutions/4108507/java-solution-using-treeset/
     *
     * idea:
     * 1) fill array by minimum element that are more left than nums[i]
     * 2) put all elements to TreeSet and check for the element that is going to be added to TreeSet:
     *      - if it is bigger than some element in TreeSet
     *      - if it is bigger than minimum element in the array from p.1
     *
     * time ~ O(n) + O(n*logn) ~ O(nlogn)
     * space ~ O(n)
     *
     * BEATS = 5%
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int[] minimums = new int[n];
        int min = nums[0];
        minimums[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            minimums[i] = min;
        }

        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = n - 1; i >= 0; i--) {
            //if we have righter element that is less than nums[i] AND righter element is bigger than lefter element => true
            Integer right = ts.lower(nums[i]);
            if (right != null && right > minimums[i]) return true;

            ts.add(nums[i]);
        }

        return false;
    }

    /**
     * SOLUTION #2: monotonic stack approach
     * see src/solving_techniques/p28_MonotonicStack/Pattern132.java
     */
}
