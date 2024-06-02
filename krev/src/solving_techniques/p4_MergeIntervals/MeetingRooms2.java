package solving_techniques.p4_MergeIntervals;

import java.util.Arrays;

public class MeetingRooms2 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 5}, {9, 11}, {3, 10}, {2, 7}};
        MeetingRooms2 obj = new MeetingRooms2();
        System.out.println(obj.countMeetingRooms(intervals));
        System.out.println(obj.minMeetingRooms2(intervals));
    }

    /**
     * KREVSKY SOLUTION:
     * NOTE! is it NOT tested by leetcode since the problem is locked. But it works correctly on given examples
     * time to solve ~ 15 mins
     * time ~ O(nLogn)
     * 1 attempt
     *
     */
    public int countMeetingRooms(int[][] intervals) {
        //1. sort by end
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int prev = 0;
        int result = 1;
        int counter = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[prev][1]) {
                counter++;
                result = Math.max(result, counter);
            } else {
                prev = i;
                counter = 1;
            }
        }

        return result;
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.ca/2016-08-09-253-Meeting-Rooms-II/
     * idea:
     * delta is timeline, and each timeslot has amount of meetings that are going at this particular moment
     *
     * time ~ O(N + T)), where (N) is the number of intervals and (T) is the fixed size of the delta array
     */
    public int minMeetingRooms2(int[][] intervals) {
        int n = 15; //or big number that is definitely bigger then end time of the last interval. for example, 1000010
        int[] delta = new int[n];
        for (int[] e : intervals) {
            ++delta[e[0]];
            --delta[e[1]];
        }
        int res = delta[0];
        for (int i = 1; i < n; ++i) {
            delta[i] += delta[i - 1];
            res = Math.max(res, delta[i]);
        }
        return res;
    }
}
