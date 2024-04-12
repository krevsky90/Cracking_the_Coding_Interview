package solving_techniques.p18_PalindromicSubsequence;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f56f80fbfd1e5b12234eb
 * OR
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings
 * <p>
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 */
public class CountOfPalindromicSubstrings {
    public static void main(String[] args) {
        String s1 = "aaa";
        CountOfPalindromicSubstrings obj = new CountOfPalindromicSubstrings();
        System.out.println(obj.countSubstrings(s1));
        System.out.println(obj.countSubstringsKrev(s1));
    }
    /**
     * NOT SOLVED by myself
     * tried to use dp table
     * <p>
     * idea:
     * see https://www.youtube.com/watch?v=4RACzI5-du8
     * 1) count palindromes that have center = arr[i] - and do it for each i
     * 2) count palindromes that have 'center' = (arr[i], arr[i+1]) - and do it for each i
     * sum the results
     * <p>
     * implementation: https://leetcode.com/problems/palindromic-substrings/solutions/4667296/short-and-easy-method-3ms-please-upvote/
     * <p>
     * time ~ O(n^2)
     * space ~ (1)
     */
    public int countSubstrings(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            result += countPalindromes(i, i, s);
            result += countPalindromes(i, i + 1, s);
        }

        return result;
    }

    private int countPalindromes(int left, int right, String s) {
        int result = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result++;
            left--;
            right++;
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION:
     * HINT: do NOT consider dp as table! think in terms of two pointers (i,j) and string s!
     *
     * idea: fill dp: [i][j] = 1 if s.substring(i,j) is palindrome, = 0 - otherwise
     * i.e. we do not store amount of palindromes that can be fetched from (i,j)-substring! just fact!
     * to count amount of palindromes, we use separate counter, that is incremented each time when we set dp[i][j] = 1
     *
     * BEATS = 34%
     */
    public int countSubstringsKrev(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        int counter = 0;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            counter++;
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = 1;
                counter++;
            }
        }
        dp[n-1][n-1] = 1;
        counter++;

        for (int k = 2; k < n; ++k) {
            for (int i = 0; i < n - k; ++i) {
                int j = i + k;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                    counter++;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return counter;
    }

    /**
     * info:
     * https://leetcode.com/discuss/general-discussion/458695/Dynamic-Programming-Patterns
     * see code for '647. Palindromic Substrings Medium' problem
     * idea is to store length of palindrome in substring(i,j).
     * but in fact we need to count the amount of non-zero cells as in my solution above
     *
     * HINT: in this solution we do not fill [i][i+1] cells separately
     * because of condition "&& dp[i + 1][j - 1] == j - i - 1" and statement"dp[i][j] = dp[i + 1][j - 1] + 2;"
     */
    public int countSubstrings2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        int counter = n;

        for (int k = 1; k < n; ++k) {
            for (int i = 0; i < n - k; ++i) {
                int j = i + k;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == j - i - 1) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    counter++;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return counter;
    }
}
