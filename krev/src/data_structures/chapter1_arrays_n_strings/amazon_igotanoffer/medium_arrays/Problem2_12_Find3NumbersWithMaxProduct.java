package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/maximum-product-of-three-numbers/solutions/104729/java-o-1-space-o-n-time-solution-beat-100/
 * <p>
 * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 6
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 24
 * <p>
 * Example 3:
 * Input: nums = [-1,-2,-3]
 * Output: -6
 * <p>
 * Constraints:
 * 3 <= nums.length <= 10000
 * -1000 <= nums[i] <= 1000
 */
public class Problem2_12_Find3NumbersWithMaxProduct {
    /**
     * NOT SOLVED by me (the idea is the same as below, but I started to think in wrong way analyzing positive, zero and negative cases)
     * space ~ O(1)
     * time ~ O(n)
     */
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }

        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    /**
     * Using heap (helps to solve the problem in case when question is not about 3 numbers but ANY number
     */
    public int maximumProductHeap(int[] nums) {
        PriorityQueue<Integer> poheap = new PriorityQueue<>();
        PriorityQueue<Integer> neheap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            poheap.offer(num);
            neheap.offer(num);
            if (poheap.size() > 3) {
                poheap.poll();
            }
            if (neheap.size() > 2) {
                neheap.poll();
            }
        }
        int c1 = 1;
        int max = 0;
        while (!poheap.isEmpty()) {
            max = poheap.poll();
            c1 *= max;
        }

        while (!neheap.isEmpty()) {
            max *= neheap.poll();
        }

        return Math.max(c1, max);
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(3);
        int[] arr = new int[]{3,3,6,9,2,5,7};
        for (int i : arr) {
            pq.add(i);
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        new Problem2_12_Find3NumbersWithMaxProduct().maximumProductHeap(arr);
        System.out.println("");
    }
}
