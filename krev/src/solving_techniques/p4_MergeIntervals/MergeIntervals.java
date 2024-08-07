package solving_techniques.p4_MergeIntervals;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63923e6de4cb724ea719007a
 * OR
 * 56. Merge Intervals (medium)
 * https://leetcode.com/problems/merge-intervals/
 * <p>
 * #Company: Yandex
 * <p>
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * Constraints:
 * 1 <= intervals.length <= 10000
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10000
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] arr1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] arr2 = {{1, 4}, {4, 5}};
        int[][] arr3 = {{1, 4}, {2, 6}, {3, 5}};

        int[][] result1 = merge(arr1);
        int[][] result2 = merge(arr2);
        int[][] result3 = merge(arr3);

        System.out.println("");
    }

    /**
     * my solution = https://leetcode.com/problems/merge-intervals/solutions/1699677/merge-overlapping-intervals-easy-java-solution-on/
     * time to solve ~ 30 mins WITHOUT SORTING! + 3 mins to see the idea to use Comparator
     * time to debug/test ~ 3 mins
     * <p>
     * time ~ O(N*logN)
     * <p>
     * 1 attempt
     */
    public static int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return result.toArray(new int[0][]);
        }

        //sort intervals - O(N*logN)
        Arrays.sort(intervals, (a, b) -> {
                    return a[0] - b[0];
                }
        );

        //once start points are sorted - merge intervals - O(logN)
        int start = intervals[0][0];
        int finish = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] tempInterval = intervals[i];

            if (tempInterval[0] > finish) {
                //i-th interval does not overlap [start, finish] => add [start, finish] to the result
                result.add(new int[]{start, finish});
                start = tempInterval[0];
                finish = tempInterval[1];
            } else {
                finish = Math.max(finish, tempInterval[1]);
            }
        }

        //add the last interval
        result.add(new int[]{start, finish});

        return result.toArray(new int[0][]);
    }

    /**
     * SOLUTION #2: idea is the same, but shorter
     * info: https://www.youtube.com/watch?v=0Ue1-11HCrs&list=PLUPSMCjQ-7ocAELVU7c5tMaFMo2bUQRF4&index=4
     */
    public static int[][] merge2(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return result.toArray(new int[0][]);
        }

        //sort intervals - O(N*logN)
        Arrays.sort(intervals, (a, b) -> {
                    return a[0] - b[0];
                }
        );

        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= result.get(result.size() - 1)[1]) {
                //but NOTE: we change the initial interval object, BUT which is added to result
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
            }
        }

        return result.toArray(new int[0][]);
    }
}
