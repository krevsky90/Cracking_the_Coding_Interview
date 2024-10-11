package solving_techniques.p2_TwoPointers;

/**
 * 392. Is Subsequence (easy)
 * https://leetcode.com/problems/is-subsequence
 *
 * #Company: Amazon Bloomberg Facebook Google Pinterest Yandex
 *
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * s and t consist only of lowercase English letters.
 *
 * Follow up:
 * Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9,
 * and you want to check one by one to see if t has its subsequence.
 * In this scenario, how would you change your code?
 */
public class IsSubsequence {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 7 mins
     *
     * time ~ O(tArr.length)
     * space ~ O(sArr.length + tArr.length)
     *
     * 1 attempt
     *
     * BEATS ~ 100%
     */
    public boolean isSubsequence(String s, String t) {
        int p1 = 0;
        int p2 = 0;
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        while (p1 < sArr.length && p2 < tArr.length) {
            if (sArr[p1] == tArr[p2]) {
                p1++;
            }
            p2++;
        }

        return p1 == sArr.length;
    }
}