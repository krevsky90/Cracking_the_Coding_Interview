package solving_techniques.p12_Bitwise_XOR;

/**
 * 2433. Find The Original Array of Prefix Xor
 * https://leetcode.com/problems/find-the-original-array-of-prefix-xor
 * <p>
 * You are given an integer array pref of size n. Find and return the array arr of size n that satisfies:
 * <p>
 * pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
 * Note that ^ denotes the bitwise-xor operation.
 * <p>
 * It can be proven that the answer is unique.
 * <p>
 * Example 1:
 * Input: pref = [5,2,0,3,1]
 * Output: [5,7,2,3,2]
 * Explanation: From the array [5,7,2,3,2] we have the following:
 * - pref[0] = 5.
 * - pref[1] = 5 ^ 7 = 2.
 * - pref[2] = 5 ^ 7 ^ 2 = 0.
 * - pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
 * - pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
 * <p>
 * Example 2:
 * Input: pref = [13]
 * Output: [13]
 * Explanation: We have pref[0] = arr[0] = 13.
 * <p>
 * Constraints:
 * 1 <= pref.length <= 10^5
 * 0 <= pref[i] <= 10^6
 */
public class FindTheOriginalArrayOfPrefixXor {
    /**
     * KREVSKY SOLUTION:
     * idea:
     *      pref[0] = arr[0]
     *      pref[1] = arr[0] ^ arr[1] = pref[0] ^ arr[1]
     *      pref[2] = pref[1] ^ arr[2]
     *      pref[i] = pref[i-1] ^ arr[i]
     * Apply " ^ pref[i-1] " to both parts of equation
     *      formula: arr[i] = pref[i] ^ pref[i-1]
     *
     * time to solve ~ 7 mins
     * time ~ O(n)
     * space ~ O(n), for arr itself
     * 1 attempt
     */

    public int[] findArray(int[] pref) {
        int[] arr = new int[pref.length];
        arr[0] = pref[0];
        for (int i = 1; i < pref.length; i++) {
            arr[i] = pref[i] ^ pref[i - 1];
        }

        return arr;
    }
}
