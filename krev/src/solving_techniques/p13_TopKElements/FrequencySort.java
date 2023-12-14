package solving_techniques.p13_TopKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1d17973a7d4466d460750
 * OR
 * 451. Sort Characters By Frequency
 * https://leetcode.com/problems/sort-characters-by-frequency/
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
public class FrequencySort {
    public static void main(String[] args) {
        String s1 = "tree";
        String s2 = "cccaaa";
        String s3 = "Aabb";
        String res1 = frequencySort(s1);
        System.out.println(res1);
        String res2 = frequencySort(s2);
        System.out.println(res2);
        String res3 = frequencySort(s3);
        System.out.println(res3);
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 12 mins
     * time to debug ~ 6 mins
     * 1 attempt
     * <p>
     * time complexity ~ O(N)
     * space complexity ~ O(N), where N - length of s
     */
    public static String frequencySort(String s) {
        //0) corner cases
        if (s == null || s.isEmpty()) return s;

        //1) create map
        Map<Character, Integer> charToCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = charToCountMap.getOrDefault(c, 0);
            charToCountMap.put(c, count + 1);
        }

        //top should have maximum frequency! => max heap => (A, B) should return B and then A if frequency of B > frequency of A
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((pairA, pairB) ->
                pairB.getValue() - pairA.getValue()
        );

        //2) put pairs to queue
        for (Map.Entry<Character, Integer> e : charToCountMap.entrySet()) {
            q.add(e);
        }

        //3) concatenate the result
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Map.Entry<Character, Integer> e = q.poll();
            for (int i = 0; i < e.getValue(); i++) {
                sb.append(e.getKey());
            }
        }

        return sb.toString();
    }
}