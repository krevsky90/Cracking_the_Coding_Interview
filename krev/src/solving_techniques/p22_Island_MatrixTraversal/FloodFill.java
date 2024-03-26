package solving_techniques.p22_Island_MatrixTraversal;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388e8887b259e5c9e8c0274
 * OR
 * 733. Flood Fill
 * https://leetcode.com/problems/flood-fill (easy)
 *
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color.
 * You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill, consider the starting pixel,
 * plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1)
 * (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel
 * (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 *
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 *
 * Constraints:
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 2^16
 * 0 <= sr < m
 * 0 <= sc < n
 *
 */
public class FloodFill {
    /**
     * KREVSKY SOLUTION:
     * 1) use DFS
     * 2) change color of current cell if its color = initial color of given [sr][sc] cell
     * time to solve ~ 10 mins
     *
     * time ~ O(n*m)
     * space ~ O(n*m)
     *
     * 2 attempts:
     * - forgot condition "if (image[sr][sc] != initColor"
     *
     * BEATS = 100%
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initColor = image[sr][sc];
        fillDFS(image, sr, sc, color, initColor);
        return image;
    }

    private void fillDFS(int[][] image, int sr, int sc, int color, int initColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) return;

        if (image[sr][sc] == color) return; //since there is nothing to fill

        //don't fill the cells that have the color that differs from the initial color of given [sr][sc] cell
        if (image[sr][sc] != initColor) return;

        image[sr][sc] = color;  //fill

        //use DFS
        fillDFS(image, sr + 1, sc, color, initColor);
        fillDFS(image, sr - 1, sc, color, initColor);
        fillDFS(image, sr, sc + 1, color, initColor);
        fillDFS(image, sr, sc - 1, color, initColor);
    }
}
