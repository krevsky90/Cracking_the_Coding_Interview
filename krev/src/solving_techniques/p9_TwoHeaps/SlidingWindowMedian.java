package solving_techniques.p9_TwoHeaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMedian obj = new SlidingWindowMedian();
        double[] result = obj.medianSlidingWindow(nums, k);
        System.out.println("");
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
                rebalance(leftMaxHeap, rightMinHeap);
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

                rebalance(leftMaxHeap, rightMinHeap);

                start++;
            }
        }

//        result[start] = findMedian(leftMaxHeap, rightMinHeap);
         Arrays.stream(result).forEach(x -> System.out.print(x + " | "));

        return result;
    }

    private void rebalance(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap) {
        //check that left elements are lower than right elements
        while (leftMaxHeap.size() > 0 && rightMinHeap.size() > 0 && leftMaxHeap.peek() > rightMinHeap.peek()) {
            int leftElementToMove = leftMaxHeap.poll();
            rightMinHeap.offer(leftElementToMove);
            int rightElementToMove = rightMinHeap.poll();
            leftMaxHeap.offer(rightElementToMove);
        }

        if (leftMaxHeap.size() - rightMinHeap.size() > 1) {
            int elementToMove = leftMaxHeap.poll();
            rightMinHeap.offer(elementToMove);
        } else if (rightMinHeap.size() - leftMaxHeap.size() > 1) {
            int elementToMove = rightMinHeap.poll();
            leftMaxHeap.offer(elementToMove);
        }
    }

    private void removeElementFromHeaps(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap, int n) {
        if (leftMaxHeap.size() == 0 || leftMaxHeap.peek() < n) {
            rightMinHeap.remove(n);
        } else {
            leftMaxHeap.remove(n);
        }
    }

    private void addElementToHeaps(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap, int n) {
        if (leftMaxHeap.size() == 0) {
            leftMaxHeap.offer(n);
            return;
        }

        if (leftMaxHeap.peek() < n) {
            rightMinHeap.offer(n);
        } else {
            leftMaxHeap.offer(n);
        }
    }

    private double findMedian(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap) {
        if (leftMaxHeap.size() == rightMinHeap.size()) {
            return ((double) leftMaxHeap.peek() + (double) rightMinHeap.peek()) / 2.0;
        } else if (leftMaxHeap.size() > rightMinHeap.size()) {
            return leftMaxHeap.peek();
        } else {
            return rightMinHeap.peek();
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
