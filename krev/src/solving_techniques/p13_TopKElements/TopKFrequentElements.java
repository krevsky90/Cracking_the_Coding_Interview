package solving_techniques.p13_TopKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1d04a19e6a3ce13cedede
 * OR
 * 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -104 <= nums[i] <= 10^4
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            int value = map.getOrDefault(n, 0);
            map.put(n, value + 1);
        }

        //find top K keys of the map using heap
        //top = max => should use min Heap => PriorityQueue
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        //gather keys of the elements that are in queue
        int[] result = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i] = pq.poll().getKey();
            i++;
        }

        return result;
    }
}
