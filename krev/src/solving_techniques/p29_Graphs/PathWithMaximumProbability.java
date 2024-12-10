package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 1514. Path with Maximum Probability (medium)
 * https://leetcode.com/problems/path-with-maximum-probability
 *
 * #Company: 0 - 6 months Google 5 Meta 2 Microsoft 2 6 months ago BlackRock 4 Amazon 3 tcs 2
 *
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list
 * where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 *
 * Example 2:
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 *
 * Example 3:
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        PathWithMaximumProbability obj = new PathWithMaximumProbability();

        int n = 3;
//        int[][] edges = {{0,1},{1,2},{0,2}};
//        double[] succProb = {0.5,0.5,0.2};
        int start = 0;
        int end = 2;
        //
        int[][] edges = {{0, 1}};
        double[] succProb = {0.5};
        double result = obj.maxProbability(n, edges, succProb, start, end);
        System.out.println(result);
    }

    /**
     * NOT SOLVED by myself since Dijkstra part was correct, but I returned INCORRECT:
     * if (visited.size() != n) {
     *             return 0;
     *         } else {
     *             return dist[end_node];
     *         }
     *
     * Instead of that I just had to write "return dist[end_node];"
     *
     * info: https://www.youtube.com/watch?v=ni18214QpcE&list=PLUPSMCjQ-7odm9bh0iW6WOCfS6wiJETnt&index=9
     *
     * NOTE: если я правильно понимаю, то можно обойтись и БЕЗ visited, просто потому, что когда мы вынимаем из очереди элемент с нодой Х,
     * путь к этому элементу Х уже является оптимальным! НО НЕ УВЕРЕН,
     * т.к. тут https://www.youtube.com/watch?v=XEb7_z5dG3c&pp=ygURI2NvZGVfaXRfeW91cnNlbGY%3D вижу visited set
     *
     * time ~ O(E) (to build adjLists) + O(V*logV) (to poll v node V times)
     * space ~ O(E) (to store adjLists) + O(V) (to store in Queue)
     *
     * BEATS ~ 40-80%
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<Pair>> adjLists = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            adjLists.putIfAbsent(edges[i][0], new ArrayList<>());
            adjLists.putIfAbsent(edges[i][1], new ArrayList<>());
            adjLists.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            adjLists.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        double[] dist = new double[n];  //default prob = 0 is ok, since we will find max prob
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.dist - a.dist < 0 ? -1 : 1);   //max heap
        pq.add(new Pair(start_node, 1.0));  //max prob = 1
        dist[start_node] = 1.0; //max prob = 1

        while (!pq.isEmpty()) {
            Pair v = pq.poll();
            if (v.node == end_node) {
                return v.dist;
            }

            List<Pair> adj = adjLists.getOrDefault(v.node, new ArrayList<>());
            for (Pair u : adj) {
                if (dist[u.node] < dist[v.node] * u.dist) {
                    dist[u.node] = dist[v.node] * u.dist;
                    pq.add(new Pair(u.node, dist[u.node]));
                }
            }
        }

        return 0.0; //it means that Queue is empty, but we still haven't reached end_node
    }

    /**
     * OR the same solution, but also storing visited nodes:
     */
    public double maxProbability2(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Pair>> adjLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjLists.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            adjLists.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            adjLists.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        double[] dist = new double[n];  //default prob = 0 is ok, since we will find max prob

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.dist - a.dist < 0 ? -1 : 1);   //max heap
        pq.add(new Pair(start_node, 1.0));  //max prob = 1
        dist[start_node] = 1.0; //max prob = 1

        while (!pq.isEmpty()) {
            Pair v = pq.poll();
            if (visited.contains(v.node)) {
                continue;
            }

            visited.add(v.node);

            List<Pair> adj = adjLists.get(v.node);
            for (Pair u : adj) {
                if (!visited.contains(u.node)) {// && dist[u.node] < dist[v.node] * u.dist) {
                    dist[u.node] = Math.max(dist[u.node], dist[v.node] * u.dist);
                    pq.add(new Pair(u.node, dist[u.node]));
                }
            }
        }

        return dist[end_node];
    }


    class Pair {
        int node;
        double dist;

        Pair(int node, double dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
