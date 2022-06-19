package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.ArrayList;

/**
 * p.148
 * 8.12 Eight Queens:
 * Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
 * so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all
 * diagonals, not just the two that bisect the board.
 * Hints: #308, #350, #371
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_12 {
    public static final int GRID_SIZE = 8;

    public static void main(String[] args) {
        //test Krevsky solution
        int[][] chess = new int[GRID_SIZE][GRID_SIZE];
        generateCombination(0, chess);

        //test original solution
        ArrayList<Integer[]> results = new ArrayList<>();
        placeQueens(0, new Integer[8], results);
        System.out.println(results.size());
    }

    /**
     * KREVSKY SOLUTION - START:
     * Values for cells:
     * 0 - available to set queen;
     * 1 - unable to set queen;
     * 2 - set queen to this cell
     * <p>
     * the idea is
     * 1) to backup desk state before setting queen
     * 2) to set queen
     * 3) to mark cells as 1 due to step 2*
     * 4) recursive call
     * 5) to restore state of desk from backup and continue for loop to current row
     */
    public static void generateCombination(int rownum, int[][] chess) {
        if (rownum >= GRID_SIZE) {
            System.out.println("solution:");
            printChess(chess);
            return;
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            if (chess[rownum][i] == 0) {
                int[][] backupChess = copyChess(chess);
                chess[rownum][i] = 2;
                markAsProhibited(rownum, i, chess);
                generateCombination(rownum + 1, chess);
                chess = backupChess;   //rollback
            }
        }
    }

    protected static void markAsProhibited(int row, int col, int[][] chess) {
        //set 1 from current row and below
        for (int i = col + 1; i < GRID_SIZE; i++) chess[row][i] = 1;
        for (int i = col - 1; i >= 0; i--) chess[row][i] = 1;
        for (int j = row + 1; j < GRID_SIZE; j++) chess[j][col] = 1;
        for (int i = col - 1, j = row + 1; i >= 0 && j < GRID_SIZE; i--, j++) chess[j][i] = 1;
        for (int i = col + 1, j = row + 1; i < GRID_SIZE && j < GRID_SIZE; i++, j++) chess[j][i] = 1;
    }

    protected static void printChess(int[][] chess) {
        int maxRow = chess.length;
        int maxCol = chess[0].length;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maxRow; i++) {
            sb.setLength(0);
            for (int j = 0; j < maxCol; j++) {
                sb.append(chess[i][j]);
            }
            System.out.println(sb.toString());
        }
        System.out.println("-------------------------");
    }

    protected static int[][] copyChess(int[][] chess) {
        int maxRow = chess.length;
        int maxCol = chess[0].length;

        int[][] copy = new int[maxRow][maxCol];
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                copy[i][j] = chess[i][j];
            }
        }

        return copy;
    }
    /**
     * KREVSKY SOLUTION - END
     */

    /**
     * ORIGINAL SOLUTION
     * idea:
     * 1) to store just Integer[], where column[r] = c indicates that row r has a queen at column c
     * 2) to check whether is it allowed to set queen or not each time (rather than have pre-calculated result as in KREVSKY solution)
     */
    public static void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results) {
        if (row == GRID_SIZE) {
            //found valid placement
            results.add(columns.clone());
        } else {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (checkValid(columns, row, col)) {
                    columns[row] = col; //place queen
                    placeQueens(row + 1, columns, results);
                }
            }
        }
    }

    /* Check if (row1, column1) is a valid spot for a queen by checking if there is a
     * queen in the same column or diagonal. We don't need to check it for queens in
     * the same row because the calling placeQueen only attempts to place one queen at
     * a time. We know this row is empty.
     */
    protected static boolean checkValid(Integer[] columns, int row1, int column1) {
        for (int row2 = 0; row2 < row1; row2++) {
            int column2 = columns[row2];
            /* Check if (row2, column2) invalidates (row1, column1) as a queen spot. */

            /* Check if rows have a queen in the same column */
            if (column1 == column2) {
                return false;
            }

            /* Check diagonals : if the distance between the columns equals the distance
             * between the rows, then they're in the same diagonal. */
            int columnDistance = Math.abs(column2 - column1);

            /* row1 > row2, so no need for abs */
            int rowDistance = row1 - row2;
            if (columnDistance == rowDistance) {
                return false;
            }
        }
        return true;
    }

}
