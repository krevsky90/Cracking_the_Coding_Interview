package solving_techniques.p14_KwayMerge;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a38552b33a04fe1cc698a5
 * OR similar
 * 373. Find K Pairs with Smallest Sums (medium)
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums
 *
 * I will solve from leetcode
 *
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * Example 2:
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 10^5
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1 and nums2 both are sorted in non-decreasing order.
 * 1 <= k <= 10^4
 * k <= nums1.length * nums2.length
 *
 */
public class ProblemChallenge1_KPairsWithLargestSums {
    /**
     * NOT SOLVED by myself (correctly)
     * problem: did not get what should be added to Heap. and how to work with it
     * info:
     * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/solutions/4052443/java-max-heap-easy-solution/
     * idea:
     * 1) starting to create all possible pairs (which will take len1*len2 in naive approach)
     * 2) use maxHeap to store pairs sorted by their sum. pushing the pairs from p.1
     * 3) each time check if maxheap.size() > k:
     * 3.2) HINT: both arrays are sorted. => if our current pair is more than maxHeap.peek(),
     *      then there is no sense to continue forming pairs, they will be bigger and bigger => break the process from p.1
     *
     * time to implement ~ 12 mins
     *
     * a lot of attempts
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Queue<Integer[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0] + b[1] - a[0] - a[1]));

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {

                if (maxHeap.size() >= k) {
                    int currentSum = nums1[i] + nums2[j];
                    Integer[] top = maxHeap.peek();
                    if (currentSum >= top[0] + top[1]) {
                        //#idea 3.2
                        break;
                    } else {
                        maxHeap.add(new Integer[]{nums1[i], nums2[j]});
                        maxHeap.poll();
                    }

                } else {
                    maxHeap.add(new Integer[]{nums1[i], nums2[j]});
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        while(maxHeap.size() > 0) {
            result.add(Arrays.asList(maxHeap.poll()));
        }

        return result;
    }


}
