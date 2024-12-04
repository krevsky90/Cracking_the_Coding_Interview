package solving_techniques.p8_TreeDepthFirstSearch;

/**
 * 440. K-th Smallest in Lexicographical Order (hard)
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order
 *
 * #Company: ByteDance Google Hulu Microsoft
 *
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 *
 * Example 1:
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 *
 * Example 2:
 * Input: n = 1, k = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= k <= n <= 10^9
 */
public class KthSmallestInLexicographicalOrder {
    /**
     * NOT SOLVED by myself:
     * idea: https://www.youtube.com/watch?v=wRubz1zhVqk
     * i.e.
     * 1) consider the numbers [1.. n] as 10-nary tree like
     * 1 2 ... 9
     * [10 11 .. 19] [20 21 .. 29] .. [90 91 .. 99]
     * [100 .. 109] ... etc
     *
     * 2) count amount of steps between neighbour elements of one level
     * 2.1) if counted value <= remain steps => move horizontally in the tree (i.e. cur +=1)
     * otherwise - go deeper (i.e. cur *= 10)
     * and always decrease the amount of remain steps
     *
     * if remain steps = 0 => return cur
     *
     * 3) NOTE: when we count steps, be careful:
     * - neighbour might be > n . And also (to count correctly, we need to use n + 1)
     * - cur*10 might be > Integer.MAX_VALUE => use long
     *
     * time ~ O((log_10_n)^2), since getSameLevelNeigbourDistance ~ log_10_n and while in findKthNumber takes log_10_n
     * space ~ O(1)
     *
     * BEATS ~ 100%
     */
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--; //since we already determined the 1st number

        while (k > 0) {
            int dist = getSameLevelNeigbourDistance(cur, n);
            if (dist <= k) {
                cur += 1;
                k -= dist;
            } else {
                cur *= 10;
                k -= 1; //go to deeper tree level (1- > 10, for ex)
            }
        }

        return cur;
    }

    private int getSameLevelNeigbourDistance(long a, int n) {
        int dist = 0;
        long neigbour = a + 1;
        while (a <= n) {
            dist += Math.min(n + 1, neigbour) - a;
            a *= 10;
            neigbour *= 10;
        }

        return dist;
    }
}
