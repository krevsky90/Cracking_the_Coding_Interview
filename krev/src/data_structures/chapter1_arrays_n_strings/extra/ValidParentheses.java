package data_structures.chapter1_arrays_n_strings.extra;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
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
 */
public class ValidParentheses {
    /**
     * Idea: to use Stack of closing/opening brackets
     * 1) if bracket is opening - put it to stack
     * 2) if bracket is closing - check if the type of bracket is the same as top bracket in the stack. if not - return false
     * 3) if stack is empty, but we still don't reach the end of the string - return false (since we have closing brackers, but don't have opening
     * <p>
     * Time complexity:  O(N), where N is the length of the input string.
     * Space complexity: O(N) in the worst case due to the stack, where N is the length of the input string.
     * <p>
     * SOLUTION 1: https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
     */

    static boolean areBracketsBalanced(String expr) {
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack = new ArrayDeque<Character>();

        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{') {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            // If current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }

    /**
     * SOLUTION 2: https://leetcode.com/problems/valid-parentheses/solutions/3946910/java-runtime-2-ms-beats-87-53-stack/
     * Or
     * IF bracket is opening - put closing bracket to stack
     * ELSE (i.e. current symbol is closing bracket) - just compare current symbol to stack.pop() bracket
     * AND check is the stack is empty
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char x : s.toCharArray()) {
            if (x == '(') {
                stack.push(')');
            } else if (x == '{') {
                stack.push('}');
            } else if (x == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != x) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
