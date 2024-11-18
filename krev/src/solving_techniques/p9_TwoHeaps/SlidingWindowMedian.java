package solving_techniques.p9_TwoHeaps;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639c8fe6165a22967d308303
 * OR
 * 480. Sliding Window Median (hard)
 * https://leetcode.com/problems/sliding-window-median/
 *
 * #Company: Amazon Meta Google Oracle Snapchat
 *
 * Given an array of numbers and a number ?k?, find the median of all the ?k? sized sub-arrays (or windows) of the array.
 *
 * Example 1:
 * Input: nums=[1, 2, -1, 3, 5], k = 2
 * Output: [1.5, 0.5, 1.0, 4.0]
 * Explanation: Let's consider all windows of size ?2?:
 * [1, 2, -1, 3, 5] -> median is 1.5
 * [1, 2, -1, 3, 5] -> median is 0.5
 * [1, 2, -1, 3, 5] -> median is 1.0
 * [1, 2, -1, 3, 5] -> median is 4.0
 *
 * Example 2:
 * Input: nums=[1, 2, -1, 3, 5], k = 3
 * Output: [1.0, 2.0, 3.0]
 * Explanation: Let's consider all windows of size ?3?:
 */
public class SlidingWindowMedian {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};         //[1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
//        int[] nums = {2147483647,1,2,3,4,5,6,7,2147483647};
        int k = 3;
        SlidingWindowMedian obj = new SlidingWindowMedian();
        double[] result = obj.medianSlidingWindow(nums, k);
        System.out.println("");
    }

    /**
     * info: https://www.youtube.com/watch?v=oKwmSnmEFpY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=119
     * info: https://leetcode.com/problems/sliding-window-median/solutions/4656535/java-two-heaps-hashmap/
     * idea:
     * 1) use 2 heaps
     * 2) use mapToRemove instead of real and instant deletion
     * 2.1) remove (really) element from heaps only if it is top of the heap => takes O(1), not O(logN)
     * 3) use balance variable to estimate if we need to rebalance the heaps or not
     *
     * time to implement ~ 40 mins
     * time ~ O(N*logK)
     * space ~ O(K)
     *
     * BEATS ~ 77%
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        PriorityQueue<Integer> maxLeftHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minRightHeap = new PriorityQueue<>();

        //1. fill heaps
        for (int i = 0; i < k; i++) {
            maxLeftHeap.add(nums[i]);
        }

        //move the biggest half of elements from to minRightHeap
        //(this is quite faster than add and rebalance each time)
        for (int i = 0; i < k/2; i++) {
            minRightHeap.add(maxLeftHeap.poll());
        }

        Map<Integer, Integer> mapToRemove = new HashMap<>();

        //2. main part. NOTE: we go until <= nums.length, BUT stop once we stored the latest median value to prevent out of bounds exception
        for (int i = k; i <= nums.length; i++) {
            double median = 0.0;
            if (k % 2 == 1) {
                median = maxLeftHeap.peek();
            } else {
                median = ((double)maxLeftHeap.peek() + (double)minRightHeap.peek())/2.0;
            }
            result[i - k] = median;

            if (i == nums.length) break;

            mapToRemove.put(nums[i - k], mapToRemove.getOrDefault(nums[i - k], 0) + 1);

            int balance = 0;
            //to find the heap that contains nums[i - k]
            if (nums[i - k] <= median) {
                //remove (logically, not phycisally) it from maxLeftHeap
                balance = -1;
            } else {
                //remove it from minRightHeap
                balance = 1;
            }

            //to find the heap where to insert nums[i] element
            if (nums[i] <= median) {
                //insert to maxLeftHeap
                balance++;
                maxLeftHeap.add(nums[i]);
            } else {
                balance--;
                minRightHeap.add(nums[i]);
            }

            //if balance = 0, it means that we the heaps are still in balanced state.
            //for example, if we (logically) removed element from minHeap, but we also add nums[i] to the same heap => its size stays intact

            if (balance < 0) {
                maxLeftHeap.add(minRightHeap.poll());
            } else if (balance > 0) {
                minRightHeap.add(maxLeftHeap.poll());
            }

            //remove the elements mentioned in mapToRemove if it is possible to do in O(1) (btw, we don't know which heap contains these elements)
            //NOTE: if heap contains element that needs to be remove, BUT this element is NOT on the top,
            //then it does NOT influence of the result (since only top element affects the calculations)
            // => we can keep so called 'removed' element in Queue until it becomes top element
            //Then it can affect us, but we can remove it O(1) so we do this
            while (!maxLeftHeap.isEmpty() && mapToRemove.getOrDefault(maxLeftHeap.peek(), 0) > 0) {
                mapToRemove.put(maxLeftHeap.peek(), mapToRemove.get(maxLeftHeap.peek()) - 1);
                maxLeftHeap.poll();
            }

            while (!minRightHeap.isEmpty() && mapToRemove.getOrDefault(minRightHeap.peek(), 0) > 0) {
                mapToRemove.put(minRightHeap.peek(), mapToRemove.get(minRightHeap.peek()) - 1);
                minRightHeap.poll();
            }
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION:
     * idea: in general it is correct: like sliding window + 2 heaps as for FindMedianOfNumberStream
     * <p>
     * BUT got Time Limit Exceeded
     *
     * time to think + to implement ~ 20 mins + about 40 mins ~ 60 mins
     * time ~ O((n+k)*logk) ~ O(N*logk)
     * space ~ O(N + k)
     *
     * many attempts:
     * - missed <= condition in rebalance
     * - missed "leftMaxHeap.size() == 0" condition in addElementToHeaps
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
        double[] result = new double[nums.length - k + 1];
        int start = 0;

        for (int i = 0; i <= nums.length; i++) {
            if (i < k) {
                addElementToHeaps(leftMaxHeap, rightMinHeap, nums[i]);
            } else {
                //fill result
                result[start] = findMedian(leftMaxHeap, rightMinHeap);
                if (i == nums.length) {
                    break;
                    //NOTE: this if-block + "i <= nums.length" helps us to avoid of code duplication after the for-loop:  result[start] = findMedian(leftMaxHeap, rightMinHeap);
                }
                //remove start element from heaps (initially we don't know which heap contains this element)
                removeElementFromHeaps(leftMaxHeap, rightMinHeap, nums[start]);

                //even if we get the situation where we need to rebalance, we can do it later - after the insertion of new i-th element
                //so now just add i-th element
                addElementToHeaps(leftMaxHeap, rightMinHeap, nums[i]);

                start++;
            }
        }

        return result;
    }

    private void removeElementFromHeaps(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap, int n) {
        if (leftMaxHeap.size() == 0 || leftMaxHeap.peek() < n) {
            rightMinHeap.remove(n);
        } else {
            leftMaxHeap.remove(n);
        }

        rebalance(leftMaxHeap, rightMinHeap);
    }

    //the same as FindMedianOfNumberStream # addNum2
    private void addElementToHeaps(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap, int n) {
        if (leftMaxHeap.size() == 0 || leftMaxHeap.peek() >= n) {
            leftMaxHeap.offer(n);
        } else {
            rightMinHeap.offer(n);
        }

        rebalance(leftMaxHeap, rightMinHeap);
    }

    //re-balance so that rightMinHeap.size() <= leftMaxHeap.size() <= rightMinHeap.size() + 1
    private void rebalance(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap) {
        if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
            rightMinHeap.offer(leftMaxHeap.poll());
        } else if (rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.offer(rightMinHeap.poll());
        }
    }

    private double findMedian(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap) {
        if (leftMaxHeap.size() == rightMinHeap.size()) {
            return ((double) leftMaxHeap.peek() + (double) rightMinHeap.peek()) / 2.0;
        } else {
            return leftMaxHeap.peek();
        }
    }

    /**
     * SOLUTION #2:
     * from https://leetcode.com/problems/sliding-window-median/solutions/4656535/java-two-heaps-hashmap/
     * honestly, did not get how can the map help us to speed up
     */
    public double[] medianSlidingWindowOptimized(int[] nums, int k) {
        double[] ans = new double[nums.length - k + 1];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // this is very tricky
        // (a,b) -> b-a will overflow
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        HashMap<Integer, Integer> removed = new HashMap<>();


        // initialize the heaps
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }
        for (int j = 0; j < k / 2; j++) {
            minHeap.offer(maxHeap.poll());
        }

        for (int i = k; i <= nums.length; i++) {
            ans[i - k] = (k % 2 == 0) ? minHeap.peek() * 0.5 + maxHeap.peek() * 0.5 : maxHeap.peek();

            // stop condition
            if (i == nums.length) {
                break;
            }

            int outNum = nums[i - k];
            int inNum = nums[i];

            removed.put(outNum, removed.getOrDefault(outNum, 0) + 1);

            // balance only takes -2, 0, 2
            // we need to balance the heap using the real size, cuz removed element might still in the heaps
            int balance = 0;
            if (outNum <= maxHeap.peek()) {
                balance--;
            } else {
                balance++;
            }

            if (inNum <= maxHeap.peek()) {
                balance++;
                maxHeap.offer(inNum);
            } else {
                balance--;
                minHeap.offer(inNum);
            }
            // rebalance the heap
            if (balance < 0) {
                maxHeap.offer(minHeap.poll());
            }
            if (balance > 0) {
                minHeap.offer(maxHeap.poll());
            }
            // lazy remove the out of window element
            // remove from the maxHeap first, because we use <= when calculating balance in outNum
            while (!maxHeap.isEmpty() && removed.getOrDefault(maxHeap.peek(), 0) > 0) {
                int removeNum = maxHeap.poll();
                removed.put(removeNum, removed.get(removeNum) - 1);
            }
            while (!minHeap.isEmpty() && removed.getOrDefault(minHeap.peek(), 0) > 0) {
                int removeNum = minHeap.poll();
                removed.put(removeNum, removed.get(removeNum) - 1);
            }
        }
        return ans;
    }
}
