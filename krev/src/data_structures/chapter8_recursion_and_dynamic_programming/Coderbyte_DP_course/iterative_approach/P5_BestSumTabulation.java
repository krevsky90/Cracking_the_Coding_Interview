package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=14843
 *
 * see description of P5_BestSum
 */
public class P5_BestSumTabulation {
    public static void main(String[] args) {
        List<Integer> result1 = bestSum(7, new int[]{5, 3, 4, 7});
        if (result1 != null) result1.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result2 = bestSum(8, new int[]{2, 3, 5});
        if (result2 != null) result2.stream().forEach(System.out::print);
        System.out.println("");
        List<Integer> result3 = bestSum(8, new int[]{1, 4, 5});
        if (result3 != null) result3.stream().forEach(System.out::print);
        System.out.println("");
    }

    /**
     * time to solve ~ 20-25 mins
     * 1 attempt
     * time complexity ~ O(targetSum*numbers.length*targetSum) - *targetSum it because of copying of subResult to newSubResult
     * space complexity ~ O(targetSum*targetSum) - since the length of map (instead of array is <= targetSum. and each list if the map contains not more than targetSum elements
     */
    public static List<Integer> bestSum(int targetSum, int[] numbers) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        memo.put(0, new ArrayList<>());

        for (int i = 0; i <= targetSum; i++) {
            List<Integer> curList = memo.get(i);
            if (curList != null) {
                for (int n : numbers) {
                    int key = i + n;
                    if (key > targetSum) continue;

                    List<Integer> keyList = memo.get(key);
                    //the expression 'keyList.size() > curList.size() + 1' means that existing list is longer than new potential list = curList + n
                    if (keyList == null || keyList.size() > curList.size() + 1) {
                        keyList = new ArrayList<>(curList);
                        keyList.add(n);
                        memo.put(key, keyList);
                    }
                }
            }
        }

        return memo.get(targetSum);
    }
}
