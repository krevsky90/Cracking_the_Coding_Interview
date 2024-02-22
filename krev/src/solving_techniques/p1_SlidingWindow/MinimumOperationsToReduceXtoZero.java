package solving_techniques.p1_SlidingWindow;

/**
 * 1658. Minimum Operations to Reduce X to Zero
 * https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero
 * <p>
 * You are given an integer array nums and an integer x.
 * In one operation, you can either remove the leftmost or the rightmost element from the array nums
 * and subtract its value from x. Note that this modifies the array for future operations.
 * <p>
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
 * <p>
 * Example 1:
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * <p>
 * Example 2:
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * <p>
 * Example 3:
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements
 * (5 operations in total) to reduce x to zero.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 */
public class MinimumOperationsToReduceXtoZero {
    //the same idea + thoughts reg "why don't we use DP in this case" are here
    //https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/solutions/2136570/change-your-perspective-java-explanation/
    //BUT the difference between this solutio and mine is additional condition (start <= end, i.e. l <= r)
    public int minOperationsLeetcode(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) sum += num;

        int maxLength = -1, currSum = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            currSum += nums[r];
            while (l <= r && currSum > sum - x) currSum -= nums[l++];
            if (currSum == sum - x) maxLength = Math.max(maxLength, r - l + 1);
        }

        return maxLength == -1 ? -1 : nums.length - maxLength;
    }

    /**
     * KREVSKY SOLUTION:
     * idea: we need to find the most wide sliding window (start and end positions) that contains the numbers with sum = totalSum
     * corner cases:
     * - totalSum < 0
     * - totalSum = 0
     * time to solve ~ 34 mins (about 20 mins were spent due to corner cases
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 3 attempts:
     * - forgot corner case 1
     * - forgot corner case 2
     */
    public int minOperations(int[] nums, int x) {
        //use sliding window
        int totalSum = 0;
        for (int n : nums) {
            totalSum += n;
        }

        totalSum -= x;
        if (totalSum < 0) {
            return -1;
        } else if (totalSum == 0) {
            return nums.length;
        }

        // 1,1 x = 3
        // ts = -1
        // end = 0
        // start = 1
        // sum = 0
        //
        int start = 0;
        int sum = 0;
        int maxWindowLength = -1;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (sum > totalSum) {
                sum -= nums[start];
                start++;
            }

            if (sum == totalSum) {
                maxWindowLength = Math.max(maxWindowLength, end - start + 1);
            }
        }

        if (maxWindowLength == -1) return -1;

        return nums.length - maxWindowLength;
    }
}
