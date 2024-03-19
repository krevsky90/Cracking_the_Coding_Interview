package solving_techniques.p21_Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/65a7f5b4b957670bb6b4eb63
 * OR
 * 1268. Search Suggestions System
 * https://leetcode.com/problems/search-suggestions-system
 *
 * You are given an array of strings products and a string searchWord.
 *
 * Design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with searchWord.
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
 *
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Explanation: The only word "havana" will be always suggested while typing the search word.
 *
 * Constraints:
 * 1 <= products.length <= 1000
 * 1 <= products[i].length <= 3000
 * 1 <= sum(products[i].length) <= 2 * 10^4
 *
 * All the strings of products are unique.
 * products[i] consists of lowercase English letters.
 * 1 <= searchWord.length <= 1000
 * searchWord consists of lowercase English letters.
 *
 */
public class SearchSuggestionsSystem {
    public static void main(String[] args) {
        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

//        String[] products = {"havana"};
//        String searchWord = "tatiana";

        List<List<String>> result = new SearchSuggestionsSystem().suggestedProducts(products, searchWord);
        for (List<String> l : result) {
            StringBuilder sb = new StringBuilder();
            for (String s : l) {
                sb.append(s).append(" ");
            }
            System.out.println(sb.toString());
        }

    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 50 mins
     *
     * 3 attempts:
     * - row "current = current.children[c - 'a'];" should be BEFORE "current.indexes.add(i);"
     * - findProductsByPrefix should return emptyList if prefix is not in the Trie - NOT OBVIOUS, not said in the description
     */
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<Integer> indexes = new ArrayList<Integer>();
//        boolean isWord;   //not used in this problem
    }

    public TrieNode root = new TrieNode();

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        buildTrie(products);

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i + 1);
            List<String> tempResult = findProductsByPrefix(prefix, products);
            result.add(tempResult);
        }

        return result;
    }

    public List<String> findProductsByPrefix(String prefix, String[] products) {
        List<String> tempResult = new ArrayList<>();
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                return tempResult;
            }
            current = current.children[c - 'a'];
        }

        //return first 3 products
        for (int i = 0; i < 3 && i < current.indexes.size(); i++) {
            tempResult.add(products[current.indexes.get(i)]);
        }

        return tempResult;
    }

    public void buildTrie(String[] products) {
        Arrays.sort(products);
        //build Trie
        for (int i = 0; i < products.length; i++) {
            addWord(products, i);
        }
    }

    public void addWord(String[] products, int i) {
        TrieNode current = root;
        String word = products[i];
        for (char c : word.toCharArray()) {
            if (current.children[c - 'a'] == null) {
                current.children[c - 'a'] = new TrieNode();
            }
            current = current.children[c - 'a'];
            current.indexes.add(i);
        }
//        current.isWord = true;
    }
}