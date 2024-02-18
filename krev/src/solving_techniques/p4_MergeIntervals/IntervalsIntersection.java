package solving_techniques.p4_MergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639249470cddc1617da1b889
 * OR
 * 986. Interval List Intersections
 * https://leetcode.com/problems/interval-list-intersections
 *
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Constraints:
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^5
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 10^5
 *
 */
public class IntervalsIntersection {
    /**
     * KREVSKY SOLUTION:
     * idea: consider 6 cases of possible positions of two intervals - see picture here
     * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
     *
     * time to solve ~ 24 mins
     * time ~ O(n)
     *
     * 1 attempt
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            if (firstList[i][1] < secondList[j][0]) {
                //no overlapping and i-th interval < j-th interval
                i++;
            } else if (firstList[i][0] < secondList[j][0] && secondList[j][0] <= firstList[i][1] && firstList[i][1] < secondList[j][1]) {
                int[] toAdd = new int[]{secondList[j][0], firstList[i][1]};
                result.add(toAdd);
                i++;
            } else if (secondList[j][0] <= firstList[i][0] && firstList[i][1] <= secondList[j][1]) {
                int[] toAdd = new int[]{firstList[i][0], firstList[i][1]};
                result.add(toAdd);
                i++;
            } else if (firstList[i][0] <= secondList[j][0] && secondList[j][1] <= firstList[i][1]) {
                int[] toAdd = new int[]{secondList[j][0], secondList[j][1]};
                result.add(toAdd);
                j++;
            } else if (secondList[j][0] < firstList[i][0] && firstList[i][0] <= secondList[j][1] && secondList[j][1] < firstList[i][1]) {
                int[] toAdd = new int[]{firstList[i][0], secondList[j][1]};
                result.add(toAdd);
                j++;
            } else if (secondList[j][1] < firstList[i][0]) {
                //no overlapping
                j++;
            }
        }

        return result.toArray(new int[0][]);
    }

    /**
     * idea: https://leetcode.com/problems/interval-list-intersections/solutions/1593579/java-two-pointers-most-intutive/
     */
    public int[][] intervalIntersectionLeetCode(int[][] firstList, int[][] secondList) {
        if(firstList.length==0 || secondList.length==0) return new int[0][0];
        int i = 0;
        int j = 0;
        int startMax = 0, endMin = 0;
        List<int[]> ans = new ArrayList<>();

        while(i<firstList.length && j<secondList.length){
            startMax = Math.max(firstList[i][0],secondList[j][0]);
            endMin = Math.min(firstList[i][1],secondList[j][1]);

            //you have end greater than start and you already know that this interval is sorrounded with startMin and endMax so this must be the intersection
            if(endMin>=startMax){
                ans.add(new int[]{startMax,endMin});
            }

            //the interval with min end has been covered completely and have no chance to intersect with any other interval so move that list's pointer
            if(endMin == firstList[i][1]) i++;
            if(endMin == secondList[j][1]) j++;
        }

        return ans.toArray(new int[0][]);
    }
}

