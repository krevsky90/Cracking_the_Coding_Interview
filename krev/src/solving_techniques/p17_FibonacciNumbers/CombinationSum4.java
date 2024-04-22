package solving_techniques.p17_FibonacciNumbers;

import java.util.Arrays;

/**
 * 377. Combination Sum IV
 * https://leetcode.com/problems/combination-sum-iv
 * <p>
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * <p>
 * Example 2:
 * Input: nums = [9], target = 3
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * All the elements of nums are unique.
 * 1 <= target <= 1000
 * <p>
 * Follow up: What if negative numbers are allowed in the given array? How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */
public class CombinationSum4 {

    /**
     * FOLLOW-UP: https://medium.com/@isriramdesai/leetcode-combination-sum-iv-7ef1d5f19237
     * If negative numbers are allowed, that would lead to some arrays having infinite solutions!!
     * For example, let's say numbers = [1, -1, 2] and target is 3. For this example, we have infinite solutions ?
     * [1, 1, 1]
     * [1, 2]
     * [2, 1]
     * [1, -1, 1, 1, 1]
     * [1, -1, 1, -1, 1, 1, 1]
     * ?
     * As you can see this can go on forever!!
     * So if they want to allow negative numbers in the array, then they need to modify the question by saying you can not reuse the numbers.
     * In one way of getting the sum, we can not use the same number multiple times.
      */

    /**
     * KREVSKY SOLUTION #1: no memo
     * time to solve ~ 2 mins
     * <p>
     * 1 attempt
     */
    public int combinationSum4(int[] nums, int target) {
        if (target < 0) return 0;
        if (target == 0) return 1;

        int res = 0;
        for (int n : nums) {
            res += combinationSum4(nums, target - n);
        }

        return res;
    }

    //NOTE: in this problems is it better to use memo (top-down), since we have overlapping problems:

    /**
     * KREVSKY SOLUTION #1.2 + memo
     * <p>
     * NOTE: look at https://leetcode.com/problems/combination-sum-iv/solutions/4020218/98-22-dynamic-programming-recursion-with-memoization/
     * we need to sort nums before using top-down!
     * "Sorting allows us to iterate over the numbers in an ascending order.
     * This order plays a crucial role in our early exit strategy during the recursive calls,
     * ensuring we don't waste time on numbers that won't contribute to our solution."
     * <p>
     * HINT: got Time Limit Exceeded, since memo[..] = 0 may be real value! => ALWAYS fill memo[..] = -1 initially!
     * <p>
     * time to solve ~ 5 mins + 30 mins (to figure out that the right condition is memo[..] != -1
     *
     * BEATS = 100%, i.e. it is faster then bottom-up, because it does not count the cells of memo that are not needed
     */
    public int combinationSum4_topDown(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        Arrays.sort(nums);

        return combinationSum4Memo(nums, target, memo);
    }

    // target = 4
    // combinationSum4(4)
    //     memo[4] += combinationSum4(3) = 4
    //                 memo[3] += combinationSum4(2) = 2
    //                             memo[2] += combinationSum4(1) = 1
    //                                         memo[1] +=  combinationSum4(0) = 1
    //                                         memo[1] +=  combinationSum4(-1) = 0
    //                                         memo[1] +=  combinationSum4(-2) = 0
    //                                         memo[1] = 1
    //                             memo[2] += combinationSum4(0) = 1
    //                             memo[2] += combinationSum4(-1) = 0
    //                             memo[2] = 2
    //                 memo[3] += combinationSum4(1) = memo[1] = 1
    //                 memo[3] += combinationSum4(0) = 1
    //                 memo[3] = 4
    //     memo[4] += combinationSum4(2) = memo[2] = 2
    //     memo[4] += combinationSum4(1) = memo[1] = 1
    //     memo[4] = 7
    public int combinationSum4Memo(int[] nums, int target, int[] memo) {
        if (target < 0) return 0;
        if (target == 0) return 1;

        if (memo[target] != -1) return memo[target];

        int temp = 0;
        for (int n : nums) {
            temp += combinationSum4Memo(nums, target - n, memo);
        }

        return memo[target] = temp;
    }

    /**
     * KREVSKY SOLUTION #2: bottom-up + memo
     * time to solve 5 mins
     * <p>
     * 2 attempts:
     * - started for from i = 0, but need from 1
     * <p>
     * BEATS = 78%
     */
    public int combinationSum4_bottomUp(int[] nums, int target) {
        int[] memo = new int[target + 1];
        memo[0] = 1;

        for (int i = 1; i < target + 1; i++) {
            for (int n : nums) {
                if (i - n >= 0) {
                    memo[i] += memo[i - n];
                }
            }
        }
        return memo[target];
    }
}
