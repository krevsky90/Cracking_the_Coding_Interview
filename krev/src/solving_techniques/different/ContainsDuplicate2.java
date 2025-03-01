package solving_techniques.different;

import java.util.*;

/**
 * 219. Contains Duplicate II (easy)
 * https://leetcode.com/problems/contains-duplicate-ii/
 * <p>
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 */
public class ContainsDuplicate2 {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 4mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 20%
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!valueToIndex.containsKey(nums[i])) {
                valueToIndex.put(nums[i], i);
            } else {
                if (Math.abs(i - valueToIndex.get(nums[i])) <= k) {
                    return true;
                } else {
                    valueToIndex.put(nums[i], i);
                }
            }
        }

        return false;
    }

    public boolean containsNearbyDuplicateShortage(int[] nums, int k) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(i - valueToIndex.getOrDefault(nums[i], -1000000)) <= k) {
                return true;
            } else {
                valueToIndex.put(nums[i], i);
            }
        }

        return false;
    }

    /**
     * Official:
     * idea: keep only k elements!
     * <p>
     * Time complexity : O(n).
     * We do n operations of search, delete and insert, each with constant time complexity.
     * <p>
     * Space complexity : O(min(n,k)).
     */
    public boolean containsNearbyDuplicateSet(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * just interesting approach using TreeSet
     * info: Editorial
     * Time complexity : O(nlog(min(k,n))).
     * <p>
     * Space complexity : O(min(n,k)).
     * Space is the size of the sliding window which should not exceed n or k.
     */
    public boolean containsNearbyDuplicateTreeSet(int[] nums, int k) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
