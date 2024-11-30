package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 3243. Shortest Distance After Road Addition Queries I (medium)
 * https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i
 * <p>
 * #Company:
 * <p>
 * You are given an integer n and a 2D integer array queries.
 * <p>
 * There are n cities numbered from 0 to n - 1.
 * Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.
 * <p>
 * queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi.
 * After each query, you need to find the length of the shortest path from city 0 to city n - 1.
 * <p>
 * Return an array answer where for each i in the range [0, queries.length - 1],
 * answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
 * <p>
 * Example 1:
 * Input: n = 5, queries = [[2,4],[0,2],[0,4]]
 * Output: [3,2,1]
 * Explanation:
 * After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
 * After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
 * After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.
 * <p>
 * Example 2:
 * Input: n = 4, queries = [[0,3],[0,2]]
 * Output: [1,1]
 * Explanation:
 * After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
 * After the addition of the road from 0 to 2, the length of the shortest path remains 1.
 * <p>
 * Constraints:
 * 3 <= n <= 500
 * 1 <= queries.length <= 500
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * There are no repeated roads among the queries.
 */
public class ShortestDistanceAfterRoadAdditionQueries1 {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 25 mins
     * idea:
     * 1) form adjLists
     * 2) iteratively add new edge from queries to adjLists and traverse graph from 0 to n-1 via bfs
     *
     * time ~ O(n) + O(queries.length * (n + queries.length)) ~ O(queries.length * (n + queries.length)),
     *      since the worst case for bfs is when adjLists has 'n + queries.length' elements => bfs time ~  O(n + queries.length)
     * space ~ O(n + queries.length), since space of bfs ~ O(queries.length)
     *
     * 3 attempts:
     * - forgot to use visited[]
     * - incorrect usage of adjLists.add(Arrays.asList(..)) since asList gives unmodifiable list => replace to adjLists.add(new ArrayList<>(Arrays.asList(
     *
     * BEATS ~ 54%
     */
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<List<Integer>> adjLists = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            adjLists.add(new ArrayList<>(Arrays.asList(i + 1)));
        }
        //just in case we set adjList for the latest city (i = n - 1)
        adjLists.add(new ArrayList<>());

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            adjLists.get(queries[i][0]).add(queries[i][1]);
            result[i] = bfs(n, adjLists);
        }

        return result;
    }

    private int bfs(int end, List<List<Integer>> adjLists) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[end];
        int result = 0;
        q.add(new int[]{0, result});
        visited[0] = true;

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            if (pair[0] == end - 1) {
                return pair[1];
            }

            for (int child : adjLists.get(pair[0])) {
                if (!visited[child]) {
                    q.add(new int[]{child, pair[1] + 1});
                    visited[child] = true;
                }
            }
        }

        return -1;  //this will never happen since  Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.
    }
}
