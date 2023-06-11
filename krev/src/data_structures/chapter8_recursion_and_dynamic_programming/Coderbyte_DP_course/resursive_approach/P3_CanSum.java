package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

import java.util.HashMap;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=4200
 *
 * Write a function 'canSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments
 * The function should return a boolean indicating whether or not it is possible to generate the targetSum using numbers from the array
 *
 * You may use as element of the array as many times as needed
 * You may assume that all input numbers are non-negative
 */
public class P3_CanSum {

    public static void main(String[] args) {
        boolean result = canSum2(7, new int[]{5,4,2});
        System.out.println(result);
    }

    /**
     * time to visualize ~ 10 mins
     * time to write code ~ 6 mins
     * 1 attempt
     * time ~ O(arrlen^(targetSum/minElement)) ~ O(arrlen^targetSum))
     * space ~ O(targetSum/minElement) ~ O(targetSum)
     */
    public static boolean canSum1(int targetSum, int[] numbers) {
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;

        for (int i = 0; i < numbers.length; i++) {
            if (canSum1(targetSum - numbers[i], numbers)) {
                return true;
            }
        }

        return false;
    }

    /**
     * time to write code ~ 10 mins
     * 2 attempts
     * time ~ O(arrlen*targetSum)
     * space ~ O(targetSum)
     *
     */
    public static boolean canSum2(int targetSum, int[] numbers) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return canSumMemo(targetSum, numbers, memo);
    }

    private static boolean canSumMemo(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;

        for (int i = 0; i < numbers.length; i++) {
            if (canSumMemo(targetSum - numbers[i], numbers, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }

        memo.put(targetSum, false);
        return false;


    }
}
