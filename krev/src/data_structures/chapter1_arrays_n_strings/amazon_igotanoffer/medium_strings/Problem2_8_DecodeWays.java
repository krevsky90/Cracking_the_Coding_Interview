package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

import java.util.Arrays;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * OR
 * 91. Decode Ways (medium)
 * https://leetcode.com/problems/decode-ways
 *
 * #Company (11.02.2025): 0 - 3 months Google 2 Meta 2 Amazon 2 Goldman Sachs 2 0 - 6 months Microsoft 2 6 months ago TikTok 12 Commvault 9 Uber 8 Apple 7 Oracle 6 Bloomberg 5 Flipkart 5 Zoho 4 Salesforce 4 Adobe 3
 *
 * OR
 * https://www.tryexponent.com/practice/prepare/decode-variations
 * <p>
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).
 * For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * <p>
 * Example 3:
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 * <p>
 * Constraints:
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class Problem2_8_DecodeWays {
    /**
     * NeedCode DP
     */
    public int numDecodings2(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, 1);   //why?

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                memo[i] = 0;
            } else {
                memo[i] = memo[i + 1];
                //check if (i,i+1) substring is number <= 26
                if (i < s.length() - 1 && Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                    memo[i] += memo[i + 2];
                }
            }
        }
        return memo[0];
    }

    /**
     * NeetCode - optimize space to O(1)
     */
    public int numDecodings22(String s) {
        int prev = 1;
        int prev2 = 1;
        int cur = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                cur = 0;
            } else {
                cur = prev;
                //check if (i,i+1) substring is number <= 26
                if (i < s.length() - 1 && Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                    cur += prev2;
                }
            }

            prev2 = prev;
            prev = cur;

        }
        return cur;
    }

    /**
     * NOT SOLVED
     * idea - dynamic programming and numDecodings("12345") = numDecodings("2345") + numDecodings("345") etc
     * used help from https://www.youtube.com/watch?v=qli-JCrSwuk
     */
    public int numDecodingsMemo(String s) {
        int[] memo = new int[s.length() + 1];
        return numDecodingsHelper(s, s.length(), memo);
    }

    //k - length of string
    public int numDecodingsHelper(String s, int k, int[] memo) {
        if (s == null) return 0;
        if (k == 0) return 1;
        int startIndex = s.length() - k;
        if ("0".equals(s.substring(startIndex, startIndex + 1))) return 0;

        if (memo[k] != 0) {
            return memo[k];
        }

        int result = numDecodingsHelper(s, k - 1, memo);
        if (k >= 2 && Integer.valueOf(s.substring(s.length() - k, s.length() - k + 2)) <= 26) {
            result += numDecodingsHelper(s, k - 2, memo);
        }
        memo[k] = result;

        return result;
    }

    /**
     * Cool solution without recursion
     * https://leetcode.com/problems/decode-ways/solutions/3410539/easy-to-understand-solution-in-java-using-dynamic-programming-both-ways-memoization-and-top-down/
     */
    public int numDecodings(String s) {
        int n = s.length();
        int ans[] = new int[n + 1];
        ans[0] = 1;
        ans[1] = s.charAt(0) - '0' == 0 ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int current = s.charAt(i - 1) - '0';
            int prev = s.charAt(i - 2) - '0';
            int twoDigitNo = 10 * prev + current;
            if (current >= 1 && current <= 9) {
                ans[i] += ans[i - 1];
            }
            if (twoDigitNo >= 10 && twoDigitNo <= 26) {
                ans[i] += ans[i - 2];
            }
        }
        return ans[n];
    }
}
