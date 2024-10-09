package solving_techniques.p2_TwoPointers;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 4. Median of Two Sorted Arrays (hard)
 * https://leetcode.com/problems/median-of-two-sorted-arrays
 * <p>
 * #Company: Adobe Airbnb Alibaba Amazon Apple Baidu Bloomberg DiDi Dropbox eBay Facebook Garena GoDaddy Goldman Sachs Google Houzz Hulu Microsoft Oracle Rubrik Tencent Two Sigma Uber Visa VMware Walmart Labs Yahoo Zenefits Zillow Zulily Yandex
 * <p>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * <p>
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * Constraints:
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class MedianOfTwoSortedArrays {
    /**
     * SOLUTION #2:
     * idea: two pointers
     * info: https://leetcode.com/problems/median-of-two-sorted-arrays/solutions/4070500/99-journey-from-brute-force-to-most-optimized-three-approaches-easy-to-understand/
     * 1) Initialize two pointers, i and j, both initially set to 0.
     * 2) Move the pointer that corresponds to the smaller value forward at each step.
     * 3) Continue moving the pointers until you have processed half of the total number of elements.
     * 4) Calculate and return the median based on the values pointed to by i and j.
     * <p>
     * time to implement ~ 24 mins
     * time ~ O(nums1.length + nums2.length)
     * space ~ O(1)
     * <p>
     * BEATS ~ 100%
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int totalCount = nums1.length + nums2.length;
        int halfCount = totalCount / 2;

        int i = 0;  //for nums1
        int j = 0;  //for nums2
        int current = 0;
        int previous = 0;

        //find the lowest halfCount elements
        for (int k = 0; k <= halfCount; k++) {
            previous = current;
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    current = nums1[i];
                    i++;
                } else {
                    current = nums2[j];
                    j++;
                }
            } else if (i < nums1.length) {
                current = nums1[i];
                i++;
            } else if (j < nums2.length) {
                current = nums2[j];
                j++;
            }
        }

        if (totalCount % 2 == 0) {
            return (current + previous) / 2.0;
        } else {
            return (double) current;
        }
    }

    /**
     * KREVSKY SOLUTION:
     * idea: use min Heap and max Heap as here https://leetcode.com/problems/find-median-from-data-stream
     * <p>
     * time to solve ~ 13 mins
     * time ~ O((nums1.length + nums2.length) * log (nums1.length + nums2.length)) - not optimal
     * space ~ O(nums1.length + nums2.length)
     * 1 attempt
     * <p>
     * BEATS ~ 10%
     */

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> maxLeftHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minRightHeap = new PriorityQueue<>();

        for (int n1 : nums1) {
            addElement(maxLeftHeap, minRightHeap, n1);
        }

        for (int n2 : nums2) {
            addElement(maxLeftHeap, minRightHeap, n2);
        }

        if (maxLeftHeap.size() > minRightHeap.size()) {
            return (double) maxLeftHeap.peek();
        } else {
            return (maxLeftHeap.peek() + minRightHeap.peek()) / 2.0;
        }
    }

    private void addElement(PriorityQueue<Integer> maxLeftHeap, PriorityQueue<Integer> minRightHeap, int element) {
        if (maxLeftHeap.isEmpty() || element <= maxLeftHeap.peek()) {
            maxLeftHeap.add(element);
        } else {
            minRightHeap.add(element);
        }

        //rebalance:
        if (maxLeftHeap.size() < minRightHeap.size()) {
            maxLeftHeap.add(minRightHeap.poll());
        } else if (maxLeftHeap.size() > minRightHeap.size() + 1) {
            minRightHeap.add(maxLeftHeap.poll());
        }
    }
}
