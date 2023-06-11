package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.iterative_approach;

/**
 * https://youtu.be/oBt53YbR9Kk?t=16692
 * <p>
 * see description of P7_CountConstruct
 */
public class P7_CountConstructTabulation {
    public static void main(String[] args) {
        String target1 = "abcdef";
        String[] wordBank1 = new String[]{"ab", "abc", "cd", "def", "abcd"};
        int result1 = countConstruct(target1, wordBank1);
        System.out.println(result1);

        String target2 = "enterapotentpot";
        String[] wordBank2 = new String[]{"a", "p", "ent", "enter", "ot", "o", "t"};
        int result2 = countConstruct(target2, wordBank2);
        System.out.println(result2);

        String target3 = "eeeeeeeeeeeeeeeeeeeeeeeef";
        String[] wordBank3 = new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"};
        int result3 = countConstruct(target3, wordBank3); //works too long without memoization. BUT honestly I didn't notice any
        System.out.println(result3);
    }

    /**
     * the idea:
     * to create array with length = target.length() + 1. Each element corresponds to the letter (i.e. how many ways we can we reach this letter)
     * time to solve ~ 5 mins
     * 2 attempts (failed since wrote arr[newPrefix.length()]++ firstly)
     * time complexity ~ O(targetLen*wordBankLen*targetLen), where targetLen - length of target word. *targetLen is because of the call of startsWith function
     * space complexity ~ O(targetLen) - space for arrays
     *
     */
    public static int countConstruct(String target, String[] wordBank) {
        int[] arr = new int[target.length() + 1];
        arr[0] = 1;

        for (int i = 0; i <= target.length(); i++) {
            if (arr[i] > 0) {
                String prefix = target.substring(0, i);
                for (String word : wordBank) {
                    String newPrefix = prefix + word;
                    if (target.startsWith(newPrefix)) {
                        arr[newPrefix.length()] += arr[i];
                    }
                }
            }
        }

        return arr[target.length()];
    }
}
