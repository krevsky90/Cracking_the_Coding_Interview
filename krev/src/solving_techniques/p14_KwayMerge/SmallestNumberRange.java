package solving_techniques.p14_KwayMerge;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a38178a70d5d4526eae293
 * OR
 * 632. Smallest Range Covering Elements from K Lists (hard)
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists
 * <p>
 * You have k lists of sorted integers in non-decreasing order.
 * Find the smallest range that includes at least one number from each of the k lists.
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 * <p>
 * Example 1:
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * <p>
 * Example 2:
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 * <p>
 * Constraints:
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -10^5 <= nums[i][j] <= 10^5
 * nums[i] is sorted in non-decreasing order.
 */
public class SmallestNumberRange {
    public static void main(String[] args) {
        List<List<Integer>> nums1 = new ArrayList<>();
        nums1.add(Arrays.asList(4,10,15,24,26));
        nums1.add(Arrays.asList(0,9,12,20));
        nums1.add(Arrays.asList(5,18,22,30));

        int[] res11 = new SmallestNumberRange().smallestRange(nums1);           //expected [20, 24]
        int[] res12 = new SmallestNumberRange().smallestRangeOptimized(nums1);  //expected [20, 24]
        System.out.println("[" + res11[0] + ", " + res11[1] + "]");
        System.out.println("[" + res12[0] + ", " + res12[1] + "]");

        //
        List<List<Integer>> nums2 = new ArrayList<>();
        nums2.add(Arrays.asList(1,9));
        nums2.add(Arrays.asList(4,12));
        nums2.add(Arrays.asList(7,10,16));

        int[] res2 = new SmallestNumberRange().smallestRange(nums2);
        System.out.println("[" + res2[0] + ", " + res2[1] + "]");
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 65 mins
     *
     * ~ 5 attempts
     * straightforward, but NOT OPTIMAL! (see optimal solution below)
     *
     * BEATS = 5%
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) return new int[0];

        int k = nums.size();

        //0. initialization
        List<Integer> positionsHistory = new ArrayList<>();
        List<Integer> listsHistory = new ArrayList<>();
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> (a.value - b.value)); //min heap

        for (int i = 0; i < k; i++) {
            pq.add(new Data(i, 0, nums.get(i).get(0)));
        }

        //1. continue inserting elements from the lists to queue:
        //max length of queue = 50*3500 = 175000 elements
        //each time we store index of polled element + number of the list that contains polled element
        while (!pq.isEmpty()) {
            Data data = pq.poll();
            positionsHistory.add(data.pos);
            listsHistory.add(data.listId);

            List<Integer> tempList = nums.get(data.listId);
            if (data.pos < tempList.size() - 1) {
                pq.add(new Data(data.listId, data.pos + 1, tempList.get(data.pos + 1)));
            }
        }

        //2. Apply 'Sliding window' for listsHistory
        int start = 0;
        int[] frequences = new int[k];
        int a = -1000000;
        int b = 1000000;
        for (int end = start; end < listsHistory.size(); end++) {
            frequences[listsHistory.get(end)]++;
            while (check(frequences, k)) {
                //NOTE: since we added the number (#) of lists in the same order as the values were in PG =>
                // => range can be defined by the information in end-th and start-th indexes of our arrays:
                int aListNumber = listsHistory.get(start);
                int bListNumber = listsHistory.get(end);
                int aPos = positionsHistory.get(start);
                int bPos = positionsHistory.get(end);
                int aNewValue = nums.get(aListNumber).get(aPos);
                int bNewValue = nums.get(bListNumber).get(bPos);
                if (bNewValue - aNewValue < b - a || (bNewValue - aNewValue == b - a && aNewValue < a)) {
                    a = aNewValue;
                    b = bNewValue;
                }

                frequences[listsHistory.get(start)]--;
                start++;
            }
        }

        return new int[]{a, b};
    }

    private boolean check(int[] arr, int k) {
        int sum = 0;
        for (int tmp : arr) {
            if (tmp > 0) sum++;
        }
        return sum == k;
    }

    /**
     * SOLUTION #2:
     * info: https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2014%3A%20K-way%20merge.md
     * idea:
     * 1) store and track a and b each time when we poll and add element to the queue
     * 2) stop the loop if we reach the end of tempList which contains polled value. There is no reason to continue,
     *      because non of the next polled elements will be left bound (i.e. 'a') that will keep some elements from the tempList
     *
     * time to implement ~ 15 mins
     * 2 attempts:
     * - forgot "maxNumber = Math.max(maxNumber, tempList.get(data.pos + 1));"
     *
     * BEATS = 84%
     */
    public int[] smallestRangeOptimized(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) return new int[0];

        int k = nums.size();

        //0. initialization
        PriorityQueue<Data> pq = new PriorityQueue<>((x, y) -> (x.value - y.value)); //min heap
        int a = -1000000;
        int b = 1000000;
        int maxNumber = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            pq.add(new Data(i, 0, nums.get(i).get(0)));
            //idea #1: track max inserted value
            maxNumber = Math.max(maxNumber, nums.get(i).get(0));
        }

        while (!pq.isEmpty()) {
            Data data = pq.poll();
            //idea #1: track min number to compare (a,b) and (minNumber,maxNumber)
            int minNumber = data.value;
            if (maxNumber - minNumber < b - a || (maxNumber - minNumber == b - a && minNumber < a)) {
                a = minNumber;
                b = maxNumber;
            }

            List<Integer> tempList = nums.get(data.listId);
            if (data.pos >= tempList.size() - 1) {
                //idea #2
                break;
            }

            //continue inserting the data from tempList
            pq.add(new Data(data.listId, data.pos + 1, tempList.get(data.pos + 1)));
            //and update maxNumber
            maxNumber = Math.max(maxNumber, tempList.get(data.pos + 1));
        }

        return new int[]{a, b};
    }



    class Data {
        int listId;  //number # of list
        int pos;    //index of value in the list with number = listId
        int value;

        Data(int listId, int pos, int value) {
            this.listId = listId;
            this.pos = pos;
            this.value = value;
        }
    }

}
