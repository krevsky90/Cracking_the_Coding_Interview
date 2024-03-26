package solving_techniques.p13_TopKElements;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1decab6ea18009a34e047
 * OR
 * https://www.geeksforgeeks.org/sum-elements-k1th-k2th-smallest-elements/
 *
 * Given an array, find the sum of all numbers between the K1?th and K2?th smallest elements of that array.
 *
 * Example 1:
 * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
 * Output: 23
 * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. The sum of numbers coming
 * between 5 and 15 is 23 (11+12).
 *
 * Example 2:
 * Input: [3, 5, 8, 7], and K1=1, K2=4
 * Output: 12
 * Explanation: The sum of the numbers between the 1st smallest number (3) and the 4th smallest
 * number (8) is 12 (5+7).
 */
public class SumOfElements {
    public static void main(String[] args) {
        int[] arr1 = {20, 8, 22, 4, 12, 10, 14};
        int k1 = 3;
        int k2 = 6;
        System.out.println(new SumOfElements().sum(arr1, k1, k2));  //expected 26
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     *
     * time ~ O(n*logn)
     * space ~ O(n)
     *
     * 1 attempt
     */
    public int sum(int[] arr, int k1, int k2) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder()); //max heap

        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
            if (q.size() > k2 - 1) {
                q.poll();
            }
        }

        //take k2 - k1 - 1 elements from queue and sum them
        int sum = 0;
        int count = 0;
        //add extra validation !q.isEmpty()
        while (!q.isEmpty() && count < k2 - k1 - 1) {
            sum += q.poll();
            count++;
        }
        return sum;
    }
}
