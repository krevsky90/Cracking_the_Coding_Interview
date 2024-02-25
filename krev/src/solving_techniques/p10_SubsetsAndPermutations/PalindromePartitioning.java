package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    /**
     * NOT SOLVED from 1st attempt
     * idea:
     * generate all combinations according to usual algorithm + filter substring [start, i] if it is palindrome
     *
     * good explanation is here https://leetcode.com/problems/palindrome-partitioning/solutions/3469514/java-0ms-backtracking-solution-step-by-step-explained/
     *
     * time to solve ~ 17 mins
     * time ~ O(n*2^n)
     * space ~ O(n) - space in stack of Java memory
     *
     * 2 attempts:
     * - wrote "start + 1" instead if "i + 1"
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        List<String> tempResult = new ArrayList<>();
        generateAllPalindromicCombinations(s, 0, tempResult, result);

        return result;
    }

    public void generateAllPalindromicCombinations(String s, int start, List<String> tempResult, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(tempResult));
        }

        for (int i = start; i < s.length(); i++) {
            String tempSubstring = s.substring(start, i + 1);
            //check if substring is a palindrome
            if (isPalindrome(tempSubstring)) {
                tempResult.add(tempSubstring);
                generateAllPalindromicCombinations(s, i + 1, tempResult, result);
                tempResult.remove(tempResult.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
