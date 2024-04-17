package solving_techniques.p12_Bitwise_XOR;

/**
 * 1442. Count Triplets That Can Form Two Arrays of Equal XOR
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
 * <p>
 * Given an array of integers arr.
 * <p>
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 * <p>
 * Let's define a and b as follows:
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 * <p>
 * Return the number of triplets (i, j and k) Where a == b.
 * <p>
 * Example 1:
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 * <p>
 * Example 2:
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 * <p>
 * Constraints:
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXor {
    /**
     * KREVSKY SOLUTION:
     * idea #1: if a == b => a^b = 0. i.e. we just search interval [i,k], where arr[i]^...^arr[k] = 0
     * idea #2: traverse k from i+1 to arr.length and calculate smth like 'prefix sum', but in fact 'xor'. each time when xor = 0, we have found desired interval
     * NOTE: calculating 'xor' helps us not to traverse from i+1 to k each time and recalculate this xor
     * idea #3: xor of each split of [i,k] will give 0. i.e. j can be everywhere, starting from i+1 and ending with k
     * => each time when we have found appropriate interval [i,k], we've got k-i triplets
     * <p>
     * time to solve ~ 32 mins
     * <p>
     * time ~ O(n^2), where n = arr.length
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 100%
     */
    public int countTriplets(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int xor = arr[i];
            for (int k = i + 1; k < arr.length; k++) {
                xor ^= arr[k];
                if (xor == 0) {
                    // if (k == i + 1) {
                    //     result++;   //since triplet is (i,k,k)
                    // } else {
                    //we can choose j starting from i+1 and ending with k
                    result += k - i;    //NOTE: it covers case when k = i+1
                    // }
                }
            }

        }

        return result;
    }
}
