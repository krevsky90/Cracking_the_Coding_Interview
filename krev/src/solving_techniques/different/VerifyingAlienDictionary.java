package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 953. Verifying an Alien Dictionary (easy)
 * https://leetcode.com/problems/verifying-an-alien-dictionary
 * <p>
 * #Company: Airbnb Amazon Meta Microsoft
 * <p>
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographically in this alien language.
 * <p>
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * <p>
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * <p>
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 * <p>
 * Constraints:
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAlienDictionary {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * idea:
     * 1) create dictionary
     * 2) compare words as pairs (N comparisons)
     * 2.1) check if w1.len <= w2.len when all common symbols are the same
     *
     * time ~ O(26) + O(M) ~ O(M), where M - sum of length of all words
     * space ~ O(26) ~ O(1)
     *
     * 1 attempt
     *
     * BEATS ~ 51%
     */
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        char[] orderArr = order.toCharArray();
        for (int i = 0; i < order.length(); i++) {
            map.put(orderArr[i], i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (!isLexicoSorted(words[i], words[i + 1], map)) {
                return false;
            }
        }

        return true;
    }

    private boolean isLexicoSorted(String w1, String w2, Map<Character, Integer> map) {
        int len = Math.min(w1.length(), w2.length());
        for (int i = 0; i < len; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (map.get(w1.charAt(i)) < map.get(w2.charAt(i))) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        //for case: app & apple
        return w1.length() <= w2.length();
    }
}
