package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/count-and-say/description/
 *
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit.
 * Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 * Example 1:
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 *
 * Example 2:
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 * Constraints:
 * 1 <= n <= 30
 */
public class Problem2_6_CountAndSay {
    /**
     * KREVSKY SOLUTION - START
     * idea ~ dynamic programming (recursion) + memorization
     * +idea for say-method
     * time to solve ~ 30 mins
     * 1 attempt
     */
    public String countAndSayMemo(int n) {
        String[] memo = new String[n+1];
        memo[0] = "";
        memo[1] = "1";//base case
        //memo = {"", "1", say("1") = "11", say("11") = "21", say("21") = "1211"}

        return countAndSayMemo(n, memo);
    }

    public String countAndSayMemo(int n, String[] memo) {
        if (memo[n] != null) {
            return memo[n];
        }

        memo[n] = say(countAndSayMemo(n-1, memo));
        return memo[n];
    }

    /**
     * KREVSKY SOLUTION WITHOUT MEMORIZATION - the same result, because we don't count memo's elements more than 2 time (only one recursion branch)
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String s = countAndSay(n-1);    //recursion
        return say(s);
    }

    //helper:
    private String say(String s) {
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        char c = arr[0];
        int counter = 1;
        for (int i = 1; i < arr.length; i++) {
            if (c != arr[i]) {
                sb.append(counter).append(c);
                c = arr[i];
                counter = 1;
            } else {
                counter++;
            }
        }
        sb.append(counter).append(c);

        return sb.toString();
    }
}