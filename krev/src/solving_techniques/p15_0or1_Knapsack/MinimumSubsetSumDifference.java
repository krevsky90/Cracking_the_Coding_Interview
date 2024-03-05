package solving_techniques.p15_0or1_Knapsack;

import java.util.Arrays;

public class MinimumSubsetSumDifference {
    /**
     * NOTE: according to leetcode's comments for this problem,
     * it should be solved NOT by DP, but by Meet-in-the-Middle technique!
     */
    public static void main(String[] args) {
        int[] arr1 = {91,14,16,82,32,2,38,94};
        //gives Time Limit Exceeded
        int[] arr2 = {-7016943,0,2205243,-794066,-6795006,5322408,9699476,6544247,6992622,7272161,5469637,4806999,-8562708,-5242263,9037593,-2746735,8072395,-1386148,4745179,3801334,-4086041,0,555427,-9249908,5045948,-7943170,1665466,9514306,7960606,-142676};

        System.out.println(new MinimumSubsetSumDifference().minimumDifference(arr2));
    }

    /**
     * NOT SOLVED by myself
     * info: https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2015%3A%200-1%20Knapsack%20(Dynamic%20Programming).md#pattern-1-01-knapsack
     * BUT added "if (len1 > nums.length/2 || len2 > nums.length/2)" validation
     *
     */
    public int minimumDifference(int[] nums) {
        int n = nums.length/2;
        int halfSum = 0;
        for (int k : nums) {
            halfSum += k;
        }
//        halfSum = halfSum/2;

        int[][] dp = new int[nums.length][halfSum];   //stores the diff for each (currentIdx, sum1)
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        return minimumDifferenceMemo(nums, 0, 0, 0, 0, 0, dp);
    }

    /**
     * PROBLEM: shows OOM for
     * {-7016943,0,2205243,-794066,-6795006,5322408,9699476,6544247,6992622,7272161,5469637,4806999,-8562708,-5242263,9037593,-2746735,8072395,-1386148,4745179,3801334,-4086041,0,555427,-9249908,5045948,-7943170,1665466,9514306,7960606,-142676};
     */
    private int minimumDifferenceMemo(int[] nums, int currentIdx, int sum1, int len1, int sum2, int len2, int[][] dp) {
        //this is important condition since otherwise we can get small diff, but the arrays will have different size
        if (len1 > nums.length/2 || len2 > nums.length/2) {
            return Integer.MAX_VALUE;
        }

        if (currentIdx == nums.length) {
            return Math.abs(sum1 - sum2);
        }

        if (dp[currentIdx][sum1] == Integer.MAX_VALUE) {
            int diff1 = minimumDifferenceMemo(nums, currentIdx + 1, sum1 + nums[currentIdx], len1 + 1, sum2, len2, dp);
            int diff2 = minimumDifferenceMemo(nums, currentIdx + 1, sum1, len1, sum2 + nums[currentIdx], len2 + 1, dp);
            dp[currentIdx][sum1] = Math.min(diff1, diff2);
        }
        return dp[currentIdx][sum1];
    }

    /**
     * PROBLEMS: shows Time Limit Exceeded for
     * {-7016943,0,2205243,-794066,-6795006,5322408,9699476,6544247,6992622,7272161,5469637,4806999,-8562708,-5242263,9037593,-2746735,8072395,-1386148,4745179,3801334,-4086041,0,555427,-9249908,5045948,-7943170,1665466,9514306,7960606,-142676};
     */
    private int minimumDifference(int[] nums, int currentIdx, int sum1, int len1, int sum2, int len2) {
        //this is important condition since otherwise we can get small diff, but the arrays will have different size
        if (len1 > nums.length/2 || len2 > nums.length/2) {
            return Integer.MAX_VALUE;
        }

        if (currentIdx == nums.length) {
            return Math.abs(sum1 - sum2);
        }

        int diff1 = minimumDifference(nums, currentIdx + 1, sum1 + nums[currentIdx], len1 + 1, sum2, len2);
        int diff2 = minimumDifference(nums, currentIdx + 1, sum1, len1, sum2 + nums[currentIdx], len2 + 1);

        return Math.min(diff1, diff2);
    }

}
