package solving_techniques.p4_MergeIntervals.from_discuss;

import java.util.*;

/**
 * Find amount of intervals that do not overlap other intervals in Java
 */
public class IntervalsThatDontOverlapOthers {
    public static void main(String[] args) {
        IntervalsThatDontOverlapOthers obj = new IntervalsThatDontOverlapOthers();
        obj.test();
    }

    /**
     * idea: sweep line approach
     *
     * It is ok, IF we consider that (1,2) and (2,3) have overlapping!
     */
    public int findMaxKrevSweepLine(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Map<Integer, Integer> map = new HashMap();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        int result = 0;
        int count = 0;
        boolean isCandidate = false;

        for (int key : map.keySet()) {
            if (count == 0 && map.get(key) == 1) {
                isCandidate = true;
                count += map.get(key);
            } else if (count + map.get(key) > 1) {
                isCandidate = false;
                count += map.get(key);
            } else if (count == 1 && map.get(key) == -1 && isCandidate) {
                result++;
                isCandidate = false;
                count += map.get(key);
            }
        }

        return result;
    }

    /**
     * idea:
     * sort by end
     * keep prev interval to check if i-th and prev intervals overlap
     * IDEA: if overlap occurs, set prev as interval with larger end (i.e. intervals[i] or prev interval)
     *
     * IF we consider that (1,2) and (2,3) does not overlap => use condition intervals[i][0] < prev[1]
     * IF we consider that (1,2) and (2,3) overlaps => use condition intervals[i][0] <= prev[1]
     */
    public int findMaxKrev(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int result = 0;
        int[] prev = null;//intervals[0];

        for (int i = 0; i < intervals.length; i++) {
            if (prev != null && intervals[i][0] < prev[1]) {
                //overlap!
                //idea: reset prev to the interval with max end!
                if (intervals[i][1] > prev[1]) {
                    prev = intervals[i];
                }
            } else {
                result++;
                prev = intervals[i];
            }
        }
        return result;
    }

    public void test() {
        int[][] intervals = {{1, 2}, {2, 3}, {6, 10}};
        int resultKrev = findMaxKrev(intervals);

        System.out.println(resultKrev);
    }
}