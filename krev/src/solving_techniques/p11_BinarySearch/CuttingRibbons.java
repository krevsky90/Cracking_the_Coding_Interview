package solving_techniques.p11_BinarySearch;

/**
 * 1891. Cutting Ribbons (medium)
 * https://leetcode.com/problems/cutting-ribbons/
 * <p>
 * #Company (1.03.2025): 0 - 3 months Meta 5 6 months ago Google 3
 * <p>
 * You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k. You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
 * <p>
 * For example, if you have a ribbon of length 4, you can:
 * Keep the ribbon of length 4,
 * Cut it into one ribbon of length 3 and one ribbon of length 1,
 * Cut it into two ribbons of length 2,
 * Cut it into one ribbon of length 2 and two ribbons of length 1, or
 * Cut it into four ribbons of length 1.
 * Your task is to determine the maximum length of ribbon, x, that allows you to cut at least k ribbons, each of length x. You can discard any leftover ribbon from the cuts. If it is impossible to cut k ribbons of the same length, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ribbons = [9,7,5], k = 3
 * Output: 5
 * Explanation:
 * - Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
 * - Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
 * - Keep the third ribbon as it is.
 * Now you have 3 ribbons of length 5.
 * Example 2:
 * <p>
 * Input: ribbons = [7,5,9], k = 4
 * Output: 4
 * Explanation:
 * - Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
 * - Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
 * - Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
 * Now you have 4 ribbons of length 4.
 * Example 3:
 * <p>
 * Input: ribbons = [5,7,9], k = 22
 * Output: 0
 * Explanation: You cannot obtain k ribbons of the same positive integer length.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= ribbons.length <= 10^5
 * 1 <= ribbons[i] <= 10^5
 * 1 <= k <= 10^9
 */
public class CuttingRibbons {
    /**
     * KREVSKY SOLUTION:
     * idea: use binary search: suppose x length is good => check if it is possible to cut the ribbons and get at least k parts with length = x
     * if is it possible - try to increase x (i.e. low = mid + 1)
     * <p>
     * time to solve ~ 10 mins
     * <p>
     * time ~ O(ribbons.length * logN), where N - max element of ribbons or some number that is definitely greater
     * space ~ O(1)
     * <p>
     * 2 attempts:
     * - incorrect low value: should be 1, not 0!
     * <p>
     * BEATS ~ 91%
     */
    public int maxLength(int[] ribbons, int k) {
        int low = 1;
        int high = 100000;

        int result = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canCut(mid, k, ribbons)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    private boolean canCut(int x, int k, int[] arr) {
        int count = 0;
        for (int a : arr) {
            count += a / x;
        }

        return count >= k;
    }
}

