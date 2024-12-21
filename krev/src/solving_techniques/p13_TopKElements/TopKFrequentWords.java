package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * 692. Top K Frequent Words (medium)
 * https://leetcode.com/problems/top-k-frequent-words
 * <p>
 * #Company: 0 - 3 months Google 2 Amazon 2 Bloomberg 2 Uber 2 0 - 6 months Box 5 Meta 2 Microsoft 2 Oracle 2 TikTok 2  Yelp 2 Pocket Gems 2 6 months ago Yandex 8 Apple 3 Netflix 3 ServiceNow 3 Goldman Sachs 2 Tesla 2 Smartsheet 2
 * <p>
 * Given an array of strings words and an integer k, return the k most frequent strings.
 * <p>
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 * <p>
 * Example 1:
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * <p>
 * Example 2:
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 * <p>
 * Constraints:
 * 1 <= words.length <= 500
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * k is in the range [1, The number of unique words[i]]
 * <p>
 * Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */
public class TopKFrequentWords {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) freqMap
     * 2) use Priority Queue (min heap) with comparator:
     * less freq => top
     * if freq is the same => lexicographical inverted order
     * 3) reverse the final collection to return the list sorted by anti-rules, in comparison with p.2
     * <p>
     * time to solve ~ 14 mins
     * time ~ O(n) to build freqMap + O(n*logk) to fill PQ + O(k*logk) to poll from PQ + O(k) to reverse
     *      since k < n => O(n) + O(n*logk) ~ O(n*logk)
     * space ~ O(n) for freqMap + O(k) for PQ => O(n + k) ~ O(n)
     * <p>
     * BEATS ~ 67%
     *
     * 3 attempts:
     * - did not compare words in PQ's comparator
     * - forgot to use getKey() in PQ's comparator
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        //min heap
        //sort by frequency (min is top) and lexicographically (later is top, i.e. "wb" is before "wa")
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        // example:
        // 2 w1
        // 3 wb
        // 3 wa
        // 4 rr
        for (Map.Entry<String, Integer> e : freqMap.entrySet()) {
            pq.add(e);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }

        Collections.reverse(result);

        return result;
    }
}
