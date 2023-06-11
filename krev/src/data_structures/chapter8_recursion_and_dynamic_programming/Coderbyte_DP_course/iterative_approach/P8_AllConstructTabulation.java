package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

import java.util.ArrayList;
import java.util.List;

/**
 * https://youtu.be/oBt53YbR9Kk?t=17430
 *
 * see description of P8_AllConstruct
 */
public class P8_AllConstructTabulation {
    public static void main(String[] args) {
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        List<List<String>> result = allConstructKrev(target1, wordBank1);
        printer(result);
    }

    /**
     * time to solve ~ 1h 10 mins (50 mins to write the whole soluton + 20 mins to rewrite List to array. BUT I could contunie using List if I initialize its elements as emptyList)
     * 4 attempts
     * the main problem is to rewrite List to array (otherwise we have problems with resut.get and result.add(ind, val) methods)
     * NOT SURE! since in the video time complexity ~ O(wordBankLen^targetLen)
     *      time complexity ~ O(targetLen*targetLen*wordBankLen*targetLen)
     *      for i - targetLen
     *      for tempList - targetLen (each letter is separate element)
     *      for w - wordBankLen
     *      target.substring - targetLen AND newList = .. - also targetLen -> summary targetLen
     * space complexity ~ O(wordBankLen^targetLen) ??
     */
    public static List<List<String>> allConstructKrev(String target, String[] wordBank) {
        List<List<String>>[] result = new ArrayList[target.length() + 1];

        List<String> emptyList = new ArrayList<>();
//        emptyList.add("");

        List<List<String>> emptyLists = new ArrayList<>();
        emptyLists.add(emptyList);

        result[0] = emptyLists;

        for (int i = 0; i <= target.length(); i++) {
            List<List<String>> tempLists = result[i];
            if (tempLists != null) {
                for (List<String> tempList : tempLists) {
                    for (String w : wordBank) {
                        int endBorder = Math.min(target.length(), i + w.length());
                        if (target.substring(i, endBorder).equals(w)) {
                            List<List<String>> endList = result[endBorder];
                            if (endList == null) {
                                endList = new ArrayList<>();
                            }
                            List<String> newList = new ArrayList<>(tempList);
                            newList.add(w);
                            endList.add(newList);
                            result[endBorder] = endList;
                        }
                    }
                }
            }
        }

        return result[target.length()];
    }

    private static void printer(List<List<String>> data) {
        for (List l : data) {
            System.out.println(String.join(",", l));
        }
    }
}
