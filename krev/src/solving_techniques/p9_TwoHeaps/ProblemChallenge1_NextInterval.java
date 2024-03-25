package solving_techniques.p9_TwoHeaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639ca31ddf76bf2533026e64
 * OR
 * 436. Find Right Interval
 * https://leetcode.com/problems/find-right-interval (medium)
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 * Note that i may equal j.
 * Return an array of right interval indices for each interval i.
 * If no right interval exists for interval i, then put -1 at index i.
 */
public class ProblemChallenge1_NextInterval {
    /**
     * NOT SOLVED by myself
     * even did not reach the idea, but went in right direction
     * idea: https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%F0%9F%99%83%20Pattern%2009%3A%20Two%20Heaps.md
     * 1) use MAX heap for BOTH: starti and endi
     * 2) to keep mapping (starti/endi) -> index of the interval in the initial array, we need to store the index as part of the structure that we push the the heaps:
     *      i.e. we push (starti, idx) and (endi, idx)
     * 3) for each element of maxEndHeap we find the lowest element from maxStartHeap such that endi <= startj
     * 3.2) once we save the index j to result[i], we return found startj back to the maxStartHeap,
     *      since this element might be fit for the others elements from maxEndHeap
     *
     * brute-force solution ~ O(n^2)
     * current solution ~ O(n*logn)
     *
     * time to solve ~ 65 mins
     *
     * 1 attempt
     *
     * BEATS = 28%
     */

    // my example: [1,3],[2,3],[4,5]
    // maxStartHeap = [4(2)], [2(1)], [1(0)]
    // maxEndHeap = [5(2)], [3(1)], [3(0)]

    // Example 3:
    // maxStartHeap = [3,4], [2,3], [1,4]
    // maxEndHeap = [1,4], [3,4], [2,3]
    public int[] findRightInterval(int[][] intervals) {
        //NOTE: we need to store index (i), since otherwise we don't know the index in 'result' array
        //that should contain the answer for the interval taken from 'maxEndHeap'

        //maxStartHeap stores int[], where [0] = start_i, [1] = i
        PriorityQueue<int[]> maxStartHeap = new PriorityQueue<>((int[] a, int[] b) -> b[0] - a[0]);
        //maxEndHeap stores int[], where [0] = end_i, [1] = i
        PriorityQueue<int[]> maxEndHeap = new PriorityQueue<>((int[] a, int[] b) -> b[0] - a[0]);

        for (int i = 0; i < intervals.length; i++) {
            maxStartHeap.add(new int[]{intervals[i][0], i});
            maxEndHeap.add(new int[]{intervals[i][1], i});
        }

        int[] result = new int[intervals.length];
        Arrays.fill(result, -1);    //by default

        while(!maxEndHeap.isEmpty()) {
            int[] topEnd = maxEndHeap.poll();
            //find 'next interval' for topEnd
            if (maxStartHeap.peek()[0] >= topEnd[0]) {
                int[] topStart = maxStartHeap.poll();
                while (!maxStartHeap.isEmpty() && maxStartHeap.peek()[0] >= topEnd[0]) {
                    topStart = maxStartHeap.poll();
                }
                //here we have the smallest topStart element => we have found 'next interval'
                result[topEnd[1]] = topStart[1];

                //since we might have the situation like end1 <= end2 <= topStart[0]
                //then we have to push topStart[0] back to the maxStartHeap, because it might be fit value for 'end1'
                maxStartHeap.add(topStart);
            }
        }

        return result;
    }
}