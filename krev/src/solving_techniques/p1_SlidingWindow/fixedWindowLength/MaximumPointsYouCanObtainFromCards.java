package solving_techniques.p1_SlidingWindow.fixedWindowLength;

/**
 * 1423. Maximum Points You Can Obtain from Cards (medium)
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards
 * <p>
 * #Company (10.02.2025): 0 - 3 months Google 6 0 - 6 months Amazon 2 TikTok 2 6 months ago Flipkart 5 Microsoft 3 Adobe 3 Bloomberg 2
 * <p>
 * There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 * <p>
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * <p>
 * Your score is the sum of the points of the cards you have taken.
 * <p>
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * <p>
 * Example 1:
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1.
 * However, choosing the rightmost card first will maximize your total score.
 * The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * <p>
 * Example 2:
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * <p>
 * Example 3:
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 * <p>
 * Constraints:
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 */
public class MaximumPointsYouCanObtainFromCards {
    /**
     * KREVSKY SOLUTION:
     * idea: sliding window with min total sum => sum of the rest parts of the initial array will be max
     * <p>
     * time to solve ~ 12 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 30%
     */
    public int maxScore(int[] cardPoints, int k) {
        int total = 0;
        int minSum = Integer.MAX_VALUE;
        int tempSum = 0;
        int n = cardPoints.length;
        int width = n - k;
        for (int i = 0; i < n; i++) {
            total += cardPoints[i];
            tempSum += cardPoints[i];

            if (i >= width) {
                tempSum -= cardPoints[i - width];
            }

            if (i >= width - 1 && minSum > tempSum) {
                minSum = tempSum;
            }
        }

        return total - minSum;
    }
}
