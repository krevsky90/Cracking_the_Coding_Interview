package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

import com.sun.xml.internal.bind.AnyTypeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=5373
 * <p>
 * White a function 'howSum(targetSum, numbers) that takes in a targetSum ans an array of numbers as arguments
 * <p>
 * The function should return an array containing any combination of elements that add up to exactly the targetSum.
 * If there is no combination, then return null
 */
public class P4_HowSum {
    public static void main(String[] args) {
//        List<Integer> result = howSum(7, new int[]{3, 2});
        List<Integer> result = howSumMemo(300, new int[]{7, 14});
        if (result == null) {
            System.out.println("null");
        } else {
            result.stream().forEach(System.out::print);
        }
    }

    /**
     * time to solve ~ 25 mins
     * 1 attempt
     * time ~ O(arrlen^(targetSum/minElement)) ~ O(arrlen^targetSum))
     * space ~ O(targetSum/minElement) ~ O(targetSum)
     */
    public static List<Integer> howSum(int targetSum, int[] numbers) {
        List<Integer> result = new ArrayList<>();
        howSum2(targetSum, numbers, result);
        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }
    }

    private static boolean howSum1(int targetSum, int[] numbers, List<Integer> result) {
        if (targetSum == 0) return true;
        if (targetSum < 0) {
            //remove the last value from the result
            result.remove(result.size() - 1);
            return false;
        }

        for (int i = 0; i < numbers.length; i++) {
            //try to add new value to the result before further checking
            result.add(numbers[i]);
            if (howSum1(targetSum - numbers[i], numbers, result)) {
                return true;
            }
        }

        if (!result.isEmpty()) {
            //remove the last value from the result
            result.remove(result.size() - 1);
        }
        return false;
    }

    private static boolean howSum2(int targetSum, int[] numbers, List<Integer> result) {
        if (targetSum == 0) return true;
        if (targetSum < 0) {
            return false;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (howSum2(targetSum - numbers[i], numbers, result)) {
                result.add(numbers[i]);
                return true;
            }
        }

        return false;
    }

    /**
     * Memo approach - NOTE! in this case we have to return (and cash) List rather then boolean! since we store to memo the path of numbers, but not boolean!
     * time to solve +15 mins
     * 1 attempt
     * time ~ O(arrlen*targetSum)
     * space ~ O(targetSum*targetSum) - since we store memo that may contain 'targetSum' different keys and each of them may contain list with length < targetSum
     *      (for case for path sum 10 = 1 1 1 1 1 1 1 1 1 1)
     */
    public static List<Integer> howSumMemo(int targetSum, int[] numbers) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        List<Integer> result = howSumMemo(targetSum, numbers, memo);
        if (result == null || result.isEmpty()) {//null - for the function that returns List, emptiness - for the function that returns boolean
            return null;
        } else {
            return result;
        }
    }


    private static List<Integer> howSumMemo(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return new ArrayList<>();
        if (targetSum < 0) {
            return null;
        }

        for (int i = 0; i < numbers.length; i++) {
            List<Integer> tempResult = howSumMemo(targetSum - numbers[i], numbers, memo);
            if (tempResult != null) {
                tempResult.add(numbers[i]);
                memo.put(targetSum, tempResult);
                return memo.get(targetSum);
            }
        }

        memo.put(targetSum, null);
        return null;
    }
}
