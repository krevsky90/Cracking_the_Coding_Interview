package solving_techniques.different;

/**
 * 348. Design Tic-Tac-Toe (medium)
 * https://leetcode.com/problems/design-tic-tac-toe
 * <p>
 * #Company (18.02.2025): 0 - 3 months Meta 6 Databricks 5 Amazon 4 0 - 6 months Microsoft 5 Apple 4 TikTok 3 6 months ago Atlassian 4 Airbnb 3 Chewy 3 Yext 3 Google 2 Two Sigma 2 Shopify 2
 * <p>
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 * <p>
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 * <p>
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board.
 * The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
 * 0 if there is no winner after the move,
 * 1 if player 1 is the winner after the move, or
 * 2 if player 2 is the winner after the move.
 * <p>
 * Example 1:
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 * <p>
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * <p>
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * <p>
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * <p>
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * <p>
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * <p>
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * <p>
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * <p>
 * Constraints:
 * 2 <= n <= 100
 * player is 1 or 2.
 * 0 <= row, col < n
 * (row, col) are unique for each different call to move.
 * At most n2 calls will be made to move.
 * <p>
 * <p>
 * Follow-up: Could you do better than O(n2) per move() operation?
 */
public class DesignTicTacToe {
    int[][] arr;

    public DesignTicTacToe(int n) {
        arr = new int[n][n];    //all cells have 0s
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     *
     * time ~ O(n)
     *
     * idea: check affected row, column, main diagonal, main anti-diagonal
     *
     * 3 attempts:
     * - did not set OR condition for all validations
     * - forgot about anti-diagonal
     *
     * BEATS ~ 100%
     */
    public int move(int row, int col, int player) {
        arr[row][col] = player;
        //validate row
        boolean rowCheck = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[row][i] != player) {
                rowCheck = false;
                break;
            }
        }

        //validate column
        boolean colCheck = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][col] != player) {
                colCheck = false;
                break;
            }
        }

        //validate diagonal
        boolean diagCheck1 = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][i] != player) {
                diagCheck1 = false;
                break;
            }
        }

        //validate anti-diagonal
        boolean diagCheck2 = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][arr.length - i - 1] != player) {
                diagCheck2 = false;
                break;
            }
        }

        return rowCheck || colCheck || diagCheck1 || diagCheck2 ? player : 0;
    }
}
