package solving_techniques.p10_SubsetsAndPermutations;

/**
 * 31. Next Permutation (medium)
 * https://leetcode.com/problems/next-permutation
 * <p>
 * #Company: Adobe Amazon Apple Bloomberg ByteDance eBay Facebook Google Houzz caMorgan Microsoft Quora Rubrik Sumologic Uber
 * <p>
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * <p>
 * Example 3:
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * Example 4 (KREVSKY):
 * Input: nums = [3,4,5,2,1] - find 5, swap 5 and 4 => 3,5,4,2,1 == sort 4,2,1 ==> 3,5,1,2,4
 * Output: [3,5,1,2,4]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class NextPermutation {
    /**
     * SOLUTION 1.2 (from 1.02.2025) - the same logic as below, BUT traverse the array from right to left to avoid overriding indexes again and again
     * time to solve ~ 15 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    //idx:  0 1 2 3 4 5 6
    //nums: 4 3 6 5 4 2 1 => idxToSwap1 = 1, idxToSwap2 = 4
    //swap: 4 4 6 5 3 2 1
    //reverse subarray [idxToSwap1 + 1, ...]: 4 4 1 2 3 5 6
    public void nextPermutation12(int[] nums) {
        if (nums.length <= 1) return;

        //generic case
        int idxToSwap1 = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idxToSwap1 = i;
                break;
            }
        }

        if (idxToSwap1 == -1) {
            //it means that nums is sorted => just reverse it and return
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int idxToSwap2 = -1;
        for (int i = nums.length - 1; i > idxToSwap1; i--) {
            if (nums[i] > nums[idxToSwap1]) {
                idxToSwap2 = i;
                break;
            }
        }

        //swap idxToSwap1 and idxToSwap2 elements
        int temp = nums[idxToSwap1];
        nums[idxToSwap1] = nums[idxToSwap2];
        nums[idxToSwap2] = temp;

        //reverse [idxToSwap1, ...] part of nums since it is still sorted in descending order
        reverse(nums, idxToSwap1 + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * /**
     * NOT SOLVED by myself
     * info:
     * 1) https://www.youtube.com/watch?v=6qXO72FkqwM - good pictures and solution, except sorting of the 'rest' part of array
     * 2) https://www.youtube.com/watch?v=quAS1iydq7U - like p.1, byt shortly and more optimal: swap elements of the 'rest' part of array instead of sorting
     * idea:
     * 1) find the 'last peak' element: (arr[n] > arr[n-1])
     * 2.1) find the element that 'more right' than the 'last peak' element, obviously less than it, BUT greater than arr[peak-1] element
     * 2.2) swap 'more right' and arr[peak-1] elements
     * 3) sort the elements that are more right than 'last peak' element in ASC order. BUT since these elements are DESC sequence => we can just swap this part of array to get ASC order
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * NOTE: if we use sorting in p.3, then time ~ O(n*logn)
     * <p>
     * time to solve ~ 40-45 mins
     * 6 attempts:
     * - forget 'return' for case 'peakIndex = -1'
     * - ~5 errors in indexes in for-loop to swap the 'rest' part of array (from i + peakIndex to n-i-1)
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 1) return;

        int peakIndex = -1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                peakIndex = i;
            }
        }

        //special case: arr is sorted in DESC order => peak is the first element => peakIndex = -1
        //in this case we transform arr to ACS ordered array by swapping the elements from left and right sides
        if (peakIndex == -1) {
            for (int i = 0; i < n / 2; i++) {
                swap(nums, i, n - 1 - i);
            }
            return;
        }

        //otherwise we find the minimal (!) element such as
        //1) its index > peakIndex (obviously this element is less than peak element)
        //2) > nums[peakIndex-1]
        int indexToSwap = peakIndex;
        for (int i = peakIndex + 1; i < n; i++) {
            if (nums[peakIndex - 1] < nums[i]) {
                indexToSwap = i;
            } else {
                break;  //small optimization
            }
        }

        //swap 'peakIndex - 1' and indexToSwap:
        //NOTE: if peakIndex = n-1 (i.e. it is the last element of the array), then for-loop will be omitted, and 'peakIndex - 1' and 'peakIndex elements will be swapped'
        swap(nums, peakIndex - 1, indexToSwap);

        //the elements from 'peakIndex' to n are already sorted (otherwise it would be not the latest peak - see the pictures from videos) in DESC order
        //so we just need to swap this part of array to get ASC order
        for (int i = 0; i + peakIndex < n - i - 1; i++) {
            swap(nums, i + peakIndex, n - i - 1);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
