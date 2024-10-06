package solving_techniques.p2_TwoPointers;

/**
 * 42. Trapping Rain Water (hard)
 * https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * #Company: Adobe Affirm Airbnb Amazon Apple Atlassian Bloomberg ByteDance Citadel Databricks Dataminr Electronic Arts Facebook Flipkart Goldman Sachs Google Grab HBO Huawei Lyft Microsoft Nvidia Oracle Palantir Technologies Qualtrics Salesforce Snapchat Tableau Twitter Uber Visa Walmart Labs Wish Yahoo Yandex Zenefits Zoho
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * <p>
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 */
public class TrappingRainWater {
    /**
     * NOT SOLVED and event NOT implemented by myself
     * time to spend ~ 1+ h
     * info: https://www.youtube.com/watch?v=4Y7irecfvLM&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=116
     * <p>
     * idea:
     * 1) use two pointers
     * 2) kepp track leftmax, rightMax
     * if leftMax < rightMax then add water delta from left side (leftMax - curLeftHeight) and move left
     * otherwise - vise versa for right side
     * <p>
     * BEATS ~ 100%
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;

        int result = 0;


        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                //move left pointer
                result += leftMax - height[left];
                left++;
            } else {
                //move right pointer
                result += rightMax - height[right];
                right--;
            }
        }

        return result;
    }

}
