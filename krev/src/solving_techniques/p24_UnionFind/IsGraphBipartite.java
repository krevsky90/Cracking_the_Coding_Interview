package solving_techniques.p24_UnionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6543978b6d67fa4141090d02
 * OR
 * 785. Is Graph Bipartite?
 * https://leetcode.com/problems/is-graph-bipartite/
 * <p>
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
 * <p>
 * The graph has the following properties:
 * - There are no self-edges (graph[u] does not contain u).
 * - There are no parallel edges (graph[u] does not contain duplicate values).
 * - If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * - The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * <p>
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B
 * such that every edge in the graph connects a node in set A and a node in set B.
 * <p>
 * Return true if and only if it is bipartite.
 * <p>
 * Example 1:
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets
 * such that every edge connects a node in one and a node in the other.
 * <p>
 * Example 2:
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 * <p>
 * Constraints:
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] does not contain u.
 * All the values of graph[u] are unique.
 * If graph[u] contains v, then graph[v] contains u.
 */
public class IsGraphBipartite {

    /**
     * SOLUTION #1: NOT SOLVED by myself
     * info:
     * https://leetcode.com/problems/is-graph-bipartite/solutions/1991184/java-clean-union-find/
     * idea: Union Find
     * for each list of nodes from graph[v] we try to union the nodes, if the list from graph[v] has different representative than v
     * if vrep == irep => return false
     *
     * time to implement ~ 15 mins
     *
     * 1 attempt
     *
     * BEATS = 26%
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            int[] nodes = graph[i];
            for (int j = 0; j < nodes.length; j++) {
                if (uf.find(i) == uf.find(nodes[j])) return false;

                //join nodes
                uf.union(nodes[0], nodes[j]);
            }
        }

        return true;
    }

    class UnionFind {
        private final int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }


        //use not optimized version (without size or rank)
        public void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 != root2) {
                parent[root1] = root2;
            }
        }
    }

    /**
     * SOLUTION #2:
     * info:
     * comment from https://leetcode.com/problems/is-graph-bipartite/solutions/3540034/easy-java-solution-with-explanation-and-intuition/
     * idea #1: color each node: 0 - not visited, 1 - visited and moved to set1, 2 - visited and moved to set2
     * idea #2: if i-th node is not visited, apply BFS to it to color their neighbours
     * while BFS: if neighbour has 0 color => color it oppositely to the color of i-th node and put to the queue
     * if neighbour has the same color as i-th node => return false
     * else - skip neighbour
     * idea #3: apply the idea #2 to all nodes since the graph may have unconnected parts. and some of them are bipartite, but some not => common answer will be 'False'
     * <p>
     * time to solve ~ 65 mins
     * <p>
     * a log of attempts
     */
    public boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];   //0 - not coloured, 1 - color1, 2 - color2

        //since graph can have nodes a and b that can not be connected,
        //we need to launch BFS for each node
        //otherwise we might have situation like [[], blabla],
        // where [] => 0 is separate node => BFS returns True for it
        //but is blabla = [[1,2,3],[0,2],[0,1,3],[0,2]], then is will return False
        //and final answer should be False

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                Queue<Integer> q = new LinkedList<>();  //queue of nodes (vertices)
                q.add(i);
                //color 0-th node by color1, for example (i.e. move 0 to some of 2 sets)
                color[i] = 1;

                //BFS
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int v : graph[node]) {
                        //if v is not coloured => it is NOT visited. Then color it by the opposite color that node has
                        //by coloring we mark v as visited + move it to particular group!
                        if (color[v] == 0) {
                            color[v] = color[node] == 1 ? 2 : 1;
                            q.add(v);
                        } else {
                            //if node and v has the same color => return false
                            if (color[node] == color[v]) return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}
