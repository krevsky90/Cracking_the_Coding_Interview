package solving_techniques.p26_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens (hard)
 * https://leetcode.com/problems/n-queens
 * <p>
 * #Company (10.07.2025): 0 - 3 months Google 17 Amazon 6 Microsoft 4 Meta 3 Zoho 3 0 - 6 months TikTok 14 Bloomberg 3 Oracle 3 6 months ago Apple 7 tcs 5 Goldman Sachs 4 Citadel 4 Adobe 3 Uber 3 Samsung 2 Snowflake 2 Salesforce 2 Yahoo 2
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space, respectively.
 * <p>
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: [["Q"]]
 * <p>
 * Constraints:
 * 1 <= n <= 9
 */
public class NQueens {
    /**
     * KREVSKY SOLUTION:
     * <p>
     * time to solve ~ 34 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 15%
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[][] board = new int[n][n];  //0 - means available, 1 - means queen is here, 2 - means not available (beaten)
        helper(0, n, board, result);

        return result;
    }

    private void helper(int pos, int n, int[][] board, List<List<String>> result) {
        if (pos == n) {
            //transform tempResult to requested form and add to result;
            List<String> tempResult = transform(board);
            result.add(tempResult);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (board[pos][j] != 0) continue;

            //save prev state of board
            int[][] boardBackup = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int ii = 0; ii < n; ii++) {
                    boardBackup[i][ii] = board[i][ii];
                }
            }
            //update board state
            updateBoardState(board, pos, j, n);
            //go deeper
            helper(pos + 1, n, board, result);

            //rollback
            board = boardBackup;
        }
    }

    private void updateBoardState(int[][] board, int r, int c, int n) {
        //set queen
        board[r][c] = 1;
        //mark cells as beaten:
        for (int i = 0; i < n; i++) {
            if (board[i][c] == 0) board[i][c] = 2;
        }

        for (int j = 0; j < n; j++) {
            if (board[r][j] == 0) board[r][j] = 2;
        }

        //diagonal top-left - bottom-right:
        int r1 = r - 1;
        int c1 = c - 1;
        while (r1 >= 0 && c1 >= 0) {
            if (board[r1][c1] == 0) board[r1][c1] = 2;
            r1--;
            c1--;
        }

        r1 = r + 1;
        c1 = c + 1;
        while (r1 < n && c1 < n) {
            if (board[r1][c1] == 0) board[r1][c1] = 2;
            r1++;
            c1++;
        }

        //diagonal top-right - bottom-left:
        r1 = r - 1;
        c1 = c + 1;
        while (r1 >= 0 && c1 < n) {
            if (board[r1][c1] == 0) board[r1][c1] = 2;
            r1--;
            c1++;
        }

        r1 = r + 1;
        c1 = c - 1;
        while (r1 < n && c1 >= 0) {
            if (board[r1][c1] == 0) board[r1][c1] = 2;
            r1++;
            c1--;
        }
    }

    private List<String> transform(int[][] board) {
        List<String> tempResult = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            sb.setLength(0);
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            tempResult.add(sb.toString());
        }
        return tempResult;
    }
}
