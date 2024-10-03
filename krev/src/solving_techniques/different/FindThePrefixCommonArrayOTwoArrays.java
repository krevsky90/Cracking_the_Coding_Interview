package solving_techniques.different;

/**
 * 2657. Find the Prefix Common Array of Two Arrays (medium)
 * https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays
 * <p>
 * #Company: Yandex
 * <p>
 * You are given two 0-indexed integer permutations A and B of length n.
 * A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers that are present at or before the index i in both A and B.
 * Return the prefix common array of A and B.
 * A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.
 * <p>
 * Example 1:
 * Input: A = [1,3,2,4], B = [3,1,2,4]
 * Output: [0,2,3,4]
 * Explanation: At i = 0: no number is common, so C[0] = 0.
 * At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
 * At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
 * At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
 * <p>
 * Example 2:
 * Input: A = [2,3,1], B = [3,1,2]
 * Output: [0,1,3]
 * Explanation: At i = 0: no number is common, so C[0] = 0.
 * At i = 1: only 3 is common in A and B, so C[1] = 1.
 * At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
 * <p>
 * Constraints:
 * 1 <= A.length == B.length == n <= 50
 * 1 <= A[i], B[i] <= n
 * It is guaranteed that A and B are both a permutation of n integers.
 */
public class FindThePrefixCommonArrayOTwoArrays {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * keep boolean arrays which shows if the element exists or not
     * and we can calculate C[i] based on that information on each step
     *
     * time to solve ~ 20 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS ~ 95%
     */
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        boolean[] arrA = new boolean[n];
        boolean[] arrB = new boolean[n];

        C[0] = A[0] == B[0] ? 1 : 0;
        arrA[A[0] - 1] = true;
        arrB[B[0] - 1] = true;

        for (int i = 1; i < n; i++) {
            arrA[A[i] - 1] = true;
            arrB[B[i] - 1] = true;
            if (A[i] == B[i]) {
                C[i] = C[i - 1] + 1;
            } else {
                C[i] = C[i - 1];
                if (arrB[A[i] - 1]) {
                    C[i]++;
                }

                if (arrA[B[i] - 1]) {
                    C[i]++;
                }
            }
        }

        return C;
    }
}
