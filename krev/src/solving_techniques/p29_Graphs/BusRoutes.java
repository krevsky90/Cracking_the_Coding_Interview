package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 815. Bus Routes (hard)
 * https://leetcode.com/problems/bus-routes/
 * <p>
 * #Company: 0 - 3 months Uber 22 Google 2 Microsoft 2 TikTok 2 Pinterest 2 6 months ago Amazon 12 PhonePe 5 Meta 2 Citadel 2 Coupang 2
 * <p>
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * <p>
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target.
 * You can travel between bus stops by buses only.
 * <p>
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 * <p>
 * Example 1:
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * <p>
 * Example 2:
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 */
public class BusRoutes {
    /**
     * SOLUTION #3:
     * similar idea as KREVSKY SOLUTION #2, BUT excluded adjMap (which takes time O(M*M*K))
     * time ~ build stopToRoutes + BFS ~ O(M*K) + O(M) ~ O(M*K), where M = routes.length, K = max(route[i])
     * space ~ O(M * K)
     *
     * BEATS ~ 73%
     */
    public int numBusesToDestination3(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, Set<Integer>> stopToRoutes = new HashMap<>(); //bus stop -> set of routes (its numbers) that contain this bus stop
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new HashSet<>());
                stopToRoutes.get(stop).add(i);
            }
        }

        if (!stopToRoutes.containsKey(source) || !stopToRoutes.containsKey(target)) return -1;

        Queue<Integer> q = new LinkedList<>();    //contains bus stops
        q.add(source);

        Set<Integer> visitedStops = new HashSet<>(); //visited bus stops
        Set<Integer> visitedRoutes = new HashSet<>(); //visited routes
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int stop = q.poll();
                if (stop == target) return depth;

                for (int routeId : stopToRoutes.get(stop)) {
                    if (visitedRoutes.contains(routeId)) continue;

                    visitedRoutes.add(routeId);

                    for (int tempStop : routes[routeId]) {
                        if (visitedStops.contains(tempStop)) continue;

                        visitedStops.add(tempStop);
                        q.add(tempStop);
                    }
                }
            }
            depth++;
        }

        return -1;
    }

    /**
     * KREVSKY SOLUTION:
     * time ~ 1h
     * idea:
     * 1) vertex = route
     * 2) edge = fact that the routes has common bus stop
     * 3) store Map: route number -> set of routes that has collision (common bus stop)
     * 4) but BEFORE store Map: bus stop -> set of routes (its numbers) that contain this bus stop
     * 5) put all srcRoutes (i.e. the route that contains src bus stop) to the queue and apply BFS traversal with stop condition = target set contains current vertex
     * <p>
     * edge cases:
     * 1) src = target => return 0
     * 2) if src or target are not part of any route => return -1
     * <p>
     * M - number of routes, K - max(route[i])
     * time ~ M*K (to build stopToRoutes) + M*K*M (to build adjMap, since addAll(setOfAdjRoutes) takes O(M)) + M (to BFS traversal) ~ O(M*K + M*M*K)
     * space ~
     * <p>
     * 4 attempts:
     * - forgot edge case #1
     * - forgot edge case #2
     * - applied BFS for each srcRoute instead of putting of srcRoutes to the queue as described in idea #5
     * <p>
     * BEATS ~ 5%
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, Set<Integer>> stopToRoutes = new HashMap<>(); //bus stop -> set of routes (its numbers) that contain this bus stop
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new HashSet<>());
                stopToRoutes.get(stop).add(i);
            }
        }

        if (!stopToRoutes.containsKey(source) || !stopToRoutes.containsKey(target)) return -1;

        Map<Integer, Set<Integer>> adjMap = new HashMap<>();   //route number -> set of routes that has collision (common bus stop)
        for (int i = 0; i < routes.length; i++) {
            adjMap.putIfAbsent(i, new HashSet<>());
            for (int stop : routes[i]) {
                Set<Integer> setOfAdjRoutes = stopToRoutes.get(stop);
                adjMap.get(i).addAll(setOfAdjRoutes);
            }
            adjMap.get(i).remove(i);    //remove route itself from its adj list
        }

        Set<Integer> srcRoutes = stopToRoutes.get(source);
        Set<Integer> targetRoutes = stopToRoutes.get(target);

        Queue<int[]> q = new LinkedList<>();    //int[] contains route number and current 'level'
        for (int src : srcRoutes) {
            q.add(new int[]{src, 1});
        }

        Set<Integer> visited = new HashSet<>(); //visited routes

        while (!q.isEmpty()) {
            int[] pair = q.poll();

            visited.add(pair[0]);

            if (targetRoutes.contains(pair[0])) {
                return pair[1];
            }

            Set<Integer> adjRoutes = adjMap.get(pair[0]);
            for (int route : adjRoutes) {
                if (!visited.contains(route)) {
                    q.add(new int[]{route, pair[1] + 1});
                }
            }
        }

        return -1;
    }

    /**
     * KREVSKY SOLUTION #2:
     * the same idea, but we store only rout number in the queue, but depth is tracked separately
     *
     * BEATS ~ 5%
     */
    public int numBusesToDestination2(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, Set<Integer>> stopToRoutes = new HashMap<>(); //bus stop -> set of routes (its numbers) that contain this bus stop
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new HashSet<>());
                stopToRoutes.get(stop).add(i);
            }
        }

        if (!stopToRoutes.containsKey(source) || !stopToRoutes.containsKey(target)) return -1;

        Map<Integer, Set<Integer>> adjMap = new HashMap<>();   //route number -> set of routes that has collision (common bus stop)
        for (int i = 0; i < routes.length; i++) {
            adjMap.putIfAbsent(i, new HashSet<>());
            for (int stop : routes[i]) {
                Set<Integer> setOfAdjRoutes = stopToRoutes.get(stop);
                adjMap.get(i).addAll(setOfAdjRoutes);
            }
            adjMap.get(i).remove(i);    //remove route itself from its adj list
        }

        Set<Integer> srcRoutes = stopToRoutes.get(source);
        Set<Integer> targetRoutes = stopToRoutes.get(target);

        Queue<Integer> q = new LinkedList<>();    //contains route number
        for (int src : srcRoutes) {
            q.add(src);
        }

        Set<Integer> visited = new HashSet<>(); //visited routes
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int temp = q.poll();

                visited.add(temp);

                if (targetRoutes.contains(temp)) {
                    return depth;
                }

                Set<Integer> adjRoutes = adjMap.get(temp);
                for (int route : adjRoutes) {
                    if (!visited.contains(route)) {
                        q.add(route);
                    }
                }
            }
            depth++;
        }

        return -1;
    }
}
