package solving_techniques.p11_ModifiedBinarySearch;

/**
 * 1011. Capacity To Ship Packages Within D Days (medium)
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
 *
 * #Company: Amazon Apple Google Uber Facebook
 *
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 *
 * Example 1:
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given,
 * so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 *
 * Example 2:
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 *
 * Example 3:
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 *
 * Constraints:
 * 1 <= days <= weights.length <= 5 * 10^4
 * 1 <= weights[i] <= 500
 */
public class CapacityToShipPackagesWithinDDays {
    /**
     * KREVSKY SOLUTION:
     * or the same info:
     * https://www.youtube.com/watch?v=4lK5pdSXhCk&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=39
     *
     * idea:
     * 1) set min and max of potential capacity
     * 2) use binary search (similarly to KokoEatingBananas)
     *
     * time to solve ~ 17 mins
     * time ~ O(N*log(max - min)), where N - weights.length, since we traverse throught weights array in canShipAll method
     * space ~ O(1)
     *
     * 1 attempt     *
     *
     * BEATS ~ 74%
     */
    public int shipWithinDays(int[] weights, int days) {
        int min = 0;
        int max = 0;
        for (int w : weights) {
            min = Math.max(min, w);
            max += w;
        }

        //or min = 500, max = 500*5*10000
        while (min < max) {
            int mid = (min + max)/2;
            if (!canShipAll(weights, days, mid)) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
// min = 15
// max = 15
// mid = 14

    private boolean canShipAll(int[] weights, int days, int capacity) {
        int counter = 0;
        int tempSum = 0;
        for (int w : weights) {
            tempSum += w;
            if (tempSum > capacity) {
                counter++;
                tempSum = w;
            }
        }
        counter++;

        return counter <= days;
    }
}
