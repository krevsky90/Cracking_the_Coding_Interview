package solving_techniques.p4_MergeIntervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639363de4599b3e5714ab022
 * OR
 * https://leetcode.com/problems/meeting-rooms (locked)
 *
 * Given an array of intervals representing ?N? appointments, find out if a person can attend all the appointments.
 *
 * Example 1:
 * Appointments: [[1,4], [2,5], [7,9]]
 * Output: false
 * Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
 *
 * Example 2:
 * Appointments: [[6,7], [2,4], [8,12]]
 * Output: true
 * Explanation: None of the appointments overlap, therefore a person can attend all of them.
 *
 * Example 3:
 * Appointments: [[4,5], [2,3], [3,6]]
 * Output: false
 *
 */
public class ConflictingAppointments {
    public static void main(String[] args) {
        int[][] arr1 = {{1,4}, {2,5}, {7,9}};
        int[][] arr2 = {{6,7}, {2,4}, {8,12}};
        int[][] arr3 = {{4,5}, {2,3}, {3,6}};

        System.out.println(conflictAppointment(arr1));  //expected 'false'
        System.out.println(conflictAppointment(arr2));  //expected 'true'
        System.out.println(conflictAppointment(arr3));  //expected 'false'
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 5 mins
     * time ~ O(N*logN), where N = arr.length
     * space ~ O(N) - need for sorting
     *
     * 1 attempt
     */
    public static boolean conflictAppointment(int[][] arr) {
        List<int[]> list = new ArrayList<>();
        for (int[] a : arr) {
            list.add(a);
        }

        Collections.sort(list, (a,b) -> a[0] - b[0]);   //O(N*logN)

        for (int i = 1; i < arr.length; i++) {
            if (list.get(i)[0] < list.get(i-1)[1]) {
                return false;
            }
        }
        return true;
    }

    //extra problem from https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2004%20%3A%20Merge%20Intervals.md
    public static List<Pair> whatAreTheConflicts(int[][] arr)  {
        List<int[]> list = new ArrayList<>();
        for (int[] a : arr) {
            list.add(a);
        }

        Collections.sort(list, (a,b) -> a[0] - b[0]);   //O(N*logN)

        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (i != j && list.get(j)[0] < list.get(i)[1]) {
                    result.add(new Pair(list.get(i), list.get(j)));
                }
            }
        }

        return result;
    }

    static class Pair {
        public int[] first;
        public int[] second;

        Pair (int[] f, int[] s) {
            this.first = f;
            this.second = s;
        }
    }
}
