package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_arrays;

import java.util.*;

/**
 * Wise - hackerrank
 * https://www.geeksforgeeks.org/count-distinct-pairs-with-given-sum/
 * Given an array arr[] of size N and an integer K,
 * the task is to find the count of distinct pairs in the array whose sum is equal to K.
 * <p>
 * Example 1:
 * Input: arr[] = { 5, 6, 5, 7, 7, 8 }, K = 13
 * Output: 2
 * Explanation:
 * Pairs with sum K( = 13) are { (arr[0], arr[5]), (arr[1], arr[3]), (arr[1], arr[4]) }, i.e. {(5, 8), (6, 7), (6, 7)}.
 * Therefore, distinct pairs with sum K( = 13) are { (arr[0], arr[5]), (arr[1], arr[3]) }.
 * Therefore, the required output is 2.
 * <p>
 * Example 2:
 * Input: arr[] = { 2, 6, 7, 1, 8, 3 }, K = 10
 * Output : 2
 * Explanation:
 * Distinct pairs with sum K( = 10) are { (arr[0], arr[4]), (arr[2], arr[5]) }.
 * Therefore, the required output is 2.
 */
public class Problem1_4_TwoUniqueSums {
    public static void main(String[] args) {
        int K = 13;
        int[] arr = new int[]{5, 6, 5, 7, 7, 8, 8};

        System.out.println(countDistinctPairsWithGivenSum1(arr, K));
        System.out.println(countDistinctPairsWithGivenSum2(arr, K));
    }

    /**
     * approach #1: two pointers (see src/solving_techniques/p2_TwoPointers/readme.txt)
     * time ~ O(n*logn)
     */
    public static int countDistinctPairsWithGivenSum1(int[] arr, int K) {
        int result = 0;

        //sort initial array
        Arrays.sort(arr);

        int p1 = 0;
        int p2 = arr.length - 1;
        while (p1 < p2) {
            if (arr[p1] + arr[p2] == K) {
                // Remove consecutive duplicate array elements
                while (p1 < p2 && arr[p1] == arr[p1 + 1]) {
                    p1++;
                }

                // Remove consecutive duplicate array elements
                while (p1 < p2 && arr[p2] == arr[p2 - 1]) {
                    p2--;
                }

                result++;
                p1++;
                p2--;
            } else if (arr[p1] + arr[p2] < K) {
                p1++;
            } else {
                p2--;
            }
        }

        return result;
    }

    /**
     * approach #2: hashing
     * time ~ O(n)
     */

    public static int countDistinctPairsWithGivenSum2(int[] arr, int K) {
        Map<Integer, Integer> numToOccurrences = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int occurrence = numToOccurrences.getOrDefault(arr[i], 0);
            numToOccurrences.put(arr[i], occurrence + 1);
        }

        int result = 0;

        for (int n : numToOccurrences.keySet()) {
            if (2*n == K) {
                //if arr really has >=2 elements = n then they form a pair
                if (numToOccurrences.get(n) > 1) {
                    result += 2;
                }
            } else {
                if (numToOccurrences.containsKey(K - n)) {
                    //NOTE: we increment counter handling EACH element of the same pair!
                    // so we will get doubled result!
                    // that's why we need to add +2 in case when '2*n == K"
                    result++;
                }
            }
        }

        return result/2;    //return real amount of different pairs
    }
}
