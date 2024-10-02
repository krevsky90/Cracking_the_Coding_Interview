package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. Isomorphic Strings (easy)
 * https://leetcode.com/problems/isomorphic-strings/
 * <p>
 * #Company: Adobe Amazon Bloomberg Google LinkedIn Oracle Salesforce Yahoo Yelp Yandex
 * <p>
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 * <p>
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * Explanation:
 * The strings s and t can be made identical by:
 * Mapping 'e' to 'a'.
 * Mapping 'g' to 'd'.
 * <p>
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * Explanation:
 * The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.
 * <p>
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 * <p>
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {
    /**
     * NOT SOLVED by myself
     * idea:
     * keep 2 maps: t -> s and s -> t
     * and traverse through the strings checking if double mapping is not broken in case is mapping exists
     * if not exists -> map chars of the strings to each other
     * <p>
     * time to solve ~ 36 mins
     * <p>
     * BEATS ~ 5 ot 48%
     * <p>
     * similar to https://www.youtube.com/watch?v=7yF-U1hLEqQ
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (mapST.containsKey(s.charAt(i)) && t.charAt(i) != mapST.get(s.charAt(i))) {
                return false;
            } else if (mapTS.containsKey(t.charAt(i)) && s.charAt(i) != mapTS.get(t.charAt(i))) {
                return false;
            } else {
                mapST.put(s.charAt(i), t.charAt(i));
                mapTS.put(t.charAt(i), s.charAt(i));
            }
        }

        return true;
    }
}
