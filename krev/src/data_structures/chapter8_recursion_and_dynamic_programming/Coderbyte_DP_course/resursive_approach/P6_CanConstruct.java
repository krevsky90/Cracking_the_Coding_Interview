package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=7969
 *
 * Write a function canConstruct(target, wordBank) that accepts a target string and an array of strings.
 * The function should return a boolean indicating whether or not the 'target' can be constructed by concatenating elements of the 'wordBank' array.
 * You can reuse elements of wordBank.
 */
public class P6_CanConstruct {
    public static void main(String[] args) {
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        boolean result1 = canConstruct(target1, wordBank1);
        System.out.println(result1);

        String target2 = "enterapotentpot";
        String[] wordBank2 = new String[]{"a", "p", "ent", "enter", "ot", "o", "t"};
        boolean result2 = canConstruct(target2, wordBank2);
        System.out.println(result2);

        Date startTime = new Date();
        String target3 = "eeeeeeeeeeeeeeeeeeeeeeeef";
        String[] wordBank3 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        boolean result3 = canConstructMemo(target3, wordBank3); //works too long without memoization. BUT honestly I didn't notice any
        System.out.println(result3);
        System.out.println(new Date().getTime() - startTime.getTime());

    }

    /**
     * bruce force solution
     * time to solve ~ 5 mins
     * 1 attempt
     * time ~ O(targetLen*wordBankLen^targetLen), where targetLen - length of target word. targetLen* is because of the call of substring function
     * space ~ (targetLen^2) - since deep of the tree = targetLen. and each subTarget has length <= targetLen
     *
     */
    public static boolean canConstruct(String target, String[] wordBank) {
        return canConstructHelper(target, wordBank);
    }

    private static boolean canConstructHelper(String target, String[] wordBank) {
        if (target.isEmpty()) return true;

        for (String word : wordBank) {
            if (target.startsWith(word)) {
                String subTarget = target.substring(word.length());
                boolean result = canConstruct(subTarget, wordBank);
                if (result) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * memoization solution
     * time to solve ~ +2 mins
     * 1 attempt
     * time ~ O(wordBankLen*targetLen*targetLen), where targetLen - length of target word. *targetLen is because of the call of substring function
     * space ~ (targetLen^2) - since deep of the tree = targetLen. and each subTarget has length <= targetLen
     *
     * NOTE: adding memo map, the key should be the value that varies from call to call - it is target
     */
    public static boolean canConstructMemo(String target, String[] wordBank) {
        //add memo map, where key is target, value is boolean result
        Map<String, Boolean> memo = new HashMap<>();
        return canConstructHelperMemo(target, wordBank, memo);
    }

    private static boolean canConstructHelperMemo(String target, String[] wordBank, Map<String, Boolean> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return true;

        for (String word : wordBank) {
            if (target.startsWith(word)) {
                String subTarget = target.substring(word.length());
                boolean result = canConstruct(subTarget, wordBank);
                if (result) {
                    memo.put(target, true);
                    return true;
                }
            }
        }

        memo.put(target, false);
        return false;
    }

}
