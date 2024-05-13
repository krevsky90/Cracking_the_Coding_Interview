package solving_techniques.p2_TwoPointers;

/**
 * 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water
 * <p>
 * ou are given an integer array height of length n.
 * There are n vertical lines drawn
 * such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * <p>
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 * <p>
 * Constraints:
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 */
public class ContainerWithMostWater {
    /**
     * NOT SOLVED by myself (but idea was right)
     * info:
     * https://leetcode.com/problems/container-with-most-water/solutions/5139915/video-simple-two-pointer-solution/
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while (left < right) {
            result = Math.max(result, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }
}
