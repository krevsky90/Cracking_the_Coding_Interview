package solving_techniques.p14_KwayMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a376b4a41249d4abecb716
 *
 * Given ?M? sorted arrays, find the K?th smallest number among all the arrays.
 *
 * Example 1:
 * Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4], K=5
 * Output: 4
 * Explanation: The 5th smallest number among all the arrays is 4, this can be verified from
 * the merged list of all the arrays: [1, 2, 3, 3, 4, 6, 6, 7, 8]
 *
 * Example 2:
 * Input: L1=[5, 8, 9], L2=[1, 7], K=3
 * Output: 7
 * Explanation: The 3rd smallest number among all the arrays is 7.
 *
 */
public class KthSmallestNumberInMSortedLists {
    public static void main(String[] args) {
        Integer[] arr11 = {2, 6, 8};
        Integer[] arr12 = {3, 6, 7};
        Integer[] arr13 = {1, 3, 4};
        List<Integer[]> list1 = new ArrayList<>();
        list1.add(arr11);
        list1.add(arr12);
        list1.add(arr13);
        int k1 = 5;
        System.out.println(findKthSmallestElement(list1, k1));  //expected 4


        Integer[] arr21 = {5, 8, 9};
        Integer[] arr22 = {1, 7};
        List<Integer[]> list2 = new ArrayList<>();
        list2.add(arr21);
        list2.add(arr22);
        int k2 = 3;

        System.out.println(findKthSmallestElement(list2, k2));  //expected 7
    }

    /**
     * KREVSKY SOLUTION:
     * ideas:
     * 1) store pair "num of array that contains the value -> value" in Priority Queue 'pq'
     * 2) store the array of current indexes of elements of given arrays
     * 1 + 2 help us to get the array after the polling pair from 'pq' => we can find the next (i.e. curIdx + 1) element of this array and add it (its pair) to 'pq'
     * 3) store value (taken from polled pair) into separate Priority Queue 'pqValues' that will be restricted by given k
     *
     * So we just push pairs to 'pq' in the right order, poll them and push the value into 'pqValues'
     *
     * time to solve ~ 40 mins
     *
     * 1 attempt
     */
    public static int findKthSmallestElement(List<Integer[]> list, int k) {
        Queue<Pair> pq = new PriorityQueue<>((a, b) -> b.value - a.value);	//max heap
        Queue<Integer> pqValues = new PriorityQueue<>();

        int[] curIndexes = new int[list.size()];	//current indexes of the arrays that is in 'lists'
        for (int i = 0; i < list.size(); i++) {
            Integer[] tempArr = list.get(i);
            if (tempArr != null && tempArr.length > 0) {
                pq.add(new Pair(i, tempArr[0]));
            }
        }

        if (pq.isEmpty()) {
            return Integer.MAX_VALUE;	//like error-message
        }

        while (!pq.isEmpty()) {
            Pair tempPair = pq.poll();
            pqValues.add(tempPair.value);
            if (pqValues.size() > k) {
                pqValues.poll();
            }

            Integer[] currentArr = list.get(tempPair.arrNumber);
            int curId = curIndexes[tempPair.arrNumber];
            if (curId + 1 < currentArr.length) {
                pq.add(new Pair(tempPair.arrNumber, currentArr[curId + 1]));
                curIndexes[tempPair.arrNumber]++;
            }
        }

        return pqValues.peek();
    }

    static class Pair {
        int arrNumber;
        int value;

        public Pair(int arrNumber, int value) {
            this.arrNumber = arrNumber;
            this.value = value;
        }
    }
}
