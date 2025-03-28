package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

/**
 * 26. Remove Duplicates from Sorted Array (easy)
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description
 *
 * #Company: 26.12.2024 0 - 3 months Google 19 Meta 8 Amazon 7 Microsoft 5 Bloomberg 4 0 - 6 months Apple 3 Zoho 2  Accenture 2 Qualcomm 2
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * <p>
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class Problem1_6_RemoveDuplicatesFromSortedArray {
    /**
     * KREVSKY SOLUTION 26.12.2024
     */
    public int removeDuplicates2(int[] nums) {
        int placeToPaste = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                //do nothing
            } else {
                nums[placeToPaste] = nums[i];
                placeToPaste++;
            }
        }

        return placeToPaste;
    }

    /**
     * KREVSKY SOLUTION
     * 1 attempt, optimal, 17 mins
     * time complexity ~ O(n)
     * space complexity ~ O(1)
     */
    public int removeDuplicatesKREV(int[] nums) {
        //0,1,2,3,4,2,2,3,3,4
        //k = 4
        //dup = 6
        //len = 10

        int k = 0;
        int dup = 1;
        while (k + dup < nums.length) {
            if (nums[k] != nums[k + dup]) {
                nums[k + 1] = nums[k + dup];
                k++;
            } else {
                dup++;
            }
        }

        return k + 1;
    }

    /**
     * https://redquark.org/leetcode/0026-remove-duplicates-from-sorted-array/
     * time complexity ~ O(n)
     * space complexity ~ O(1)
     */
    public int removeDuplicates(int[] nums) {
        // Length of the updated array
        int count = 0;
        // Loop for all the elements in the array
        for (int i = 0; i < nums.length; i++) {
            // If the current element is equal to the next element, we skip
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                continue;
            }
            // We will update the array in place
            nums[count] = nums[i];
            count++;
        }
        return count;
    }
}
