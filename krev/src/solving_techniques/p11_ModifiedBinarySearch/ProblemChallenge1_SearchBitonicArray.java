package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dd6e95be2006431ceabf40
 * OR
 * 1095. Find in Mountain Array (hard)
 * https://leetcode.com/problems/find-in-mountain-array
 * <p>
 * (This problem is an interactive problem.)
 * You may recall that an array arr is a mountain array if and only if:
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.
 * If such an index does not exist, return -1.
 * <p>
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * Example 1:
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * <p>
 * Example 2:
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 * <p>
 * Constraints:
 * 3 <= mountain_arr.length() <= 10^4
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 */
public class ProblemChallenge1_SearchBitonicArray {
    interface MountainArray {
        public int get(int index);

        public int length();
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) find peak
     * 2) use binary search for left ACS side (since we want to return minimal index)
     * 3) if p.2. did not give the answer - use binary search for right DESC side
     * MAIN HINT to reduce amount of calls of mountainArr.get IS TO SAVE midPrevVal, midVal, midNextVal!
     *
     * time to solve ~ 25 mins
     * 3 attempts:
     * - mixed up "left = mid + 1;" and "right = mid - 1;" for binary sorting
     * - did not use the HINT in the first version of my solution
     *
     * time ~ O(log(n)), where n = mountainArr.length()
     * space ~ O(1)
     *
     * BEATS = 100%
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        //1. find peak
        int left = 0;
        int right = mountainArr.length() - 1;
        int peakIdx = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midPrevVal = mountainArr.get(mid - 1);
            int midVal = mountainArr.get(mid);
            int midNextVal = mountainArr.get(mid + 1);
            //3 cases:
            if (midPrevVal < midVal && midVal > midNextVal) {
                peakIdx = mid;
                break;
            } else if (midPrevVal < midVal && midVal < midNextVal) {
                left = mid;
            } else {
                right = mid;
            }
        }

        //2. use binary search for left ACS side (since we want to return minimal index)
        left = 0;
        right = peakIdx;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal == target) {
                return mid;
            } else if (midVal < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        //3. if p.2. did not give the answer - use binary search for right DESC side
        left = peakIdx;
        right = mountainArr.length() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal == target) {
                return mid;
            } else if (midVal < target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        //can't find the target
        return -1;
    }
}
