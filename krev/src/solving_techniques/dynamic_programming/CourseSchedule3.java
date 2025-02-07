package solving_techniques.dynamic_programming;

import java.util.Arrays;

/**
 * 630. Course Schedule III (hard)
 * https://leetcode.com/problems/course-schedule-iii
 * <p>
 * #Company (7.02.2025): 6 months ago Amazon 2 Flipkart 2 Works Applications 2
 * <p>
 * There are n different online courses numbered from 1 to n.
 * You are given an array courses where courses[i] = [durationi, lastDayi] indicate
 * that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 * <p>
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 * <p>
 * Return the maximum number of courses that you can take.
 * <p>
 * Example 1:
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * <p>
 * Example 2:
 * Input: courses = [[1,2]]
 * Output: 1
 * <p>
 * Example 3:
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= courses.length <= 10^4
 * 1 <= durationi, lastDayi <= 10^4
 */
public class CourseSchedule3 {
    /**
     * NOT SOLVED by myself
     * idea (from Editorial):
     * 1) sort by lastDay
     * 2) use DP top-down approach: function - to get max num of courses if we start with i-th courses at the particular 'time'
     * 3) use memo[courses][time moments], since max lastDay <= 10^4
     *
     * time to implement ~ 6 mins
     *
     * time ~ O(courses.length * max lastDay)
     * space ~ O(time ~ O(courses.length * max lastDay)
     *
     * BUT finally it gives TLE
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]); //sort by due date
        int maxDueDate = courses[courses.length - 1][1];
        Integer[][] memo = new Integer[courses.length + 1][maxDueDate + 1];

        return scheduleCourse(courses, 0, 0, memo);
    }

    private int scheduleCourse(int[][] courses, int time, int courseNum, Integer[][] memo) {
        if (courseNum == courses.length) return 0;

        if (memo[courseNum][time] != null) return memo[courseNum][time];

        int taken = 0;
        if (time + courses[courseNum][0] <= courses[courseNum][1]) {
            taken = 1 + scheduleCourse(courses, time + courses[courseNum][0], courseNum + 1, memo);
        }
        //consider notTaken option anyway
        int notTaken = scheduleCourse(courses, time, courseNum + 1, memo);
        memo[courseNum][time] = Math.max(taken, notTaken);
        return memo[courseNum][time];
    }
}
