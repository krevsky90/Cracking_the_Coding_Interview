package solving_techniques.bucketSort;

/**
 * 1094. Car Pooling (medium)
 * https://leetcode.com/problems/car-pooling
 * <p>
 * #Companies (30.01.2025): 0 - 3 months Meta 3 6 months ago Amazon 8 Google 5 Apple 3 Zepto 3 Microsoft 2 Flipkart 2 TikTok 2 Lyft 2
 * <p>
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * <p>
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi]
 * indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 * <p>
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 * <p>
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * <p>
 * Example 2:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * <p>
 * Constraints:
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */
public class CarPooling {
    /**
     * Approach #2 - see Editorial - Bucket Sort (since 'from' and 'to' <= 1000)
     * time ~ O(max(1001, N))
     * space ~ O(1001) ~ O(1)
     */
    public boolean carPooling2(int[][] trips, int capacity) {
        int[] timestamp = new int[1001];
        for (int[] trip : trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }
        int usedCapacity = 0;
        for (int number : timestamp) {
            usedCapacity += number;
            if (usedCapacity > capacity) {
                return false;
            }
        }
        return true;
    }
}
