package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 127. Word Ladder (hard)
 * https://leetcode.com/problems/word-ladder
 * <p>
 * #Company: Affirm Airbnb Amazon Apple Audible Bloomberg Cohesity Expedia Facebook Google LinkedIn Lyft Microsoft Oracle Qualtrics Salesforce Samsung Snapchat Square Tesla Uber Walmart Labs Yelp Zillow
 * <p>
 * A transformation sequence from word beginWord to word endWord
 * using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * <p>
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * <p>
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * Constraints:
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
public class WordLadder {
    /**
     * KREVSKY SOLUTION:
     * time to solve  ~ 8 (think) + 20 (implement) + 2 (debug) ~ 30 mins
     * idea:
     * use BFS graph approach
     * 1) convert words to vertices: build adj Lists
     * 2) use BFS
     * <p>
     * time ~ O(wordList.size() * wordList.size() * N, where N - the length of the longest word
     * space ~ O(wordList.size() * wordList.size()) * N)
     *
     * 4 attempts:
     * - 2 typos
     * - initially I did not get the problem correctly and returned the number of transitions, but not the number of word
     *      so... fixed it as "q.add(new int[]{beginIdx, 1});" but not "q.add(new int[]{beginIdx, 0});"
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int endIdx = -1;

        List<Set<Integer>> adjList = new ArrayList<>(wordList.size() + 1);
        for (int i = 0; i < wordList.size(); i++) {
            adjList.add(new HashSet<>());
            if (endWord.equals(wordList.get(i))) {
                endIdx = i;
            }
        }

        // O(wordList.size() * wordList.size() * N, where N - the length of the longest word
        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (isAdjWord(wordList.get(i), wordList.get(j))) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        //for beginWord
        adjList.add(new HashSet<>());
        int beginIdx = adjList.size() - 1;
        for (int i = 0; i < wordList.size(); i++) {
            if (isAdjWord(wordList.get(i), beginWord)) {
                adjList.get(i).add(beginIdx);
                adjList.get(beginIdx).add(i);
            }
        }

        boolean[] visited = new boolean[adjList.size()];
        visited[beginIdx] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{beginIdx, 1});

        // O(wordList.size()
        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int wordIdx = pair[0];
            int counter = pair[1];
            if (wordIdx == endIdx) return counter;

            for (int adjWordIdx : adjList.get(wordIdx)) {
                if (!visited[adjWordIdx]) {
                    visited[adjWordIdx] = true;
                    q.add(new int[]{adjWordIdx, counter + 1});
                }
            }
        }

        return 0;
    }

    private boolean isAdjWord(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff >= 1) {
                    return false;
                } else {
                    diff++;
                }
            }
        }

        return true;
    }
}
