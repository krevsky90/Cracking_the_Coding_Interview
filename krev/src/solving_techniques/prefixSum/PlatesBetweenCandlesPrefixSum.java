package solving_techniques.prefixSum;

import java.util.ArrayList;
import java.util.List;

/**
 * 2055. Plates Between Candles (medium)
 * https://leetcode.com/problems/plates-between-candles/
 * <p>
 * #Company: Amazon (OA)
 * <p>
 * There is a long table with a line of plates and candles arranged on top of it.
 * You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 * <p>
 * You are also given a 0-indexed 2D integer array queries
 * where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive).
 * For each query, you need to find the number of plates between candles that are in the substring.
 * A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 * <p>
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|".
 * The number of plates between candles in this substring is 2,
 * as each of the two plates has at least one candle in the substring to its left and right.
 * <p>
 * Return an integer array answer where answer[i] is the answer to the ith query.
 * <p>
 * Example 1:
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 * <p>
 * Example 2:
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 * <p>
 * Constraints:
 * 3 <= s.length <= 10^5
 * s consists of '*' and '|' characters.
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 */
public class PlatesBetweenCandlesPrefixSum {
    /**
     * NOT SOLVED by myself:
     * info-idea: https://www.youtube.com/watch?v=SbqYno-Zv-w
     * 1) create sorted list of indexes which has '|'
     * 2) for each index in s we store the position of the INDEX (from list p.1) of the closest right candle and closest left candle
     * 3) count the amount of '*' as indexes.get(rightCandleIdx) - indexes.get(leftCandleIdx) -1 - (rightCandleIdx - leftCandleIdx - 1);
     *
     * time to implement ~ 23 mins
     *
     * time ~ O(n + m)
     * space ~ O(n)
     *
     * 2 attempts:
     * - in the corner cases we need to set closestRight[i] = Integer.MAX_VALUE, but not -1;
     *   the same is about closestLeft
     *
     *  BEATS ~ 37%
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] sArr = s.toCharArray();
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] == '|') {
                indexes.add(i);
            }
        }

        int[] closestRight = new int[sArr.length];
        int[] closestLeft = new int[sArr.length];

        int idx = 0;
        for (int i = 0; i < sArr.length; i++) {
            if (idx == indexes.size()) {
                closestRight[i] = Integer.MAX_VALUE;
            } else {
                if (i < indexes.get(idx)) {
                    closestRight[i] = idx;
                } else if (i == indexes.get(idx)) {
                    closestRight[i] = idx;
                    idx++;
                }
            }
        }

        idx = indexes.size() - 1;
        for (int i = sArr.length - 1; i >= 0; i--) {
            if (idx < 0) {
                closestLeft[i] = Integer.MIN_VALUE;
            } else {
                if (i > indexes.get(idx)) {
                    closestLeft[i] = idx;
                } else if (i == indexes.get(idx)) {
                    closestLeft[i] = idx;
                    idx--;
                }
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = calculate(closestRight, closestLeft, queries[i][0], queries[i][1], indexes);
        }

        return result;
    }

    private int calculate(int[] closestRight, int[] closestLeft, int qLeft, int qRight, List<Integer> indexes) {
        int candleL = closestRight[qLeft];
        int candleR = closestLeft[qRight];
        if (candleR <= candleL) return 0;

        return (indexes.get(candleR) - indexes.get(candleL) - 1) - (candleR - candleL - 1);
    }
}
