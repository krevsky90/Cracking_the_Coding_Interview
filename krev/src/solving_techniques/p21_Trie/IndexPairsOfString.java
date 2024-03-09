package solving_techniques.p21_Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/65a7852e37950fb882d813ae
 * OR
 * https://leetcode.com/problems/index-pairs-of-a-string (locked)
 *
 * Given a string text and a list of strings words, identify all [i, j] index pairs
 * such that the substring text[i...j] is in words.
 *
 * These index pairs should be returned in ascending order, first by the start index, then by the end index.
 * Find every occurrence of each word within the text, ensuring that overlapping occurrences are also identified.
 *
 * Examples
 * Input: text = "bluebirdskyscraper", words = ["blue", "bird", "sky"]
 * Expected Output: [[0, 3], [4, 7], [8, 10]]
 */
public class IndexPairsOfString {
    public static void main(String[] args) {
        String text = "bluebirdskyscraper";
        String[] words = {"blue", "bird", "sky"};

        int[][] result = new IndexPairsOfString().findIndexPairsOfString(text, words);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 25 mins
     *
     * 2 attempts:
     * - should put "node = node.children[c - 'a'];' before "if (node.isWord) {"
     */
    public int[][] findIndexPairsOfString(String text, String[] words) {
        //initialize and fulfill trie:
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }

        List<int[]> result = new ArrayList<>();
        int n = text.length();
        for (int i = 0; i < n; i++) {
            TrieNode node = trie.root;
            for (int j = i; j < n; j++) {
                char c = text.charAt(j);
                if (node.children[c - 'a'] == null) {
                    break;
                }

                node = node.children[c - 'a'];

                if (node.isWord) {
                    result.add(new int[]{i,j});
                }
            }
        }

        return result.toArray(new int[0][]);
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
        char c; //just for better debugging

        public TrieNode(char c) {
            this.c = c;
        }
    }

    class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode('0');
        }

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
}
