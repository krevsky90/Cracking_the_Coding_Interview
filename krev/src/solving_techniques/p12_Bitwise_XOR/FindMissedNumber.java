package solving_techniques.p12_Bitwise_XOR;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a09d143fbc39976653e236
 *
 * Given an array of n-1 integers in the range from 1 to n, find the one number that is missing from the array.
 * Example:
 * Input: 1, 5, 2, 6, 4
 * Answer: 3
 */
public class FindMissedNumber {
    /**
     * SOLUTION:
     * https://www.geeksforgeeks.org/find-the-missing-number/ (see approach 3)
     *
     * idea: to use property #2
     */
    public static int getMissedNumber(int arr[]) {
        int n = arr.length + 1;

        //1) For xor of all the elements in array
        int x1 = arr[0];
        for (int i = 1; i < arr.length; i++) {
            x1 ^= arr[i];
        }

        //2) For xor of all the elements from 1 to n
        int x2 = 1;
        for (int j = 2; j <= n; j++) {
            x2 ^= j;
        }

        //x2 ^ result = x1
        //let's apply XOR x2 to both parts of this equation:
        // x2 ^ x2 ^ result = x1 ^ x2
        // result = x1 ^ x2
        return x1 ^ x2;
    }
}
