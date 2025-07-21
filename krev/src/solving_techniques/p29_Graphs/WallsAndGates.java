package solving_techniques.p29_Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 286. Walls and Gates (medium)
 * https://leetcode.com/problems/walls-and-gates/
 * <p>
 * #Company (21.07.2025): 0 - 3 months DoorDash 19 Meta 3 Amazon 3 0 - 6 months Google 3 Uber 2 Visa 2 6 months ago TikTok 8 Microsoft 2 Salesforce 2 Shopify 2
 * <p>
 * You are given an m x n grid rooms initialized with these three possible values.
 * <p>
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * <p>
 * Example 1:
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * <p>
 * Example 2:
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 * <p>
 * Constraints:
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 2^31 - 1.
 */
public class WallsAndGates {
    /**
     * KREVSKY SOLUTION:
     * got TLE
     * idea: use BFS for each tower
     * time to solve ~ < 20 mins
     * <p>
     * time ~ O(n^2 * m^2)
     * space ~ O(n*m)
     * <p>
     * 1 attempt
     */
    private static final List<int[]> dirs = Arrays.asList(new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 0}, new int[]{-1, 0});

    public void wallsAndGates(int[][] rooms) {
        //use BFS approach from each gate
        int n = rooms.length;
        if (n == 0) return;
        int m = rooms[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, n, m, i, j);
                }
            }
        }
    }

    private void bfs(int[][] rooms, int n, int m, int initR, int initC) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{initR, initC, 0});

        while (!q.isEmpty()) {
            int[] el = q.poll();
            rooms[el[0]][el[1]] = el[2];

            for (int[] dir : dirs) {
                int newR = el[0] + dir[0];
                int newC = el[1] + dir[1];
                int newDist = el[2] + 1;

                if (0 <= newR && newR < n && 0 <= newC && newC < m && rooms[newR][newC] != -1 && rooms[newR][newC] > newDist) {
                    q.add(new int[]{newR, newC, newDist});
                }
            }
        }
    }

    /**
     * Official solution
     * idea: use MULTI-SOURCE BFS from all gates
     * <p>
     * time ~ O(n*m)
     * space ~ O(n*m)
     * <p>
     * BEATS ~ 22%
     */
    public void wallsAndGatesOptimized(int[][] rooms) {
        //use BFS approach from each gate
        int n = rooms.length;
        if (n == 0) return;
        int m = rooms[0].length;

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] el = q.poll();

            for (int[] dir : dirs) {
                int newR = el[0] + dir[0];
                int newC = el[1] + dir[1];

                //if new cell is empty - fill it
                if (0 <= newR && newR < n && 0 <= newC && newC < m && rooms[newR][newC] == Integer.MAX_VALUE) {
                    rooms[newR][newC] = rooms[el[0]][el[1]] + 1;
                    q.add(new int[]{newR, newC});
                }
            }
        }
    }

}
