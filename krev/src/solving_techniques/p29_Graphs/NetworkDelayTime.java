package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 743. Network Delay Time (medium)
 * https://leetcode.com/problems/network-delay-time
 * <p>
 * #Company (22.03.2025): 0 - 3 months Amazon 2 Netflix 2 0 - 6 months Google 3 6 months ago Adobe 3 TikTok 3 Yahoo 3 Bloomberg 2 Apple 2 Akuna Capital 2
 * <p>
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 * <p>
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 * <p>
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 * <p>
 * Example 2:
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 * <p>
 * Example 3:
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n1 = 4;
        int k1 = 2;
//        new NetworkDelayTime().convertAdjListToMatrix(times1, n1);
        new NetworkDelayTime().networkDelayTime3(times1, n1, k1);


        int[][] times2 = {{1, 2, 1}};
        int n2 = 2;
        int k2 = 2;
        new NetworkDelayTime().networkDelayTime2(times2, n2, k2);
        new NetworkDelayTime().networkDelayTime3(times2, n2, k2);
    }

    /**
     * KREVSKY SOLUTION (the BEST!) (22.03.2025) #3: Dijkstra based on Priority Queue
     * time to solve ~ 30 mins
     * <p>
     * time ~ O(N+ElogN)
     * to find min element in dist array takes ~ O(N)
     * to build Dijkstra algorithm takes ~ O(E*logN), because
     * a) max amount vertices that can be added to PQ - is E => push/pop operations take ~ O(logE). Since E ~ N^2 => O(logN^2) ~ O(logN)
     * b) since we will traverse through all adjMap => we will handle all edges once
     * that's why Dijkstra takes ~ O(E*logN)
     * space ~ O(N+E),
     * to store dist array ~ O(N)
     * to build adjMap takes O(E), Priority Queue ~ O(N*(N-1 )), because each node can be added to queue N-1 times.
     * BUT since N*N ~ E => space ~ O(N + N*(N-1)) ~ O(N + E)
     * <p>
     * BEATS ~ 86%
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;    //stub for non existing node

        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            adjMap.put(i, new ArrayList<>());
        }

        for (int[] t : times) {
            adjMap.get(t[0]).add(new int[]{t[1], t[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((p, q) -> p[1] - q[1]);    //min heap. [0] - number of node, [1] - distance to this node
        //update queue and dist
        pq.add(new int[]{k, 0});
        dist[k] = 0;

        while (!pq.isEmpty()) {
            int[] el = pq.poll();
            int node = el[0];
            int d = el[1];
            if (dist[node] < d) {
                //do nothing with this non-optimal entry of the queue
                continue;
            }

            List<int[]> adjList = adjMap.get(node);
            for (int[] neighbour : adjList) {
                int newD = neighbour[1] + d;
                if (dist[neighbour[0]] > newD) {
                    //update queue and dist
                    pq.add(new int[]{neighbour[0], newD});
                    dist[neighbour[0]] = newD;
                }
            }
        }

        //find max element in dist array:
        int result = -1;
        for (int d : dist) {
            if (d > result) result = d;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * KREVSKY SOLUTION #1: use adjacent list (times)
     * <p>
     * we have
     * 1) dist[]
     * 2) visited[]
     * <p>
     * time to solve ~ 2h
     * <p>
     * time ~ O(times.length* n^2)
     * space ~ O(n)
     * <p>
     * a lot of attempts
     * <p>
     * BEATS = 5%
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k - 1] = 0;

        for (int i = 0; i < n; i++) {
            int minIndex = minIndex(dist, visited);
            //it means that there are no not visited nodes that are connected to k-th node anyhow
            //=> we can't reach some of the nodes => return -1
            if (minIndex == -1) return -1;

            visited[minIndex] = true;

            for (int v = 0; v < n; v++) {
                if (visited[v] == false) {
//                        && dist[minIndex] != Integer.MAX_VALUE    //NOTE: this validation is not necessary since if it happens => minIndex will be -1 => we can't reach this line of code in this case
                    //find times element that connects minIndex and v vertices
                    int[] edge = getTimesElement(minIndex + 1, v + 1, times);
                    if (edge != null && dist[v] > dist[minIndex] + edge[2]) {
                        dist[v] = dist[minIndex] + edge[2];
                    }
                }
            }
        }

        int result = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            result = Math.max(result, dist[i]);
        }
        return result;
    }

    /**
     * KREVSKY SOLUTION #2: use adjacent matrix
     * <p>
     * we have
     * 1) dist[]
     * 2) visited[]
     * <p>
     * time ~ O(n^2) - since for-for costs n^2 and convertAdjListToMatrix costs n^2
     * space ~ O(n^2) - because of adjMatrix
     * <p>
     * BEATS = 98%
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        int[][] adjMatrix = convertAdjListToMatrix(times, n);
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k - 1] = 0;

        for (int i = 0; i < n; i++) {
            int minIndex = minIndex(dist, visited);
            //it means that there are no not visited nodes that are connected to k-th node anyhow
            //=> we can't reach some of the nodes => return -1
            if (minIndex == -1) return -1;

            visited[minIndex] = true;

            for (int v = 0; v < n; v++) {
                if (visited[v] == false
//                        && dist[minIndex] != Integer.MAX_VALUE    //NOTE: this validation is not necessary since if it happens => minIndex will be -1 => we can't reach this line of code in this case
                        && adjMatrix[minIndex][v] != Integer.MAX_VALUE  //NOTE: it is better to set MAX_VALUE rather than 0! otherwise it can lead to errors
                        && dist[v] > dist[minIndex] + adjMatrix[minIndex][v]) {
                    dist[v] = dist[minIndex] + adjMatrix[minIndex][v];
                }
            }
        }

        int result = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            result = Math.max(result, dist[i]);
        }
        return result;
    }

    private int minIndex(int[] dist, boolean[] visited) {
        int minIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < dist.length; i++) {
            if (visited[i] == false && dist[i] < minDistance) {
                minIndex = i;
                minDistance = dist[i];
            }
        }

        return minIndex;
    }

    private int[] getTimesElement(int u, int v, int[][] times) {
        for (int[] t : times) {
            if (t[0] == u && t[1] == v) return t;
        }
        return null;
    }

    //time ~ O(n^2)
    private int[][] convertAdjListToMatrix(int[][] times, int n) {
        int[][] result = new int[n][n];
        //NOTE: it is better to set MAX_VALUE rather than 0! otherwise it can lead to errors
        for (int[] r : result) Arrays.fill(r, Integer.MAX_VALUE);

        for (int[] t : times) {
            result[t[0] - 1][t[1] - 1] = t[2];
        }
        return result;
    }

    /**
     * KREVSKY SOLUTION #2: optimized (use Priority Queue)
     * similar to https://leetcode.com/problems/network-delay-time/
     * <p>
     * we have
     * 1) dist[]
     * 2) PQ
     * 3) settle (set of processed vertices)
     * <p>
     * Time complexity: O(V+E(Log(V)))
     * V vertices in the graph each need to be traversed. Logn is the insertion time for each vertex in the heap (priority queue) here n is V;
     * if a vertex is added twice in priority queue the maximum number of time it can be added is the number of edges. hence ElogV.
     * <p>
     * BEATS = 41%
     */
    public int networkDelayTime3(int[][] times, int n, int k) {
        //each entry: vertex -> list of adjacent vertices and distance to them
        Map<Integer, List<int[]>> adjData = new HashMap<>(n);
        for (int[] t : times) {
            if (adjData.containsKey(t[0])) {
                List<int[]> tempList = adjData.get(t[0]);
                tempList.add(new int[]{t[1], t[2]});
            } else {
                List<int[]> tempList = new ArrayList<>();
                tempList.add(new int[]{t[1], t[2]});
                adjData.put(t[0], tempList);
            }
        }

        //apply usual Dijkstra algorithm using Priority Queue
        int[] dist = new int[n];
        Set<Integer> settle = new HashSet<>();
        //min heap, stores "length of path to vertex -> vertex" elements
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k - 1] = 0;
        pq.add(new int[]{0, k});

        while (settle.size() != n) {
            if (pq.isEmpty()) {
                return -1;
            }

            int[] tempMin = pq.poll();
            int v = tempMin[1];
            if (settle.contains(v)) {
                continue;
            }
            settle.add(v);
            //update neighbours. each pair = vertex and distance v -> pair[0]
            if (adjData.containsKey(v)) {
                for (int[] pair : adjData.get(v)) {
                    int u = pair[0];
                    int edge = pair[1];
                    if (!settle.contains(u)) {
                        dist[u - 1] = Math.min(dist[u - 1], dist[v - 1] + edge);
                        pq.add(new int[]{dist[u - 1], u});
                    }
                }
            }
        }

        int result = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            result = Math.max(result, dist[i]);
        }
        return result;
    }

    /**
     * Official leetcode solution (not the best)
     * Simple (not-optimal) BFS solution:
     * use Queue = Linked List
     * <p>
     * <p>
     * Here N is the number of nodes and E is the number of total edges in the given network.
     * <p>
     * Time complexity: O(N⋅E)
     * <p>
     * Each of the N nodes can be added to the queue for all the edges connected to it, hence in a complete graph,
     * the total number of operations would be O(NE). Also, finding the minimum time required in signalReceivedAt takes O(N).
     * <p>
     * Space complexity: O(N⋅E)
     * <p>
     * Building the adjacency list will take O(E) space and the queue for BFS will use O(N⋅E)
     * space as there can be this much number of nodes in the queue.
     */
    // Adjacency list
    Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

    class Pair<N, M> {
        N key;
        M value;

        Pair(N key, M value) {
            this.key = key;
            this.value = value;
        }

        N getKey() {
            return key;
        }

        M getValue() {
            return value;
        }
    }

    private void BFS(int[] signalReceivedAt, int sourceNode) {
        Queue<Integer> q = new LinkedList<>();
        q.add(sourceNode);

        // Time for starting node is 0
        signalReceivedAt[sourceNode] = 0;

        while (!q.isEmpty()) {
            int currNode = q.remove();

            if (!adj.containsKey(currNode)) {
                continue;
            }

            // Broadcast the signal to adjacent nodes
            for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                int time = edge.getKey();
                int neighborNode = edge.getValue();

                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time :
                // time when signal reaches neighborNode
                int arrivalTime = signalReceivedAt[currNode] + time;
                if (signalReceivedAt[neighborNode] > arrivalTime) {
                    signalReceivedAt[neighborNode] = arrivalTime;
                    q.add(neighborNode);
                }
            }
        }
    }

    public int networkDelayTimeBFS(int[][] times, int n, int k) {
        // Build the adjacency list
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];

            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new Pair(travelTime, dest));
        }

        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

        BFS(signalReceivedAt, k);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}