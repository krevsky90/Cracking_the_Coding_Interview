package solving_techniques.p21_Trie;

/**
 * 616 - Add Bold Tag in String (medium) (locked)
 * https://leetcode.com/problems/add-bold-tag-in-string
 * OR
 * https://leetcode.ca/all/616.html
 *
 * #Company: Facebook Google
 *
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
 * If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 *
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 *
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 *
 * Note:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 */
public class AddBoldTagInString {
    class TrieNode {
        TrieNode[] children = new TrieNode[128];
        boolean isEnd;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c] == null) {
                    current.children[c] = new TrieNode();
                }
                current = current.children[c];
            }
            current.isEnd = true;
        }
    }

    /**
     * KREVSKY SOLUTION: but with optimization (idea #2)
     * time to solve ~ 35 mins
     * idea:
     * 1) use Trie in similar way as IndexPairsOfString
     * 2) use idea from https://www.youtube.com/watch?v=WXb3ItOlRfQ&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=90
     *  i.e. just mark indexes of letters that should be bold,
     *  instead of merging intervals
     *
     *  time ~ O(s.length()^2)
     *  space ~ O(s.length())
     *
     *  3 attempts:
     *  - wrote "children[arr[j] - 'a']" but since we might have numbers, it gives OOB exception
     *  - wrote "else if (root.isEnd)" instead of correct "else if (root.children[arr[j]].isEnd)"
     */
    public String addBoldTag(String s, String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }

        char[] arr = s.toCharArray();
        boolean[] bold = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            TrieNode root = trie.root;
            for (int j = i; j < s.length(); j++) {
                if (root.children[arr[j]] == null) {
                    break;
                } else if (root.children[arr[j]].isEnd) {
                    for (int k = i; k <= j; k++) {
                        bold[k] = true;
                    }
                }
                root = root.children[arr[j]];
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < bold.length) {
            if (bold[i] == false) {
                sb.append(arr[i]);
                i++;
            } else {
                sb.append("<b>");
                while (i < bold.length && bold[i]) {
                    sb.append(arr[i]);
                    i++;
                }
                sb.append("</b>");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "abcxyz123";
        String[] words1 = {"abc","123"};

        String s2 = "aaabbb";
        String[] words2 = {"aa","b"};

        String s3 =  "aaabbcc";
        String[] words3 = {"aaa","aab","bc"};

        AddBoldTagInString obj = new AddBoldTagInString();
        System.out.println(obj.addBoldTag(s1, words1));
        System.out.println(obj.addBoldTag(s2, words2));
        System.out.println(obj.addBoldTag(s3, words3));
    }
}
