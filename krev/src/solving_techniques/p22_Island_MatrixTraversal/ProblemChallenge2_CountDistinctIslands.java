package solving_techniques.p22_Island_MatrixTraversal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638c920fa19edaace544c805
 * OR suppose it is
 * https://leetcode.com/problems/number-of-distinct-islands
 * OR
 * https://www.geeksforgeeks.org/find-the-number-of-distinct-islands-in-a-2d-matrix/
 * <p>
 * You are given a 2D matrix containing only 1s (land) and 0s (water).
 * <p>
 * An island is a connected set of 1s (land) and is surrounded by either an edge or 0s (water).
 * Each cell is considered connected to other cells horizontally or vertically (not diagonally).
 * Two islands are considered the same if and only if they can be translated (not rotated or reflected) to equal each other.
 * Write a function to find the number of distinct islands in the given matrix.
 * <p>
 * Examples:
 * Input: grid[][] =
 * {{1, 1, 0, 0, 0},
 * 1, 1, 0, 0, 0},
 * 0, 0, 0, 1, 1},
 * 0, 0, 0, 1, 1}}
 * Output: 1
 * <p>
 * Island 1, 1 at the top left corner is same as island 1, 1 at the bottom right corner
 * Input: grid[][] =
 * {{1, 1, 0, 1, 1},
 * 1, 0, 0, 0, 0},
 * 0, 0, 0, 0, 1},
 * 1, 1, 0, 1, 1}}
 * Output: 3
 */
public class ProblemChallenge2_CountDistinctIslands {
    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};

        int[][] grid2 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        //NOTE: to get result, please uncomment method that need to be checked. AND comment method that works with the same grid
        System.out.println(new ProblemChallenge2_CountDistinctIslands().countDistinctIslands(grid1)); //expected 1
//        System.out.println(new ProblemChallenge2_CountDistinctIslands().countDistinctIslandsGFG(grid1)); //expected 1
        System.out.println(new ProblemChallenge2_CountDistinctIslands().countDistinctIslands(grid2)); //expected 3
//        System.out.println(new ProblemChallenge2_CountDistinctIslands().countDistinctIslandsGFG(grid2)); //expected 3
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     * idea: shape of island can be identified by the sequence of the steps (what we see: 1 or 0),
     * since the sequence of traversing is always the same: up, down, left, right
     * => we just need to note down about the result of each step (what we see: 0 or 1).
     * if the whole sequence of what we see during traversal is the same as we already have in Set => islands are similar
     * <p>
     * 1 attempt
     */
    public int countDistinctIslands(int[][] grid) {
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    formIslandDFS(grid, i, j, sb);
                    islands.add(sb.toString());
                }
            }
        }
        return islands.size();
    }

    private void formIslandDFS(int[][] grid, int i, int j, StringBuilder sb) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            //step out of bound - it is also information about shape of the island!
            //water or already visited cell - it is also information about shape of the island!
            sb.append(0);
            return;
        }

        //mark as visited
        grid[i][j] = 0;
        sb.append(1);   //set 1 since current cell is ground

        formIslandDFS(grid, i - 1, j, sb);
        formIslandDFS(grid, i + 1, j, sb);
        formIslandDFS(grid, i, j - 1, sb);
        formIslandDFS(grid, i, j + 1, sb);
    }

    /**
     * SOLUTION #2:
     * info:
     * https://www.geeksforgeeks.org/find-the-number-of-distinct-islands-in-a-2d-matrix/
     * <p>
     * the same idea, as mine, but
     * 1) more detailed information (relative coordinates)
     * 2) use int[][] dirs array - best practice
     * <p>
     * Time complexity: O(rows * cols * log(rows * cols))
     * we visit every cell so O(row * col) for that and for every cell we need to add atmost (row * col) pairs in set
     * which will cost us O(log(rows*cols)) so overall time complexity will be O(rows * cols * log(rows * cols))
     * todo: DO NOT understand why does it cost log(..)?
     * <p>
     * Space: O(rows * cols)
     * In set we need to add atmost rows*cols entry so space complexity will be O(rows * cols)
     */
    // 2D array for the storing the horizontal and vertical directions. (Up, left, down, right}
    static int[][] dirs = {{0, -1},
            {-1, 0},
            {0, 1},
            {1, 0}
    };

    // Main function that returns distinct count of islands in a given boolean 2D matrix
    public int countDistinctIslandsGFG(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Set<List<String>> coordinates = new HashSet<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // If a cell is not 1 no need to dfs
                if (grid[i][j] == 1) {
                    // vector to hold coordinates of this island
                    List<String> v = new ArrayList<>();
                    dfs(grid, i, j, i, j, v);
                    // insert the coordinates for this island to set
                    coordinates.add(v);
                }
            }
        }

        return coordinates.size();
    }

    // Function to perform dfs of the input grid
    private void dfs(int[][] grid, int x0, int y0, int i, int j, List<String> v) {
        int rows = grid.length, cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] <= 0) return;

        // marking the visited element as -1
        grid[i][j] *= -1;

        // computing coordinates with x0, y0 as base
        v.add("" + (i - x0) + " " + (j - y0));

        // repeat dfs for neighbors
        for (int k = 0; k < 4; k++) {
            dfs(grid, x0, y0, i + dirs[k][0], j + dirs[k][1], v);
        }
    }
}

