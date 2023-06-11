package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

import java.util.Date;

/**
 * https://youtu.be/oBt53YbR9Kk?t=15654
 * <p>
 * see description of P6_CanConstruct
 */
public class P6_CanConstructTabulation {
    public static void main(String[] args) {
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        boolean result1 = canConstruct(target1, wordBank1);
        System.out.println(result1);

        String target2 = "enterapotentpot";
        String[] wordBank2 = new String[]{"a", "p", "ent", "enter", "ot", "o", "t"};
        boolean result2 = canConstruct(target2, wordBank2);
        System.out.println(result2);
    }

    /**
     * NOT SOLVED without tip about the idea:
     * to create array with length = target.length() + 1. Each element corresponds to the letter (i.e. can we reach this letter or not)
     * 2 attempts
     * time to solve ~ 10 mins
     * time complexity ~ O(targetLen*targetLen*wordBankLen) because
     *      for i - needs O(targetLen),*
     *      for word - needs O(wordBankLen),
     *      target.startsWith - needs O(targetLen)
     *   NOTE: we don't tak into account target.substring - needs O(targetLen) because the complexity of for word covers it
     *      (i.e. wordBankLen*targetLen makes targetLen negligible)
     * space complexity ~ O(targetLen) - space for array
     *
     */
    public static boolean canConstructKrev(String target, String[] wordBank) {
        boolean[] arr = new boolean[target.length() + 1];
        arr[0] = true;

        for (int i = 0; i <= target.length(); i++) {
            if (arr[i]) {
                String prefix = target.substring(0, i);
                for (String word : wordBank) {
                    String newPrefix = prefix + word;
                    if (target.startsWith(newPrefix)) {
                        arr[newPrefix.length()] = true;
                    }
                }
            }
        }

        return arr[target.length()];
    }

    public static boolean canConstruct(String target, String[] wordBank) {
        boolean[] arr = new boolean[target.length() + 1];
        arr[0] = true;

        for (int i = 0; i <= target.length(); i++) {
            if (arr[i]) {
                for (String word : wordBank) {
                    int endBorder = Math.min(target.length(), i + word.length());   //Math.min to avoid outBound exception
                    String slice = target.substring(i, endBorder);
                    if (word.equals(slice)) {
                        arr[i + word.length()] = true;
                    }
                }
            }
        }

        return arr[target.length()];
    }

}
