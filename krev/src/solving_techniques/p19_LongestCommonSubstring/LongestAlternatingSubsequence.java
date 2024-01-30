package solving_techniques.p19_LongestCommonSubstring;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f6b1fa356150aa3550e51
 * OR
 * https://www.geeksforgeeks.org/longest-alternating-subsequence/
 *
 * A sequence {X1, X2, .. Xn} is an alternating sequence if its elements satisfy one of the following relations :
 *   X1 < X2 > X3 < X4 > X5 < ?. xn or
 *   X1 > X2 < X3 > X4 < X5 > ?. xn
 *
 * Example 1:
 * Input: arr[] = {1, 5, 4}
 * Output: 3
 * Explanation: The whole arrays is of the form  x1 < x2 > x3
 *
 * Example 2:
 * Input: arr[] = {10, 22, 9, 33, 49, 50, 31, 60}
 * Output: 6
 * Explanation: The subsequences {10, 22, 9, 33, 31, 60} or
 * {10, 22, 9, 49, 31, 60} or {10, 22, 9, 50, 31, 60}
 * are longest subsequence of length 6
 */
public class LongestAlternatingSubsequence {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 4};
        System.out.println(longestAlternatingSubsequence(arr1));    //expected 3
        int[] arr2 = {10, 22, 9, 33, 49, 50, 31, 60};
        System.out.println(longestAlternatingSubsequence(arr2));    //expected 6
    }

    /**
     * NOT SOLVED by myself
     * idea: https://www.geeksforgeeks.org/longest-alternating-subsequence/
     * las[i][0] = Length of the longest alternating subsequence ending at index i and last element is greater than its previous element
     * las[i][1] = Length of the longest alternating subsequence ending at index i and last element is smaller than its previous element
     * las[i][0] = max (las[i][0], las[j][1] + 1); for all j < i and A[j] < A[i]
     * las[i][1] = max (las[i][1], las[j][0] + 1); for all j < i and A[j] > A[i]
     *
     * time to implement ~ 10 mins
     *
     * time ~ O(n^2)
     * space O(n). where n - arr.length
     *
     * 1 attempt
     */

//    		     10, 22, 9, 33, 49, 50, 31, 60
//    las[i][0]  1   2   1   4   4   4   4   6
//    las[i][1]  1   1   3   1   1   1   5   1
//
//    LAS = 10 22 9 33/49/50 31 60, where x/y means x OR y
//    max length of LAS = 6
    public static int longestAlternatingSubsequence(int arr[]) {
        int n = arr.length;
        int[][] las = new int[n][2];
        //initial fill
        for (int i = 0; i < n; i++) {
            las[i][0] = 1;
            las[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    las[i][0] = Math.max(las[i][0], las[j][1] + 1);
                } else if (arr[j] > arr[i]) {
                    las[i][1] = Math.max(las[i][1], las[j][0] + 1);
                }
            }
        }

        return Math.max(las[n-1][0], las[n-1][1]);
    }
}