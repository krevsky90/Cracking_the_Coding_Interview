package data_structures.heap;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * IDEA: k-th smallest => use MaxHeap => PriorityQueue
 * k-th largest => use MinHeap => PriorityQueue.
 * we should remove (poll) top element once the size of queue is more than k
 * In case of Min Heap we will get the heap that has k the most big values and the top value is the smallest one
 */
public class KthLargestElementOfArray {

    public static void main(String[] args) {
        int[] arr = new int[]{4,9,5,1,7,6,2};
        int k = 3;

        int result = getKthLargestOfArray(arr, k);
        System.out.println(result);
    }

    public static int getKthLargestOfArray(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //min heap
        for (int i : arr) {
            pq.add(i);

            if (pq.size() > k) {
                pq.poll();  //remove top element that is the smallest element of current heap
            }
        }

        //here we have heap that has k the most big values
        //and the top value is the smallest one => k-th largest element of the array
        return pq.peek();
    }
}
