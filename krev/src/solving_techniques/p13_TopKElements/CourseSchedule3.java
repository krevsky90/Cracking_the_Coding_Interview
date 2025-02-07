package solving_techniques.p13_TopKElements;

import java.util.Arrays;
import java.util.PriorityQueue;

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
     * idea (from Editorial Approach #6) - see in the comments
     * <p>
     * time to implement ~ 20 mins
     * <p>
     * time ~ O(N*logN), where N = courses.length
     * space ~ O(N)
     * <p>
     * 2 attempts:
     * - forgot to check if q is empty (before calling peek() method)
     *
     * BEATS ~ 95%
     */
    public int scheduleCourse(int[][] courses) {
        //idea #1: sort courses by lastDay => it will let maximize the number of taken courses
        Arrays.sort(courses, (a, b) -> a[1] - b[1]); //sort by due date

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);   //max heap of taken courses sorted by duration of taken courses

        int totalTime = 0;
        for (int[] c : courses) {
            if (totalTime + c[0] <= c[1]) {
                pq.add(c);
                totalTime += c[0];
            } else {
                if (pq.isEmpty()) {
                    continue;   //just skip current course
                }

                // idea #2:
                // try to swap current course with course in the queue, with max duration (IF cur_duration < removed_duration)
                // => this way we will gain extra time = cur_duration - removed_duration
                // also it will not affect the other (already) included courses, just moved them to the 'left' (on timeline axis)
                int[] toBeRemoved = pq.peek();
                if (toBeRemoved[0] > c[0]) {
                    //replace
                    pq.poll();
                    totalTime -= toBeRemoved[0];
                    pq.add(c);
                    totalTime += c[0];
                } else {
                    //do nothing, just skip current course
                }
            }
        }

        return pq.size();
    }
}
