package solving_techniques.p21_Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1233. Remove Sub-Folders from the Filesystem (medium)
 * https://leetcode.com/problems/remove-sub-folders-from-the-filesystem
 * <p>
 * #Company: Amazon Google
 * <p>
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders.
 * You may return the answer in any order.
 * <p>
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 * A sub-folder of folder[j] must start with folder[j], followed by a "/".
 * For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
 * <p>
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
 * <p>
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 * <p>
 * Example 1:
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * <p>
 * Example 2:
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
 * <p>
 * Example 3:
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 * <p>
 * Constraints:
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'.
 * folder[i] always starts with the character '/'.
 * Each folder name is unique.
 */
public class RemoveSubFoldersFromTheFilesystem {
    /**
     * NOT SOLVED optimally by myself
     * hint: use Trie
     * <p>
     * time to solve ~ 25-30 mins
     * <p>
     * idea:
     * 1) split each word by "/"
     * 1.2) and build Trie:
     * - each node stores children MAP: splitted part -> TrieNode
     * - optionally nod contains fullWord - i.e. full folder path (like "/a/b")
     * 2) use DFS to traverse Trie:
     * - if current TrieNode has fullWord => save it and there is no reason to go deeper
     * - else - get TrieNode children and use DFS for each of them
     * <p>
     *
     * time ~ O(folder.length * L), where L - the length of the longest word
     * space ~ O(folder.length * L),
     *      since folder.length * L - fullWords that are saved in TrieNodes,
     *      O(result) = O(folder.length * L)  in case if all paths are unique
     *      max amount of TrieNodes = folder.length * L
     *      so... ~ 3*(folder.length * L) ~ (folder.length * L)
     *
     * 3 attempts:
     * - e.value() => e.getValue()
     * - forgot to remove word from TrieNode() constructor
     *
     * BEATS ~ 16%
     */
    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();
        for (String f : folder) {
            trie.addWord(f);
        }

        List<String> result = new ArrayList<>();

        dfs(trie.root, result);

        return result;
    }

    private void dfs(TrieNode node, List<String> result) {
        if (node.fullWord != null) {
            result.add(node.fullWord);
        } else {
            for (Map.Entry<String, TrieNode> e : node.children.entrySet()) {
                dfs(e.getValue(), result);
            }
        }
    }
}

class TrieNode {
    Map<String, TrieNode> children;
    String fullWord;

    public TrieNode() {
        this.children = new HashMap<>();
    }
}

class Trie {
    TrieNode root = new TrieNode();

    public void addWord(String w) {
        String[] split = w.split("/");
        TrieNode cur = root;
        for (String s : split) {
            if (!cur.children.containsKey(s)) {
                cur.children.put(s, new TrieNode());
            }
            cur = cur.children.get(s);
        }
        cur.fullWord = w;
    }

}
