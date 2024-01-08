package solving_techniques.p13_TopKElements;

import java.util.PriorityQueue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1d25873a7d4466d4608f3
 * OR
 * 703. Kth Largest Element in a Stream
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * <p>
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Implement KthLargest class:
 * <p>
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 * <p>
 * <p>
 * Example 1:
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 * <p>
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4 (since q = 4 5 8)
 * kthLargest.add(5);   // return 5 (since q = 5 5 8)
 * kthLargest.add(10);  // return 5 (since q = 5 8 10)
 * kthLargest.add(9);   // return 8 (since q = 8 9 10)
 * kthLargest.add(4);   // return 8 (since q = 8 9 10)
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * At most 104 calls will be made to add.
 * It is guaranteed that there will be at least k elements in the array when you search for the kth element.
 */
public class KthLargestNumberInStream {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 13 mins
     * 3 attempts:
     * - forgot code
     *     if (q.size() > k) {
     *         q.poll();
     *     }
     *     in add method
     * - did not store k as class variable
     */
    private PriorityQueue<Integer> q = new PriorityQueue<>();   //min heap
    private int k = 0;

    public KthLargestNumberInStream(int k, int[] nums) {
        this.k = k;

        for (int n : nums) {
            q.add(n);
            if (q.size() > k) {
                q.poll();
            }
        }
    }

    public int add(int val) {
        q.add(val);
        if (q.size() > k) {
            q.poll();
        }
        return q.peek();
    }
}
