package solving_techniques.p11_ModifiedBinarySearch;

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
public class PlatesBetweenCandles {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 60 mins
     * idea:
     * 1) create sorted list of indexes which has '|'
     * 2) for each query use binary search to find:
     * a) the smallest value (i.e. index of the initial string s) in the list p.1 which is >= query[0]
     * b) the largest value in the list p.1 which is <= query[1]
     * 3) count the amount of '*' as indexes.get(rightCandleIdx) - indexes.get(leftCandleIdx) + 1 - (rightCandleIdx - leftCandleIdx + 1);
     * <p>
     * time ~ O(m*logn), where m = queries.length, n = s.length()
     * space ~ O(n)
     * <p>
     * a lot of attempts
     * <p>
     * BEATS ~ 26%
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] sArr = s.toCharArray();

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] == '|') {
                indexes.add(i);
            }
        }

        int length = queries.length;
        int[] result = new int[length];

        for (int i = 0; i < queries.length; i++) {
            result[i] = calc(indexes, queries[i][0], queries[i][1]);
        }

        return result;
    }

// "***|**|*****|**||**|*"
//queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
// [3,6,12,15,16,19]

    private int calc(List<Integer> indexes, int left, int right) {
        int leftCandleIdx = -1;
        int low = 0;
        int high = indexes.size() - 1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (indexes.get(mid) == left) {
                leftCandleIdx = mid;
                break;
            } else if (left < indexes.get(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (leftCandleIdx == -1) leftCandleIdx = low;

        int rightCandleIdx = -1;
        low = 0;
        high = indexes.size() - 1;
        while (low <= high) {
            int midIdx = (high + low) / 2;
            if (indexes.get(midIdx) == right) {
                rightCandleIdx = midIdx;
                break;
            } else if (right < indexes.get(midIdx)) {
                high = midIdx - 1;
            } else {
                low = midIdx + 1;
            }
        }

        if (rightCandleIdx == -1) rightCandleIdx = high;

        if (leftCandleIdx >= rightCandleIdx) {
            return 0;
        } else {
            return indexes.get(rightCandleIdx) - indexes.get(leftCandleIdx) + 1 - (rightCandleIdx - leftCandleIdx + 1);
        }
    }
}
