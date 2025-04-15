package solving_techniques.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * 2551. Put Marbles in Bags (hard)
 * https://leetcode.com/problems/put-marbles-in-bags
 *
 * #Company (15.04.2025): 0 - 3 months Amazon 13 Meta 5 Google 3 TikTok 2 0 - 6 months DE Shaw 2 6 months ago Apple 3 Flipkart 2 Uber 2
 *
 * You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of the ith marble.
 * You are also given the integer k.
 * <p>
 * Divide the marbles into the k bags according to the following rules:
 * <p>
 * No bag is empty.
 * If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and jth indices should also be in that same bag.
 * If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is weights[i] + weights[j].
 * The score after distributing the marbles is the sum of the costs of all the k bags.
 * <p>
 * Return the difference between the maximum and minimum scores among marble distributions.
 * <p>
 * Example 1:
 * Input: weights = [1,3,5,1], k = 2
 * Output: 4
 * Explanation:
 * The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6.
 * The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10.
 * Thus, we return their difference 10 - 6 = 4.
 * <p>
 * Example 2:
 * Input: weights = [1, 3], k = 2
 * Output: 0
 * Explanation: The only distribution possible is [1],[3].
 * Since both the maximal and minimal score are the same, we return 0.
 * <p>
 * Constraints:
 * 1 <= k <= weights.length <= 10^5
 * 1 <= weights[i] <= 10^9
 */
public class PutMarblesInBags {
    /**
     * NOT SOLVED by myself
     * idea:
     * weights[0] and weights[n - 1] will be part of total sum anyway => will not consider it since it does not influence on max - min
     *
     * if we split at i-th index, it will increase total sum by weights[i] + weights[i+1]
     * where i = 0 .. n - 1 inclusively
     * Let's pre-calculate these additions for each i
     */

    /**
     * Official solution:
     * just sort pre-calculated sums of bounds and calculate total sum of sub-array [0, k-2] and the same from right bound
     *
     * BEATS ~ 99%
     */
    public long putMarblesSort(int[] weights, int k) {
        int n = weights.length;
        if (n == k) return 0;

        int[] parts = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            parts[i] = weights[i] + weights[i + 1];
        }

        Arrays.sort(parts);

        long minSum = 0;
        for (int i = 0; i < k - 1; i++) {
            minSum += parts[i];
        }

        long maxSum = 0;
        for (int i = 0; i < k - 1; i++) {
            maxSum += parts[n - 2 - i];
        }

        return maxSum - minSum;
    }

    /**
     * SOLUTION #2:
     * using Heap
     * <p>
     * time to solve ~ 50 mins
     * <p>
     * time ~ O(n*logk)
     * space ~ O(n + k)
     * <p>
     * BEATS ~ 5%
     */
    public long putMarblesHeap(int[] weights, int k) {
        int n = weights.length;
        if (n == k) return 0;

        //weights[0] and weights[n - 1] will be part of total sum anyway => will not consider it since it does not influence on max - min

        //if we split at i-th index, it will increase total sum by weights[i] + weights[i+1]
        //where i = 0 .. n - 1 inclusively
        //Let's pre-calculate these additions for each i
        int[] parts = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            parts[i] = weights[i] + weights[i + 1];
        }

        //keep max heap with k - 1 elements. it stores min elements => helps to calc min sum
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < parts.length; i++) {
            maxHeap.add(parts[i]);
            if (maxHeap.size() > k - 1) {
                maxHeap.poll();
            }
        }

        //keep min heap with k - 1 elements. it stores max elements => helps to calc max sum
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < parts.length; i++) {
            minHeap.add(parts[i]);
            if (minHeap.size() > k - 1) {
                minHeap.poll();
            }
        }

        //calc min sum
        long minSum = 0;
        while (!maxHeap.isEmpty()) {
            minSum += maxHeap.poll();
        }

        //calc max sum
        long maxSum = 0;
        while (!minHeap.isEmpty()) {
            maxSum += minHeap.poll();
        }

        return maxSum - minSum;
    }
}
