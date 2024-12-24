package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 140. Word Break II (hard)
 * https://leetcode.com/problems/word-break-ii
 *
 * #Company: (24.12.2024) 0 - 3 months TikTok 7 Amazon 4 Bloomberg 4 Meta 3 Google 2 Moveworks 2 0 - 6 months Uber 2 X 2 Snap 2 Grammarly 2 Dropbox 2 6 months ago Microsoft 7 Adobe 2 Apple 2 Oracle 2 Myntra 2 ServiceNow 2
 *
 * Given a string s and a dictionary of strings wordDict,
 *      add spaces in s to construct a sentence where each word is a valid dictionary word.
 *      Return all such possible sentences in any order.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 * Constraints:
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * Input is generated in a way that the length of the answer doesn't exceed 10^5.
 */
public class WordBreak2 {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * the same as \src\solving_techniques\p21_Trie\WordBreak.java # wordBreak26112024
     * 1) i.e. use startsWith for all words from dictionary. if true => call the same method recursively, but check substring of s
     * 2) also track temp list of words which are equals s.startsWith(word)
     * base case: s.length() == 0
     * then we save all temporary stored words to the result.
     * 3) remove word from tempResult as part of backtracking logic
     *
     * time to solve ~ 11 mins
     *
     * 2 attempts:
     * - wrote formStringResult incorrectly (using StringBuilder and add spaces)
     *
     * BEATS ~ 92%
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        helper(s, wordDict, new ArrayList<>(), result);
        return result;
    }

    private void helper(String s, List<String> wordDict, List<String> tempResult, List<String> result) {
        if (s.length() == 0) {
            //it means we splitted the initial string => add tempResult to result
            result.add(formStringResult(tempResult));
            return;
        }

        for (String w : wordDict) {
            if (s.startsWith(w)) {
                tempResult.add(w);
                helper(s.substring(w.length()), wordDict, tempResult, result);
                tempResult.remove(tempResult.size() - 1);   //backtracking
            }
        }
    }

    private String formStringResult(List<String> tempResult) {
        return String.join(" ", tempResult);
    }
}
