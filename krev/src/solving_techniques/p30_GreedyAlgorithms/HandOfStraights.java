package solving_techniques.p30_GreedyAlgorithms;

import java.util.*;

/**
 * 846. Hand of Straights
 * https://leetcode.com/problems/hand-of-straights
 * <p>
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize,
 * and consists of groupSize consecutive cards.
 * <p>
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize,
 * return true if she can rearrange the cards, or false otherwise.
 * <p>
 * Example 1:
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 * <p>
 * Example 2:
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 * <p>
 * Constraints:
 * 1 <= hand.length <= 10^4
 * 0 <= hand[i] <= 10^9
 * 1 <= groupSize <= hand.length
 */
public class HandOfStraights {
    /**
     * NOT SOLVED by myself, byt idea was correct
     * info:
     * https://leetcode.com/problems/hand-of-straights/solutions/5054533/hand-of-straights-java-greedy-hash-map-priority-queue/
     * <p>
     * idea:
     * 1) create map: number -> frequency
     * 2) convert to minHeap, where each element is [number, frequency]
     * 3) while minHeap is not empty:
     * take min element and try to build group, starting from this element,
     * i.e. take groupSize - 1 elements from the same minHeap (in internal loop)
     * and (if curEl - prevEl == 1 is true) decrease frequency for taken elements
     * and save them to temp group list
     * once the group is filled, add the elements back from temp group list to minHeap, if frequency > 0
     * <p>
     * time to solve ~ 60 mins
     * <p>
     * time ~ O(nlogn) + O(n*(logn + k*logn + k*logn)) ~ O(nlogn + n*logn + n*k*logn) ~ O(n*k*logn)
     * space ~ O(n), for map and pq
     * 1 attempt
     * <p>
     * BEATS = 64%
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        if (groupSize == 1) return true;

        Map<Integer, Integer> map = new HashMap<>();
        for (int h : hand) {
            if (map.containsKey(h)) {
                map.put(h, map.get(h) + 1);
            } else {
                map.put(h, 1);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);   //put int -> frequency
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.add(new int[]{e.getKey(), e.getValue()});
        }

        while (!pq.isEmpty()) {     //O(n)
            List<int[]> tempGroup = new ArrayList<>();
            int[] minPair = pq.poll();      //O(logn)
            int curValue = minPair[0];
            minPair[1]--;
            tempGroup.add(minPair);

            for (int j = 1; j < groupSize; j++) {   //O(k)
                if (!pq.isEmpty()) {
                    int[] tempPair = pq.poll();     //O(logn)
                    if (tempPair[0] - curValue != 1) {
                        return false;
                    }

                    tempPair[1]--;
                    tempGroup.add(tempPair);

                    curValue = tempPair[0];

                } else {
                    return false;
                }
            }

            // Add back Pairs to the priority queue if their frequency is greater than 0
            for (int[] p : tempGroup) {     //O(k)
                if (p[1] > 0) {
                    pq.add(p);      //O(logn)
                }
            }
        }

        return true;
    }
}
