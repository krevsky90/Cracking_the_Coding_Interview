package solving_techniques.p4_MergeIntervals;

import java.util.Arrays;

/**
 * 252. Meeting Rooms
 * https://leetcode.com/problems/meeting-rooms (easy) (blocked)
 * info: https://leetcode.ca/2016-08-08-252-Meeting-Rooms/
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: true
 *
 */
public class MeetingRooms {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) sort by start (IMHO it is no matter if we sort by start or end)
     * 2) check if i-1-th and i-th intervals have overlapping
     *
     * time to solve ~ 8 mins
     * 1 attempt
     */
    public boolean hasOverlapping(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);  //sort, for example, by start

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }

        return true;
    }
}
