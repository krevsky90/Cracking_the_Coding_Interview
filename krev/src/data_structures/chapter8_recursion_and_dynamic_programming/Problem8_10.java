package data_structures.chapter8_recursion_and_dynamic_programming;

/**
 * p.148
 * 8.10 Paint Fill:
 * Implement the "paint nil" function that one might see on many image editing programs.
 * That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
 * fill in the surrounding area until the color changes from the original color.
 * Hints: #364, #382
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_10 {
    public static void main(String[] args) {
        Color[][] arr = new Color[][]{
                {Color.R, Color.G, Color.B, Color.R, Color.R},
                {Color.R, Color.G, Color.G, Color.G, Color.B},
                {Color.G, Color.G, Color.B, Color.G, Color.B},
                {Color.G, Color.Y, Color.Y, Color.G, Color.G},
                {Color.R, Color.G, Color.G, Color.G, Color.R},
                {Color.R, Color.G, Color.R, Color.R, Color.R}
        };

        System.out.println("arr before paintFill: ");
        print(arr);

        int x = 1;
        int y = 1;
        Color newColor = Color.B;
        paintFill(arr, x, y, newColor);

        System.out.println("arr after paintFill: ");
        print(arr);
    }

    /**
     * KREVSKY SOLUTION
     */
    public static void paintFill(Color[][] arr, int x, int y, Color newColor) {
        if (arr[x][y] == newColor) return;

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        Color oldColor = arr[x][y];
        paintFill(arr, x, y, oldColor, newColor, visited);
    }

    protected static void paintFill(Color[][] arr, int x, int y, Color oldColor, Color newColor, boolean[][] visited) {
        if (arr[y][x] != oldColor || visited[y][x]) {
            return;
        } else {
            arr[y][x] = newColor;
            visited[y][x] = true;
        }

        if (x - 1 >= 0) paintFill(arr, x - 1, y, oldColor, newColor, visited);
        if (x + 1 <= arr[0].length - 1) paintFill(arr, x + 1, y, oldColor, newColor, visited);
        if (y - 1 >= 0) paintFill(arr, x, y - 1, oldColor, newColor, visited);
        if (y + 1 <= arr.length - 1) paintFill(arr, x, y + 1, oldColor, newColor, visited);
    }

    enum Color {
        R, G, B, Y
    }

    private static void print(Color[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        String tempStr;
        for (int i = 0; i < rows; i++) {
            tempStr = "";
            for (int j = 0; j < columns; j++) {
                tempStr += arr[i][j];
            }
            System.out.println(tempStr);
        }
    }
}
