package solving_techniques.different;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.tryexponent.com/practice/prepare/pairs-with-specific-difference
 *
 * Given an array arr of distinct integers and a nonnegative integer k,
 * write a function findPairsWithGivenDifference that returns an array of all pairs [x,y] in arr,
 * such that x - y = k. If no such pairs exist, return an empty array.
 * <p>
 * Note: the order of the pairs in the output array should maintain the order of the y element in the original array.
 * <p>
 * Examples:
 * input:  arr = [0, -1, -2, 2, 1], k = 1
 * output: [[1, 0], [0, -1], [-1, -2], [2, 1]]
 * <p>
 * input:  arr = [1, 7, 5, 3, 32, 17, 12], k = 17
 * output: []
 * Constraints:
 * <p>
 * [time limit] 5000ms
 * <p>
 * [input] array.integer arr
 * <p>
 * 0 ≤ arr.length ≤ 100
 * [input]integer k
 * <p>
 * k ≥ 0
 */
public class PairsWithSpecificDifference {
    /**
     * idea:
     * 1) need to fast check if pair exists => store all arr elements to Set
     * 2) since pairs should be ordered by y-th position, we will consider y = arr[i], since i is index when we traverse through arr
     * 2.2) then x = y + k => we check if set contains x
     *
     * time ~ O(arr.length)
     * space ~ O(arr.length)
     */
    static int[][] findPairsWithGivenDifference(int[] arr, int k) {
        // your code goes here
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            //arr[i] = y to keep pairs in order as y'th index
            //if (set.contains(x)) => add pair
            //x = y + k
            if (set.contains(arr[i] + k)) {
                result.add(new int[]{arr[i] + k, arr[i]});
            }
        }

        return result.toArray(new int[0][]);
    }
}
