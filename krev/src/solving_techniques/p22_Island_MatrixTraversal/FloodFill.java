package solving_techniques.p22_Island_MatrixTraversal;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388e8887b259e5c9e8c0274
 * OR
 * todo:
 *
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
