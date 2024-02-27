package solving_techniques.p11_ModifiedBinarySearch;

/**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element
 * <p>
 * A peak element is an element that is strictly greater than its neighbors.
 * Given a 0-indexed integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -?.
 * In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * <p>
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums[i] != nums[i + 1] for all valid i
 */
public class FindPeakElement {
    /**
     * KREVSKY SOLUTION:
     * idea: usual binary search
     * + consider 4 cases for mid element:
     * 1) mid is part of asc sequence
     * left = mid + 1 !!! +1 is very important for case when right - left = 1
     * (since in this case mid = left)
     * 2) mid is part of desc sequence
     * right = mid OR mid - 1 (here is not the same as for p.1)
     * 3) mid is local peak
     * 4) mid is local anti-peak
     * we may consider left or right part, no matter. but DO NOT forget about +1 (or -1) for mid
     * + be careful with prev and next values that are around nums[mid]:
     * IF the index out of bound, THEN prev/next value = Long.MIN_VALUE !
     * NOTE: not Integer.MIN_VALUE, but LONG! since nums[mid] can be = Integer.MIN_VALUE, according to the constraints
     * time to solve + debug (fix the things described above) ~ 10 + 24 ~ 34 mins
     * <p>
     * time ~ O(logN)
     * ~ 5 attempts
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Long beforeVal = mid - 1 >= 0 ? nums[mid - 1] : Long.MIN_VALUE;
            Long afterVal = mid + 1 <= nums.length - 1 ? nums[mid + 1] : Long.MIN_VALUE;

            //there can be 4 cases
            if (beforeVal < nums[mid] && nums[mid] > afterVal) {
                //we found peak
                return mid;
            } else if (beforeVal < nums[mid] && nums[mid] < afterVal) {
                //acs
                left = mid + 1;
            } else if (beforeVal > nums[mid] && nums[mid] > afterVal) {
                //desc
                right = mid;    // NOT necessary to "- 1;"
            } else {
                //if beforeVal > nums[mid] && nums[mid] < afterVal
                //anti-peak
                //no matter where we will go. for example, to the right
                left = mid + 1;
            }
        }

        return -1;  // dummy statement
    }

    //btw
    public int withBruteforce(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}
