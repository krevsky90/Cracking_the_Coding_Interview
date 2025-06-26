package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency (medium)
 * #Company: Apple Amazon Bloomberg Expedia Facebook Google LinkedIn Uber Yahoo
 * <p>
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * <p>
 * Return the sorted string. If there are multiple answers, return any of them.
 * <p>
 * Example 1:
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * <p>
 * Example 2:
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * <p>
 * Example 3:
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * <p>
 * Constraints:
 * 1 <= s.length <= 5 * 10^5
 * s consists of uppercase and lowercase English letters and digits.
 */
public class SortCharactersByFrequency {
    /**
     * KREVSKY SOLUTION: O(nlogn)
     * idea: create map, push its entries to PriorityQueue, pull from the queue and build the final string
     * NOTE: the same idea is here: https://leetcode.com/problems/sort-characters-by-frequency/solutions/4689154/beats-100-users-c-java-python-javascript-2-approaches-explained/
     * <p>
     * time ~ O(nlogn)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS time = 33%
     * BEATS memory = 75%
     */
    public String frequencySort1(String s) {
        Map<Character, Integer> hm = new HashMap<>();

        for (char c : s.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );

        pq.addAll(hm.entrySet());

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }

        return result.toString();
    }

    /**
     * Approach 2 - bucket sort - see separate file
     */
}
