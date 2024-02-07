package solving_techniques.p1_SlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6385d8b24a29c96532f7c329
 * OR
 * 438. Find All Anagrams in a String
 * https://leetcode.com/problems/find-all-anagrams-in-a-string
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * Constraints:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s and p consist of lowercase English letters.
 */
public class ProblemChallenge2_StringAnagrams {
    /**
     * KREVSKY SOLUTION:
     * idea: is the same as for ProblemChallenge1_PermutationInString # checkInclusionOptimized(..)
     *
     * time to solve ~ 25 mins
     *
     * time ~ O(n)
     * space ~ O(1). since we have 2 arrays with length = 26
     *
     * 3 attempts:
     * - forget "if (compareArrs(arrP, arrS))" after the for-loop
     * - forget "if (p.length() > s.length()) return result;"
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) return result;

        int[] arrP = new int[26];
        for (int i = 0; i < p.length(); i++) {
            arrP[p.charAt(i) - 'a']++;
        }

        int[] arrS = new int[26];
        for (int i = 0; i < p.length(); i++) {
            arrS[s.charAt(i) - 'a']++;
        }

        for (int start = 0; start < s.length() - p.length(); start++) {
            if (compareArrs(arrP, arrS)) {
                result.add(start);
            }

            arrS[s.charAt(start) - 'a']--;
            arrS[s.charAt(start + p.length()) - 'a']++;
        }

        if (compareArrs(arrP, arrS)) {
            result.add(s.length() - p.length());
        }

        return result;
    }

    private boolean compareArrs(int[] data1, int[] data2) {
        for (int i = 0; i < data1.length; i++) {
            if (data1[i] != data2[i]) return false;
        }
        return true;
    }
}
