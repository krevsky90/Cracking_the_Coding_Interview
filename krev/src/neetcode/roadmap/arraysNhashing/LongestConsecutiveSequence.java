package neetcode.roadmap.arraysNhashing;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 *Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *  Constraints:
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 */
public class LongestConsecutiveSequence {
    /**
     * NOT SOLVED by myself
     *
     * Algorithm:
     * 1) create an empty hash
     * 2) insert all array to this hash
     * 3) Do following for every element of array:
     * a) check if this element is starting point of a sequence. To do this - look if "nums[i] - 1" is in set. If yes - skip this nums[i] element
     * b) if it is not found in set => nums[i] can be start point of the sequence: count the number of elements in the consecutive starting from nums[i].
     *      Iterate from nums[i] + 1 till the last element that can be found
     * c) if temp counter is more than result => update result
     *
     * time to implement ~ 10 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * BEATS = 16%
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            //try to start the sequence from i-th element
            int j = 1;
            while (set.contains(nums[i] + j)) {
                j++;
            }
            result = Math.max(result, j);
        }

        return result;
    }
}
