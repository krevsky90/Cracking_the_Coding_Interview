package data_structures.chapter1_arrays_n_strings;

/**
 * p.103
 *
 * 1.5. One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 * Hints: #23, #97, #130
 *
 * ASSUMPTION:
 * 1) strings are not empty
 * 2) spaces are not taken into account
 *
 */
public class Problem1_5 {
    public static void main(String[] args) {
        String s1 = "pale";
        String s2 = "paless";
        System.out.println(result(s1, s2));
    }

    /**
     * SOLUTION:
     * it is like my solution, but with merge of the code for replace and insert/delete cases
     *
     */
    public static boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        //s1 - short string, s2 - long string
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;

        boolean diff = false;
        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (diff) {
                    return false;
                }
                diff = true;

                if (s1.length() == s2.length()) {
                    index1++;   //on replace, move shorter pointer
                }
            } else {
                index1++;   //if matching, move shorter pointer
            }
            index2++;   //always move pointer for longer string
        }

        return true;
    }

    //KREVSKY
    public static boolean result(String s1, String s2) {
        if (s1.length() == s2.length()) {
            //check 'replace a character' case
            int numbDiff = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    numbDiff++;
                    if (numbDiff > 1) {
                        return false;
                    }
                }
            }

            return true;
        } else if (s1.length() - s2.length() == 1) {
            //check 'remove a character' case
            return compareDiffLength(s1, s2);
        } else if (s2.length() - s1.length() == 1) {
            //check 'remove a character' case
            return compareDiffLength(s2, s1);
        } else {
            return false;
        }
    }

    /**
     * @param s1 - long string
     * @param s2 - short string
     */
    private static boolean compareDiffLength(String s1, String s2) {
        int numbDiff = 0;
        for (int i = 0, j = 0; i < s2.length(); i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                numbDiff++;
                if (numbDiff > 1) {
                    return false;
                }
                i++;
            }
        }

        return true;
    }
}
