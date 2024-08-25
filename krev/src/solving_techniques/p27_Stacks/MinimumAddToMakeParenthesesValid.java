package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 921. Minimum Add to Make Parentheses Valid (medium)
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid
 * <p>
 * #Company: Facebook ServiceNow
 * <p>
 * A parentheses string is valid if and only if:
 * <p>
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 * <p>
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 * <p>
 * Example 1:
 * Input: s = "())"
 * Output: 1
 * <p>
 * Example 2:
 * Input: s = "((("
 * Output: 3
 * <p>
 * My Example:
 * Input: s = "))("
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.
 */
public class MinimumAddToMakeParenthesesValid {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 4 mins
     * idea: use stack
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 64%
     */
    public int minAddToMakeValidKrev(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.add(c);
                }
            }
        }

        return stack.size();
    }

    /**
     * SOLUTION #2:
     * info:
     * https://www.youtube.com/watch?v=LzcyBJRMhSw&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=10
     * idea:
     * keep counter of left brackets
     * keep counter of right brackets
     * keep counter (name it 'leftAdded') of left brackets to be added to make the expression valid
     * if countLeft < countRight => we will have to add '('.
     * result = leftAdded + (leftCounter - rightCounter)
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * BEATS = 100%
     */
    public int minAddToMakeValid(String s) {
        int left = 0;
        int right = 0;
        int leftAdded = 0;

        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                left++;
            } else {
                //i.e. c == ')'
                if (right < left) {
                    right++;
                } else {
                    leftAdded++;
                }
            }
        }

        return leftAdded + left - right;
    }
}
