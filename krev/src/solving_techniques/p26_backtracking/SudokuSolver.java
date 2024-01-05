package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63d4f0770dd4b828ad1a6fce
 * OR
 * 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver/
 * <p>
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * <p>
 * Example 1:
 * board = [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * <p>
 * Output:
 * [["5","3","4","6","7","8","9","1","2"],
 * ["6","7","2","1","9","5","3","4","8"],
 * ["1","9","8","3","4","2","5","6","7"],
 * ["8","5","9","7","6","1","4","2","3"],
 * ["4","2","6","8","5","3","7","9","1"],
 * ["7","1","3","9","2","4","8","5","6"],
 * ["9","6","1","5","3","7","2","8","4"],
 * ["2","8","7","4","1","9","6","3","5"],
 * ["3","4","5","2","8","6","1","7","9"]]
 * <p>
 * Constraints:
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board1 = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };


        char[][] board2 = {{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };

        System.out.println("Sudoku board before:");
        print2dArray(board2);
        System.out.println("Sudoku board after:");
//        new SudokuSolver().solve(board2);
        boolean res = sudokuSolverKrev(board2);
        print2dArray(board2);
    }

    /**
     * KREVSKY SOLUTION
     * time to solve ~ 90+ mins
     * 5 attempts:
     * - tried to remove elements inside for-loops in filterNums method
     * - did not know that Arrays.asList returns a fixed-size list (see https://stackoverflow.com/questions/2965747/why-do-i-get-an-unsupportedoperationexception-when-trying-to-remove-an-element-f)
     * - wrongly added "if (nums.size() == 1)" to speed up the solution
     * - removed "if (nums.isEmpty()) return false;" and returned 'false' only in case if we had to revert num to '.'
     */
    public void solveSudoku(char[][] board) {
        sudokuSolverKrev(board);
    }

    public static boolean sudokuSolverKrev(char[][] arr) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr[i][j] == '.') {
                    //we need to fill [i][j] cell since it is empty (i.e. contains '.')
                    List<Character> nums = new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

                    filterNums(nums, arr, i, j);

//                    if (nums.isEmpty()) return false; <-- this is wrong. We have to return 'false' only in case if we need to revert num to '.'

//                    if (nums.size() == 1) {   <-- this does not work in case if you can't set the value that is the only valid value, and you have you fill few steps further to check if your choise was correct
                    for (char num : nums) {
                        arr[i][j] = num;
                        if (sudokuSolverKrev(arr)) {
                            return true;
                        }
                        //revert in case if suggestion 'arr[i][j] = num' did not bring success
                        arr[i][j] = '.';
                    }

                    return false;
                }
            }
        }

        //if we reach this point => we went through the whole arr[][] and fill it successfully.
        // Otherwise the method would return 'false' since there is no way to fill some cell,
        // because nums is empty after filtering
        return true;
    }

    private static void filterNums(List<Character> nums, char[][] arr, int i, int j) {
        Set<Character> numsToRemove = new HashSet<>();

        //filter by row
        for (int jj = 0; jj < 9; jj++) {
            numsToRemove.add((arr[i][jj]));
        }

        //filter by column
        for (int ii = 0; ii < 9; ii++) {
            numsToRemove.add(arr[ii][j]);
        }

        //filter by 3x3 cell
        int qi = i / 3;
        int qj = j / 3;
        for (int ii = qi * 3; ii < qi * 3 + 3; ii++) {
            for (int jj = qj * 3; jj < qj * 3 + 3; jj++) {
                numsToRemove.add(arr[ii][jj]);
            }
        }

        nums.removeAll(numsToRemove);
    }

    private static void print2dArray(char[][] arr) {
        if (arr == null || arr.length == 0) return;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            System.out.println(sb.toString());
            sb.setLength(0);    //clear
        }
    }

    /**
     * ===== ALTERNATIVE( but similar) SOLUTION https://leetcode.com/problems/sudoku-solver/solutions/4461217/solve-sudoku-problem-in-easy-steps-java-backtracking/ ========
     */
    public boolean solve(char[][] board) {
        int n = board.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == '.') {
                    for (int tryNum = 1; tryNum <= 9; tryNum++) {
                        if (isConflicts(board, tryNum, row, col)) {
                            board[row][col] = (char) (tryNum + '0');
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = '.';
                        }
                    }
                    return false;

                }

            }
        }
        return true;
    }

    public boolean isConflicts(char[][] board, int tryNum, int row, int col) {
        return !rowCheck(board, tryNum, row) && !colCheck(board, tryNum, col) && !boxCheck(board, tryNum, row, col);
    }

    //for row check
    public boolean rowCheck(char[][] board, int tryNum, int row) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == (char) (tryNum + '0')) {
                return true;
            }
        }
        return false;
    }

    //for col check
    public boolean colCheck(char[][] board, int tryNum, int col) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[i][col] == (char) (tryNum + '0')) {
                return true;
            }
        }
        return false;
    }

    //fow individual box check
    public boolean boxCheck(char[][] board, int tryNum, int row, int col) {
        int localRow = row - row % 3;
        int localCol = col - col % 3;
        for (int i = localRow; i < localRow + 3; i++) {
            for (int j = localCol; j < localCol + 3; j++) {
                if (board[i][j] == (char) (tryNum + '0')) {
                    return true;
                }
            }
        }
        return false;
    }
}