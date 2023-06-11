package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://youtu.be/oBt53YbR9Kk?t=10052
 *
 * Write a function allConstruct(target, wordBank) that accepts a target string and an array of strings.
 * The function should return 2D array containing all of the ways that the target can be constructed by concatenating elements of the wordBank array.
 * Each element of the 2D array should represent on combination that constructs the target
 * You can reuse elements of wordBank.
 */
public class P8_AllConstruct {
    public static void main(String[] args) {
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        allConstructMemo(target1, wordBank1);
    }


    /**
     * time to solve ~ 30+9 mins
     * 1 attempt
     * time ~ O(wordBankLen^targetLen), where targetLen - length of target word.
     * space ~ (targetLen) - since deep of the tree = targetLen. we don't include space of the result to space complexity parameter!
     */
    public static List<List<String>> allConstruct(String target, String[] wordBank) {
        List<List<String>> result = allConstructHelper(target, wordBank);
        printer(result);
        return result;
    }

    private static List<List<String>> allConstructHelper(String target, String[] wordBank) {
        List<List<String>> result = new ArrayList<>();
        if (target.isEmpty()) {
            List<String> emptyList = new ArrayList();
//            emptyList.add("");
            result.add(emptyList);
            return result;
        }

        for (String w : wordBank) {
            if (target.startsWith(w)) {
                String subTarget = target.substring(w.length());
                List<List<String>> subResult = allConstructHelper(subTarget, wordBank);
                if (!subResult.isEmpty()) {
                    for (List l : subResult) {
                        List<String> tempList = new ArrayList<>();
                        tempList.add(w);
                        tempList.addAll(l);
                        result.add(tempList);
                    }
                }
            }
        }

        return result;
    }

    /*
     * NOTE: since we need to go through all nodes, memoization doesn't decrease complexity!!!
     * time ~ O(wordBankLen^targetLen), where targetLen - length of target word.
     * space ~ (targetLen) - since deep of the tree = targetLen. we don't include space of the result to space complexity parameter!
     */
    public static List<List<String>> allConstructMemo(String target, String[] wordBank) {
        Map<String, List<List<String>>> memo = new HashMap<>();
        List<List<String>> result = allConstructHelperMemo(target, wordBank, memo);
        printer(result);
        return result;
    }

    private static List<List<String>> allConstructHelperMemo(String target, String[] wordBank, Map<String, List<List<String>>> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }

        List<List<String>> result = new ArrayList<>();
        if (target.isEmpty()) {
            List<String> emptyList = new ArrayList();
//            emptyList.add("");
            result.add(emptyList);
            return result;
        }

        for (String w : wordBank) {
            if (target.startsWith(w)) {
                String subTarget = target.substring(w.length());
                List<List<String>> subResult = allConstructHelperMemo(subTarget, wordBank, memo);
                if (!subResult.isEmpty()) {
                    for (List l : subResult) {
                        List<String> tempList = new ArrayList<>();
                        tempList.add(w);
                        tempList.addAll(l);
                        result.add(tempList);
                    }
                }
            }
        }

        memo.put(target, result);
        return result;
    }

    private static void printer(List<List<String>> data) {
        for (List l : data) {
            System.out.println(String.join(",", l));
        }
    }
}
