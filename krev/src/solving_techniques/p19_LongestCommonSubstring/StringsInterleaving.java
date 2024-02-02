package solving_techniques.p19_LongestCommonSubstring;

import java.util.Arrays;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6de8046a2a95a84d4834
 * OR
 * 97. Interleaving String
 * https://leetcode.com/problems/interleaving-string/
 * <p>
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings respectively, such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 * <p>
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 * <p>
 * Example 3:
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 * <p>
 * Constraints:
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 * <p>
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class StringsInterleaving {
    /**
     * Simple and logical solution
     * info: https://walkccc.me/LeetCode/problems/0097/#__tabbed_2_2
     * idea: dp[i][j] := true if s3[0..i + j) is formed by the interleaving of s1[0..i) and s2[0..j)
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        final int m = s1.length();
        final int n = s2.length();
        if (m + n != s3.length())
            return false;

        // dp[i][j] := true if s3[0..i + j) is formed by the interleaving of
        // s1[0..i) and s2[0..j)
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 1; i <= m; ++i)
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);

        for (int j = 1; j <= n; ++j)
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                        dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

        return dp[m][n];
    }

    /**
     * Space optimization:
     * dp[i] = dp[] && smth
     */
    public boolean isInterleaveSpaceOptimized(String s1, String s2, String s3) {
        final int m = s1.length();
        final int n = s2.length();
        if (m + n != s3.length())
            return false;

        boolean[] dp = new boolean[n + 1];

        for (int i = 0; i <= m; ++i)
            for (int j = 0; j <= n; ++j)
                if (i == 0 && j == 0)
                    dp[j] = true;
                else if (i == 0)
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                else if (j == 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                else
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                            dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);

        return dp[n];
    }

    /**
     * KREVSKY SOLUTION #1.1: without memo => lead to time limit exceeded
     * idea: for each letter from s3 we have 4 options:
     * - no one of current letters s1[i1] and s2[i2] equal current letter of s3
     * - only current letter s1[i1] equals current letter of s3 => choose the only possible way => i1++
     * - only current letter s2[i2] equals current letter of s3 => choose the only possible way => i2++
     * - both current letters s1[i1] and s2[i2] equal current letter of s3 => try both ways
     * <p>
     * time to solve ~ 40 mins (incorrect idea) + 20 mins (correct)
     * time ~ O(2^len3), where len3 = s3.length()
     * space ??
     * <p>
     * a lot of attempts
     */
    public boolean isInterleave1(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        return isInterleave1(s1, s2, s3, 0, 0);
    }

    private boolean isInterleave1(String s1, String s2, String s3, int i1, int i2) {
        //base case
        if (s1.length() == i1) {
            return s2.substring(i2).equals(s3.substring(i1 + i2));
        }

        if (s2.length() == i2) {
            return s1.substring(i1).equals(s3.substring(i1 + i2));
        }

        boolean eq1 = s3.charAt(i1 + i2) == s1.charAt(i1);
        boolean eq2 = s3.charAt(i1 + i2) == s2.charAt(i2);

        boolean result = false;
        if (!eq1 && !eq2) {
            return false;
        } else if (!eq1) {
            //it means eq2 = true => the only ways is to take letter from s2
            result = result || isInterleave1(s1, s2, s3, i1, i2 + 1);
        } else if (!eq2) {
            //it means eq1 = true => the only ways is to take letter from s1
            result = result || isInterleave1(s1, s2, s3, i1 + 1, i2);
        } else {
            //it means eq1 = true AND eq2 = true
            //let's go both ways and check if at least one of them gives us true
            result = result || isInterleave1(s1, s2, s3, i1, i2 + 1);
            result = result || isInterleave1(s1, s2, s3, i1 + 1, i2);
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION #1.2: with memo
     * idea: 1.1: + memo
     * be careful with values for:
     * UNDEFINED - MIN_VALUE
     * -1 - false
     * 1 - true
     * <p>
     * ~ 3 attempts:
     * typos, refactoring due to the changes of method signature
     * time ~ O(len1*len2)
     * space ~ O(len1*len2)
     */
    public boolean isInterleaveMemo(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        //Integer.MIN_VALUE means undefined
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        return isInterleaveMemo(s1, s2, s3, 0, 0, dp) == 1 ? true : false;
    }

    private int isInterleaveMemo(String s1, String s2, String s3, int i1, int i2, int[][] dp) {
        //base case
        if (s1.length() == i1) {
            return s2.substring(i2).equals(s3.substring(i1 + i2)) == true ? 1 : -1;
        }

        if (s2.length() == i2) {
            return s1.substring(i1).equals(s3.substring(i1 + i2)) == true ? 1 : -1;
        }

        //memo
        if (dp[i1][i2] != Integer.MIN_VALUE) {
            return dp[i1][i2];
        }

        boolean eq1 = s3.charAt(i1 + i2) == s1.charAt(i1);
        boolean eq2 = s3.charAt(i1 + i2) == s2.charAt(i2);

        //-1 - means impossible (false)
        // 1 - means possible (true)
        if (!eq1 && !eq2) {
            dp[i1][i2] = -1;
        } else if (!eq1) {
            //it means eq2 = true => the only ways is to take letter from s2
            dp[i1][i2] = isInterleaveMemo(s1, s2, s3, i1, i2 + 1, dp);
        } else if (!eq2) {
            //it means eq1 = true => the only ways is to take letter from s1
            dp[i1][i2] = isInterleaveMemo(s1, s2, s3, i1 + 1, i2, dp);
        } else {
            //it means eq1 = true AND eq2 = true
            //let's go both ways and check if at least one of them gives us true
            int subResult1 = isInterleaveMemo(s1, s2, s3, i1, i2 + 1, dp);
            int subResult2 = isInterleaveMemo(s1, s2, s3, i1 + 1, i2, dp);
            if (subResult1 == 1 || subResult2 == 1) {
                dp[i1][i2] = 1;
            } else {
                dp[i1][i2] = -1;
            }
        }

        return dp[i1][i2];
    }
}
