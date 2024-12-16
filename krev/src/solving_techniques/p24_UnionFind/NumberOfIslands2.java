package solving_techniques.p24_UnionFind;

import java.util.*;

/**
 * 305. Number of Islands II (hard)
 * https://leetcode.com/problems/number-of-islands-ii
 * <p>
 * #Company: 0 - 3 months Uber 8 0 - 6 months Google 3 TikTok 3 6 months ago Snap 4
 * <p>
 * You are given an empty 2D binary grid grid of size m x n.
 * The grid represents a map where 0's represent water and 1's represent land.
 * Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 * <p>
 * We may perform an add land operation which turns the water at position into a land.
 * You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 * <p>
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid is filled with water.
 * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 * <p>
 * Example 2:
 * Input: m = 1, n = 1, positions = [[0,0]]
 * Output: [1]
 * <p>
 * Constraints:
 * 1 <= m, n, positions.length <= 10^4
 * 1 <= m * n <= 10^4
 * positions[i].length == 2
 * 0 <= ri < m
 * 0 <= ci < n
 * <p>
 * Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?
 */
public class NumberOfIslands2 {
    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    /**
     * NOT SOLVED by myself
     * info: https://www.youtube.com/watch?v=ffq9rP_2_XE
     * idea:
     * 1) use Union Find, where we find representative cell for each island cell
     * 2) cell will be uniquely identified as row * m + column
     * 3) if current cell and neighbour cell have different representatives (diff islands)
     *      => join them and decrease the number of islands
     *
     * time to solve ~ 2h
     *
     * since we finally add all cells to Map => it will take n*m
     * since we traverse through positions and find and union operations takes O(alpha(N)) ~ O(1) => traversal needs O(positions.length)
     * time ~ O(m*n + positions.length)
     * space ~ O (m*n)
     *
     * a lot of attempts
     *
     * BEATS ~ 18%
     */
    private Map<Integer, Integer> repElements = new HashMap<>();

    private int find(int p) {
        if (!repElements.containsKey(p)) {
            repElements.put(p, p);
        } else {
            int parent = repElements.get(p);
            if (p != parent) {
                int pp = find(parent);
                repElements.put(p, pp); //compression
            }
        }
        return repElements.get(p);
    }

    private void union(int p1, int p2) {
        int parent1 = repElements.get(p1);
        int parent2 = repElements.get(p2);

        if (parent1 != parent2) {
            //join
            repElements.put(parent1, parent2);  //no matter who is key who is value
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        Set<Integer> set = new HashSet<>();

        List<Integer> result = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < positions.length; i++) {
            int pr = positions[i][0];
            int pc = positions[i][1];
            //idea: transform 2D into 1D
            int id = pr * n + pc;
            if (set.contains(id)) {
                //for repeatable position we should return the same amount of islands
                result.add(counter);
                continue;
            }

            set.add(id);
            counter++;

            //check if new cell is neighbour of somee islands
            for (int[] dir : dirs) {
                int newR = pr + dir[0];
                int newC = pc + dir[1];
                int newId = newR * n + newC;
                if (0 <= newR && newR < m && 0 <= newC && newC < n && set.contains(newId)) {
                    //check if p and newP are cell of the same island. if yes - do nothing fpr this newP
                    if (find(id) == find(newId)) {
                        continue;
                    }

                    //otherwise join p and newP and decrement counter of islands
                    union(id, newId);
                    counter--;
                }
            }

            result.add(counter);
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION - DOES NOT WORK!
     * time to solve ~ 27 mins
     */
    public List<Integer> numIslands2doesNotWork(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            int prevNumOfIslands = i == 0 ? 0 : result.get(i - 1);
            int curNumOfIslands = addLand(grid, m, n, positions[i], prevNumOfIslands);
            result.add(curNumOfIslands);
        }

        return result;
    }

    private int addLand(int[][] grid, int m, int n, int[] land, int numOfIslands) {
        int deltaIslands = 1;
        int r = land[0];
        int c = land[1];
        grid[r][c] = 1;

        for (int[] dir : dirs) {
            int newR = r + dir[0];
            int newC = c + dir[1];

            if (0 <= newR && newR < m && 0 <= newC && newC < n && grid[newR][newC] == 1) {
                // NOTE: here is the problem! we (r, c) can have seleval neighbour cells of the same island
                // => we should decrease deltaIslands only for unique neighbout islands
                // So.. approach DOES NOT work! (or we have to mark and remark the cells (using bfs, for example) after joining the islands)
                deltaIslands--;
            }
        }

        return numOfIslands + deltaIslands;
    }
}
