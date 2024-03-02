package solving_techniques.p14_KwayMerge;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a37843a41249d4abecb89a
 * OR
 * 378. Kth Smallest Element in a Sorted Matrix
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix
 *
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 *  You must find a solution with a memory complexity better than O(n^2).
 *
 * Example 1:
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Example 2:
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n^2
 *
 * Follow up:
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity?
 *      The solution may be too advanced for an interview but you may find reading this paper fun.
 *
 */
public class KthSmallestNumberInSortedMatrix {
    public static void main(String[] args) {
        int[][] arr = {{-5,-4},{-5,-4}};
        int k = 1;
        System.out.println(-5 + (-4+5)/2);
        System.out.println(new KthSmallestNumberInSortedMatrix().kthSmallestBinary(arr, k));
    }
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 45 mins
     * time (not sure 100%) ~ rowNum*log(rowNum) + (log(rowNum) + log(rowNum))*k ~ (rowNum + k)*log(rowNum)
     * space ~ O(rowNum)
     *
     * 2 attempts:
     * - incorrect type of Heap
     */
    public int kthSmallest(int[][] matrix, int k) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        int[] idxVector = new int[rowNum];
        //pair = {number of row, value[i]}
        Queue<Pair> pq = new PriorityQueue<>((a, b) -> a.value - b.value);  //min-Heap
        for (int i = 0; i < rowNum; i++) {
            pq.add(new Pair(i, matrix[i][0]));
        }

        int counter = 0;
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            counter++;
            if (counter == k) {
                return pair.value;
            }

            int rowId = pair.id;   //rowIdWithMinElement
            idxVector[rowId]++;
            if (idxVector[rowId] < colNum) {
                //push the next element of this row to the queue
                pq.add(new Pair(rowId, matrix[rowId][idxVector[rowId]]));
            }
        }

        return Integer.MIN_VALUE;  //dummy value for case when k > total elements in the matrix
    }

    /**
     * SOLUTION #3: use Binary Search
     * idea:
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2014%3A%20K-way%20merge.md
     *
     * time ~ O(N*Log(maxValue - minValue)), since each iteratior of BS we look we find:
     *  the biggest element <= midValue
     *  the smallest element > midValue
     *  the amount of elements that <= midValue
     *  => it takes N iterations
     * space ~ O(1)
     *
     * a lot of attempts:
     * - correct condition is "while (min < max)", but not "min <= max"
     * - set initial values for beforeMid, afterMid, counterBefore INSIDE while-loop
     * - mid = (min + max)/2 works INcorrectly fo negative numbers!! i.e. (-5-4)/2 = -4, rather than -5!
     *      so we need to write mid = min + (max - min)/2 !
     */
    public int kthSmallestBinary(int[][] matrix, int k) {
        int n = matrix.length;
        int min = matrix[0][0];
        int max = matrix[n-1][n-1];

        while (min < max) {
            int beforeMid = min;
            int afterMid = max;
            int counterBefore = 0;
            int mid = min + (max - min)/2;
            int c = 0;
            int r = n - 1;

            while (c < n && r >= 0) {
                if (matrix[r][c] <= mid) {
                    counterBefore += r + 1;
                    beforeMid = Math.max(beforeMid, matrix[r][c]);
                    c++;
                } else {
                    //we can calculate it each time OR...
                    // afterMid = Math.min(afterMid, matrix[r][c]);
                    r--;
                }
            }

            //...OR we just can set afterMid = mid + 1, since we use integer values
            afterMid = mid + 1;

            if (counterBefore == k) {
                return beforeMid;
            } else if (counterBefore < k) {
                min = afterMid;
            } else {
                max = beforeMid;
            }
        }

        return min;
    }


    class Pair {
        int id;    //row #
        int value;

        Pair (int rowNum, int value) {
            this.id = rowNum;
            this.value = value;
        }
    }
}
