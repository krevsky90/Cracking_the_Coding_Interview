package solving_techniques.p13_TopKElements;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1ceee19e6a3ce13ceddd0
 * OR
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks (premium)
 *
 * Given ?N? ropes with different lengths, we need to connect these ropes into one big rope with minimum cost.
 * The cost of connecting two ropes is equal to the sum of their lengths.
 *
 * Example 1:
 * Input: [1, 3, 11, 5]
 * Output: 33
 * Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)
 *
 * Example 2:
 * Input: [3, 4, 5, 6]
 * Output: 36
 * Explanation: First connect 3+4(=7), then 5+6(=11), 7+11(=18). Total cost is 36 (7+11+18)
 *
 * Example 3:
 * Input: [1, 3, 11, 5, 2]
 * Output: 42
 */
public class ConnectRopes {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 11, 5};
        System.out.println(connect(arr1));

        int[] arr2 = {3, 4, 5, 6};
        System.out.println(connect(arr2));

        int[] arr3 = {1, 3, 11, 5, 2};
        System.out.println(connect(arr3));

        int[] arr4 = {3,4};
        System.out.println(connect(arr4));

        int[] arr5 = {9};
        System.out.println(connect(arr5));
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     * time ~ O(n*h), since we iterate throw the queue n times, and each poll() and add() operation causes restructurization of the heap, where h - height of the heap, h = logn =>
     * => time ~ O(n*logn)
     * space ~ O(n)
     * idea: min cost will be in case if we connect 2 min elements each time
     * so we have to connect 2 mins elements, then add the result to the heap and repeat again
     **/
    public static int connect(int[] arr) {
        //corner case
        if (arr == null || arr.length <= 1) return 0;

        Queue<Integer> q = new PriorityQueue<>();   //min heap
        for (int a : arr) {
            q.add(a);
        }
        //for example #3: q = 1 2 3 5 11
        //q = 22
        int result = 0;
        while (q.size() > 1) {
            Integer n1 = q.poll();  //11
            Integer n2 = q.poll();  //11
            int n = n1 + n2;
            result += n;
            q.add(n);   // add 22
        }

        return result;
    }
}
