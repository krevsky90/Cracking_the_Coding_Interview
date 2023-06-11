package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

import java.util.HashMap;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=9519
 *
 * Write a function countConstruct(target, wordBank) that accepts a target string and an array of strings.
 * The function should return the number of ways that the target can be constructed by concatenating elements of the wordBank array
 * You can reuse elements of wordBank.
 */
public class P7_CountConstruct {
    public static void main(String[] args) {
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        int result1 = countConstructMemo(target1, wordBank1);
        System.out.println(result1);

        String target2 = "enterapotentpot";
        String[] wordBank2 = new String[]{"a", "p", "ent", "enter", "ot", "o", "t"};
        int result2 = countConstructMemo(target2, wordBank2);
        System.out.println(result2);

        String target3 = "eeeeeeeeeeeeeeeeeeeeeeeef";
        String[] wordBank3 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        int result3 = countConstructMemo(target3, wordBank3); //works too long without memoization. BUT honestly I didn't notice any
        System.out.println(result3);
    }

    /**
     *
     * time to solve ~ 7 mins
     * time to debug on paper ~ 4 mins
     * 1 attempt
     * time ~ O(wordBankLen*targetLen*targetLen), where targetLen - length of target word. *targetLen is because of the call of substring function
     * space ~ O(targetLen^2) - since deep of the tree = targetLen. and each subTarget has length <= targetLen
     * NOTE: adding memo map, the key should be the value that varies from call to call - it is target
     */
    public static int countConstructMemo(String target, String[] wordBank) {
        Map<String, Integer> memo = new HashMap<>();
        return countConstructHelperMemo(target, wordBank, memo);
    }

    public static int countConstructHelperMemo(String target, String[] wordBank, Map<String, Integer> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return 1;

        int count = 0;
        for (String word : wordBank) {
            if (target.startsWith(word)) {
                String subTarget = target.substring(word.length());
                count += countConstructHelperMemo(subTarget, wordBank, memo);
            }
        }

        memo.put(target, count);
        return count;
    }
}
