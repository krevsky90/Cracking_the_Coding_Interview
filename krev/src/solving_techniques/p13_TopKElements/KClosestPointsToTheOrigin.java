package solving_techniques.p13_TopKElements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1cd9e19e6a3ce13cedcbc
 * OR
 * 973. K Closest Points to Origin
 * https://leetcode.com/problems/k-closest-points-to-origin/
 * <p>
 * Given an array of points in a 2D plane, find ?K? closest points to the origin.
 * <p>
 * Example 1:
 * Input: points = [[1,2],[1,3]], K = 1
 * Output: [[1,2]]
 * Explanation: The Euclidean distance between (1, 2) and the origin is sqrt(5).
 * The Euclidean distance between (1, 3) and the origin is sqrt(10).
 * Since sqrt(5) < sqrt(10), therefore (1, 2) is closer to the origin.
 * <p>
 * Example 2:
 * Input: point = [[1, 3], [3, 4], [2, -1]], K = 2
 * Output: [[1, 3], [2, -1]]
 */
public class KClosestPointsToTheOrigin {
    public static void main(String[] args) {
        int[][] arr2 = {{1, 3}, {3, 4}, {2, -1}};
        int k2 = 2;
        int[][] res2 = kClosest(arr2, k2);
        System.out.println("");

    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 12 mins
     *
     * 1 attempt
     */
    public static int[][] kClosest(int[][] points, int k) {
        //k closest => we should remove all farest => use max heap => comparator (a - b) should return 1
        Queue<int[]> q = new PriorityQueue<>((int[] a, int[] b) -> -a[0] * a[0] - a[1] * a[1] + b[0] * b[0] + b[1] * b[1]);

        for (int[] p : points) {
            q.add(p);
            if (q.size() > k) {
                q.remove();
            }
        }

        return q.toArray(new int[0][]);
    }
}
