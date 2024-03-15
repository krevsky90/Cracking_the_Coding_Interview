https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/652d0ab87de1d937871990c8

Theory:
A Trie, short for retrieval, is a specialized tree-based data structure primarily used for efficient storing, searching, and retrieval of strings over a given alphabet.
It excels in scenarios where a large collection of strings needs to be managed and pattern-matching operations need to be performed with optimal efficiency.

https://www.youtube.com/watch?v=CZA9bLo9_VQ
https://www.youtube.com/watch?v=AcFHT2l1b3E

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
}

TrieNode root = new TrieNode();

public WordDictionary() {}

public void addWord(String word) {
    TrieNode current = root;
    for (char c : word.toCharArray()) {
        if (current.children[c - 'a'] == null) {
            current.children[c - 'a'] = new TrieNode();
        }
        current = current.children[c - 'a'];
    }
    current.isWord = true;
}



Sequence of problems:
1) Implement Trie (Prefix Tree) (medium) - done
2) Index Pairs of a String (easy) - done
3) Design Add and Search Words Data Structure (medium) - done
4) Extra Characters in a String (medium) - done
5) Search Suggestions System (medium) - todo
