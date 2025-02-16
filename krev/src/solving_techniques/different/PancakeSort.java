package solving_techniques.different;

import java.util.Arrays;

/**
 * https://www.tryexponent.com/practice/prepare/pancake-sort
 * <p>
 * Given an array of integers arr:
 * <p>
 * Write a function flip(arr, k) that reverses the order of the first k elements in the array arr.
 * Write a function pancakeSort(arr) that sorts and returns the input array.
 * You are allowed to use only the function flip you wrote in the first step in order to make changes in the array.
 * <p>
 * Example:
 * input:  arr = [1, 5, 4, 3, 2]
 * output: [1, 2, 3, 4, 5] # to clarify, this is pancakeSort's output
 * <p>
 * Analyze the time and space complexities of your solution.
 * <p>
 * Note: it’s called pancake sort because it resembles sorting pancakes on a plate with a spatula,
 * where you can only use the spatula to flip some of the top pancakes in the plate.
 * To read more about the problem, see the Pancake Sorting Wikipedia page.
 */
public class PancakeSort {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) find index of maximum element in subarray (initially subarray = whole array)
     * 2) if maxId != 0, then flip [0, maxIdx] => max element will be at index = 0
     * 3) flip subarray => max element will be at the end of subarray
     * 4) decrease right bound of subarray. Now we will repeat 1-3 for smaller part of the initial subarray
     * NOTE: my optimization: if maxId = right bound of subarray => max element is already in the end of subarray => skip 2-3
     * <p>
     * time to solve ~ 30 mins
     * <p>
     * time ~ O(n*n)
     * space ~ O(1)
     */
    static int[] pancakeSort(int[] arr) {
        // your code goes here
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int idx = 0;
            int maxEl = Integer.MIN_VALUE;
            for (int i = left; i <= right; i++) {
                if (maxEl < arr[i]) {
                    idx = i;
                    maxEl = arr[i];
                }
            }

            if (idx != right) {
                if (idx != 0) {
                    flip(arr, idx);
                }

                flip(arr, right);
            }
            right--;

        }
        return arr;
    }

    private static void flip(int[] arr, int k) {
        int left = 0;
        int right = k;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,5,3,8,4};
        int[] arr = new int[]{1,5,4,3,2};

        System.out.println(Arrays.toString(pancakeSort(arr)));
    }
}
