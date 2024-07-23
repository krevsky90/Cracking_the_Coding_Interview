package solving_techniques.p1_SlidingWindow;

/**
 * 487. Max Consecutive Ones II (meduim) (blocked)
 * https://leetcode.com/problems/max-consecutive-ones-ii
 * OR
 * https://leetcode.ca/all/487.html
 * <p>
 * #Company: Yandex
 * <p>
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * <p>
 * Example 1:
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation:
 * - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
 * The max number of consecutive ones is 4.
 * <p>
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 * Explanation:
 * - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
 * The max number of consecutive ones is 4.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * <p>
 * Follow up: What if the input numbers come in one by one as an infinite stream?
 * In other words, you can't store all numbers coming from the stream as it's too large to hold in memory.
 * Could you solve it efficiently?
 */
public class MaxConsecutiveOnes2 {
    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, 1, 1, 0};
        MaxConsecutiveOnes2 obj = new MaxConsecutiveOnes2();
        System.out.println(obj.findMaxConsecutiveOnes(arr1));
    }

    /**
     * KREVSKY SOLUTION
     * or info: https://leetcode.ca/2017-03-31-487-Max-Consecutive-Ones-II/
     * idea: sliding window
     * <p>
     * time ~ O(n)
     * time to solve ~ 8 mins
     * <p>
     * 1 attempt
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int start = 0;
        int k = 1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                k--;
            }

            while (k < 0) {
                if (nums[start] == 0) {
                    k++;
                }
                start++;
            }

            res = Math.max(res, i - start + 1);
        }

        return res;
    }


}
