package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f9a0cd239f7cde26dde2b
 * OR
 * 33. Search in Rotated Sorted Array (medium)
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * #Company: Adobe Alibaba Amazon Apple Baidu Bloomberg ByteDance Cisco eBay Expedia Facebook Goldman Sachs Google caMorgan LinkedIn Microsoft NetEase Nutanix Nvidia Oracle Samsung Snapchat Tencent Tesla TripAdvisor Twitch Uber Visa VMware Walmart Labs Yahoo Zillow Zulily Yandex
 * <p>
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= nums.length <= 5000
 * -10000 <= nums[i] <= 10000
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -10000 <= target <= 10000
 */
public class ProblemChallenge2_SearchInRotatedArray {
    public static void main(String[] args) {
        int[] nums1 = {3, 1};
        int target1 = 1;
        System.out.println(search2(nums1, target1));

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 0;
        System.out.println(search2(nums2, target2));

        int[] nums3 = {1};
        int target3 = 0;
        System.out.println(search2(nums3, target3));
    }

    /**
     * info:
     * https://www.youtube.com/watch?v=WJesTo72lFY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=77
     * similar to KREVSKY SOLUTION, BUT:
     * 1) remove part for usual binary search, since it is covered by our algorithm
     * 2) simplified else-part doing it symmetric as if-part
     * <p>
     * BEATS ~ 100%
     */
    public static int search1(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return mid;

            if (nums[low] <= nums[mid]) {
                //it means that low - mid part is sorted in ascending order
                //but mid - high - not sorted
                if (nums[low] <= target && target < nums[mid]) {
                    //it means that target belongs to sorted part
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //it means that mid - high part is sorted in ascending order - see p.2
                if (nums[mid] < target && target <= nums[high]) {
                    //it means that target belongs to sorted part
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        //target is not found
        return -1;
    }

    /**
     * the same as https://www.youtube.com/watch?v=WJesTo72lFY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=77
     * BUT set "<=" everywhere => it simplifies understanding
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * KREVSKY SOLUTION #2:
     * time to solve ~ 45 mins
     * time ~ O(logN)
     * space ~ O(1)
     * 4 attempts:
     * 1) forget "=" in expression: nums[mid] >= nums[low])
     * 2) forget "&& target >= nums[low]" in expression: if (nums[mid] > target && target >= nums[low])
     * 3) set "int mid = (low + high)/2;"  out of while loop for case of NOT rotated array
     */
    public static int search2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        if (nums[low] < nums[high]) {
            //if the array is NOT rotated
            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums[mid] == target) return mid;

                if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        } else {
            //if the array is rotated => nums[low] > nums[high]
            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums[mid] == target) return mid;

                if (nums[mid] >= nums[low]) {
                    //it means that low - mid part is sorted in ascending order
                    //but mid - high - not sorted
                    if (nums[mid] > target && target >= nums[low]) {
                        //it means that target belongs to sorted part
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    if (nums[mid] > target) {
                        high = mid - 1;
                    } else {
                        if (nums[high] >= target) {
                            low = mid + 1;
                        } else {
                            //i.e. target locates somewhere in the left part
                            high = mid - 1;
                        }
                    }
                }
            }
        }

        //target is not found
        return -1;
    }
}
