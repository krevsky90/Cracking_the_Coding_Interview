package solving_techniques.different;

/**
 * 3071. Minimum Operations to Write the Letter Y on a Grid (medium)
 * https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid
 * <p>
 * #Company: (23.12.2024) 0 - 3 months Uber 2 Capital One 2 0 - 6 months ZipRecruiter 2 6 months ago Zeta 2
 * <p>
 * You are given a 0-indexed n x n grid where n is odd, and grid[r][c] is 0, 1, or 2.
 * <p>
 * We say that a cell belongs to the Letter Y if it belongs to one of the following:
 * <p>
 * The diagonal starting at the top-left cell and ending at the center cell of the grid.
 * The diagonal starting at the top-right cell and ending at the center cell of the grid.
 * The vertical line starting at the center cell and ending at the bottom border of the grid.
 * The Letter Y is written on the grid if and only if:
 * <p>
 * All values at cells belonging to the Y are equal.
 * All values at cells not belonging to the Y are equal.
 * The values at cells belonging to the Y are different from the values at cells not belonging to the Y.
 * Return the minimum number of operations needed to write the letter Y on the grid given that in one operation you can change the value at any cell to 0, 1, or 2.
 * <p>
 * Example 1:
 * Input: grid = [[1,2,2],[1,1,0],[0,1,0]]
 * Output: 3
 * Explanation: We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 1 while those that do not belong to Y are equal to 0.
 * It can be shown that 3 is the minimum number of operations needed to write Y on the grid.
 * <p>
 * Example 2:
 * Input: grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
 * Output: 12
 * Explanation: We can write Y on the grid by applying the changes highlighted in blue in the image above. After the operations, all cells that belong to Y, denoted in bold, have the same value of 0 while those that do not belong to Y are equal to 2.
 * It can be shown that 12 is the minimum number of operations needed to write Y on the grid.
 * <p>
 * Constraints:
 * 3 <= n <= 49
 * n == grid.length == grid[i].length
 * 0 <= grid[i][j] <= 2
 * n is odd.
 */
public class MinimumOperationsToWriteTheLetterYOnGrid {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) count 0s, 1s, 2s in Y and out of it
     * 2) check all 6 potential combinations
     * <p>
     * time to solve ~ 24 mins
     * <p>
     * time ~ O(n*n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 88%
     */
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int mid = n / 2;

        int totalY = 0;
        int[] yData = new int[3];   //amount of 0s 1s, 2s that belongs to Y
        int[] notYData = new int[3];   //amount of 0s 1s, 2s that does not belong to Y
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                if ((i == j && i <= mid) || (i + j + 1 == n && i <= mid) || (i >= mid && j == mid)) {
                    yData[val]++;
                    totalY++;
                } else {
                    notYData[val]++;
                }
            }
        }

        // 6  combinations:
        //Y is formed by 0s, notY is formed by 1s
        int[] candidates = new int[6];
        candidates[0] = (totalY - yData[0]) + (n * n - totalY - notYData[1]);
        candidates[1] = (totalY - yData[0]) + (n * n - totalY - notYData[2]);
        candidates[2] = (totalY - yData[1]) + (n * n - totalY - notYData[0]);
        candidates[3] = (totalY - yData[1]) + (n * n - totalY - notYData[2]);
        candidates[4] = (totalY - yData[2]) + (n * n - totalY - notYData[0]);
        candidates[5] = (totalY - yData[2]) + (n * n - totalY - notYData[1]);

        int result = Integer.MAX_VALUE;
        for (int can : candidates) {
            result = Math.min(result, can);
        }

        return result;
    }
}
