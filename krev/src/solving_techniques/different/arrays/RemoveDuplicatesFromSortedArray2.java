package solving_techniques.different.arrays;

/**
 * 80. Remove Duplicates from Sorted Array II (medium)
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii
 *
 * #Company (20.03.2025): 0 - 3 months Google 5 Amazon 5 Meta 4 Microsoft 2 0 - 6 months Bloomberg 4 Accolite 2 TikTok 2 6 months ago Adobe 13 Apple 6 tcs 3 Yandex 3 Yahoo 3 Zoho 2 Uber 2 Purplle
 *
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice.
 * The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Custom Judge:
 *
 * The judge will test your solution with the following code:
 *
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 *
 * int k = removeDuplicates(nums); // Calls your implementation
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 */
public class RemoveDuplicatesFromSortedArray2 {

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 29 mins
     *
     * idea:
     * set nums[placeId] = nums[i] and increment placeId in case if:
     *  - nums[i] != nums[i-1]
     *  OR
     *  - cnt <= 2
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * 1 attempt
     *
     * BEATS ~ 100%
     */
    public int removeDuplicates(int[] nums) {
        //time ~ O(n)
        int len = nums.length;
        if (len <= 2) return len;

        int placeIdx = 1;
        int cnt = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i-1]) {
                cnt++;
                if (cnt <= 2) {
                    nums[placeIdx] = nums[i];
                    placeIdx++;
                }
            } else {
                nums[placeIdx] = nums[i];
                placeIdx++;
                cnt = 1;
            }
        }

        return placeIdx;
        //0 1 2 3 4 5 6 7 8
        //1,1,1,2,2,2,2,3,4
        //1,1,2,2,3,4,2,3,4
        // i = 8
        // cnt = 1
        // place = 6
    }
}
