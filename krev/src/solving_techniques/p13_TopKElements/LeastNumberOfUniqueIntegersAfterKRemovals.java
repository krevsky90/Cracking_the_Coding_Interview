package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * 1481. Least Number of Unique Integers after K Removals
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals
 * (similar to https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1da0f1d305c96d5e6191d)
 * <p>
 * Given an array of integers arr and an integer k.
 * Find the least number of unique integers after removing exactly k elements.
 * <p>
 * Example 1:
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 * <p>
 * Constraints:
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {
    public static void main(String[] args) {
        int[] arr = {2,1,1,3,3,3};
        int k = 3;
        System.out.println(new LeastNumberOfUniqueIntegersAfterKRemovals().findLeastNumOfUniqueInts2(arr, k));
    }
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 13 mins
     * time ~ O(n)
     * space ~ O(n)
     * 1 attempt
     */
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> numToCountMap = new HashMap<>();
        for (int n : arr) {
            numToCountMap.put(n, numToCountMap.getOrDefault(n, 0) + 1);
        }

        Queue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b) -> {
            return a.getValue() - b.getValue();
        });

        for (Map.Entry<Integer, Integer> e : numToCountMap.entrySet()) {
            q.add(e);
        }

        while (!q.isEmpty() && k - q.peek().getValue() >= 0) {
            Map.Entry<Integer, Integer> e = q.poll();
            k -= e.getValue();
            numToCountMap.remove(e.getKey());
        }

        return numToCountMap.size();
    }

    /**
     * SOLUTION #2 without Queue
     * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/solutions/4734460/beats-99-users-c-java-python-javascript-explained/
     * idea: we just need to get sorted order of frequences and work with it
     */
    public int findLeastNumOfUniqueInts2(int[] arr, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int a : arr) mp.put(a, mp.getOrDefault(a, 0) + 1);

        List<Integer> v = new ArrayList<>();
        for (int a : mp.values()) {
            v.add(a);
        }

        Collections.sort(v);

        int cnt = 0;
        for (int i = 0; i < v.size() && k > 0; i++) {
            if (k >= v.get(i)) {
                k -= v.get(i);
                cnt++;
            }
        }
        return v.size() - cnt;
    }
}
