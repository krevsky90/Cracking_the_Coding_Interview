package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 399. Evaluate Division (medium)
 * https://leetcode.com/problems/evaluate-division
 *
 * #Company: 0 - 3 months Uber 4 Amazon 3 Bloomberg 3 Rippling 3 0 - 6 months Stripe 6 Google 5 MakeMyTrip 2 6 months ago BlackRock 24 GE Healthcare 6 Meta 5 TikTok 5 Citadel 4 Microsoft 3 Zeta 3 Snowflake 3 Snap 3 Goldman Sachs 2
 *
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
 * where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero
 *      and that there is no contradiction.
 *
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * note: x is undefined => -1.0
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Constraints:
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        EvaluateDivision obj = new EvaluateDivision();
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        double[] res = obj.calcEquation(equations, values, queries);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) strings = vertices
     * 2) edges = pairs like a/b and values[i] is weight of the edge. NOTE: graph is directional => b/a = 1/values[i]
     * 3) keep list of letters that are part of equations
     * 4) for each query (which is considered as a pair of start and end positions) we
     * - check if current 'start' vertex is not in the list of letters => all operations with this letter are undefined => return -1
     * - else - use dfs to find a way from start and end. if exists - we calculate the weight of all path
     * 5) keep visited set of vertices to avoid infinite loops, since graph is bidirectional
     *
     * time to think + implement + debug ~ 10 + 30 + 20 ~ 60 mins
     *
     * N = equations.size(), M = queries.size()
     * time ~ O(N) + O(M*N) ~ O(M*N) - to build graph + traverse all vertices for each of M query
     * space ~ O(N) + O(N) +  O(M) ~ O(N + M) - to store graph + visited + result
     *
     * 4 attempts:
     * - forgot to introduce 'visited' set
     * - did not add 'visited.remove(start);' in case if (dfsResult != -1)
     * - compared start and end incorrectly: as start == end, rather than start.equals(end)
     *
     * BEATS ~ 98%
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> vertices = new HashSet<>();
        Map<String, List<Pair>> adjMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            vertices.add(eq.get(0));
            vertices.add(eq.get(1));

            adjMap.putIfAbsent(eq.get(0), new ArrayList<>());
            adjMap.get(eq.get(0)).add(new Pair(eq.get(1), values[i]));

            adjMap.putIfAbsent(eq.get(1), new ArrayList<>());
            adjMap.get(eq.get(1)).add(new Pair(eq.get(0), 1 / values[i]));
        }

        double[] result = new double[queries.size()];
        for (int j = 0; j < queries.size(); j++) {
            String start = queries.get(j).get(0);
            String end = queries.get(j).get(1);
            if (!vertices.contains(start) || !vertices.contains(end)) {
                result[j] = -1;
            } else {
                Set<String> visited = new HashSet<>();
                result[j] = dfs(adjMap, start, end, visited);
            }
        }

        return result;
    }

    // adjMap:
    // a/b = a/c * c/d * d/b
    // a b c
    // a -> (b, 2)
    // b -> (a, 0.5), (c, 3)
    // c -> (b, 1/3)

    private double dfs(Map<String, List<Pair>> adjMap, String start, String end, Set<String> visited) {
        if (start.equals(end)) return 1;

        if (visited.contains(start)) return -1;

        visited.add(start);

        List<Pair> adjList = adjMap.getOrDefault(start, new ArrayList<>());
        for (Pair p : adjList) {
            double dfsResult = dfs(adjMap, p.v, end, visited);
            if (dfsResult != -1) {
                visited.remove(start);
                return dfsResult * p.dist;
            }
        }

        visited.remove(start);

        return -1;
    }

    class Pair {
        String v;
        double dist;

        Pair(String v, double dist) {
            this.v = v;
            this.dist = dist;
        }
    }
}
