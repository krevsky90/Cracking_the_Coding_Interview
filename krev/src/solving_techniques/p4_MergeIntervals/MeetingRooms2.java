package solving_techniques.p4_MergeIntervals;

import java.util.Arrays;

/**
 * 253. Meeting Rooms II (medium) (blocked)
 * https://leetcode.com/problems/meeting-rooms-ii/
 * OR
 * https://leetcode.ca/all/253.html
 *
 * #Company: Yandex
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: 1
 *
 */
public class MeetingRooms2 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 5}, {9, 11}, {3, 10}, {2, 7}};
        MeetingRooms2 obj = new MeetingRooms2();
        System.out.println(obj.countMeetingRooms(intervals));
        System.out.println(obj.minMeetingRooms2(intervals));
        System.out.println(obj.minMeetingRooms3(intervals));
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
     * info: https://www.youtube.com/watch?v=FdzJmTCVyJU
     * I came to the same idea:
     * 1) get all start points and sort them
     * 2) get all end points and sort them
     * 3) iterate through each array from p.1 and p.2 and increment/decrement counter
     * return max value of counter
     */
    public int minMeetingRooms2(int[][] intervals) {
        int n = intervals.length;
        int[] startArr = new int[n];
        int[] endArr = new int[n];
        for (int i = 0; i < intervals.length; i++) {
            startArr[i] = intervals[i][0];
            endArr[i] = intervals[i][1];
        }

        //sort startArr and endArr - O(nLogn)
        Arrays.sort(startArr);
        Arrays.sort(endArr);

        int counter = 0;
        int maxCounter = 0;
        int i = 0;
        int j = 0;
        //NOTE: it does not matter to consider j, when we finished iterations for i, since moving j we only can decrease maxCounter
        while (i < n) {
            if (startArr[i] < endArr[j]) {
                counter++;
                i++;
            } else {
                counter--;
                j++;
            }
            maxCounter = Math.max(maxCounter, counter);
        }
        return maxCounter;
    }

    /**
     * SOLUTION #3:
     * info:
     * https://leetcode.ca/2016-08-09-253-Meeting-Rooms-II/
     * idea:
     * delta is timeline, and each timeslot has amount of meetings that are going at this particular moment
     *
     * time ~ O(N + T)), where (N) is the number of intervals and (T) is the fixed size of the delta array
     */
    public int minMeetingRooms3(int[][] intervals) {
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