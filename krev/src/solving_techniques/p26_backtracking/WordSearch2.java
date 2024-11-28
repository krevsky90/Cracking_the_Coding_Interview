package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 212. Word Search II (hard)
 * https://leetcode.com/problems/word-search-ii
 * <p>
 * #Company: Airbnb Amazon Apple Bloomberg Citadel Facebook Google Houzz Microsoft Oracle Snapchat Uber Yahoo
 * <p>
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * <p>
 * Example 1:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * <p>
 * Example 2:
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 * <p>
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class WordSearch2 {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use Trie, its nodes contain word in case is the node is the latest letter of this word
     * 2) gather the list of the words starting from each cell of the board
     * - if i,j-th cell is not out of bound && not visited && exists in Trie && trieNode.word != null => add the word to result
     * 3) use backtracking:
     * a) mark i,j-th cell as visited
     * b) start dfs in 4 directions
     * c) mark i,j-th cell as UNvisited
     * <p>
     * time to solve ~ 36 mins
     *
     * Complexity estimations: see https://leetcode.com/problems/word-search-ii/solutions/4707890/backtracking-solution-using-trie-explained/
     * time ~ O(C + board.length * board[0].length * 4^L),
     *      where C - total length of all words from dictionary, L - the longest word from dictionary
     * space ~ O(C) - trie storage, O(board.length * board[0].length) - visited storage, O(L) - recursion stack, O(C) - result list
     *  => O(C + board.length * board[0].length)
     * <p>
     * 3 attempts:
     * - forgot to implement buildTrie
     * - forgot to mark i,j-th cell as unvisited (see idea #3.c)
     * <p>
     * BEATS ~ 54%
     */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();

        Trie trie = buildTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(board, i, j, trie, result);
            }
        }

        return new ArrayList<>(result);
    }

    private Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.addWord(w);
        }
        return trie;
    }

    private void findWords(char[][] board, int i, int j, Trie trie, Set<String> result) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        TrieNode cur = trie.root;

        dfs(board, i, j, cur, result, visited);
    }

    private void dfs(char[][] board, int i, int j, TrieNode cur, Set<String> result, boolean[][] visited) {
        if (!(0 <= i && i < board.length && 0 <= j && j < board[0].length && !visited[i][j])) return;

        if (cur.children[board[i][j] - 'a'] == null) return;

        //if not null => exists in Trie
        cur = cur.children[board[i][j] - 'a'];

        visited[i][j] = true;

        if (cur.word != null) {
            result.add(cur.word);
        }

        //go deeper
        dfs(board, i - 1, j, cur, result, visited);
        dfs(board, i + 1, j, cur, result, visited);
        dfs(board, i, j + 1, cur, result, visited);
        dfs(board, i, j - 1, cur, result, visited);

        //backtrack:
        visited[i][j] = false;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    class Trie {
        public TrieNode root = new TrieNode();

        public void addWord(String word) {
            TrieNode cur = root;
            char[] arr = word.toCharArray();
            for (char c : arr) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = word;
        }
    }
}
