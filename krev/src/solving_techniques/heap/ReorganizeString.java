package solving_techniques.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 767. Reorganize String (medium)
 * https://leetcode.com/problems/reorganize-string
 * <p>
 * #Company (11.02.2025): 0 - 3 months Amazon 34 Google 6 Roblox 4 Meta 2 0 - 6 months TikTok 5 Microsoft 3 Oracle 2 6 months ago Pinterest 14 Adobe 9 Apple 8 Uber 7 Bloomberg 6 PayPal 5 Yahoo 5 Tesla 3 Walmart Labs 2 Goldman Sachs 2
 * <p>
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * <p>
 * Return any possible rearrangement of s or return "" if not possible.
 * <p>
 * Example 1:
 * Input: s = "aab"
 * Output: "aba"
 * <p>
 * Example 2:
 * Input: s = "aaab"
 * Output: ""
 * <p>
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class ReorganizeString {
    /**
     * idea:
     * 1) create freq map
     * 2) put all pairs to max heap
     * 3) take top 2 elements and add their chars to the result
     * 3.2) if occurrence of obtained pair > 1 => push it back to heap with n-1 occurrence
     *
     * time to solve ~ 9 mins
     *
     * time ~ O(n*logn)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS ~ 54%
     */
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.n - a.n); //max heap of pairs (character -> its frequency)
        for (char c : map.keySet()) {
            pq.add(new Pair(c, map.get(c)));
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() >= 2) {
            Pair p1 = pq.poll();
            Pair p2 = pq.poll();

            sb.append(p1.c).append(p2.c);
            if (p1.n > 1) {
                pq.add(new Pair(p1.c, p1.n - 1));
            }

            if (p2.n > 1) {
                pq.add(new Pair(p2.c, p2.n - 1));
            }
        }

        if (pq.size() == 1 && pq.peek().n > 1) return "";

        //otherwise it means that pq is empty OR the only one element in pq has 1 occurrence
        if (!pq.isEmpty()) sb.append(pq.poll().c);

        return sb.toString();
    }

    class Pair {
        char c;
        int n;

        Pair(char c, int n) {
            this.c = c;
            this.n = n;
        }
    }
}
