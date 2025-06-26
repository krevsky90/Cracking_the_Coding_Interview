package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram (hard)
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 * <p>
 * #Company: Adobe Amazon Bloomberg Facebook Flipkart Google Microsoft Twitter Walmart Labs
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * <p>
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= heights.length <= 10^5
 * 0 <= heights[i] <= 10^4
 */
public class LargestRectangleInHistogram {
    /**
     * NOT SOLVED by myself
     * info: https://www.youtube.com/watch?v=zx5Sw9130L0
     * idea:
     * use monotonic non-decreasing stack
     * and store pair: index, height
     * <p>
     * if i-th element is lower, then
     * 1) pop pair from stack
     * 2) count area pair[1] * (i - pair[0]) and check if it is max area
     * 3) store indexToSet = pair[0]
     * 4) add pair {indexToSet, i-th element} since it means that rectangle with i-th height begins from indexToSet position
     * <p>
     * time to implement ~ 25 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - size = heights.length, but not stack.size()
     * - do not forget to calc tempArea when you pop larger elements from stack
     *
     * BEATS ~ 80%
     */
    public int largestRectangleArea(int[] heights) {
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

    public static void main(String[] args) {
        int[] heights = {6, 7, 5};
        LargestRectangleInHistogram obj = new LargestRectangleInHistogram();
        obj.largestRectangleArea2(heights);
    }

    /**
     * SOLUTION #2
     * info: https://vladisov.notion.site/Largest-Histogram-a63adc25a64b44938a679b6983e05714
     */
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        //NOTE: i++ is NOT in for-loop
        for(int i = 0; i <= heights.length;) {
            int height = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || height >= heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int currHeight = heights[stack.pop()];
                int right = i - 1;
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                int width = right - left + 1;
                maxArea = Math.max(maxArea, currHeight * width);
            }
        }
        return maxArea;
    }
}
