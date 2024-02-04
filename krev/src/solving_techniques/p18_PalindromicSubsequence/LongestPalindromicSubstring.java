package solving_techniques.p18_PalindromicSubsequence;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f54e6a842de869cd5f13f
 * OR
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring
 * <p>
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s1 = "babad";
        System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindromeLeetcodeApproach2(s1));
    }

    /**
     * BEST SOLUTION #1.1
     * since this idea can be applied for CountOfPalindromicSubstrings
     * idea: https://www.youtube.com/watch?v=XYQecbcd6_c
     * 1) start considering palindrome from the center, where center is i-th element of given s string
     * iterating through all i, we will find the longest palindrome with center = s.charAt(i)
     * 2) start considering palindrome from the 'center', where 'center' is pair of elements (i, i+1)
     * iterating through all i, we will find the longest palindrome with 'center' = (i, i+1)
     */
    public static String longestPalindromeCenterApproach(String s) {
        int[] ans = new int[2];
        for (int i = 0; i < s.length(); i++) {
            //step 1: odd length
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > ans[1] - ans[0]) {
                    ans[0] = left;
                    ans[1] = right;
                }
                left--;
                right++;
            }

            //step 2: even length
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > ans[1] - ans[0]) {
                    ans[0] = left;
                    ans[1] = right;
                }
                left--;
                right++;
            }
        }

        return s.substring(ans[0], ans[1] + 1);
    }

    /**
     * BEST SOLUTION #1.2
     * just code optimization
     **/
    public static String longestPalindromeCenterApproachOptimized(String s) {
        int[] ans = new int[2];
        for (int i = 0; i < s.length(); i++) {
            //step 1: odd length
            countLongestPalindrome(s, ans, i, i);
            //step 2: even length
            countLongestPalindrome(s, ans, i, i + 1);
        }

        return s.substring(ans[0], ans[1] + 1);
    }

    private static void countLongestPalindrome(String s, int[] ans, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > ans[1] - ans[0]) {
                ans[0] = left;
                ans[1] = right;
            }
            left--;
            right++;
        }
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 110 mins
     * A LOT OF attempt!
     * <p>
     * idea:
     * 1) Axis of the table are start and end index! DO NOT imagine the letters!
     * 2) IF s.charAt(i) == s.charAt(j) && dp[i+1][j-1] > 0 THEN dp[i][j] = 2 + dp[i+1][j-1];
     * 3) fill the table diagonally! due to p.2
     */
    public static String longestPalindrome(String s) {
        return longestPalindromeHelperBottomUp(s);
    }

    private static String longestPalindromeHelperBottomUp(String s) {
        int[][] dp = new int[s.length()][s.length()];

        int startI = 0;
        int maxLength = 1;

        int k = 0;
        int i = 0;
        while (k + i < s.length()) {
            while (i + k < s.length()) {
                if (k == 0) {
                    dp[i][i + k] = 1;
                } else if (k == 1 && s.charAt(i) == s.charAt(i + k)) {
                    dp[i][i + k] = 2;
                } else if (s.charAt(i) == s.charAt(i + k) && dp[i + 1][i + k - 1] > 0) {
                    //since dp[i+1][j-1] can be > 0 only if substring (i+1,j-1) is palindrome
                    //BUT the problem is filling i+1-th row is NOT filled when we are trying to fill i-th row!
                    //that's why we have to fill the table diagonally (from the main diagonal to the right upper corner of dp table)
                    dp[i][i + k] = 2 + dp[i + 1][i + k - 1];
                } else {
                    dp[i][i + k] = 0;
                }

                if (maxLength < dp[i][i + k]) {
                    maxLength = dp[i][i + k];
                    startI = i;
                }

                i++;
            }
            i = 0;
            k++;
        }

        return s.substring(startI, startI + maxLength);
    }

    /**
     * LEETCODE SOLUTION:
     * https://leetcode.com/problems/longest-palindromic-substring/editorial/, see Approach 2
     * the same idea as mine, BUT implementation is simpler but to pre-filled TWO diagonals!
     */
    public static String longestPalindromeLeetcodeApproach2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[]{0, 0};

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                ans[0] = i;
                ans[1] = i + 1;
            }
        }

        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }
}