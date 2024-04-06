package solving_techniques.p4_MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinGroups {
    /**
     * KREVSKY SOLUTION:
     * idea 1: sort by start point
     * idea 2: check id tempInterval fits to any list
     *
     * time to solve ~ 20 mins
     *
     * time ~ O(n*logn) + O(n^2) ~ O(n^2), n = intervals.length
     * space ~ O(n)
     *
     * Time Limit Exceeded !!!
     */
    public int minGroups(int[][] intervals) {
        //sort by start point
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        List<int[]> rightValuesList = new ArrayList<>();
        //may cause O(n^2) if all intervals overlap, since then rightValuesList.size() = intervals.length
        //=> it is NOT optimal
        for (int[] tempInterval : intervals) {
            boolean included = false;
            for (int i = 0; i < rightValuesList.size(); i++) {
                int[] rightInterval = rightValuesList.get(i);
                if (rightInterval[1] < tempInterval[0]) {
                    //replace rightInterval with tempInterval. but in fact we change the bounds of rightInterval similar to the bounds of tempInterval
                    rightInterval[0] = tempInterval[0];
                    rightInterval[1] = tempInterval[1];

                    included = true;
                    break;
                }
            }

            if (!included) {
                //add tempInterval to rightValuesList
                rightValuesList.add(tempInterval);
            }
        }

        return rightValuesList.size();
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/solutions/2561379/simplest-way-min-heap-java-solution/
     * idea #1: sort by start point
     * idea #2: use PriorityQueue (min heap) to store ends of intervals
     * details: every time we add current interval to the heap, BUT we check if top (i.e. min) element overlaps with current interval. If no - remove top
     *
     * NOTE: even if tempInterval does not overlap any others intervals (example: [1,5],[2,3] and tempInterval = [6,10],
     * then we remove [2,3], and queue contains NOT overlapping intervals [1,5],[6,10],
     * BUT the size of queue stays correct answer despite its content
     *
     * COULD NOT implement it by myself!
     */
    public int minGroups2(int[][] intervals) {
        //sort by start point
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        //The minimum number of groups we need is equivalent to the maximum number of intervals that overlap at some point
        PriorityQueue<Integer> pq = new PriorityQueue<>();    //min heap, stores ends of intervals
        for (int[] arr : intervals) {
            if (pq.size() > 0 && pq.peek() < arr[0]) {
                pq.remove();
            }

            pq.add(arr[1]);
        }

        return pq.size();
    }
}
