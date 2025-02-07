package solving_techniques.different;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 871. Minimum Number of Refueling Stops (hard)
 * https://leetcode.com/problems/minimum-number-of-refueling-stops
 * <p>
 * #Company (25.01.2025) 0 - 3 months Amazon 2 0 - 6 months Google 2 DE Shaw 2 6 months ago Microsoft 4 Snap 4 Flipkart 3 Morgan Stanley 3 Barclays 3 Goldman Sachs 2  Rubrik 2
 * <p>
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * <p>
 * There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.
 * <p>
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * <p>
 * Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.
 * <p>
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 * Example 2:
 * <p>
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can not reach the target (or even the first gas station).
 * Example 3:
 * <p>
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation: We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target, startFuel <= 109
 * 0 <= stations.length <= 500
 * 1 <= positioni < positioni+1 < target
 * 1 <= fueli < 109
 */
public class MinimumNumberOfRefuelingStops {
    /**
     * KREVSKY DP SOLUTION - gives TLE!!!
     * I could not apply memoization in my solution
     * time to solve ~ 20 mins
     * <p>
     * 1 attempt
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int subres = helper(target, 0, 0, startFuel, stations);
        return subres == 1000000 ? -1 : subres;
    }

    private int helper(int target, int curPos, int stationIdx, int curFuel, int[][] stations) {
        if (target - curPos <= curFuel) return 0;
        if (stationIdx == stations.length) return 1000000;  //there is no stations, but we still can't reach target
        if (stations[stationIdx][0] - curPos > curFuel) return 1000000; //impossible to reach the nearest station

        //if we reach stations[stationIdx], we can use it of skip
        //1. use:
        int res1 = 1 + helper(target, stations[stationIdx][0], stationIdx + 1, curFuel - (stations[stationIdx][0] - curPos) + stations[stationIdx][1], stations);
        //2. skip:
        int res2 = helper(target, stations[stationIdx][0], stationIdx + 1, curFuel - (stations[stationIdx][0] - curPos), stations);

        return Math.min(res1, res2);
    }

    /**
     * info: https://www.youtube.com/watch?v=vj3CiiwZw1o&list=PLUPSMCjQ-7oeenUxyrJFDFqd40PokIqdO&index=15
     * idea: use max heap
     * time ~ O(N*logN)
     * space ~ O(N*logN)
     *
     * BEATS ~ 100%
     */
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        // idea #1: sort stations by position - NOT required since the stations are already sorted (according to the Constraints)

        int fuel = startFuel;
        int prevPos = 0;
        int counter = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);    //max heap sorted by max fuel storage
        int n = stations.length;

        //traverse until length + 1 as if target is n+1-th station
        for (int i = 0; i < n + 1; i++) {
            if (i < n) {
                fuel -= (stations[i][0] - prevPos);
            } else {
                //i.e. i = n
                fuel -= target - prevPos;
            }

            while (fuel < 0 && !pq.isEmpty()) {
                fuel += pq.poll()[1];
                counter++;
            }

            if (fuel < 0) return -1;

            if (i < n) {
                pq.add(stations[i]);
                prevPos = stations[i][0];
            }
        }

        return counter;

        // fuel -= (target - prevPos);
        // while (fuel < 0 && !pq.isEmpty()) {
        //     fuel += pq.poll()[1];
        //     counter++;
        // }

        // if (fuel < 0) {
        //     return -1;
        // } else {
        //     return counter;
        // }
    }
}
