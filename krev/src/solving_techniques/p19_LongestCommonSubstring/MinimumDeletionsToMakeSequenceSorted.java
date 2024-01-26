package solving_techniques.p19_LongestCommonSubstring;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6729a356150aa354e0f3
 * OR
 * https://www.geeksforgeeks.org/minimum-number-deletions-make-sorted-sequence/
 * <p>
 * Given a number sequence, find the minimum number of elements
 * that should be deleted to make the remaining sequence sorted.
 * Example 1:
 * Input: {4,2,3,6,10,1,12}
 * Output: 2
 * Explanation: We need to delete {4,1} to make the remaining sequence sorted {2,3,6,10,12}.
 * <p>
 * Example 2:
 * Input: {-4,10,3,7,15}
 * Output: 1
 * Explanation: We need to delete {10} to make the remaining sequence sorted {-4,3,7,15}.
 * <p>
 * Example 3:
 * Input: {3,2,1,0}
 * Output: 3
 * Explanation: Since the elements are in reverse order, we have to delete all except one to get a
 * sorted sequence
 */
public class MinimumDeletionsToMakeSequenceSorted {
    public static void main(String[] args) {
        int[] arr1 = {4, 2, 3, 6, 10, 1, 12};
        System.out.println("KREV " + minimumDeletionsDP(arr1));   //expected 2
        System.out.println("GFG " + minDeletionsGFG(arr1));   //expected 2

        int[] arr2 = {-4, 10, 3, 7, 15};
        System.out.println("KREV " + minimumDeletionsDP(arr2));   //expected 1
        System.out.println("GFG " + minDeletionsGFG(arr2));   //expected 1

        int[] arr3 = {3, 2, 1, 0};
        System.out.println("KREV " + minimumDeletionsDP(arr3));   //expected 3
        System.out.println("GFG " + minDeletionsGFG(arr3));   //expected 3
    }

    /**
     * KREVSKY SOLUTION:
     * idea: track minimum deletions for each subsequence 0-i
     * time to solve ~ 10-15 mins
     * <p>
     * time ~ O(n^2)
     * space ~ O(n), where n = arr.length;
     * 2 attempts:
     * - typo "i++" instead of "j++"
     */

//    4,2,3,6,10,1,12
//    dp = 0 1 1 1 1 2 2
    public static int minimumDeletionsDP(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                //NOTE: we use  ">=", since we want to get STRICTLY increasing subsequence!
                if (arr[j] >= arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
        }

        return dp[arr.length - 1];
    }

    /**
     * GFG SOLUTION:
     * https://www.geeksforgeeks.org/minimum-number-deletions-make-sorted-sequence/
     * idea:
     * 1) to find 'LongestIncreasingSubsequence' (the same as src/solving_techniques/p19_LongestCommonSubstring/LongestIncreasingSubsequence.java)
     * 2) to return arr.length - LIS.length()
     * <p>
     * BUT let's find LIS using binary search to get time complexity ~ O(n*logn) instead of O(n^2)
     */

//    4,2,3,6,10,1,12
//    sub = 1 3 6 10
//    index = 0
//    val = 1
//    l = 0
//    r = -1
//    mid = 0
    public static int minDeletionsGFG(int[] arr) {
        int n = arr.length;
        List<Integer> sub = new ArrayList<>(); // Stores the longest increasing subsequence

        sub.add(arr[0]); // Initialize the subsequence with the first element of the array

        for (int i = 1; i < n; i++) {
            if (arr[i] > sub.get(sub.size() - 1)) {
                // If the current element is greater than the last element of the subsequence,
                // it can be added to the subsequence to make it longer.
                sub.add(arr[i]);
            } else {
                int index = -1; // Initialize index to -1
                int val = arr[i]; // Current element value
                int l = 0, r = sub.size() - 1; // Initialize left and right pointers for binary search

                // Binary search to find the index where the current element can be placed in the subsequence
                while (l <= r) {
                    int mid = (l + r) / 2; // Calculate the middle index

                    if (sub.get(mid) >= val) {
                        index = mid; // Update the index if the middle element is greater or equal to the current element
                        r = mid - 1; // Move the right pointer to mid - 1
                    } else {
                        l = mid + 1; // Move the left pointer to mid + 1
                    }
                }

                if (index != -1) {
                    sub.set(index, val); // Replace the element at the found index with the current element
                }
            }
        }

        // The minimum number of deletions is equal to the difference between the input array size and the size of the longest increasing subsequence
        return n - sub.size();
    }

}
