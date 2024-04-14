package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * 1047. Remove All Adjacent Duplicates In String (easy)
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string
 * <p>
 * You are given a string s consisting of lowercase English letters.
 * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 * We repeatedly make duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 * <p>
 * Example 1:
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
 * The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * <p>
 * Example 2:
 * Input: s = "azxxzy"
 * Output: "ay"
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 */
public class RemoveAllAdjacentDuplicatesInString {
    /**
     * KREVSKY SOLUTION #1:
     * <p>
     * time to solve ~ 11 mins
     * <p>
     * 2 attempts:
     * - mistake: wrote '+' in "res += stack.pop() + res;" instead of "res = stack.pop() + res;"
     *
     * BEATS = 14%
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.add(c);
            }
        }

        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }

        return res;
    }

    /**
     * leetcode solution:
     * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
     * <p>
     * idea: traverse from end to start, since in this case stack will store the elements in reverse order =>
     * => when we pop the elements, we do it in doubly-reverse order = straight order => we can use StringBuilder instead of res = stack.pop() + res;
     *
     * BEATS = 52%
     */
    public String removeDuplicates2(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.add(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
