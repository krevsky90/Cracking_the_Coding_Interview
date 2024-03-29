package solving_techniques.p24_UnionFind;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/65438cf5d54679234a4370cd
 * OR
 * 684. Redundant Connection
 * https://leetcode.com/problems/redundant-connection
 * <p>
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
 * The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 * The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates
 * that there is an edge between nodes ai and bi in the graph.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the input.
 * <p>
 * Example 1:
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * <p>
 * Example 2:
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 * <p>
 * Constraints:
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * There are no repeated edges.
 * The given graph is connected.
 */
public class RedundantConnection {
    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {1, 3}};
        int[] result = new RedundantConnection().findRedundantConnection(edges);
        System.out.println("{" + result[0] + ", " + result[1] + "}");
    }

    /**
     * NOT SOLVED by myself
     * idea:
     * https://www.youtube.com/watch?v=FXWRE67PLL0
     * when we try to union x and y, but they have the same representative element => they are already connected
     * => if we connect them again - we will create loop by the edge that we are trying to add
     *
     * time to solve ~ 21 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 2 attempts:
     * - typo 'parent[xrank]' instead of parent[xrep] in union method
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];    //0..0
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            if (!unionByRank(edge[0], edge[1], parent, rank)) {
                return edge;
            }
        }

        //it means there are no circles
        return new int[0];  //stub
    }

    public int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    public boolean unionByRank(int x, int y, int[] parent, int[] rank) {
        int xrep = find(x, parent);
        int yrep = find(y, parent);

        if (xrep == yrep) {
            //idea:
            //it means that x and y are ALREADY in the same group => if we connect them now, we will create loop!
            //then the edge[x, y] causes loop => return [x, y] edge as answer of the problem
            return false;
        }

        //union
        int xrank = rank[xrep];
        int yrank = rank[yrep];
        if (xrank < yrank) {
            parent[xrep] = yrep;
        } else if (xrank > yrank) {
            parent[yrep] = xrep;
        } else {
            parent[xrep] = yrep;
            rank[yrep]++;
        }

        return true;
    }
}
