package solving_techniques.different;

/**
 * 88. Merge Sorted Array (easy)
 * https://leetcode.com/problems/merge-sorted-array/
 * <p>
 * #Company: Adobe Amazon Apple Baidu Bloomberg Cisco eBay Expedia Facebook Goldman Sachs Google IBM Indeed Intel LinkedIn Microsoft Oracle Quip (Salesforce) Tableau Uber VMware Yahoo Yandex
 * <p>
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n,
 * where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * <p>
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * <p>
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * <p>
 * Example 3:
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1.
 * The 0 is only there to ensure the merge result can fit in nums1.
 * <p>
 * Constraints:
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeSortedArray {
    /**
     * SOLUTION #1
     * time ~ O(n + m)
     * space ~ O(1)
     * <p>
     * idea: fill nums1 from the end!
     *
     * time to implement ~ 10 mins
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[i + j + 1] = nums1[i];
                nums1[i] = 0;
                i--;
            } else {
                nums1[i + j + 1] = nums2[j];
                j--;
            }
        }

        while (j >= 0) {
            nums1[j] = nums2[j];
            j--;
        }
    }

    /**
     * KREVSKY SOLUTION: NOT SPACE OPTIMAL
     * time to solve ~ 24 mins
     * <p>
     * time ~ O(n + m)
     * space ~ O(n + m)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[n + m];

        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                result[i + j] = nums1[i];
                i++;
            } else {
                result[i + j] = nums2[j];
                j++;
            }
        }

        while (i < m) {
            result[i + j] = nums1[i];
            i++;
        }

        while (j < n) {
            result[i + j] = nums2[j];
            j++;
        }

        for (int k = 0; k < n + m; k++) {
            nums1[k] = result[k];
        }
    }
}
