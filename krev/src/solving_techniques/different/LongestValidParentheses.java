package solving_techniques.different;

/**
 * 32. Longest Valid Parentheses (hard)
 * https://leetcode.com/problems/longest-valid-parentheses
 * <p>
 * #Company (23.01.2025): 0 - 3 months Amazon 7 Google 3 MakeMyTrip 2 0 - 6 months Zoho 4 Meta 3 Microsoft 3 Intuit 2  InMobi 2 6 months ago Uber 10 Adobe 9 Bloomberg 7 Apple 7 Oracle 5 TikTok 5 Yahoo 3 SOTI 3 Salesforce 2 Sprinklr 2
 * <p>
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses
 * substring
 * .
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * <p>
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * <p>
 * Example 3:
 * Input: s = ""
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 3 * 10^4
 * s[i] is '(', or ')'.
 */
public class LongestValidParentheses {
    /**
     * NOT SOLVED by myself:
     * info: https://www.youtube.com/watch?v=D5b_JWlkXxw&list=PLUPSMCjQ-7oeenUxyrJFDFqd40PokIqdO&index=2&t=24s
     * <p>
     * time to implement ~ 11 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n) if create array from the string
     * <p>
     * 1 attempt
     *
     * BEATS ~ 99%
     */
    public int longestValidParentheses(String s) {
        int cntLeft = 0;
        int cntRight = 0;
        int left = 0;
        int right = 0;
        int result = 0;

        char[] arr = s.toCharArray();

        // from left to right: example: Input: s = ")()())" => Output: 4
        for (right = 0; right < arr.length; right++) {
            if (arr[right] == '(') {
                cntLeft++;
            } else {
                cntRight++;
            }

            if (cntLeft == cntRight) {
                result = Math.max(result, right - left + 1);
            } else if (cntRight > cntLeft) {
                //reset
                left = right + 1;
                cntLeft = 0;
                cntRight = 0;
            }
        }

        // from right to left: example: s = "(()" => Output: 2
        cntLeft = 0;
        cntRight = 0;
        right = arr.length - 1;
        for (left = arr.length - 1; left >= 0; left--) {
            if (arr[left] == ')') {
                cntRight++;
            } else {
                cntLeft++;
            }

            if (cntLeft == cntRight) {
                result = Math.max(result, right - left + 1);
            } else if (cntRight < cntLeft) {
                //reset
                right = left - 1;
                cntLeft = 0;
                cntRight = 0;
            }
        }

        return result;
    }
}
