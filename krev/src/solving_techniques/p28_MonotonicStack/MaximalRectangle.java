package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * 85. Maximal Rectangle (hard)
 * https://leetcode.com/problems/maximal-rectangle
 * <p>
 * #Company: Adobe Amazon Bloomberg Facebook Google Indeed Microsoft Pinterest Samsung Uber VMware Wayfair Yandex
 * <p>
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example 1:
 * Input: matrix = [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * <p>
 * Example 2:
 * Input: matrix = [["0"]]
 * Output: 0
 * <p>
 * Example 3:
 * Input: matrix = [["1"]]
 * Output: 1
 * <p>
 * Constraints:
 * rows == matrix.length
 * cols == matrix[i].length
 * 1 <= row, cols <= 200
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalRectangle {
    /**
     * KREVSKY SOLUTION:
     * based on https://leetcode.com/problems/largest-rectangle-in-histogram/
     * which is solved here https://www.youtube.com/watch?v=zx5Sw9130L0
     * <p>
     * idea:
     * 1) form histogram by adding line to line and each time calculate maxArea,
     * as described here:
     * use monotonic non-decreasing stack
     * and store pair: index, height
     * <p>
     * if i-th element is lower, then
     * 1) pop pair from stack
     * 2) count area pair[1] * (i - pair[0]) and check if it is max area
     * 3) store indexToSet = pair[0]
     * 4) add pair {indexToSet, i-th element} since it means that rectangle with i-th height begins from indexToSet position
     *
     * 1 attempt
     *
     * BEATS ~ 77%
     */
    public int maximalRectangle(char[][] matrix) {
        //this is similar to https://leetcode.com/problems/largest-rectangle-in-histogram/
        // convert to histogram and count max area line by line
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            int indexToSet = i;
            while (!stack.isEmpty() && heights[i] < stack.peek()[1]) {
                int[] pair = stack.pop();
                int tempSquare = pair[1] * (i - pair[0]);
                maxArea = Math.max(maxArea, tempSquare);
                indexToSet = pair[0];
            }

            stack.add(new int[]{indexToSet, heights[i]});
        }

        int size = heights.length;
        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int tempSquare = pair[1] * (size - pair[0]);
            maxArea = Math.max(maxArea, tempSquare);
        }

        return maxArea;
    }
}
