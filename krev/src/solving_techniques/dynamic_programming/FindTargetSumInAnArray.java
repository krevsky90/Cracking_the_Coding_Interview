package solving_techniques.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Explain how to find a target sum in an array.
 * https://www.tryexponent.com/questions/1439/find-target-sum-array#answers
 *
 */
public class FindTargetSumInAnArray {
    public static void main(String[] args) {
        // debug your code below
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println("Number of ways: " + changeSigns(nums, S));
    }

    public static int changeSigns(int[] nums, int S) {
        // your code goes here
        Map<List<Integer>, Integer> memo = new HashMap<>();    //list of [sum, start index] -> num of ways
        int res = countWays(nums, S, 0, memo);
        return res == 0 ? -1 : res;
    }

    private static int countWays(int[] nums, int S, int i, Map<List<Integer>, Integer> memo) {
        if (S == 0 && i == nums.length) return 1;
        if (i >= nums.length) return 0;
        if (memo.containsKey(Arrays.asList(S, i))) {
            return memo.get(Arrays.asList(S, i));
        }

        int result = 0;
        //take with +
        result += countWays(nums, S - nums[i], i + 1, memo);
        //take with -
        result += countWays(nums, S + nums[i], i + 1, memo);

        memo.put(Arrays.asList(S, i), result);
        return result;
    }
}
