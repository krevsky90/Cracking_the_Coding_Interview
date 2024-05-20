package solving_techniques.p30_GreedyAlgorithms;

/**
 * 134. Gas Station
 * https://leetcode.com/problems/gas-station
 *
 * #Company: Apple
 *
 * There are n gas stations along a circular route,
 * where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel
 * from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index
 * if you can travel around the circuit once in the clockwise direction,
 * otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 * Example 1:
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas, but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 * Constraints:
 * n == gas.length == cost.length
 * 1 <= n <= 10^5
 * 0 <= gas[i], cost[i] <= 10^4
 */
public class GasStation {
    public static void main(String[] args) {
        int[] gas1 = {1,2,3,4,5};
        int[] cost1 = {3,4,5,1,2};

        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};

        System.out.println(new GasStation().canCompleteCircuit(gas1, cost1));
        System.out.println(new GasStation().canCompleteCircuit(gas2, cost2));

    }
//    gas  = [1,2,3,4,5]
//    cost = [3,4,5,1,2]
//
//    n = 5
//    i = 3
//    j = 3, idx = 3: s = 0 + 4 - 1 = 3
//    j = 4, idx = 4: s = 3 + 5 - 2 = 6
//    j = 5, idx = 0: s = 6 + 1 - 3 = 4
//    j = 6, idx = 1: s = 4 + 2 - 4 = 2
//    j = 7, idx = 2: s = 2 + 3 - 5 = 0

    /**
     * KREVSKY SOLUTION: TIME LIMIT EXCEEDED! => not solved properly. it is just brute force approach
     *
     * idea: check each element as start point
     *
     * time to solve ~ 15 mins
     *
     * time ~ O(n^2)
     * space ~ O(1)
     *
     * 1 attempt
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            //check each element as start point
            boolean canComplete = true;
            int s = 0;
            for (int j = i; j < i + n; j++) {
                int idx = j % n;
                s += gas[idx] - cost[idx];
                if (s < 0) {
                    canComplete = false;
                    break;
                }
            }

            if (canComplete) return i;
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/gas-station/solutions/5024094/beats-98-99-user-easiest-solution-java/
     * or
     * https://www.youtube.com/watch?v=lJwbPZGo05A
     *
     * idea:
     * sum of gas >= sum of cost
     * if(sum of gas < sum of cost) {
     *     return -1; // because that will be invalid!!!
     * } else {
     *     curr+=gas[i]-cost[i];
     *     if (curr < 0) {
     *          //check the next (i.e. i+1-th) position
     *
     *     }
     * }
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * BEATS = 98%
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;

        for (int g : gas) {
            totalGas += g;
        }

        for (int c : cost) {
            totalCost += c;
        }

        if (totalGas < totalCost) return -1;

        int startIdx = 0;
        int s = 0;
        for (int i = 0; i < gas.length; i++) {
            s += gas[i] - cost[i];
            if (s < 0) {
                startIdx = i + 1;   //will check the next position as start point
                s = 0;  //reset s
            }
            //NOTE: the non-trivial idea is:
            // if we did not face with s < 0 during [startIdx, gas.length - 1], then we will NOT face with it on [0, startIdx - 1] because totalGas >= totalCost!
        }
        return startIdx;
    }

}
