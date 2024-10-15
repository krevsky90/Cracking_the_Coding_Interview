package solving_techniques.p4_MergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639249470cddc1617da1b889
 * OR
 * 986. Interval List Intersections (medium)
 * https://leetcode.com/problems/interval-list-intersections
 *
 * #Company:
 *
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Example 1:
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Example 2:
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
 * Constraints:
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 109
 * endi < starti+1
 * 0 <= startj < endj <= 109
 * endj < startj+1
 */
public class IntervalsIntersection {
    /**
     * KREVSKY SOLUTION:
     * idea: consider 6 cases of possible positions of two intervals - see picture here
     * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
     *
     * time to solve ~ 24 mins
     * time ~ O(n)
     *
     * 1 attempt
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            if (firstList[i][1] < secondList[j][0]) {
                //no overlapping and i-th interval < j-th interval
                i++;
            } else if (firstList[i][0] < secondList[j][0] && secondList[j][0] <= firstList[i][1] && firstList[i][1] < secondList[j][1]) {
                int[] toAdd = new int[]{secondList[j][0], firstList[i][1]};
                result.add(toAdd);
                i++;
            } else if (secondList[j][0] <= firstList[i][0] && firstList[i][1] <= secondList[j][1]) {
                int[] toAdd = new int[]{firstList[i][0], firstList[i][1]};
                result.add(toAdd);
                i++;
            } else if (firstList[i][0] <= secondList[j][0] && secondList[j][1] <= firstList[i][1]) {
                int[] toAdd = new int[]{secondList[j][0], secondList[j][1]};
                result.add(toAdd);
                j++;
            } else if (secondList[j][0] < firstList[i][0] && firstList[i][0] <= secondList[j][1] && secondList[j][1] < firstList[i][1]) {
                int[] toAdd = new int[]{firstList[i][0], secondList[j][1]};
                result.add(toAdd);
                j++;
            } else if (secondList[j][1] < firstList[i][0]) {
                //no overlapping
                j++;
            }
        }

        return result.toArray(new int[0][]);
    }

    public int[][] intervalIntersectionKrev2(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            if (firstList[i][1] < secondList[j][0]) {
                //no intersection, Ai before Bj
                i++;
            } else if (firstList[i][0] > secondList[j][1]) {
                //no intersection, Bj before Ai
                j++;
            } else {
                //intersection
                int tempStart = Math.max(firstList[i][0], secondList[j][0]);
                int tempEnd = Math.min(firstList[i][1], secondList[j][1]);
                result.add(new int[]{tempStart, tempEnd});

                if (firstList[i][1] < secondList[j][1]) {
                    i++;
                } else if (firstList[i][1] > secondList[j][1]) {
                    j++;
                } else {
                    //ends are equal
                    i++;
                    j++;
                }
            }
        }

        return result.toArray(new int[0][]);
    }

    /**
     * idea: https://leetcode.com/problems/interval-list-intersections/solutions/1593579/java-two-pointers-most-intutive/
     */
    public int[][] intervalIntersectionLeetCode(int[][] firstList, int[][] secondList) {
        if(firstList.length==0 || secondList.length==0) return new int[0][0];
        int i = 0;
        int j = 0;
        int startMax = 0, endMin = 0;
        List<int[]> ans = new ArrayList<>();

        while(i<firstList.length && j<secondList.length){
            startMax = Math.max(firstList[i][0],secondList[j][0]);
            endMin = Math.min(firstList[i][1],secondList[j][1]);

            //you have end greater than start and you already know that this interval is sorrounded with startMin and endMax so this must be the intersection
            if(endMin>=startMax){
                ans.add(new int[]{startMax,endMin});
            }

            //the interval with min end has been covered completely and have no chance to intersect with any other interval so move that list's pointer
            if(endMin == firstList[i][1]) i++;
            if(endMin == secondList[j][1]) j++;
        }

        return ans.toArray(new int[0][]);
    }
}

