package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/64c14baaee20248bcc6d1d4c
 * OR
 * 1209. Remove All Adjacent Duplicates in String II (medium)
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii
 *
 * #Company: Bloomberg FactSet Google VMware
 * <p>
 * You are given a string s and an integer k,
 * a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them,
 * causing the left and the right side of the deleted substring to concatenate together.
 * <p>
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 * <p>
 * Example 1:
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * <p>
 * Example 2:
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * <p>
 * Example 3:
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s only contains lowercase English letters.
 */
public class RemoveAllAdjacentDuplicatesInString2 {
    /**
     * KREVSKY SOLUTION #1:
     *
     * time to solve ~ 22 mins
     *
     * 3 attempts:
     * 1) incorrect "stack.peek()" instead of correct "stack.peek().c
     * 2) incorrect "sb.append(stack.pop())" instead of correct "sb.append(stack.pop().c)"
     *
     * BEATS = 48%
     */
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char tempChar = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(new Pair(tempChar, 1));
            } else {
                if (tempChar == stack.peek().c) {
                    if (stack.peek().num == k - 1) {
                        //remove k-1 elements (duplicates) from the stack
                        for (int j = 0; j < k - 1; j++) {
                            stack.pop();
                        }
                    } else {
                        stack.push(new Pair(tempChar, stack.peek().num + 1));
                    }
                } else {
                    stack.push(new Pair(tempChar, 1));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().c);
        }
        return sb.toString();
    }

    /**
     * SOLUTION #2:
     * idea: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/solutions/1161097/java-stack-easy/
     *
     * code looks more simple, but works a little bit longer (extra push to stack in case if we push k-th duplicate)
     * BEATS = 38%
     */
    public String removeDuplicates2(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char tempChar = s.charAt(i);
            int tempNum = 1;
            if (!stack.isEmpty() && stack.peek().c == tempChar) {
                tempNum = stack.peek().num + 1;
            }   //skip 'else' since otherwise we would set 1 (that is default value)

            //we push tempChar + tempNum anyway!
            stack.push(new Pair(tempChar, tempNum));

            //then we check if the top element of the stack is k-th duplicate or not
            if (stack.peek().num == k) {
                //remove k elements (duplicates) from the stack
                for (int j = 0; j < k; j++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop().c);
        }
        return sb.toString();
    }

    class Pair {
        char c;
        int num;

        Pair(char c, int num) {
            this.c = c;
            this.num = num;
        }
    }
}
