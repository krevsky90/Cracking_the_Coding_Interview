package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 * <p>
 * Given a string s, return the longest
 * palindromic substring in s.
 * <p>
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class Problem2_2_LongestPalindromicSubstring {
    /**
     * KREVSKY SOLUTION - 1h 23mins
     * explanation https://www.youtube.com/watch?v=b4vgaENSRrY
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public String longestPalindromeKREV(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        //for odd substrings
        for (int i = 0; i < s.length(); i++) {
            int c = 1;
            while (i - c >= 0 && i + c < s.length() && s.charAt(i - c) == s.charAt(i + c)) {    //NOTE: time complexity of charAt is O(1)
                c++;
            }

            if ((i + (c - 1)) - (i - (c - 1)) + 1 > maxLength) {
                left = i - (c - 1);
                right = i + (c - 1);
                maxLength = right - left + 1;
            }
        }

        //for even substrings
        for (int i = 0; i + 1 < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                int c = 1;

                while (i - c >= 0 && i + 1 + c < s.length() && s.charAt(i - c) == s.charAt(i + 1 + c)) {
                    c++;
                }

                if ((i + 1 + (c - 1)) - (i - (c - 1)) + 1 > maxLength) {
                    left = i - (c - 1);
                    right = i + 1 + (c - 1);
                    maxLength = right - left + 1;
                }
            }
        }

        return s.substring(left, right + 1);
    }

    /**
     * Official solution
     */

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
