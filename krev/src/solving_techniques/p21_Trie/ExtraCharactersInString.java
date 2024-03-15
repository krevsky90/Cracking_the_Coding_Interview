package solving_techniques.p21_Trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/65a7b27a654cd318e798fc76
 * OR
 * 2707. Extra Characters in a String
 * https://leetcode.com/problems/extra-characters-in-a-string
 * <p>
 * You are given a 0-indexed string s and a dictionary of words dictionary.
 * You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary.
 * There may be some extra characters in s which are not present in any of the substrings.
 * <p>
 * Return the minimum number of extra characters left over if you break up s optimally.
 * <p>
 * Example 1:
 * Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
 * Output: 1
 * Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8.
 * There is only 1 unused character (at index 4), so we return 1.
 * <p>
 * Example 2:
 * Input: s = "sayhelloworld", dictionary = ["hello","world"]
 * Output: 3
 * Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12.
 * The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 * <p>
 * Constraints:
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] and s consists of only lowercase English letters
 * dictionary contains distinct words
 */
public class ExtraCharactersInString {
    public static void main(String[] args) {
        String s1 = "leetscode";
        String[] dictionary1 = {"leet", "code", "leetcode"};
        System.out.println(new ExtraCharactersInString().minExtraChar1(s1, dictionary1));   //expected 1
        System.out.println(new ExtraCharactersInString().minExtraChar(s1, dictionary1));   //expected 1
    }
    /**
     * NOT SOLVED by myself!
     */

    /**
     * SOLUTION #1:
     * ONLY DP + memoization. NO Trie
     * idea: leetcode (can't find original solution)
     * time to implement ~ 30 mins
     *
     * a lot of attempts
     * - mixed up i and j
     * - forgot to fill dp by MAX_VALUE => returned 0
     *
     * BEATS 18%
     */
    public int minExtraChar1(String s, String[] dictionary) {
        Set<String> setDict = new HashSet<>();
        for (String w : dictionary) {
            setDict.add(w);
        }

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (setDict.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                } else {
                    dp[i] = Math.min(dp[i], dp[j] + i - j);
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * SOLUTION #2:
     * Trie + DP + memoization
     * idea + implementation:
     * https://leetcode.com/problems/extra-characters-in-a-string/solutions/3991034/java-100-faster-commented-clean-code-trie-dynamic-programming-recursion-memoization/
     *
     * BEATS 100%
     */
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    public TrieNode root = new TrieNode();

    public void add(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNode();
            }
            current = current.children[c - 'a'];
        }
        current.isWord = true;
    }

    public int minExtraChar(String s, String[] dictionary) {
        for (String w : dictionary) {
            add(w);
        }

        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        return minExtraCharHelper(s, 0, dp);

    }

    public int minExtraCharHelper(String s, int start, int[] dp) {
        if (start == s.length()) return 0;

        if (dp[start] != Integer.MAX_VALUE) return dp[start];

        //case 1: skip i-th letter
        int localMin = minExtraCharHelper(s, start + 1, dp) + 1;   //add 1 since we consider i-th element as not covered by dictionary

        //case 2: take i-th letter and try to find the word that starts from i-th letter
        int j = start;
        TrieNode current = root;
        while (j < s.length()) {
            if (current.children[s.charAt(j) - 'a'] == null) {
                break;
            }

            current = current.children[s.charAt(j) - 'a'];

            if (current.isWord) {
                localMin = Math.min(localMin, minExtraCharHelper(s, j + 1, dp));
            }

            j++;
        }

        return dp[start] = localMin;
    }
}