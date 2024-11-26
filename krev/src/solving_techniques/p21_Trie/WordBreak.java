package solving_techniques.p21_Trie;

import java.util.*;

/**
 * 139. Word Break (medium)
 * https://leetcode.com/problems/word-break
 *
 * #Company: Adobe Amazon Apple Audible Bloomberg Coupang Facebook Google HBO Hulu Lyft Microsoft Oracle Pinterest Pocket Gems Qualtrics Salesforce Snapchat Square TripAdvisor Twilio Uber VMware Walmart Labs Yahoo Yelp
 * <p>
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {
    public static void main(String[] args) {
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");

        String s3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict3 = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");

        String s4 = "carcad";
        List<String> wordDict4 = Arrays.asList("car", "carca", "d");

        WordBreak obj = new WordBreak();

        System.out.println(obj.wordBreakCrackingFaang(s2, wordDict2));
        System.out.println(obj.wordBreakTopDownAndTrieKrev(s3, wordDict3));
    }

    /**
     * info:
     * https://www.youtube.com/watch?v=CUMRilS83fE&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=9
     * idea:
     * 1) use BFS and queue of not handled (sub)words
     * 2) if the word was already considered - skip it (it is like memo in DP approach)
     * time to implement ~ 10 mins + 10 mins to debug
     * time ~ O(n*n*n) ~ O(n^2)
     * space ~ O(n), where n = s.length();
     *
     * 2 attempt:
     * - put extra "visited.add(s)" before while loop
     *
     * BEATS ~ 70%
     */
    public boolean wordBreakCrackingFaang(String s, List<String> wordDict) {
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        q.add(s);
        while (!q.isEmpty()) {
            String temp = q.poll();

            if ("".equals(temp)) {
                return true;
            }

            if (visited.contains(temp)) {
                //do nothing
            } else {
                visited.add(temp);
                for (String w : wordDict) {
                    if (temp.startsWith(w)) {
                        q.add(temp.substring(w.length()));
                    }
                }
            }
        }

        return false;
    }

    /**
     * KREVSKY SOLUTION #1:
     * time to solve (without DP) ~ 23 mins
     * <p>
     * 2 attempts:
     * - forgot base case: if (startIdx == s.length()) return true;
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }

        return wordBreak(s, 0, trie);
    }

    public boolean wordBreak(String s, int startIdx, Trie trie) {
        //base case:
        if (startIdx == s.length()) return true;

        TrieNode current = trie.root;
        for (int i = startIdx; i < s.length(); i++) {
            if (current.children[s.charAt(i) - 'a'] == null) {
                return false;
            }
            current = current.children[s.charAt(i) - 'a'];

            if (current.isWord) {
                //try to break the string s at this position
                boolean subRes = wordBreak(s, i + 1, trie);
                if (subRes) {
                    return true;
                }
            }
            //if we could not break the string at i-th point, move forward to find the next 'isWord' point and try again
        }
        //if we did not find any 'isWord' point that let's us to break the string into dictWords parts, then return false
        return false;
    }

    /**
     * KREVSKY SOLUTION #2:
     * = #1 + top-down DP with memoization!
     *
     * time to solve ~ 3 hours!!
     * idea: = idea from SOLUTION #1,
     * BUT the main problem was to introduce memoization properly. I looked at src/solving_techniques/p21_Trie/ExtraCharactersInString.java
     * the things that I had to apply:
     * change boolean to -1,0,1 to distinct "not filled" from "negative" result
     * use only concept dp[startIdx] (not dp[i] etc). just save the value before return it. and add "if (dp[startIdx] != 0) return dp[startIdx]"
     *
     * BEATS = 99%
     */
    public boolean wordBreakTopDownAndTrieKrev(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }

        int[] dp = new int[s.length() + 1]; //0 - undefined, -1 - false, 1 - true

        return wordBreakTopDownAndTrieKrev(s, 0, trie, dp) == 1;
    }

    //NOTE: if we count some dp it may be stored or used ONLY for startIdx!
    public int wordBreakTopDownAndTrieKrev(String s, int startIdx, Trie trie, int[] dp) {
        //base case:
        if (startIdx == s.length()) return 1;

        //use memo
        if (dp[startIdx] != 0) return dp[startIdx];

        TrieNode current = trie.root;
        for (int i = startIdx; i < s.length(); i++) {
            if (current.children[s.charAt(i) - 'a'] == null) {
                return dp[startIdx] = -1;
            }
            current = current.children[s.charAt(i) - 'a'];

            if (current.isWord) {
                //try to break the string s at this position
                //NOTE: todo - THE PROBLEM TO INTRODUCE MEMOIZATION WAS HERE!
                // the mistake: I tried to save the result of  wordBreakDpKrev(s, i + 1, trie, dp) to dp[i], BUT NOT in dp[startIdx]!
                // so the idea is to set dp[startIdx] = 1 if wordBreakDpKrev(s, i + 1, trie, dp) = 1. If not, we will try when(if) current.isWord = true again.
                int subRes = wordBreakTopDownAndTrieKrev(s, i + 1, trie, dp);
                if (subRes == 1) {
                    dp[startIdx] = subRes;
                    break;  //or return dp[startIdx]
                }
            }
            //if we could not break the string at i-th point, move forward to find the next 'isWord' point and try again
        }
        //if we did not find any 'isWord' point that let's us to break the string into dictWords parts, then return false
        return dp[startIdx];
    }

    class TrieNode {
        char letter;
        TrieNode[] children = new TrieNode[26];
        boolean isWord;

        public TrieNode(char c) {
            this.letter = c;
        }

        public TrieNode() {}

        public String toString() {
            return "" + letter;
        }
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode(c);
                }
                current = current.children[c - 'a'];
            }
            current.isWord = true;
        }
    }

    /**
     * LEETCODE SOLUTION #3: bottom-up:
     * based on https://leetcode.com/problems/word-break/editorial + KREVSKY adaptation
     * <p>
     * time ~ O(s.length() * wordDict*size() * k), where k - average length of the words in wordDict,
     *  since for each i we traverse through wordDict and for each word we compare substring and word => for each i we need to do wordDict*size() * k actions
     * space ~ O(s.length())
     *
     * BEATS = 83%
     */
    public boolean wordBreakBottomUp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (String word : wordDict) {
                if (i - word.length() >= 0 && dp[i - word.length()] == true) {
                    if (s.substring(i - word.length(), i).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * LEETCODE SOLUTION #4: Trie optimization
     * based on https://leetcode.com/problems/word-break/editorial + KREVSKY adaptation
     *
     * the idea of optimization is that we optimize the actions that are performed for each i:
     * in comparison with SOLUTION #3: (where we need to perform wordDict*size() * k actions for each i),
     * now we just need to traverse through the substring (j,s.length()) and execute some trie findings that cost O(1) =>
     *
     * we get time ~ O(O from i * O from j) ~ O(s.length() * s.length())
     *
     * but we also need to build the Trie ~ O(wordDict.length() * k)
     *
     * total time ~ O(s*length()^2 + wordDict.length() * k)
     * Space: O(s*length() + wordDict.length() * k)
     *
     * BEATS = 89%
     */
    public boolean wordBreakBottomUpAndTrie(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }

        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || dp[i - 1]) {
                TrieNode current = trie.root;
                for (int j = i; j < s.length(); j++) {
                    //traverse trie and mark dp[j] as true, if j-th element of s is marked (in tire) as isWord = true
                    char c = s.charAt(j);
                    if (current.children[c - 'a'] == null) {
                        // No words exist
                        break;
                    }

                    current = current.children[c - 'a'];
                    if (current.isWord) {
                        dp[j] = true;
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }

    /**
     * KREVSKY 26.11.2024
     *
     * BEATS ~ 90%
     */
    public boolean wordBreak26112024(String s, List<String> wordDict) {
        Map<String, Boolean> memo = new HashMap<>();

        return wordBreak26112024(s, wordDict, memo);
    }

    public boolean wordBreak26112024(String s, List<String> wordDict, Map<String, Boolean> memo) {
        if (s == null || s.length() == 0) return true;

        if (memo.containsKey(s)) return memo.get(s);

        for (String w : wordDict) {
            if (s.startsWith(w)) {
                if (wordBreak26112024(s.substring(w.length()), wordDict, memo)) {
                    memo.put(s, true);
                    return true;
                }
            }
        }

        memo.put(s, false);
        return false;
    }
}
