package solving_techniques.p31_OrderedSet;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

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
     * KREVSKY SOLUTION:
     * idea:
     * 1) use TreeMap: key is point of the route, value: amount of people who come in or out of the car at this point
     * 2) traverse the values of map: if capacity < 0 => return false;
     * time to solve ~ 15 mins
     * <p>
     * time ~ O(N*logN), where N = trips.length
     * space ~ O(N)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 27%
     */
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap<>();    //sorted by key which is number of kilometers due east
        // [2,1,5],[3,3,7]], => map (1,-2), (3, -3), (5, 2), (7, 3)
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) - trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) + trip[0]);
        }

        for (int v : map.values()) {
            capacity += v;
            if (capacity < 0) return false;
        }

        return true;
    }

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

    /**
     * SOLUTION #3:
     * info: https://www.youtube.com/watch?v=GpNoUiaP3tM&list=PLUPSMCjQ-7oeenUxyrJFDFqd40PokIqdO&index=20
     * idea:
     * 1) sort trips by start pos
     * 2) keep heap of on-going trips and pop finished trips from the heap once we faced start point of new trip
     *
     * BEATS ~ 46%
     */
    public boolean carPooling3(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);   //ordered by end position

        for (int[] trip : trips) {
            //remove all finished trips and increase capacity
            while (!pq.isEmpty() && trip[1] >= pq.peek()[2]) {
                int[] toremove = pq.poll();
                capacity += toremove[0];
            }


            if (trip[0] > capacity) return false;

            capacity -= trip[0];
            pq.add(trip);
        }

        return true;
    }
}

