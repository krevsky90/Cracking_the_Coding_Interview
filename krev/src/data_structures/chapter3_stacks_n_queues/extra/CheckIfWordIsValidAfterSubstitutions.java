package data_structures.chapter3_stacks_n_queues.extra;

import java.util.Stack;

/**
 * 1003. Check If Word Is Valid After Substitutions
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions
 *
 * Given a string s, determine if it is valid.
 *
 * A string s is valid if, starting with an empty string t = "",
 * you can transform t into s after performing the following operation any number of times:
 *
 * Insert string "abc" into any position in t.
 * More formally, t becomes tleft + "abc" + tright, where t == tleft + tright.
 * Note that tleft and tright may be empty.
 * Return true if s is a valid string, otherwise, return false.
 *
 * Example 1:
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 *
 * Example 2:
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 *
 * Example 3:
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 *
 * Constraints:
 * 1 <= s.length <= 2 * 10^4
 * s consists of letters 'a', 'b', and 'c'
 */
public class CheckIfWordIsValidAfterSubstitutions {
    public static void main(String[] args) {
        String s1 = "aabcbc";
        String s2 = "abcabcababcc";
        String s3 = "abccba";
        System.out.println(s1 + " is " + (isValid(s1) ? "" : "NOT ") + "valid");
        System.out.println(s1 + " is " + (isValidLeetcode(s1) ? "" : "NOT ") + "valid");
        System.out.println(s1 + " is " + (isValid(s2) ? "" : "NOT ") + "valid");
        System.out.println(s1 + " is " + (isValid(s3) ? "" : "NOT ") + "valid");
    }

    /**
     * NOT SOLVED by myself, just with tip to use separate string as buffer/stack
     * in fact, it is even not stack, we just check the last 3 elements of this string-buffer each time when its length >= 3
     * <p>
     * time to solve/implement ~ 22 mins
     * <p>
     * 2 attempts:
     * - did not consider sb.length = 3
     */
    public static boolean isValid(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (sb.length() >= 3) {
                if ("abc".equals(sb.substring(sb.length() - 3, sb.length()))) {
                    sb.delete(sb.length() - 3, sb.length());
                }
            }
        }

        return sb.length() == 0;
    }

    /**
     * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/solutions/3067699/java-c-100-solution-using-stack-check-if-word-is-valid-after-substitutions/
     * see solution of bijayabhatta
     *
     * idea: if we add 'c' and the last 3 letters are ont 'abc' => the whole string can not be valid!
     *
     */
    public static boolean isValidLeetcode(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));

            if (stack.peek() == 'c' && stack.size() >= 3) {
                char c = stack.pop();
                char b = stack.pop();
                char a = stack.pop();
                if (c != 'c' || b != 'b' || a != 'a') {
                    return false;
                }
            } else if (stack.peek() == 'c') {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
