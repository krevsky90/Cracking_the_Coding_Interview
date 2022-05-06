package data_structures.chapter1_arrays_n_strings;

/**
 * p.103
 *
 * 1.7. Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 *
 * Hints: #51, #100
 *
 * ASSUMPTION:
 *  1) matrix is not empty
 *  2) maxRow = maxColumn
 *
 */
public class Problem1_7 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix2 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rotate2(matrix2);

        System.out.println("");
    }

    //KREVSKY
    public static boolean rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return false;
        }

        int n = matrix.length;
        int startRow = 0;
        int startColumn = 0;
        int end = n - 1;	//we have already changed the last element of row/column by rotation
        int[] temp = new int[4];

        for (int i = startRow; i < end; i++) {
            for (int j = startColumn; j < end; j++) {
                temp[0] = matrix[i][j];
                temp[1] = matrix[j][n-i-1];
                temp[2] = matrix[n-i-1][n-j-1];
                temp[3] = matrix[n-j-1][i];

                matrix[j][n-i-1] = temp[0];
                matrix[n-i-1][n-j-1] = temp[1];
                matrix[n-j-1][i] = temp[2];
                matrix[i][j] = temp[3];
            }
            startColumn++;
            end--;
        }

        return true;
    }

    /**
     *
     * SOLUTION: the idea is similar to KREVSKY's solution, but better,
     * because KREVSKY stores 4 values, but the original solution stores the only element (int top)
     */
    public static boolean rotate2(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) {
            return false;
        }

        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            int first = i;
            int last = n-1-i;
            for (int j = first; j < last; j++) {
                int offset = j - first;
                int top = matrix[first][j]; // save top
                // left -> top
                matrix[first][j] = matrix[last-offset][first];
                // bottom -> left
                matrix[last-offset][first] = matrix[last][last - offset];
                // right -> bottom
                matrix[last][last - offset] = matrix[j][last];
                // top -> right
                matrix[j][last] = top; // right <- saved top
            }
        }

        return true;
    }
}
