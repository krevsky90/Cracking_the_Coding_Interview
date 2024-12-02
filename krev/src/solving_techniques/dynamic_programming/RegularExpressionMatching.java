package solving_techniques.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 10. Regular Expression Matching (hard)
 * https://leetcode.com/problems/regular-expression-matching/
 * <p>
 * #Company: Adobe Airbnb Alibaba Amazon Apple Bloomberg ByteDance Coursera Cruise Automation eBay Facebook Google Houzz Lyft Microsoft Oracle Palantir Technologies Pocket Gems Twitter Uber VMware Zulily
 * <p>
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * <p>
 * Example 2:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * <p>
 * Example 3:
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * <p>
 * Constraints:
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class RegularExpressionMatching {
    /**
     * NOT solved by me
     * faced in on https://www.tryexponent.com MOCK
     * info: https://www.youtube.com/watch?v=HAA8mgxlov8
     * <p>
     * idea:
     * 1) use i, j indices for text and pattern
     * 2) use DP
     * - each * symbol makes us to handle 2 cases (by recursion)
     * a) do not apply * (i.e. x* will be replaced with "") => call dfs method with (i, j + 2)
     * b) apply * (i.e. if i-th char of text = j-th char of pattern, => call dfs with (i + 1, j)
     * return resultA || resultB
     * - otherwise check if i-th char of text = j-th char of pattern OR i-th char of text = '.'
     * if yes => call dfs with (i + 1, j + 1)
     * else return false
     * 3) base cases:
     * - if we are out of bound for both strings simultaneously => return true
     * - if we are out of bound only for pattern => return false
     * NOTE: i can be out of bound (example: a and a*)
     * 4) use memo - map (i,j) -> boolean
     * <p>
     * time ~ O(n*m) where n = s.length(), m = p.length()
     * space ~ O(n*m)
     * <p>
     * a lot of attempts
     * <p>
     * BEATS ~ 24%
     */
    public boolean isMatch(String s, String p) {
        Map<List<Integer>, Boolean> memo = new HashMap<>();
        return isMatch(s, p, 0, 0, memo);
    }

    private boolean isMatch(String text, String pattern, int i, int j, Map<List<Integer>, Boolean> memo) {
        //base condition 1
        if (i == text.length() && j == pattern.length()) {
            return true;
        }

        //base condition 2
        if (j >= pattern.length()) return false;

        //NOTE: i can be = text.length(). For example: a and a*

        if (memo.containsKey(Arrays.asList(i, j))) {
            return memo.get(Arrays.asList(i, j));
        }

        boolean match = i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');

        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
            //case 1: * means 0 occurence
            boolean zeroOccurences = isMatch(text, pattern, i, j + 2, memo);
            //case 2: * means 1 or more occurences.
            //idea: if current chars are the same then we move i index, but keep j!
            boolean moreOccurences = match && isMatch(text, pattern, i + 1, j, memo);

            memo.put(Arrays.asList(i, j), zeroOccurences || moreOccurences);
            return memo.get(Arrays.asList(i, j));
        }

        memo.put(Arrays.asList(i, j), match && isMatch(text, pattern, i + 1, j + 1, memo));
        return memo.get(Arrays.asList(i, j));
    }
}