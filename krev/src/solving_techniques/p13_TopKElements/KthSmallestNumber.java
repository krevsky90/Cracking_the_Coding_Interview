package solving_techniques.p13_TopKElements;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1cc3eccf84a14e3c8ec6f
 * OR (similar, but largest element)
 * 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * <p>
 * let's solve 'smallest' problem
 * Given an unsorted array of numbers, find Kth smallest number in it.
 * <p>
 * Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.
 * <p>
 * Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.
 * <p>
 * Example 1:
 * Input: [1, 5, 12, 2, 11, 5], K = 3
 * Output: 5
 * Explanation: The 3rd smallest number is '5', as the first two smaller numbers are [1, 2].
 * <p>
 * Example 2:
 * Input: [1, 5, 12, 2, 11, 5], K = 4
 * Output: 5
 */
public class KthSmallestNumber {
    public static void main(String[] args) {
        int[] arr1 = {1,5,12,2,11,5};
        int k1 = 3;
        System.out.println(findKthSmallest(arr1, k1));
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 7 mins
     * time ~ O(n)
     * space ~ O(k)
     * 1 attempt
     */

//    [1,5,12,2,11,5],K =3
//    q =5 2 1
    public static int findKthSmallest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        for (int n : nums) {
            q.add(n);
            if (q.size() > k) {
                q.poll();
            }
        }

        return q.peek();
    }
}