package solving_techniques.heap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 239. Sliding Window Maximum (hard)
 * https://leetcode.com/problems/sliding-window-maximum
 * <p>
 * #Company (26.03.2025):
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use Priority queue to keep (nums[i], i)
     * 2) to calculate max element in the window, exclude all non-relevant values from PQ (which index < left bound of window)
     * and return current top element of the queue (since it is in the bounds of the window)
     * <p>
     * time ~ O(n*logn). NOTE: PQ may have more than k elements in case of increasing sequence
     * space ~ O(n) - see NOTE above
     * <p>
     * time to solve ~ 25 mins
     * <p>
     * BEATS ~ 14%
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);    //max heap sorted by values
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            pq.add(new int[]{nums[i], i});

            if (i < k - 1) {
                continue;
            }

            while (pq.peek()[1] <= i - k) {
                pq.poll();  //just exclude from queue the values that can influence the max value (since they were real max values for some previous windows, but now they are former data)
            }

            result[i - k + 1] = pq.peek()[0];
        }

        return result;
    }

    /**
     * Official and the MOSY optimal solution:
     * idea:
     * 1) use Deque to keep indexes of nums's elements in monotonically decreasing order
     *      since if we face x => all elements that are more left and <= x will NOT be max value for any further window
     * 2) before getting max value of window you need to exclude (from deque) the values that are out of current window, i.e. too old.
     *      this is easy to find using their indexes => we store indexes in the deque
     *      (this idea is similar to my solution with PriorityQueue)
     *
     *      Once we filtered deque, we take the left most element
     *
     * time to implement ~ 7 mins
     *
     * time ~ O(n)
     * space ~ O(k)
     *
     * BEATS ~ 90%
     */
    public int[] maxSlidingWindowDeque(int[] nums, int k) {
        //to keep indexes of nums's elements in monotonically decreasing order
        Deque<Integer> deque = new LinkedList<>();

        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);

            if (i < k - 1) {
                continue;
            }

            while (deque.peekFirst() <= i - k) {
                deque.poll();  //just exclude from dueue the indexes of the values that can influence the max value (since they were real max values for some previous windows, but now they are former data)
            }

            result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }
}
