package solving_techniques.p29_Graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/651a85ec58fd4e18cd6e4fe3
 * OR
 * 1557. Minimum Number of Vertices to Reach All Nodes
 * https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes
 *
 * Given a DIRECTED (!) acyclic graph, with n vertices numbered from 0 to n-1,
 * and an array edges where edges[i] = [fromi, toi] represents
 * a directed edge from node fromi to node toi.
 *
 * Find the smallest set of vertices from which all nodes in the graph are reachable.
 * It's guaranteed that a unique solution exists.
 *
 * Notice that you can return the vertices in any order.
 *
 * Example 1:
 * Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
 * Output: [0,3]
 * Explanation: It's not possible to reach all the nodes from a single vertex.
 * From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].
 *
 *
 */
public class MinimumNumberOfVerticesToReachAllNodes {
    /**
     * NOT SOLVED by myself
     * idea: We only have to count the number of nodes with zero incoming edges.
     *
     * time to implement ~ 5 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 2 attempt:
     * - syntax error (wrote edge[1] instead of edge.get(1))
     *
     * BEATS = 98%
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        //idea: We only have to count the number of nodes with zero incoming edges.
        int[] counters = new int[n];    //0..0
        for (List<Integer> edge : edges) {
            counters[edge.get(1)]++;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] == 0) result.add(i);
        }

        return result;
    }
}
