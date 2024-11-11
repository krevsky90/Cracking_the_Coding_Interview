package solving_techniques.different;

/**
 * 1539. Kth Missing Positive Number (easy)
 * <p>
 * https://leetcode.com/problems/kth-missing-positive-number
 * <p>
 * #Company: Microsoft Meta
 * <p>
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Return the kth positive integer that is missing from this array.
 * <p>
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * <p>
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 * <p>
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 * <p>
 * Follow up:
 * Could you solve this problem in less than O(n) complexity?
 */
public class KthMissingPositiveNumber {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 11 mins
     * time ~ O(n)
     * space ~ O(1)
     */
    public int findKthPositive(int[] arr, int k) {
        int counter = 1;
        int idx = 0;
        int len = arr.length;
        int numOfMissed = 0;
        while (counter <= arr[len - 1] && idx < len) {
            if (counter == arr[idx]) {
                idx++;
            } else {
                numOfMissed++;
                if (numOfMissed == k) return counter;
            }

            counter++;
        }

        return arr[len - 1] + (k - numOfMissed);
    }

    /**
     * info:
     * https://www.youtube.com/watch?v=R15876l3tSE&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=112
     */
    public int findKthPositive2(int[] arr, int k) {
        int tempK = k;
        //case 1:
        if (arr[0] != 1) {
            tempK -= arr[0] - 1;
        }

        if (tempK <= 0) return k;

        //case 2:
        int i = 0;
        int diff = -1;
        while (i < arr.length - 1) {
            diff = arr[i + 1] - arr[i];
            if (diff > 1) {
                for (int j = arr[i] + 1; j < arr[i + 1]; j++) {
                    tempK--;
                    if (tempK == 0) return j;
                }
            }

            i++;
        }

        //case 3:
        return arr[arr.length - 1] + tempK;
    }
}
