package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_strings;

import java.util.*;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * OR
 * 20. Valid Parentheses (easy)
 * https://leetcode.com/problems/valid-parentheses
 * <p>
 * #Company: Yandex
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class Problem1_16_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            } else if (c == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            } else if (c == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    /**
     * SOLUTION #2:
     * info: https://www.youtube.com/watch?v=WTzjTskDFMg
     * idea is the same, but implementation is more flexible
     *
     */
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');

        for (char c : arr) {
            if (closeToOpen.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != closeToOpen.get(c).charValue()) {
                    return false;
                }
            } else {
                stack.add(c);
            }
        }

        return stack.isEmpty();
    }
}
