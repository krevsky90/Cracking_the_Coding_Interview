package solving_techniques.p4_MergeIntervals;

import java.util.Arrays;

/**
 * 435. Non-overlapping Intervals
 * https://leetcode.com/problems/non-overlapping-intervals
 *
 * Given an array of intervals where intervals[i] = [starti, endi],
 * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 *
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 */
public class NonOverlappingIntervals {
    /**
     * NOT SOLVED by myself
     * idea #1: sort by end
     * (NOT start! because consider the example: [[1,100],[11,22],[1,11],[2,12]] => [1,100] covers all intervals and gives the answer 3, but in fact the ans = 2)
     * idea #2: save the 'prev' number of interval that does not have overlappings. otherwise - increment counter
     *
     * time to spend ~ 60 mins
     *
     * time ~ O(n*logn)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);  //sort by end

        int result = 0;
        int prevNum = -1;
        for (int i = 0; i < intervals.length; i++) {
            if (prevNum < 0 || intervals[prevNum][1] <= intervals[i][0]) {
                prevNum = i;
            } else {
                result++;
            }
        }
        return result;
    }
}
