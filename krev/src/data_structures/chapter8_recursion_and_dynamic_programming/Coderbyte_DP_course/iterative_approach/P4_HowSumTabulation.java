package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *https://youtu.be/oBt53YbR9Kk?t=13995
 *
 * see description of P4_HowSum
 */
public class P4_HowSumTabulation {
    public static void main(String[] args) {
        List<Integer> result = howSum(7, new int[]{3, 2});
//        List<Integer> result = howSum(300, new int[]{7, 14});
        if (result == null) {
            System.out.println("null");
        } else {
            result.stream().forEach(System.out::print);
        }
    }

    /**
     * time to solve ~ 25-30 mins
     * 1 attempt
     * time complexity ~ O(targetSum*numbers.length*targetSum) - *targetSum it because of copying of subResult to newSubResult
     * space complexity ~ O(targetSum*targetSum) - since the length of map (instead of array is <= targetSum. and each list if the map contains not more than targetSum elements
     */
    public static List<Integer> howSum(int targetSum, int[] numbers) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        memo.put(0, new ArrayList<>());	//base case

        for (int i = 0; i <= targetSum; i++) {
            List<Integer> curList = memo.get(i);
            if (curList != null) {
                for (int n : numbers) {
                    int key = i + n;
                    if (key > targetSum) continue;

                    List<Integer> keyList = memo.get(key);
                    //to avoid of rewriting. since if we already have some path to 'key' element, we do not need to find some other ways
                    if (keyList == null) {
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
