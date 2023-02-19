package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

/**
 * https://igotanoffer.com/blogs/tech/array-interview-questions
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent-set-2/
 * or https://medium.com/@arunistime/maximum-sum-of-non-adjacent-numbers-algorithm-explained-159f08b5790a
 *
 * Given an array of positive (OR also negative???) numbers,
 * find the maximum sum of a subsequence with the constraint that no 2 numbers in the sequence should be adjacent in the array.
 * So
 * 3 2 7 10 should return 13 (sum of 3 and 10) or
 * 3 2 5 10 7 should return 15 (sum of 3, 5, and 7).
 *
 */
public class Problem2_4_LargestSumOfNonAdjacentNumbers {
    public static void main(String[] args) {
        int[] array = new int[]{2,7,9,3,-1};//,3,5,1,6,7};
        int result = findMaxKrevLoopSpaceOptimized(array);
        System.out.println(result);
    }

    public static int findMax(int[] array) {
        if (array == null || array.length == 0) return 0;

        return findMaxKrevRecursion(array, array.length - 1);
    }

    /**
     * KREVSKY SOLUTION
     * using recursion
     * time complexity ~ O(2^n) - draw the tree of calls
     * space complexity ~ O(n)  - proportional to the depth of the tree
     */
    private static int findMaxKrevRecursion(int[] array, int index) {
        if (index == 0) return array[0];
        if (index == 1) return Math.max(array[0], array[1]);

        int prevPrevSum  = findMaxKrevRecursion(array, index - 2) + array[index];
        int prevSum = findMaxKrevRecursion(array, index - 1);

        return Math.max(prevSum, prevPrevSum);
    }

    /**
     * KREVSKY SOLUTION
     * using recursion + memorization
     * time complexity ~ O(n)
     * space complexity ~ O(n)  - proportional to the depth of the tree
     */

    public static int findMaxRecursionMemo(int[] array) {
        if (array == null || array.length == 0) return 0;

        int[] memo = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            memo[i] = Integer.MIN_VALUE;
        }

        return findMaxKrevRecursionMemo(array, memo, array.length - 1);
    }

    private static int findMaxKrevRecursionMemo(int[] array, int[] memo, int index) {
        if (memo[index] != Integer.MIN_VALUE) {
            return memo[index];
        }

        if (index == 0) {
            memo[0] = array[0];
            return memo[0];
        }

        if (index == 1) {
            memo[1] = Math.max(array[0], array[1]);
            return memo[1];
        }

        int prevPrevSum  = findMaxKrevRecursionMemo(array,  memo, index - 2) + array[index];
        int prevSum = findMaxKrevRecursionMemo(array, memo, index - 1);

        memo[index] = Math.max(prevSum, prevPrevSum);
        return memo[index];
    }

    /**
     * KREVSKY SOLUTION using loop = https://www.youtube.com/watch?v=SfSGoa2ck6s
     * time complexity ~ O(n)
     * space complexity ~ O(n)
     */
    public static int findMaxKrevLoop(int[] array) {
        if (array == null || array.length == 0) return 0;

        if (array.length == 1) return array[0];

        int[] dp = new int[array.length];

        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);

        for (int i = 2; i < array.length; i++) {
            dp[i] = Math.max(dp[i-2] + array[i], dp[i-1]);
        }

        return dp[array.length - 1];
    }

    /**
     * KREVSKY SOLUTION using loop + memory optimization
     * time complexity ~ O(n)
     * space complexity ~ O(1)
     * the same solution is 'Space Optimized Dynamic programming'
     * here https://leetcode.com/discuss/interview-question/702177/apple-phone-maximum-sum-of-non-adjacent-elements
     */
    public static int findMaxKrevLoopSpaceOptimized(int[] array) {
        if (array == null || array.length == 0) return 0;

        if (array.length == 1) return array[0];

        int prepre = array[0];
        int pre = Math.max(array[0], array[1]);
        int cur = Integer.MIN_VALUE;

        for (int i = 2; i < array.length; i++) {
            cur = Math.max(prepre + array[i], pre);
            prepre = pre;
            pre = cur;
        }

        return pre;
    }
}
