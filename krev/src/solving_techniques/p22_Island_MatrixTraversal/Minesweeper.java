package solving_techniques.p22_Island_MatrixTraversal;

/**
 * 529. Minesweeper (medium)
 * https://leetcode.com/problems/minesweeper
 * <p>
 * #Company (31.03.2025): 0 - 3 months Meta 7 Anduril 2 0 - 6 months Applied Intuition 3 Google 2 6 months ago Amazon 4 Uber 4 Apple 2 Docusign 2
 * <p>
 * Let's play the minesweeper game (Wikipedia, online game)!
 * <p>
 * You are given an m x n char matrix board representing the game board where:
 * <p>
 * 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
 * digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
 * 'X' represents a revealed mine.
 * You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
 * <p>
 * Return the board after revealing this position according to the following rules:
 * <p>
 * If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
 * If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 * <p>
 * <p>
 * Example 1:
 * Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
 * Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 * <p>
 * Example 2:
 * Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
 * Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] is either 'M' or 'E'.
 */
public class Minesweeper {
    /**
     * KREVSKY SOLUTION:
     * idea: use DFS to imitate clicks to adj cells in case if we initially clicked to blank cell
     * <p>
     * time to solve ~ 35 mins
     * <p>
     * 2 attempts:
     * - forgot 8 directions, not 4
     * <p>
     * BEATS ~ 100%
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];
        if (!(0 <= r && r < board.length && 0 <= c && c < board[0].length)) {
            //i.e. do nothing
            return board;
        }

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        } else if (board[r][c] == 'E') {
            //check adjacent mines
            int cnt = 0;
            for (int i = r - 1; i <= r + 1; i++) {
                if (i >= 0 && i < board.length) {
                    for (int j = c - 1; j <= c + 1; j++) {
                        if (j >= 0 && j < board[0].length) {
                            if (i != r || j != c) {
                                if (board[i][j] == 'M') {
                                    cnt++;
                                }
                            }
                        }
                    }
                }
            }

            if (cnt > 0) {
                board[r][c] = (char) ('0' + cnt);
                return board;
            } else {
                //means clicked cell is blank => need to unreveal all blank cells + the cells that are adjacent to mines
                board[r][c] = 'B';
                //use DFS to handle adj cells (8 directions!)
                updateBoard(board, new int[]{r - 1, c - 1});
                updateBoard(board, new int[]{r - 1, c});
                updateBoard(board, new int[]{r - 1, c + 1});
                updateBoard(board, new int[]{r + 1, c - 1});
                updateBoard(board, new int[]{r + 1, c});
                updateBoard(board, new int[]{r + 1, c + 1});
                updateBoard(board, new int[]{r, c - 1});
                updateBoard(board, new int[]{r, c + 1});
                updateBoard(board, new int[]{r, c + 1});
            }
        }

        return board;
    }

    /**
     * Optimized from code quality perspective
     * BUT BEATS ~ 53%
     */
    public char[][] updateBoard2(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        if (!(0 <= i && i < board.length && 0 <= j && j < board[0].length)) {
            //i.e. do nothing
            return board;
        }

        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        } else if (board[i][j] == 'E') {
            //check adjacent mines
            int[][] neighbors = {{i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1},
                    {i, j - 1}, {i, j + 1},
                    {i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1}};
            int cnt = 0;
            for (int[] neigbour : neighbors) {
                int r = neigbour[0];
                int c = neigbour[1];
                if (0 <= r && r < board.length && 0 <= c && c < board[0].length) {
                    if (board[r][c] == 'M') {
                        cnt++;
                    }
                }
            }

            if (cnt > 0) {
                board[i][j] = (char) ('0' + cnt);
                return board;
            } else {
                //means clicked cell is blank => need to unreveal all blank cells + the cells that are adjacent to mines
                board[i][j] = 'B';
                //use DFS to handle adj cells (8 directions!)
                for (int[] neigbour : neighbors) {
                    updateBoard2(board, new int[]{neigbour[0], neigbour[1]});
                }
            }
        }

        return board;
    }
}

