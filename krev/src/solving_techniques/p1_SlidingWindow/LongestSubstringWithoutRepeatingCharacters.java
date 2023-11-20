package solving_techniques.p1_SlidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * <p>
 * Given a string s, find the length of the longest
 * substring without repeating characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * <p>
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * Constraints:
 * 0 <= s.length <= 5 * 10000
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 21 mins
     * 1 attempt
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                maxLen = Math.max(maxLen, end - start + 1);
                continue;
            }

            while (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                start++;
            }

            set.add(s.charAt(end));
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

}
