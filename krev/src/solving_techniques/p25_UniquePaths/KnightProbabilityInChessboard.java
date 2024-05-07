package solving_techniques.p25_UniquePaths;

/**
 * 688. Knight Probability in Chessboard
 * https://leetcode.com/problems/knight-probability-in-chessboard
 * <p>
 * On an n x n chessboard, a knight starts at the cell (row, column)
 * and attempts to make exactly k moves.
 * The rows and columns are 0-indexed, so the top-left cell is (0, 0),
 * and the bottom-right cell is (n - 1, n - 1).
 * <p>
 * A chess knight has eight possible moves it can make, as illustrated below.
 * Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
 * <p>
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random
 * (even if the piece would go off the chessboard) and moves there.
 * <p>
 * The knight continues moving until it has made exactly k moves or has moved off the chessboard.
 * <p>
 * Return the probability that the knight remains on the board after it has stopped moving.
 * <p>
 * Example 1:
 * Input: n = 3, k = 2, row = 0, column = 0
 * Output: 0.06250
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 * <p>
 * Example 2:
 * Input: n = 1, k = 0, row = 0, column = 0
 * Output: 1.00000
 * <p>
 * Constraints:
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n - 1
 */
public class KnightProbabilityInChessboard {
    /**
     * NOT SOLVED by myself (did not find idea #)
     * ideas:
     * 1) store probability, rather than amount of "positive" cases. i.e. divide by 8.
     * otherwise we will need to divide to 8^30 => use BigDecimal etc
     * 2) constraint block should be BEFORE base case in this problem
     * 3) using memoization, we need to ADD counted result, but not set it as is!
     *      BECAUSE: example: way1 (i,j,k) -> (i+1,j+2,k-1) -> (i,j,k-2)
     *                    and way2 (i,j,k) -> (i-1,j-2,k-1) -> (i,j,k-2).
     *
     *
     * time to spend ~ 50 mins
     *
     * time ~ O(n*n*k)
     * space ~ O(n*n*k)
     *
     * a lot of attempts due to lack of ideas #1 and #3
     *
     * BEATS = 98%
     */
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[n+1][n+1][k+1];  //stores (row, column, step)

        return knightProbability(n, k, row, column, dp);
    }

    private double knightProbability(int n, int k, int i, int j, double[][][] dp) {
        //constraint
        if (i < 0 || i >= n || j < 0 || j >= n) return 0;

        //base case:
        if (k == 0) return 1;

        if (dp[i][j][k] != 0) return dp[i][j][k];

        double result = 0;
        result += knightProbability(n, k-1, i-2, j+1, dp);
        result += knightProbability(n, k-1, i-1, j+2, dp);
        result += knightProbability(n, k-1, i+1, j+2, dp);
        result += knightProbability(n, k-1, i+2, j+1, dp);
        result += knightProbability(n, k-1, i+2, j-1, dp);
        result += knightProbability(n, k-1, i+1, j-2, dp);
        result += knightProbability(n, k-1, i-1, j-2, dp);
        result += knightProbability(n, k-1, i-2, j-1, dp);

        return dp[i][j][k] += result/8.0;
    }
}

