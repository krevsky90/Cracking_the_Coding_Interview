package data_structures.chapter1_arrays_n_strings;

/**
 * p.103
 *
 * 1.8. Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to O.
 * Hints: #17, #74, #102
 *
 * ASSUMPTION:
 * 1) matrix is not empty
 *
 */
public class Problem1_8 {
    public static void main(String[] args) {
        int[][] matrix = new int[3][4];
        matrix[0] = new int[]{1, 2, 0, 4};
        matrix[1] = new int[]{2, 22, 3, 24};
        matrix[2] = new int[]{3, 32, 33, 34};
        setZeros(matrix);

        System.out.println("");
    }

    //KREVSKY
    public static void setZerosMy(int[][] matrix) {
        int rowNum = matrix.length;
        int columnNum = matrix[0].length;
        int[] listY = new int[rowNum];
        int[] listX = new int[columnNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (matrix[i][j] == 0) {
                    listY[i] = 1;
                    listX[j] = 1;
                }
            }
        }

        for (int i = 0; i < listY.length; i++) {
            if (listY[i] == 1) {
                for (int k = 0; k < columnNum; k++) {
                    matrix[i][k] = 0;
                }
            }
        }

        for (int j = 0; j < listX.length; j++) {
            if (listX[j] == 1) {
                for (int k = 0; k < rowNum; k++) {
                    matrix[k][j] = 0;
                }
            }
        }
    }

    /**
     * SOLUTION: optimized of KREVSKY-solution - to store listX and listY in the first row and column of the matrix
     * We can reduce the space to 0 (1) by using the first row as a replacement for the row array and the first
     * column as a replacement for the column array. This works as follows:
     * 1. Check if the first row and first column have any zeros, and set variables rowHasZero and
     * columnHasZero. (We'll nullify the first row and first column later, if necessary.)
     * 2. Iterate through the rest of the matrix, setting matrix[i][e] and matrix [e][j] to zero whenever
     * there's a zero in matrix[i][j].
     * 3. Iterate through rest of matrix, nullifying row i if there's a zero in matrix[i][e].
     * 4. Iterate through rest of matrix, nullifying column j if there's a zero in matrix[e][j].
     * 5. Nullify the first row and first column, if necessary (based on values from Step 1).
     */
    public static void setZeros(int[][] matrix) {
        //check whether 0-th row contains '0' element
        boolean rowHasZero = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowHasZero = true;
                break;
            }
        }

        //check whether 0-th column contains '0' element
        boolean colHasZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        // Check for zeros in the rest of the array
        for (int i = 1; i < matrix. length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Nullify rows based on values in first column
        for (int i = 1; i < matrix. length; i++) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i);
            }
        }
        // Nullify columns based on values in first row
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                nullifyColumn(matrix, j);
            }
        }
        // Nullify first row
        if (rowHasZero) {
            nullifyRow(matrix, 0);
        }
        // Nullify first column
        if (colHasZero) {
            nullifyColumn(matrix, 0);
        }
    }

    private static void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    private static void nullifyColumn(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
}
