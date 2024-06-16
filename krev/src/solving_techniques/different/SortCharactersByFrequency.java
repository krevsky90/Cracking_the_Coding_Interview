package solving_techniques.different;

import java.util.*;

/**
 * 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency (medium)
 * #Company: Apple
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
     * SOLUTION #2: O(n)
     * info: https://www.youtube.com/watch?v=OWPXweP5B0Q&list=PLUPSMCjQ-7oeyhZZ7xjXPQmWEn1EQUiME&index=18
     * idea: use Bucket sort
     * 1) create map c -> freq and track max_freq
     * 2) create 2D array (size = max_freq), where i-th row has chars with freq = i + 1
     * 3) just traverse through 2D array from end to start and build the string
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS time = 56%
     * BEATS memory = 5%
     */
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxFreq = -1;
        for (char c : s.toCharArray()) {
            int tempFreq = map.getOrDefault(c, 0) + 1;
            maxFreq = Math.max(maxFreq, tempFreq);
            map.put(c, tempFreq);
        }

        List<List<Character>> buckets = new ArrayList<>(maxFreq + 1);
        for (int i = 0; i < maxFreq + 1; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Character c : map.keySet()) {
            buckets.get(map.get(c)).add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = buckets.size() - 1; i > 0; i--) {
            List<Character> tempList = buckets.get(i);
            for (int j = 0; j < tempList.size(); j++) {
                for (int c = 0; c < i; c++) {
                    sb.append(tempList.get(j));
                }
            }
        }

        return sb.toString();
    }
}
