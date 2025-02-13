package solving_techniques.p29_Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1730. Shortest Path to Get Food (medium) (locked)
 * https://leetcode.com/problems/shortest-path-to-get-food
 * <p>
 * #Company (13.02.2025): 6 months ago Bloomberg 3 DoorDash 3 Google 2 Meta 2 Amazon 2
 * <p>
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 * <p>
 * You are given an m x n character matrix, grid, of these different types of cells:
 * <p>
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 * <p>
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 * <p>
 * Example 1:
 * Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
 * Output: 3
 * Explanation: It takes 3 steps to reach the food.
 * <p>
 * Example 2:
 * Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
 * Output: -1
 * Explanation: It is not possible to reach the food.
 * <p>
 * Example 3:
 * Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
 * Output: 6
 * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[row][col] is '*', 'X', 'O', or '#'.
 * The grid contains exactly one '*'.
 */
public class ShortestPathToGetFood {
    /**
     * KREVSKY SOLUTION
     * idea: use DFS
     * time to solve ~ 14 mins
     * <p>
     * time ~ O(n*m)
     * space ~ O(n*m)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 10%
     */
    public int getFood(char[][] grid) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //to find * => time ~ O(n*m)
        int m = grid.length;
        int n = grid[0].length;
        int[] startPoint = findStartPoint(grid);

        //BFS traversal - need to visit n*m cells in the worst case => time ~ O(n*m), space ~ O(n*m)
        Queue<int[]> q = new LinkedList<>();    //[0] - y, [1] - x, [2] - distance
        q.add(new int[]{startPoint[0], startPoint[1], 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(n * startPoint[0] + startPoint[1]);   //like id of the cell

        while (!q.isEmpty()) {
            int[] el = q.poll();
            int row = el[0];
            int col = el[1];
            int dist = el[2];
            if (grid[row][col] == '#') return dist;

            for (int[] dir : dirs) {
                int newR = row + dir[0];
                int newC = col + dir[1];
                int newIdx = newR * n + newC;

                if (0 <= newR && newR < m && 0 <= newC && newC < n && !visited.contains(newIdx) && grid[newR][newC] != 'X') {
                    q.add(new int[]{newR, newC, dist + 1});
                    visited.add(newIdx);
                }
            }
        }

        return -1;
    }

    private int[] findStartPoint(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') return new int[]{i, j};
            }
        }

        return new int[]{-1, -1};    //should not happen
    }
}
