package solving_techniques.p11_BinarySearch;

import java.util.HashSet;
import java.util.Set;

/**
 * 778. Swim in Rising Water (hard)
 * https://leetcode.com/problems/swim-in-rising-water
 *
 * #Company (06.06.2025): 0 - 3 months Meta 3 Google 2 WeRide 2 6 months ago Uber 8 DoorDash 5 Amazon 4 Apple 3 PhonePe 3 Microsoft 2 Bloomberg 2 DE Shaw 2
 *
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 * Example 1:
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 */
public class SwimInRisingWater {
    /**
     * idea: find min t by binary search
     * using isPossible method that shows we can traverse (using DFS) the grid to bottom right corner
     *
     * time ~ O(n*n*logn), where isPossible ~ O(n*n)
     * space ~ O(n*n)
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int low = 0;
        int high = n * n;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (!isPossible(grid, mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean isPossible(int[][] grid, int t) {
        int n = grid.length;
        //use DFS
        Set<Integer> visited = new HashSet<>();
        dfs(grid, 0, 0, t, visited);

        return visited.contains(n * (n - 1) + (n - 1));
    }

    private void dfs(int[][] grid, int r, int c, int t, Set<Integer> visited) {
        int n = grid.length;
        if (grid[r][c] > t)
            return; //need to place it here, rather than below (if-condition), since we need to check (0,0) element before moving forward

        visited.add(r * n + c);

        for (int[] dir : dirs) {
            int newR = r + dir[0];
            int newC = c + dir[1];
            if (0 <= newR && newR < n && 0 <= newC && newC < n && !visited.contains(newR * n + newC)) {
                dfs(grid, newR, newC, t, visited);
            }
        }
    }
}
