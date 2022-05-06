package data_structures.chapter1_arrays_n_strings;

/**
 * p.102
 *
 * 1.1. Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures?
 * Hints: #44, #117, #132
 *
 * ASSUMPTION:
 * 1) what about coding of characters? (work with ASCII alphabet that has 128 different characters)
 *
 */
public class Problem1_1 {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(isUnique(s));
    }

    /**
     * SOLUTION p.104:
     * One solution is to create an array of boolean values, where the flag at index i indicates whether character i
     * in the alphabet is contained in the string. The second time you see this character you can immediately return false.
     */
    public static boolean isUnique(String s) {
        if (s.length() > 128) return false;

        boolean[] arr = new boolean[128];
        for (char c : s.toCharArray()) {
            int i = c;
            if (arr[i]) {
                return false;
            } else {
                arr[i] = true;
            }
        }

        return true;
    }
}
