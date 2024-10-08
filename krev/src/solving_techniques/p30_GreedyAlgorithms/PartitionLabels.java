package solving_techniques.p30_GreedyAlgorithms;

import java.util.*;

/**
 * 763. Partition Labels (medium)
 * https://leetcode.com/problems/partition-labels
 * <p>
 * #Company: Amazon Yandex
 * <p>
 * You are given a string s.
 * We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * <p>
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * <p>
 * Return a list of integers representing the size of these parts.
 * <p>
 * Example 1:
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * <p>
 * Example 2:
 * Input: s = "eccbbbbdec"
 * Output: [10]
 * <p>
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class PartitionLabels {
    public static void main(String[] args) {
        String s1 = "ababcbacadefegdehijhklij";
        String s2 = "eccbbbbdec";

        List<Integer> res1 = new PartitionLabels().partitionLabels(s1);
        List<Integer> res2 = new PartitionLabels().partitionLabels(s2);

        System.out.println("");

        Map<Character, Integer> map1 = new HashMap<>();
        System.out.println(map1.get('1'));
        System.out.println();
    }

    /**
     * KREVSKY SOLUTION 25.05.2024:
     * <p>
     * time to solve ~ 30 mins
     * <p>
     * idea:
     * 1) transform string to Map: char -> {start, end}
     * 2) sort list of {start, end} by start
     * 3) apply greedy algorithm with case "if (intervals.get(i)[0] > finish"
     * <p>
     * 3 attempts:
     * - incorrect sort by finish
     * - forgot "result.add(finish - start + 1)" in the end of the method
     * <p>
     * BEATS = 46%
     */
    public List<Integer> partitionLabels(String s) {
        //0. convert string to map of intervals
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int[] interval = map.get(c);
                interval[1] = i;
            } else {
                int[] interval = new int[]{i, i};
                map.put(c, interval);
            }
        }

        //1. the idea is the same as here https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/
        List<int[]> intervals = new ArrayList<>();
        for (Character c : map.keySet()) {
            intervals.add(map.get(c));
        }

        //2. sort intervals by start index
        Collections.sort(intervals, (a, b) -> a[0] - b[0]);

        //3. greedy approach
        List<Integer> result = new ArrayList<>();
        int start = intervals.get(0)[0];
        int finish = intervals.get(0)[1];

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i)[0] > finish) {
                result.add(finish - start + 1);
                start = intervals.get(i)[0];
                finish = intervals.get(i)[1];
            } else {
                finish = Math.max(finish, intervals.get(i)[1]);
            }
        }
        result.add(finish - start + 1);

        return result;
    }

    /**
     * KREVSKY #2: 04.10.2024
     * the same as https://leetcode.com/problems/partition-labels/solutions/1868842/java-c-visually-explaineddddd/
     * time to solve ~ 15 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 51%
     */
    public List<Integer> partitionLabels2(String s) {
        //0. convert string to map: Character -> index of the last occurence
        Map<Character, Integer> map = new HashMap<>();
        char[] arrS = s.toCharArray();
        for (int i = 0; i < arrS.length; i++) {
            char c = arrS[i];
            map.put(c, i);  //we don't calculate max element since each i is greates than the previous index of any letter
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < arrS.length; i++) {
            char c = arrS[i];
            int tempEnd = map.get(c);
            end = Math.max(end, tempEnd);
            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
                end = i + 1;
            }
        }

        return result;
    }
}