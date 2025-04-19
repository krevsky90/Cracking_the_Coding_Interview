package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses (hard)
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * <p>
 * #Company (19.04.2025): 0 - 3 months Meta 14 0 - 6 months Microsoft 2 TikTok 2 6 months ago Google 4 Amazon 3 Bloomberg 3 Adobe 3 Uber 3 Yandex 3 Zoho 2 Snowflake 2 Rubrik 2 Deliveroo 2
 * <p>
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 * <p>
 * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * <p>
 * Example 2:
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * <p>
 * Example 3:
 * Input: s = ")("
 * Output: [""]
 * <p>
 * Constraints:
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 */
public class RemoveInvalidParentheses {
    /**
     * NOT SOLVED by myself
     * idea: since string is short => use backtracking and recursion!
     * idea #2: optimize it by checking if we already removed all incorrect opening and closing breacket. if yes - stop this branch of recursion
     * idea #3: to avoid isValid(..) method before saving the result, keep openUsed and closeUsed counters in state: openUsed >= closeUsed
     * <p>
     * time to implement ~ 40 mins
     * <p>
     * time ~ O(2^n)
     * space ~ O(n)
     * <p>
     * BEATS ~ 36%
     */
    public List<String> removeInvalidParentheses(String s) {
        int open = 0;
        int close = 0;
        int cnt = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                cnt++;
            } else if (c == ')') {
                cnt--;
                if (cnt < 0) {
                    close++;
                    cnt = 0;
                }
            }
        }

        cnt = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ')') {
                cnt++;
            } else if (arr[i] == '(') {
                cnt--;
                if (cnt < 0) {
                    open++;
                    cnt = 0;
                }
            }
        }

        Set<String> result = new HashSet<>();
        generateStrings(arr, 0, 0, 0, open, close, result, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void generateStrings(char[] arr, int pos, int openUsed, int closeUsed, int open, int close, Set<String> result, StringBuilder sb) {
        if (open < 0 || close < 0) return;
        if (openUsed < closeUsed) return;   //incorrect subsequence

        if (open == 0 && close == 0 && pos == arr.length) {
            // if (!isValid(sb)) return;

            //check if combination is valid (using stack, for example)
            result.add(sb.toString());
            return;
        }

        if (pos == arr.length) return;

        if (arr[pos] == '(') {
            //remove it
            generateStrings(arr, pos + 1, openUsed, closeUsed, open - 1, close, result, sb);
            //keep it
            sb.append(arr[pos]);
            generateStrings(arr, pos + 1, openUsed + 1, closeUsed, open, close, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else if (arr[pos] == ')') {
            //remove it
            generateStrings(arr, pos + 1, openUsed, closeUsed, open, close - 1, result, sb);
            //keep it
            sb.append(arr[pos]);
            generateStrings(arr, pos + 1, openUsed, closeUsed + 1, open, close, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            //letter
            sb.append(arr[pos]);
            generateStrings(arr, pos + 1, openUsed, closeUsed, open, close, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private boolean isValid(StringBuilder sb) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == '(') {
                stack.add(c);
            } else {
                //do nothing. do not add lettr into stack
            }
        }

        return stack.isEmpty();
    }
}
