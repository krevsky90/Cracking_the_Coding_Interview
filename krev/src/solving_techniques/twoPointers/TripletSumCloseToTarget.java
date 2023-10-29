package solving_techniques.twoPointers;

import java.util.Arrays;
import java.util.Collections;

/**
 * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
 *      https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63ddad31ff57d05f33aceba8
 *
 *  OR
 *  16. 3Sum Closest
 *  https://leetcode.com/problems/3sum-closest/
 */
public class TripletSumCloseToTarget {
    public static void main(String[] args) {
//        searchTriplet(new int[]{0}, 4);

        int[] arr1 = {-1, 0, 2, 3};
        int target1 = 3;
        System.out.println(searchTriplet(arr1, target1));   //Output: 2, since [-1, 0, 3], [-1, 0, 2]

        int[] arr2 = {-3, -1, 1, 2};
        int target2 = 1;
        System.out.println(searchTriplet(arr2, target2));   //Output: 0, since [-3, 1, 2]

        int[] arr3 = {1, 0, 1, 1};
        int target3 = 100;
        System.out.println(searchTriplet(arr3, target3));   //Output: 3, since [1, 1, 1]

        int[] arr4 = {0, 0, 1, 1, 2, 6};
        int target4 = 5;
        System.out.println(searchTriplet(arr4, target4));   //output: 4, since [1, 1, 2] & [0,0, 6], BUT we choose [1, 1, 2]
    }

    /**
     * NOT SOLVED by me
     * current solution:
     * total time complexity ~ O(n^2)
     * space complexity ~ O(1)
     * (FYI: naive solution: time complexity ~ O(n^3))
     */
    public static int searchTriplet(int[] arr, int targetSum) {
        //commented it due to leetcode constraint: 3 <= nums.length <= 500
//        if (arr == null || arr.length < 3) throw new IllegalArgumentException();

        Arrays.sort(arr);   // O(N*LogN)

        int smallestDifference = Integer.MAX_VALUE;

        //O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            //two pointers
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow
                // so, we will try to find a target difference
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                if (targetDiff == 0) return targetSum;


                if (Math.abs(targetDiff) < Math.abs(smallestDifference)) {
                    smallestDifference = targetDiff;
                }

                //it is 'OR' part or the previous condition (separated it for better understanding)
                //this is implementation of the requirement:
                //"If there are more than one such triplet, return the sum of the triplet with the smallest sum" - see Example 4 from the link above
                if (Math.abs(targetDiff) == Math.abs(smallestDifference) && targetDiff > smallestDifference) {
                    smallestDifference = targetDiff;
                }

                if (targetDiff > 0) {
                    //increase total sum to decrease delta (i.e. targetDiff) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return targetSum - smallestDifference;
    }
}
