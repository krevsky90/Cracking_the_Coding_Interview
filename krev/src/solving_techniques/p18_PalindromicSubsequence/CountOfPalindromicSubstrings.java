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
}
