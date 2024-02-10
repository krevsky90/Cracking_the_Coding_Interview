package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f1a8b44223ca42ca4a723
 * <p>
 * Given a sorted array of numbers, find if a given number ?key? is present in the array.
 * Though we know that the array is sorted, we don?t know if it?s sorted in ascending or descending order.
 * You should assume that the array can have duplicates.
 * <p>
 * Write a function to return the index of the ?key? if it is present in the array, otherwise return -1.
 * <p>
 * Example 1:
 * Input: [4, 6, 10], key = 10
 * Output: 2
 * <p>
 * Example 2:
 * Input: [1, 2, 3, 4, 5, 6, 7], key = 5
 * Output: 4
 * <p>
 * Example 3:
 * Input: [10, 6, 4], key = 10
 * Output: 0
 */
public class OrderAgnosticBinarySearch {
    public static void main(String[] args) {
        int[] arr1 = {4, 6, 10};
        int key1 = 10;
        System.out.println(search(arr1, key1)); //2
        System.out.println(search2(arr1, key1)); //2

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        int key2 = 5;
        System.out.println(search(arr2, key2)); //4
        System.out.println(search2(arr2, key2)); //4

        int[] arr3 = {10, 6, 4};
        int key3 = 10;
        System.out.println(search(arr3, key3)); //0
        System.out.println(search2(arr3, key3)); //0

        int[] arr4 = {1, 2, 4, 6};
        int key4 = 10;
        System.out.println(search(arr4, key4)); //-1
        System.out.println(search2(arr4, key4)); //-1

    }

    /**
     * KREVSKY SOLUTION: recursive approach
     * time to solve ~ 18 mins
     * time ~ O(logN)
     * space ~ O(1)
     * 1 attempt
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        boolean acs = nums[0] < nums[right];
        return search(nums, target, left, right, acs);
    }

    public static int search(int[] nums, int target, int left, int right, boolean acs) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (acs && nums[mid] < target) {
            //check right part
            return search(nums, target, mid + 1, right, acs);
        } else if (acs && nums[mid] > target) {
            //check left part
            return search(nums, target, left, mid - 1, acs);
        } else if (!acs && nums[mid] < target) {
            //check left part
            return search(nums, target, left, mid - 1, acs);
        } else {    //if (!acs && nums[mid] > target)
            //check right part
            return search(nums, target, mid + 1, right, acs);
        }
    }

    /**
     * KREVSKY SOLUTION: iterative approach
     * time to solve ~ 10 mins
     * time ~ O(logN)
     * space ~ O(1)
     * 1 attempt
     */
    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        boolean acs = nums[0] < nums[right];

        while (left <= right) {
            int mid = (left + right)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (acs && nums[mid] < target) {
                //check right part
                left = mid + 1;
            } else if (acs && nums[mid] > target) {
                //check left part
                right = mid - 1;
            } else if (!acs && nums[mid] < target) {
                //check left part
                right = mid - 1;
            } else {    //if (!acs && nums[mid] > target)
                //check right part
                left = mid + 1;
            }
        }

        return -1;
    }
}
