package solving_techniques.p22_Island_MatrixTraversal;

import java.util.*;

/**
 * 827. Making A Large Island (hard)
 * https://leetcode.com/problems/making-a-large-island
 *
 * #Company: Amazon Google Uber
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 * Example 1:
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 *
 * Example 2:
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 *
 * Example 3:
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] is either 0 or 1.
 */
public class MakingLargeIsland {
    public static void main(String[] args) {
        int[][] grid = {{1,1},{1,0}};
        MakingLargeIsland obj = new MakingLargeIsland();

        System.out.println(obj.largestIsland(grid));
    }

    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * NOT fully solved by myself, but reach of some ideas:
     * info:
     * https://www.youtube.com/watch?v=FPTvylRPyFQ&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=58
     * idea:
     * 1) traverse the grid and calculate the sizes of existing islands
     * 2) set id to each island (store it in visited[][], since we can't change the initial grid)
     * 3) keep map: island id -> island size
     * 4) traverse grid again and for each cell = 0 add size s of unique island that surrounds this 0-cell
     *
     * time to implement ~ 45 mins
     * time ~ O(n*n)
     * space ~ O(n*n)
     *
     * a lot of attempts
     *
     * BEATS ~ 43%
     */
    public int largestIsland(int[][] grid) {
        int islandNumber = -1;  //let's use negative numbers to avoid collision with 0 and 1 values
        int n = grid.length;
        int[][] visited = new int[n][n];    //stores 0 - unsivited cell, 'some number' which is island id
        Map<Integer, Integer> islandToSize = new HashMap<>();

        int maxSize = -1;
        //1. form mapping "island -> its size"
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    int tempSize = getSize(grid, visited, i, j, islandNumber);
                    maxSize = Math.max(tempSize, maxSize);
                    islandToSize.put(islandNumber, tempSize);
                    islandNumber--;
                }
            }
        }

        //2. for each cell that contains 0 we check 4 neighbour cells
        // and if it is part of som island, then we just add its size, taken from 'islandToSize' map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int tempSize = 1;
                    Set<Integer> addedIslands = new HashSet<>();
                    for (int[] dir : dirs) {
                        int newI = i + dir[0];
                        int newJ = j + dir[1];
                        if (newI >= 0 && newI < n && newJ >= 0 && newJ < n) {
                            int islandId = visited[newI][newJ];
                            if (!addedIslands.contains(islandId)) {
                                tempSize += islandToSize.getOrDefault(islandId, 0);
                                addedIslands.add(islandId);
                            }
                        }
                    }

                    maxSize = Math.max(tempSize, maxSize);
                }
            }
        }

        return maxSize;
    }

    //use BFS
    private int getSize(int[][] grid, int[][] visited, int i, int j, int islandNumber) {
        int size = 1;
        int n = grid.length;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = islandNumber;
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int cellI = cell[0];
            int cellJ = cell[1];

            for (int[] dir : dirs) {
                int newI = cellI + dir[0];
                int newJ = cellJ + dir[1];
                if (newI >= 0 && newI < n && newJ >= 0 && newJ < n && visited[newI][newJ] == 0 && grid[newI][newJ] == 1) {
                    q.add(new int[]{newI, newJ});
                    visited[newI][newJ] = islandNumber;
                    size++;
                }
            }
        }

        return size;
    }
}
