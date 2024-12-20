package solving_techniques.different;

import java.util.HashSet;
import java.util.Set;

/**
 * 36. Valid Sudoku (medium)
 * https://leetcode.com/problems/valid-sudoku/
 * <p>
 * #Company: 0 - 3 months Amazon 6 Meta 4 Apple 3 Uber 3 Google 2 Confluent 2 Samsara 2 0 - 6 months Microsoft 8 Bloomberg 4 LinkedIn 3 Walmart Labs 2 Snap 2 6 months ago Adobe 13 Riot Games 11 Geico 4 Oracle 3 Goldman Sachs 3 Zoho 3 Media.net 3 Yahoo 3 SIG 3 Nvidia 2
 * <p>
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * <p>
 * Constraints:
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */
public class ValidSudoku {
    /**
     * KREVSKY SOLUTION:
     * <p>
     * time to solve ~ 10 mins
     * time ~ O(n^2)
     * space ~ O(n) if we speak about extra created entities
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 78%
     */
    public boolean isValidSudoku(char[][] board) {
        //check cols
        for (int r = 0; r < 9; r++) {
            Set<Character> set = new HashSet<>();
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') continue;

                if (set.contains(board[r][c])) return false;

                set.add(board[r][c]);
            }
        }

        //check rows
        for (int c = 0; c < 9; c++) {
            Set<Character> set = new HashSet<>();
            for (int r = 0; r < 9; r++) {
                if (board[r][c] == '.') continue;

                if (set.contains(board[r][c])) return false;

                set.add(board[r][c]);
            }
        }

        //check sub-boxes
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!checkSubbox(board, 3 * i, 3 * (i + 1), 3 * j, 3 * (j + 1))) return false;
            }
        }

        return true;
    }

    //right and botton are not included
    private boolean checkSubbox(char[][] board, int left, int right, int top, int bottom) {
        Set<Character> set = new HashSet<>();
        for (int c = left; c < right; c++) {
            for (int r = top; r < bottom; r++) {
                if (board[r][c] == '.') continue;

                if (set.contains(board[r][c])) return false;

                set.add(board[r][c]);
            }
        }

        return true;
    }

    /**
     * Official solution
     * idea:
     * store arr of hashsets (for each row - one hashset) and each this hashset contains the numbers that belongs to this row
     * the same - for columns
     * for sub-boxes - the same, but we will identify the number (idx) of box as (r/3)*3 + c/3
     */
    public boolean isValidSudokuOfficial(char[][] board) {
        int N = 9;

        // Use hash set to record the status
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];

                // Check if the position is filled with number
                if (val == '.') {
                    continue;
                }

                // Check the row
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);

                // Check the column
                if (cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }
        return true;
    }
}
