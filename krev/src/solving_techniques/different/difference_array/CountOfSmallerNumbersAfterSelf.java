package solving_techniques.different.difference_array;

import java.util.*;

/**
 * 315. Count of Smaller Numbers After Self (hard)
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * <p>
 * #Company (5.07.2025): 0 - 6 months Google 3 Meta 3 Bloomberg 2 6 months ago Amazon 10 Microsoft 5 Apple 3 TikTok 3 Geico 2
 * <p>
 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example 1:
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * <p>
 * Example 2:
 * Input: nums = [-1]
 * Output: [0]
 * <p>
 * Example 3:
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class CountOfSmallerNumbersAfterSelf {
    /**
     * SOLUTION #1:
     * info: https://www.youtube.com/watch?v=xV92hYM6iCU
     * idea: for all nums from right to left use left insertion to sorted list
     * after each insertion index of element means the number of elements that are lower in the original 'nums' array
     * <p>
     * time to implement ~ 19 mins
     * time ~ O(n*logn), BUT it is slow because when we install list.add(index, value) - it takes more than O(1)
     * space ~ O(n)
     * <p>
     * BEATS ~ 10%
     */
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> sorted = new ArrayList<>(n);
        List<Integer> result = new ArrayList<>(n);

        for (int i = n - 1; i >= 0; i--) {
            int idx = leftInsertToBinaryTree(nums[i], sorted);
            result.add(idx);
        }


        //do not forget to reverse list!
        Collections.reverse(result);
        return result;
    }

    //find the most left (since we might have duplicates) position to insert val into sorted list
    private int leftInsertToBinaryTree(int val, List<Integer> sorted) {
        int low = -1;
        int high = sorted.size();
        while (high - low > 1) {
            int mid = low + (high - low) / 2;
            if (sorted.get(mid) < val) {
                low = mid;
            } else {
                high = mid;
            }
        }

        sorted.add(high, val);
        return high;
    }

    /**
     * SOLUTION #2: KREVSKY SOLUTIION
     * give TLE since time ~ O(n*logn*logn)
     *
     * time to solve ~ 20-25 mins
     */
    public List<Integer> countSmaller2(int[] nums) {
        //sort by value,then - by index
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[1] == a[1] ? b[0] - a[0] : b[1] - a[1]
            ); //max heap that keeps pairs: [0] - index i, [1] - value nums[i]

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{i, nums[i]});
        }

        TreeSet<Integer> polledIndexes = new TreeSet<>();
        int[] res = new int[n];
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int idx = pair[0];
            res[idx] = (n - idx - 1) - polledIndexes.tailSet(idx).size();
            polledIndexes.add(idx);
        }

        List<Integer> result = new ArrayList<>(n);
        for (int c : res) {
            result.add(c);
        }

        return result;
    }
}
