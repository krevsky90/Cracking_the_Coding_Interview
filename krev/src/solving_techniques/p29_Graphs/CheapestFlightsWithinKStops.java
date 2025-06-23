package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops (medium)
 * https://leetcode.com/problems/cheapest-flights-within-k-stops
 *
 * #Company: 0 - 3 months Amazon 3 Meta 2 0 - 6 months MakeMyTrip 4 Google 3 Airbnb 3 6 months ago TikTok 10 Snap 7 Adobe 6 Bloomberg
 *
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei] indicates
 *      that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 *
 * Example 1:
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 *
 * Example 2:
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 *
 * Example 3:
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 * Constraints:
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 10^4
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        CheapestFlightsWithinKStops obj = new CheapestFlightsWithinKStops();
        int n = 4;
        int[][] edges = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        int start = 0;
        int end = 3;
        int k = 1;

        int result = obj.findCheapestPrice(n, edges, start, end, k);
        System.out.println(result);
    }

    /**
     * KREVSKY SOLUTION not fully solved by myself - needed stop[] arr to avoid TLE:
     * idea:
     * 1) Dijkstra algorithm
     * 2) check if the path is not longer than k
     * 3) track stops[] like dist, because despite the price might be optimal, we might not reach end_node in <= k steps
     *  for example:
     *      int[][] edges = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
     *      int start = 0;
     *      int end = 3;
     *      int k = 1;
     *  here we can reach {2} with price = 2, but to reach {3} we will need 2 stops which is prohibited
     *  so we will need also to store the case when {2} is reached at cost 5
     *
     *  time to implement and debug ~ 21 + 30 = 51 mins
     *
     *  time ~ O(E + V*logV)
     *  space ~ O(E + V)
     *
     * BEATS ~ 47%
     *
     * many attempts:
     * - tried to use visited set
     * - duplicate data in dist[] and Queue => dist affected the results from Queue
     * - returned el[2] instead of el[1]
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>(); //int[] - stores vertex and price
        for (int[] edge : flights) {
            adjMap.putIfAbsent(edge[0], new ArrayList<>());
            adjMap.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);    //min heap, where int[] - stores vertex, cheapest price, amount of stops
        pq.add(new int[]{src, 0, -1});

        int[] stops = new int[n];   //store number of stops that require to reach i-th vertex
        Arrays.fill(stops, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            int[] el = pq.poll();

            // NOTE: this optimization helps to avoid TLE
            // 1) if the path is longer than k => skip this path
            // 2) if we have already handled this vertex => its price was < then el[1].
            // What may be the reason to consider el? only if it contains < stops than we faced/stored before for this vertex.
            //      Otherwise, it has worse price and worse or same amount of stops
            if (el[2] > k || stops[el[0]] <= el[2]) continue;

            stops[el[0]] = el[2];

            if (el[0] == dst) return el[1];

            List<int[]> adjList = adjMap.getOrDefault(el[0], new ArrayList<>());
            for (int[] adj : adjList) {
                pq.add(new int[]{adj[0], el[1] + adj[1], el[2] + 1});
            }
        }

        return -1;
    }
}
