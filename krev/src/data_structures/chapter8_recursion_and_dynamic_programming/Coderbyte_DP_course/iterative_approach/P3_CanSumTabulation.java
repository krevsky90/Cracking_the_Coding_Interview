package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

import java.util.HashMap;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=13080
 *
 * see description of P3_CanSum
 */
public class P3_CanSumTabulation {
    public static void main(String[] args) {
        boolean result = canSum(7, new int[]{5,4,2});
        System.out.println(result);
    }

    /**
     * idea: since we need to reach targetSum, we should store true/false for each number from 0 to targetSum.
     * i.e. we need to create array that has size ~ targetSum
     *
     * time to solve ~ 15 mins
     * 2 attempts
     * time complexity ~ O(targetSum*number.length)
     * space complexity ~ O(targetSum)
     */
    public static boolean canSum(int targetSum, int[] numbers) {
        boolean[] memo = new boolean[targetSum+1];
        memo[0] = true; //like base case
        for (int i = 0; i <= memo.length; i++) {
            if (memo[i]) {
                for (int j = 0; j < numbers.length; j++) {
                    if (i + numbers[j] == targetSum) {
                        return true;    //just a little improvement of performance
                    } else if (i + numbers[j] < targetSum) {
                        memo[i + numbers[j]] = true;
                    }
                }
            }
        }

        return memo[targetSum];
    }
}
