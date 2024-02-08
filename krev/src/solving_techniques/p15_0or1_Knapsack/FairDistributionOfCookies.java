package solving_techniques.p15_0or1_Knapsack;

/**
 * 2305. Fair Distribution of Cookies
 * https://leetcode.com/problems/fair-distribution-of-cookies/
 *
 * You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag.
 * You are also given an integer k that denotes the number of children to distribute all the bags of cookies to.
 * All the cookies in the same bag must go to the same child and cannot be split up.
 *
 * The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.
 * Return the minimum unfairness of all distributions.
 *
 * Example 1:
 * Input: cookies = [8,15,10,20,8], k = 2
 * Output: 31
 * Explanation: One optimal distribution is [8,15,8] and [10,20]
 * - The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
 * - The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
 * The unfairness of the distribution is max(31,30) = 31.
 * It can be shown that there is no distribution with an unfairness less than 31.
 *
 * Example 2:
 * Input: cookies = [6,1,3,2,2,4,1,2], k = 3
 * Output: 7
 * Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
 * - The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
 * - The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
 * - The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
 * The unfairness of the distribution is max(7,7,7) = 7.
 * It can be shown that there is no distribution with an unfairness less than 7.
 *
 * Constraints:
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 10^5
 * 2 <= k <= cookies.length
 */
public class FairDistributionOfCookies {
    /**
     * KREVSKY SOLUTION:
     * idea: is the same as PartitionToKEqualSumSubsets + propagate int[] to store the final result
     * time to think + implement ~ 7 + 13 = 21 mins
     * time ~ O(k^n), where n = cookies.length
     * space ~ O(k + n), because k - size of children array, n - depth of recursion
     *
     * 2 attempts:
     * - forgot initialization: unfairness[0] = Integer.MAX_VALUE;
     */
    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];
        int[] unfairness = new int[1];
        unfairness[0] = Integer.MAX_VALUE;
        helper(cookies, 0, children, unfairness);
        return unfairness[0];
    }

    private void helper(int[] cookies, int currentCookieIdx, int[] children, int[] unfairness) {
        if (currentCookieIdx == cookies.length) {
            //all cookies are distributed => let's count unfairness
            int maxTotal = -1;
            for (int i = 0; i < children.length; i++) {
                maxTotal = Math.max(maxTotal, children[i]);
            }
            //save the result - use int[] result since we need to return the result as object
            unfairness[0] = Math.min(unfairness[0], maxTotal);
            return;
        }

        //try to give start-th cookie to each child and look what will be result
        for (int i = 0; i < children.length; i++) {
            children[i] += cookies[currentCookieIdx];   //backtracking starts
            helper(cookies, currentCookieIdx + 1, children, unfairness);
            children[i] -= cookies[currentCookieIdx];   //backtracking ends
        }
    }

    /**
     * Optimized solution https://leetcode.com/problems/fair-distribution-of-cookies/editorial/
     * additional idea is stop criteria:
     */
    public int distributeCookiesOptimized(int[] cookies, int k) {
        int[] children = new int[k];
        int[] unfairness = new int[1];
        unfairness[0] = Integer.MAX_VALUE;
        int amountChildrenWithZeroCookies = k;
        helperOptimized(cookies, 0, children, unfairness, amountChildrenWithZeroCookies);
        return unfairness[0];
    }

    private void helperOptimized(int[] cookies, int currentCookieIdx, int[] children, int[] unfairness, int amountChildrenWithZeroCookies) {
        //optimization - stop criteria
        if (cookies.length - currentCookieIdx < amountChildrenWithZeroCookies) {
            return;
        }

        if (currentCookieIdx == cookies.length) {
            //all cookies are distributed => let's count unfairness
            int maxTotal = -1;
            for (int i = 0; i < children.length; i++) {
                maxTotal = Math.max(maxTotal, children[i]);
            }
            //save the result - use int[] result since we need to return the result as object
            unfairness[0] = Math.min(unfairness[0], maxTotal);
            return;
        }

        //try to give start-th cookie to each child and look what will be result
        for (int i = 0; i < children.length; i++) {
            //decrease amount of children without cookies, if current cookie goes to the child who has not have cookies before
            amountChildrenWithZeroCookies -= children[i] == 0 ? 1 : 0;

            children[i] += cookies[currentCookieIdx];   //backtracking starts
            helperOptimized(cookies, currentCookieIdx + 1, children, unfairness, amountChildrenWithZeroCookies);
            children[i] -= cookies[currentCookieIdx];   //backtracking ends

            //decrease amount of children without cookies, if we take away current cookie and this cookie was the only cookies that this child had
            amountChildrenWithZeroCookies += children[i] == 0 ? 1 : 0;
        }
    }

    /**
     * refactoring: the helper should return the value rather than store it in pseudo-array (that is parameter of the method)
     */
    private int helperOptimized2(int[] cookies, int currentCookieIdx, int[] children, int amountChildrenWithZeroCookies) {
        //optimization - stop criteria
        if (cookies.length - currentCookieIdx < amountChildrenWithZeroCookies) {
            return Integer.MAX_VALUE;
        }

        if (currentCookieIdx == cookies.length) {
            //all cookies are distributed => let's count unfairness
            int maxTotal = Integer.MIN_VALUE;
            for (int i = 0; i < children.length; i++) {
                maxTotal = Math.max(maxTotal, children[i]);
            }
            return maxTotal;
        }

        int result = Integer.MAX_VALUE;
        //try to give start-th cookie to each child and look what will be result
        for (int i = 0; i < children.length; i++) {
            //decrease amount of children without cookies, if current cookie goes to the child who has not have cookies before
            amountChildrenWithZeroCookies -= children[i] == 0 ? 1 : 0;

            children[i] += cookies[currentCookieIdx];   //backtracking starts
            result = Math.max(result, helperOptimized2(cookies, currentCookieIdx + 1, children, amountChildrenWithZeroCookies));
            children[i] -= cookies[currentCookieIdx];   //backtracking ends

            //decrease amount of children without cookies, if we take away current cookie and this cookie was the only cookies that this child had
            amountChildrenWithZeroCookies += children[i] == 0 ? 1 : 0;
        }

        return result;
    }

}
