package data_structures.chapter1_arrays_n_strings;

import java.util.Arrays;

/**
 * p.102
 *
 * 1.2. Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 * Hints: #1, #84, #122, #131
 *
 * ASSUMPTION:
 * 1) if the permutation comparison is case sensitive? (YES)
 * 2) if whitespace is significant? (YES)
 * 3) what is the size of character set? (let's 128)
 *
 */
public class Problem1_2 {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ecbad";
        System.out.println(isPerm1(s1, s2));
        System.out.println(isPerm2(s1, s2));

    }

    /**
     * SOLUTION p.205:
     * If two strings are permutations, then we know they have the same characters, but in different orders. Therefore,
     * sorting the strings will put the characters from two permutations in the same order. We just need to
     * compare the sorted versions of the strings.
     */
    public static boolean isPerm1(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        return sort(s1).equals(sort(s2));
    }

    private static String sort(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    /**
     * SOLUTION p.206:
     * We can also use the definition of a permutation-two words with the same character counts-to implement
     * this algorithm. We simply iterate through this code, counting how many times each character appears.
     * Then, afterwards, we compare the two arrays.
     */
    public static boolean isPerm2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int[] letters = new int[128];   //assumption!
        char[] chars1 = s1.toCharArray();
        for (char c : chars1) {
            letters[c]++;
        }

        char[] chars2 = s2.toCharArray();
        for (char c2 : chars2) {
            letters[c2]--;
            if (letters[c2] < 0) {
                return false;
            }
        }

        return true;
    }
}
