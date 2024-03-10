package solving_techniques.p4_MergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639245dffed07e597e96219f
 * OR
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval
 *
 * You are given an array of non-overlapping intervals
 * where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
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
 */
public class InsertIntervals {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = new int[]{4, 8};

        int[][] result = insert2(intervals, newInterval);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 60 mins
     * <p>
     * NOTE: it could be done easily by ArrayList + converting:
     * return result.toArray(new int[result.size()][2]);
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - typo in new int[]{start, end}; instead of new int[]{left, right};
     * - initially I set result with length = n+1 => it contained {0,0} elements in the end
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        int start = newInterval[0];
        int end = newInterval[1];
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int affectedLeftInterval = -1;
        int affectedRightInterval = -1;

        for (int i = 0; i < n; i++) {
            if (start < intervals[i][0]) {
                left = start;
                //in general it may be no true if [start,end] is the leftmost interval. but if not - we will use this variable while merging
                affectedLeftInterval = i;
                break;
            } else if (intervals[i][0] <= start && start <= intervals[i][1]) {
                left = intervals[i][0];
                //in general it may be no true if [start,end] is the leftmost interval. but if not - we will use this variable while merging
                affectedLeftInterval = i;
                break;
            }
        }

        //when start is more right then n-1-th interval
        if (left == Integer.MIN_VALUE) {
            int[][] result = new int[n + 1][2];
            //just put newInterval as the rightmost interval of the result
            for (int i = 0; i < n; i++) {
                result[i] = intervals[i];
            }
            result[n] = newInterval;
            return result;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (end > intervals[i][1]) {
                right = end;
                //in general it may be no true if [start,end] is the rightmost interval. but if not - we will use this variable while merging
                affectedRightInterval = i;
                break;
            } else if (intervals[i][0] <= end && end <= intervals[i][1]) {
                right = intervals[i][1];
                //in general it may be no true if [start,end] is the rightmost interval. but if not - we will use this variable while merging
                affectedRightInterval = i;
                break;
            }
        }

        //when end is more left, then 0-th interval
        if (right == Integer.MIN_VALUE) {
            int[][] result = new int[n + 1][2];
            //just put newInterval as the leftmost interval of the result
            result[0] = newInterval;
            for (int i = 0; i < n; i++) {
                result[i + 1] = intervals[i];
            }
            return result;
        }

        //otherwise let's merge intervals
        int[][] result = new int[n - (affectedRightInterval - affectedLeftInterval)][2];
        for (int i = 0; i < affectedLeftInterval; i++) {
            result[i] = intervals[i];
        }
        result[affectedLeftInterval] = new int[]{left, right};
        int k = affectedLeftInterval + 1;
        for (int i = affectedRightInterval + 1; i < n; i++) {
            result[k] = intervals[i];
            k++;
        }
        return result;
    }

    /**
     * NEETCODE SOLUTION:
     * https://www.youtube.com/watch?v=A8NUOmlwOlM
     *
     * time to implement ~ 10 mins
     * 1 attempt
     */
    public static int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            if (newInterval[1] < intervals[i][0]) {
                result.add(newInterval);
                //add all the rest intervals and return
                for (int k = i; k < intervals.length; k++) {
                    result.add(intervals[k]);
                }
                return result.toArray(new int[0][]);
                // OR return result.toArray(new int[result.size()][2]);
            } else if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
            } else {
                //overlapping
                //idea: CHANGE the bounds of newInterval itself!
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        //if we did not return the result since "(newInterval[1] < intervals[i][0]", then the last interval overlaps with newInterval
        //and we need to add newInterval here, after the for-loop
        result.add(newInterval);
        return result.toArray(new int[0][]);
    }
}