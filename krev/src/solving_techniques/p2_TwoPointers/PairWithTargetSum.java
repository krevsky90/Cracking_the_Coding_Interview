package solving_techniques.p2_TwoPointers;

import java.util.HashMap;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63dda46a2f02a9827daabd4c
 *
 * Given an array of numbers sorted in ascending order and a target sum, find a pair in the array whose sum is equal to the given target.
 *
 * Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given target.
 *
 * Example 1:
 * Input: [1, 2, 3, 4, 6], target=6
 * Output: [1, 3]
 * Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6
 *
 * DO NOT MIX UP WITH https://leetcode.com/problems/two-sum/ !
 * since 'PairWithTargetSum' applies 'two pointers' approach because the array is SORTED!
 * BUT approach with HashMap can be also applied to UNsorted array!
 */
public class PairWithTargetSum {
    public static void main(String[] args) {
        int[] result = search(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = search(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }

    /**
     * two pointers approach
     * time complexity ~ O(n)
     * space complexity ~ O(1)
     */
    public static int[] search(int[] arr, int targetSum) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] == targetSum) {
                return new int[]{left, right};
            }

            if (arr[left] + arr[right] < targetSum) {
                left++;     // we need a pair with a bigger sum
            } else {
                right--;    // we need a pair with a smaller sum
            }
        }

        return new int[]{-1, -1};   //mean the pair is not found
    }

    /**
     * HashMap approach
     * time complexity ~ O(n)
     * space complexity ~ O(n)
     * NOTE: this approach DOES NOT require array to be SORTED!
     */
    public static int[] searchUsingHashMap(int[] arr, int targetSum) {
        HashMap<Integer, Integer> valueToIndexMap = new HashMap<>();
        for (int i1 = 0; i1 < arr.length - 1; i1++) {
            if (valueToIndexMap.containsKey(targetSum - arr[i1])) {
                //find the result
                int i2 = valueToIndexMap.get(targetSum - arr[i1]);
                return new int[]{i1, i2};
            } else {
                // put the number and its index in the map
                valueToIndexMap.put(arr[i1], i1);
            }
        }

        return new int[]{-1, -1};   //mean the pair is not found
    }
}
