package solving_techniques.p19_LongestCommonSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/number-of-matching-subsequences/
 * 792. Number of Matching Subsequences
 * <p>
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * <p>
 * Example 1:
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * <p>
 * Example 2:
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */
public class NumberOfMatchingSubsequences {

    /**
     * KREVSKY SOLUTION 1.1
     * causes time limit exceeded
     */
    public int numMatchingSubseq11(String s, String[] words) {
        int result = 0;
        for (String w : words) {
            result += isSubsequence(s, w);
        }
        return result;
    }

    /**
     * KREVSKY SOLUTION 1.2
     * add map to avoid duplicate of calculations
     * <p>
     * time to solve ~ 20 mins
     * <p>
     * 1 attempt
     */
    public int numMatchingSubseq12(String s, String[] words) {
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (map.containsKey(w)) {
                result += map.get(w);
            } else {
                int subRes = isSubsequence(s, w);
                map.put(w, subRes);
                result += subRes;
            }
        }

        return result;
    }

    private int isSubsequence(String s, String word) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == word.charAt(j)) {
                j++;
            }

            if (j == word.length()) {
                return 1;
            }
        }

        return 0;
    }
}