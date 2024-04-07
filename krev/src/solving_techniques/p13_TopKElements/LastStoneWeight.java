package solving_techniques.p13_TopKElements;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1046. Last Stone Weight (easy?)
 * https://leetcode.com/problems/last-stone-weight
 * <p>
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together.
 * Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the weight of the last remaining stone. If there are no stones left, return 0.
 * <p>
 * Example 1:
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 * <p>
 * Example 2:
 * Input: stones = [1]
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class LastStoneWeight {
    // 2,7,4,1,8,1
    // 8 7 4 2 1 1 => 1 v 4 2 1 1 => 4 2 1 1 1
    // 4 2 1 1 1 => 2 v 1 1 1 => 2 1 1 1
    // 2 1 1 1 => 1 v 1 1 => 1 1 1
    // 1 1 1 => 1


    /**
     * KREVSKY SOLUTION:
     * NOTE: very similar to src/solving_techniques/p13_TopKElements/ConnectRopes.java
     *
     * time ~ 12 mins
     *
     * 3 attempts:
     * - incorrect method: need "Collections.reverseOrder()" instead of "Collections.reverse()"
     * - incorrect method: need "pq.poll()" instead of "pq.pop()"
     *
     * BEATS = 98%
     **/
    public int lastStoneWeight(int[] stones) {
        //max heap to take max elements from the top
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            pq.add(stone);
        }

        //take 2 max stones and smash them
        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();
            if (x < y) {
                pq.offer(y - x);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}
