package solving_techniques.p21_Trie;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/652e0225ba11faebe9a2b521
 * OR
 * 208. Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree
 * <p>
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * Example 1:
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * Constraints:
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 10^4 calls in total will be made to insert, search, and startsWith.
 */
public class ImplementTriePrefixTree {
    /**
     * KREVSKY SOLUTION:
     */
    class NodeKrev {
        Map<Character, NodeKrev> children = new HashMap<>();
        boolean isWord;
    }

    private NodeKrev rootKrev;

    public ImplementTriePrefixTree() {
        rootKrev = new NodeKrev();
        root = new TrieNode();
    }

    public void insertKrev(String word) {
        NodeKrev current = rootKrev;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                NodeKrev newNode = new NodeKrev();
                current.children.put(c, newNode);
            }
            current = current.children.get(c);
        }
        current.isWord = true;
    }

    public boolean searchKrev(String word) {
        NodeKrev current = traverseKrev(word);
        return current != null && current.isWord;
    }

    public boolean startsWithKrev(String prefix) {
        NodeKrev current = traverseKrev(prefix);
        return current != null;
    }

    private NodeKrev traverseKrev(String word) {
        NodeKrev current = rootKrev;
        for (char c : word.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return null;
            }
            current = current.children.get(c);
        }
        return current;
    }

    /**
     * SOLUTION
     * https://www.youtube.com/watch?v=FoBULb3fktE
     * idea: no HashMap, but array, where index = ch - 'a'
     */
    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public TrieNode root;

    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        TrieNode current = traverse(word);
        return current != null && current.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode current = traverse(prefix);
        return current != null;
    }

    private TrieNode traverse(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                return null;
            }
            current = current.children[c - 'a'];
        }
        return current;
    }
}



