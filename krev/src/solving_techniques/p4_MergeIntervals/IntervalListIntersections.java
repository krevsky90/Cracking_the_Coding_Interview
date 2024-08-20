package solving_techniques.p4_MergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 986. Interval List Intersections (medium)
 * https://leetcode.com/problems/interval-list-intersections
 *
 * #Company: Amazon Apple DoorDash Facebook Google Snapchat Uber
 *
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Example 1:
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Example 2:
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
 * Constraints:
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 10^9
 * endi < starti+1
 * 0 <= startj < endj <= 10^9
 * endj < startj+1
 *
 */
public class IntervalListIntersections {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 32 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     * where n = min(firstList.length, secondList.length);
     *
     * 1 attempt
     *
     * BEATS ~ 59%
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            if (firstList[i][1] < secondList[j][0]) {
                //no intersection, Ai before Bj
                i++;
            } else if (firstList[i][1] >= secondList[j][0] && firstList[i][0] <= secondList[j][1]) {
                //intersection
                int tempStart = Math.max(firstList[i][0], secondList[j][0]);
                int tempEnd = Math.min(firstList[i][1], secondList[j][1]);
                result.add(new int[]{tempStart, tempEnd});

                if (firstList[i][1] < secondList[j][1]) {
                    i++;
                } else if (firstList[i][1] > secondList[j][1]) {
                    j++;
                } else {
                    //ends are equal
                    i++;
                    j++;
                }
            } else if (firstList[i][0] > secondList[j][1]) {
                //no intersection, Bj before Ai
                j++;
            }
        }

        return result.toArray(new int[0][]);
    }
}
