package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=6729
 * <p>
 * Write a function 'bestSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments.
 * The function should return an array containing the SHORTEST combination of numbers that add up to exactly the targetSum.
 * If there is any tie for the shortest combination, you may return any one of the shortest.
 */
public class P5_BestSum {
    public static void main(String[] args) {
        List<Integer> result1 = bestSum(7, new int[]{5, 3, 4, 7});
        if (result1 != null) result1.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result2 = bestSum(8, new int[]{2, 3, 5});
        if (result2 != null) result2.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result3 = bestSum(8, new int[]{1,4,5});
        if (result3 != null) result3.stream().forEach(System.out::print);
        System.out.println("");
//        List<Integer> result4 = bestSum(100, new int[]{1,2,5,25});  //too long wihthout memoization
//        if (result4 != null) result4.stream().forEach(System.out::print);

        List<Integer> result11 = bestSumMemo(7, new int[]{5, 3, 4, 7});
        if (result11 != null) result11.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result12 = bestSumMemo(8, new int[]{2, 3, 5});
        if (result12 != null) result12.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result13 = bestSumMemo(8, new int[]{1,4,5});
        if (result13 != null) result13.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result14 = bestSumMemo(100, new int[]{1,2,5,25});
        if (result14 != null) result14.stream().forEach(System.out::print);
    }

    public static List<Integer> bestSum(int targetSum, int[] numbers) {
        return bestSumHelper(targetSum, numbers);
    }

    /**
     * bruce force solution
     * time to solve ~ 35 mins
     * 1 tip used
     * 1 attempt
     * time ~ O(arrlen^(targetSum/minElement)) ~ O(arrlen^targetSum))
     * space ~ (targetSum*targetSum) - since we have ~ targetSum deep recursion, and shortestList contains ~ targetSum amount of element
     *
     */
    private static List<Integer> bestSumHelper(int targetSum, int[] numbers) {
        if (targetSum == 0) return new ArrayList<>();
        if (targetSum < 0) return null;

        List<Integer> tempResult = null;
        List<Integer> shortestList = null;
        int shortestSize = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            tempResult = bestSumHelper(targetSum - numbers[i], numbers);
            if (tempResult != null) {
                tempResult.add(numbers[i]);
                if (tempResult.size() < shortestSize) {
                    shortestSize = tempResult.size();
                    shortestList = tempResult;
                }
            }
        }

        return shortestList;
    }

    /**
     * memoization solution
     */
    public static List<Integer> bestSumMemo(int targetSum, int[] numbers) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        return bestSumHelperMemo(targetSum, numbers, memo);
    }

    /**
     * time to solve ~ +5 mins
     * 2 attempts
     * time ~ O(arrlen*targetSum))
     * space ~ O(targetSum/minElement) ~ O(targetSum*targetSum) - since we have ~ targetSum deep recursion, and shortestList contains ~ targetSum amount of element
     *
     */
    private static List<Integer> bestSumHelperMemo(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return new ArrayList<>();
        if (targetSum < 0) return null;

        List<Integer> tempResult = null;
        List<Integer> shortestList = null;
        int shortestSize = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            tempResult = bestSumHelperMemo(targetSum - numbers[i], numbers, memo);
            if (tempResult != null) {
                tempResult.add(numbers[i]);
                if (tempResult.size() < shortestSize) {
                    shortestSize = tempResult.size();
                    shortestList = tempResult;
                }
            }
        }

        memo.put(targetSum, shortestList);
        return shortestList;
    }
}
