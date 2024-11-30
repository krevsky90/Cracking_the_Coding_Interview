package solving_techniques.p29_Graphs;

/**
 * 2924. Find Champion II (medium)
 * https://leetcode.com/problems/find-champion-ii
 * <p>
 * #Company:
 * <p>
 * There are n teams numbered from 0 to n - 1 in a tournament; each team is also a node in a DAG.
 * <p>
 * You are given the integer n and a 0-indexed 2D integer array edges of length m representing the DAG,
 * where edges[i] = [ui, vi] indicates that there is a directed edge from team ui to team vi in the graph.
 * <p>
 * A directed edge from a to b in the graph means that team a is stronger than team b and team b is weaker than team a.
 * <p>
 * Team a will be the champion of the tournament if there is no team b that is stronger than team a.
 * <p>
 * Return the team that will be the champion of the tournament if there is a unique champion, otherwise, return -1.
 * <p>
 * Notes
 * <p>
 * A cycle is a series of nodes a1, a2, ..., an, an+1 such that node a1 is the same node as node an+1,
 * the nodes a1, a2, ..., an are distinct, and there is a directed edge from the node ai to node ai+1 for every i in the range [1, n].
 * A DAG is a directed graph that does not have any cycle.
 * <p>
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2]]
 * Output: 0
 * Explanation: Team 1 is weaker than team 0. Team 2 is weaker than team 1. So the champion is team 0.
 * <p>
 * Example 2:
 * Input: n = 4, edges = [[0,2],[1,3],[1,2]]
 * Output: -1
 * Explanation: Team 2 is weaker than team 0 and team 1. Team 3 is weaker than team 1.
 * But team 1 and team 0 are not weaker than any other teams. So the answer is -1.
 * <p>
 * <p>
 * Constraints:
 * 1 <= n <= 100
 * m == edges.length
 * 0 <= m <= n * (n - 1) / 2
 * edges[i].length == 2
 * 0 <= edge[i][j] <= n - 1
 * edges[i][0] != edges[i][1]
 * The input is generated such that if team a is stronger than team b, team b is not stronger than team a.
 * The input is generated such that if team a is stronger than team b and team b is stronger than team c, then team a is stronger than team c.
 */
public class FindChampionII {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 8-10 mins
     *
     * idea:
     * since graph does not contain cycles and there are no disconnected nodes (NOT SURE where it is mentioned, honestly!)
     * then there might be 2 cases: 1 champion or >1 champions
     * in other words: we can count the amount of incoming edges for each node.
     * if some node has 0 incoming edges => it is champion.
     * if >1 champions => return -1
     *
     * time ~ O(n + E)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS ~ 100%
     */
    public int findChampion(int n, int[][] edges) {
        int[] incoming = new int[n];
        for (int[] edge : edges) {
            incoming[edge[1]]++;
        }

        int result = -1;
        for (int i = 0; i < incoming.length; i++) {
            if (incoming[i] == 0) {
                if (result == -1) {
                    result = i;
                } else {
                    return -1;
                }
            }
        }

        return result;
    }
}

// Input: n = 4, edges = [[0,2],[1,3],[1,2]]
// incoming = 0 0 2 1
// result = 0
