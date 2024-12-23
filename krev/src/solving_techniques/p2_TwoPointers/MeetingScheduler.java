package solving_techniques.p2_TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1229. Meeting Scheduler (medium)
 * https://leetcode.com/problems/meeting-scheduler/
 * <p>
 * #Company: 0 - 3 months Datadog 10 Google 2 Uber 2 0 - 6 months PayPal 2 6 months ago Amazon 3
 * <p>
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
 * return the earliest time slot that works for both of them and is of duration duration.
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 * It is guaranteed that no two availability slots of the same person intersect with each other.
 * That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 * <p>
 * Example 1:
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * <p>
 * Example 2:
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 * <p>
 * Constraints:
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 */
public class MeetingScheduler {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) sort by (for example) start pos
     * 2) use 2 pointers
     * 3) optimization: no nee to check slots that have length < given duration
     * <p>
     * time to solve ~ 20 mins
     * time ~ O(N1*logN1 + N2*logN2)
     * space ~ O(1)
     * <p>
     * 3 attempts:
     * - typo in constructions as Arrays.sort(slots1, (a, b) -> a[0] - b[0])
     * - incorrect condition for optimization: need to ise it for each slots array and increment corresponding counter
     * <p>
     * BEATS ~ 85%
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

        int i = 0;
        int j = 0;
        while (i < slots1.length && j < slots2.length) {
            int[] s1 = slots1[i];
            int[] s2 = slots2[j];
            //optimization
            if (s1[1] - s1[0] < duration) {
                i++;
                continue;
            }

            if (s2[1] - s2[0] < duration) {
                j++;
                continue;
            }

            int maxStart = Math.max(s1[0], s2[0]);
            int minEnd = Math.min(s1[1], s2[1]);
            if (minEnd - maxStart >= duration) {
                return Arrays.asList(maxStart, maxStart + duration);
            }

            if (s1[1] == s2[1]) {
                i++;
                j++;
            } else if (s1[1] > s2[1]) {
                j++;
            } else if (s1[1] < s2[1]) {
                i++;
            }
        }

        return new ArrayList<>();
    }
}
