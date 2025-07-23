package solving_techniques.dynamic_programming;

/**
 * 44. Wildcard Matching (hard)
 * https://leetcode.com/problems/wildcard-matching/
 *
 * #Company (22.07.2025): 0 - 3 months Google 6 Meta 3 Bloomberg 3 Zoho 3 Amazon 2 Coupang 2 0 - 6 months Microsoft 2 6 months ago Apple 9 Adobe 7 Walmart Labs 3 Instacart 3 Confluent 2 X 2 Snap 2 Salesforce 2 Two Sigma 2
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 * Constraints:
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 */
public class WildcardMatching {
    /**
     * KREVSKY SOLUTION:
     *
     * time to solve ~ 45+ mins
     *
     * idea:
     * 1) use DP with edge cases:
     * if both iterators reach ends of strings => true
     * if i = s.length and j is p.length - 1, BUT p[j] is * => true
     * if i < s.length and j == p.length => false
     * if i = s.length and j < p.length => false
     *
     * if p[j] = * => 2 options:
     *      * is nothing => call isMatch(i, j + 1)
     *      * is some char sequence => call isMatch(i + 1, j)
     * if p[j] = ? => move pointers, i.e. call isMatch(i + 1, j + 1)
     * if p[j] is letter, than compare it with s[i]:
     *      if different => return false
     *      else call isMatch(i + 1, j + 1)
     *
     * 2) to handle cases like p = *****, remove extra * before we start matching - DID NOT REACH THIS IDEA!
     * 3) use memoization
     *
     * time ~ O(P + S*P) ~ O(S*P)
     * space ~ O(S*P + recursion_stack) ~ O(S*P + (S + P)) ~ O(S*P)
     *
     * 4 attempts:
     * - forgot case 'if i = s.length and j < p.length => false'
     * - did not handle case with repeatable * => failed s = "" and p = *****
     * - got TLE because of lack of memoization
     *
     * BEATS ~ 84%
     *
     */
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        char[] sArr = s.toCharArray();

        //remove extra '*'
        StringBuilder sb = new StringBuilder();
        for (char c : p.toCharArray()) {
            if (sb.isEmpty() || c != '*' || sb.charAt(sb.length() - 1) != '*') {
                sb.append(c);
            }
        }

        char[] pArr = sb.toString().toCharArray();

        Boolean[][] dp = new Boolean[sArr.length + 1][pArr.length + 1];

        return isMatch(sArr, pArr, 0, 0, dp);
    }

    private boolean isMatch(char[] sArr, char[] pArr, int i, int j, Boolean[][] dp) {
        if (i == sArr.length && j == pArr.length) return true;
        if (i == sArr.length && (j == pArr.length - 1 && pArr[j] == '*')) return true;
        if (i < sArr.length && j == pArr.length) return false;
        if (i == sArr.length && j < pArr.length) return false;

        if (dp[i][j] != null) return dp[i][j];

        if (Character.isLetter(pArr[j])) {
            if (pArr[j] != sArr[i]) return dp[i][j] = false;
            return dp[i][j] = isMatch(sArr, pArr, i + 1, j + 1, dp);
        } else if (pArr[j] == '?') {
            return dp[i][j] = isMatch(sArr, pArr, i + 1, j + 1, dp);
        } else {
            //if pArr[j] == '*'
            return dp[i][j] = isMatch(sArr, pArr, i, j + 1, dp) || isMatch(sArr, pArr, i + 1, j, dp);
            // for (int ii = i - 1; ii < sArr.length; ii++) {
            //     if (isMatch(sArr, pArr, ii + 1, j + 1, dp)) {
            //         return dp[i][j] = true;
            //     }
            // }

            // return dp[i][j] = false;
        }
    }
}
