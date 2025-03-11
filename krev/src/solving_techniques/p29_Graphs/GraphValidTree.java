package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 261. Graph Valid Tree (medium) (locked)
 * https://leetcode.com/problems/graph-valid-tree
 * <p>
 * #Company (11.03.2025): 0 - 6 months Google 3 Amazon 3 TikTok 2 6 months ago LinkedIn 5 Meta 4 Snowflake 4 Microsoft 3 Salesforce 3 Zenefits 2
 * <p>
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 * <p>
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 * <p>
 * Example 1:
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * <p>
 * Example 2:
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no self-loops or repeated edges.
 */
public class GraphValidTree {
    /**
     * NOT SOLVED by myself!
     * idea:
     * 1) visited.size() = n
     * 2) to find cycle, keep mapping curNode -> parent node! (did not reach this idea by myself)
     * <p>
     * time to solve ~ 30 mins
     * <p>
     * time ~ Time Complexity : O(N + E), but in the worst case E = N - 1= > time ~ O(2*N) ~ O(N)
     * Space Complexity : O(N + E) ~ O(N)
     * <p>
     * 4 attempts:
     * - did not reach the idea of cur -> parent map
     * - forgot to put neighbour -> node to this map
     * - used adjMap instead of List<List<Integer>> => faced problems when n >= 1 and edges is empty
     * <p>
     * BEATS ~ 11%
     */
    public boolean validTree(int n, int[][] edges) {
        //it is better to use List of Lists/Sets, but not map<Integer, Set<Integer>>!
        //because if edges are empty, but n > 0, we will get empty adjMap
        //more convenient way is to work with n empty lists
        List<Set<Integer>> adjLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjLists.add(i, new HashSet<>());
        }

        for (int[] e : edges) {
            adjLists.get(e[0]).add(e[1]);
            adjLists.get(e[1]).add(e[0]);
        }

        //we have 2 options:
        //1) to remove edge B -> A once we used A -> B
        //2) keep Map<curNode, parentNode> to avoid returning to parent
        // to distinct parent node (which is visited and it is ok) and the other neighbour nodes (if they are visited => we have loop!)
        //let's use p.2
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> curToParent = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int startNode = 0;
        q.add(startNode);
        visited.add(startNode);
        curToParent.put(startNode, -1); //dummy parent = -1

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbour : adjLists.get(node)) {
                if (neighbour == curToParent.get(node)) continue;

                if (visited.contains(neighbour)) return false;

                //otherwise - include neighbour to graph (i.e. makr as visited)
                q.add(neighbour);
                visited.add(neighbour);
                //and set parent
                curToParent.put(neighbour, node);
            }
        }

        return visited.size() == n;
    }
}
