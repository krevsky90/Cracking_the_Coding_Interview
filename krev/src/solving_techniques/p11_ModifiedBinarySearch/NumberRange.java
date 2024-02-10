package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f27ef7ae2ac6893f42b83
 * OR
 * 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * <p>
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * <p>
 * Constraints:
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums is a non-decreasing array.
 * -10^9 <= target <= 10^9
 */
public class NumberRange {
    /**
     * KREVSKY SOLUTION:
     * time to solve = 11 (to think + 42 = 53 mins
     * <p>
     * 3 attempts:
     * - incorrect syntax: new int[2]{-1, -1}; instead of new int[]{-1, -1};
     * - forgot break => got infinite loop
     * <p>
     * time ~ O(logN)
     * space ~ O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = nums.length - 1;
        return searchRange(nums, target, left, right);
    }

    private int[] searchRange(int[] nums, int target, int left, int right) {
        int low = -1;
        int high = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                low = mid;
                high = mid;
                int searchLeftBound = searchBound(nums, target, left, mid - 1, true);
                int searchRightBound = searchBound(nums, target, mid + 1, right, false);
                if (searchLeftBound != -1) {
                    low = searchLeftBound;
                }

                if (searchRightBound != -1) {
                    high = searchRightBound;
                }

                break;  //forgot it firstly
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                //nums[mid] > target
                right = mid - 1;
            }
        }

        return new int[]{low, high};
    }

    private int searchBound(int[] nums, int target, int left, int right, boolean isLeftBound) {
        if (left > right) {
            return -1;
        }

        int boundIdx = -1;
        int mid = (left + right) / 2;

        if (isLeftBound) {
            if (nums[mid] == target) {
                boundIdx = mid;
                int searchLeftBound = searchBound(nums, target, left, mid - 1, isLeftBound);
                if (searchLeftBound != -1) {
                    boundIdx = searchLeftBound;
                }
            } else if (nums[mid] < target) {
                return searchBound(nums, target, mid + 1, right, isLeftBound);
            } else {
                //nums[mid] > target: can not be => skip "return searchBound(nums, target, left, mid - 1, isLeftBound);"
            }
        } else {
            if (nums[mid] == target) {
                boundIdx = mid;
                int searchRightBound = searchBound(nums, target, mid + 1, right, isLeftBound);
                if (searchRightBound != -1) {
                    boundIdx = searchRightBound;
                }
            } else if (nums[mid] > target) {
                return searchBound(nums, target, left, mid - 1, isLeftBound);
            } else {
                //nums[mid] < target: can not be
            }
        }

        return boundIdx;
    }

    /**
     * Alternative and beautiful solution:
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/4489136/simple-java-solution-beats-100-users-using-recurrent-binary-searches/
     * Approach
     * Find an initial occurrence of the element in the array.
     * Find an occurrence of the element in the array before the inital occurance. Set corresponding flag to true if not found. Do not search again if flag is true.
     * Find an occurrence of the element in the array after the inital occurance. Set corresponding flag to true if not found. Do not search again if flag is true.
     * Continue till both flags are not true - it means that we consider the intervals that do not consist target value => the bounds are stored in 'res' array
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0)
            return res;
        boolean nolow = false, nohigh = false;
        int init = binSearch(nums, target, 0, nums.length - 1);
        if (init == -1)
            return res;
        else {
            res[0] = init;
            res[1] = init;
        }
        int poslow = -1, poshigh = -1;
        while (!nolow || !nohigh) {
            if (!nolow)
                poslow = binSearch(nums, target, 0, res[0] - 1);
            if (!nohigh)
                poshigh = binSearch(nums, target, res[1] + 1, nums.length - 1);
            if (poslow != -1)
                res[0] = poslow;
            else
                nolow = true;
            if (poshigh != -1)
                res[1] = poshigh;
            else
                nohigh = true;
        }
        return res;
    }

    private int binSearch(int[] nums, int target, int start, int limit) {
        int l = start, u = limit;
        while (u >= l) {
            int mid = (u + l) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                l = mid + 1;
            else
                u = mid - 1;
        }
        return -1;
    }


}
