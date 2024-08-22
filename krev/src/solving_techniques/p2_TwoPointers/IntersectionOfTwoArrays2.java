package solving_techniques.p2_TwoPointers;

import java.util.*;

/**
 * 350. Intersection of Two Arrays II (easy)
 * https://leetcode.com/problems/intersection-of-two-arrays-ii
 *
 * #Company: Amazon Apple Bloomberg Databricks Facebook Google LinkedIn Microsoft Oracle Salesforce Uber Yandex
 * <p>
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays
 * and you may return the result in any order.
 * <p>
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * <p>
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 * <p>
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 * <p>
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk,
 * and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArrays2 {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 7 mins
     * time ~ O(n + m)
     * space ~ O(??)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 80%
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = convertToMap(nums1);
        Map<Integer, Integer> map2 = convertToMap(nums2);

        List<Integer> resultList = new ArrayList<>();
        for (int key : map1.keySet()) {
            if (map2.containsKey(key)) {
                int iterations = Math.min(map1.get(key), map2.get(key));
                for (int i = 0; i < iterations; i++) {
                    resultList.add(key);
                }
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    private Map<Integer, Integer> convertToMap(int[] arr) {
        Map<Integer, Integer> map = new HashMap();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        return map;
    }

    /**
     * info:
     * https://leetcode.com/problems/intersection-of-two-arrays-ii/solutions/5400128/beats-100-explained-with-video-c-java-python-js-two-pointers-sorting/
     * <p>
     * idea:
     * 1) sort
     * 2) apply two-pointers
     * time ~ (n log n + m log m + n + m) - BUT it is faster than my solution!
     * <p>
     * BEATS ~ 95%
     *
     * FOLLOW-UP questions:
     * 1) What if the given array is already sorted? How would you optimize your algorithm?
     *      If the arrays are already sorted, the sorting step can be skipped.
     *      This reduces the time complexity to (O(n + m)) for the two-pointer traversal.
     * 2) What if nums1's size is small compared to nums2's size? Which algorithm is better?
     *      If nums1 is much smaller than nums2, using a hash map to count elements in nums1
     *      and then iterating through nums2 to find common elements can be more efficient.
     *      This avoids the overhead of sorting the larger array.
     * 3) What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
     *      If elements of nums2 are stored on disk, a multi-pass approach might be necessary.
     *      First, load chunks of nums2 into memory, compare with nums1 (which can be fully loaded if it is small enough),
     *      and keep track of the intersection. This can be done using external sorting or streaming techniques.
     *
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int i = 0, j = 0, k = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (i < l1 && j < l2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                nums1[k++] = nums1[i++];
                j++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}
