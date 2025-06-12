package solving_techniques.p9_TwoHeaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream (hard)
 * https://leetcode.com/problems/find-median-from-data-stream
 * OR
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639b685c5cda0fa79d72b471
 *
 * #Company: Amazon Apple Atlassian Bloomberg ByteDance eBay Expedia Facebook Goldman Sachs Google Microsoft Netflix Oracle Pinterest Qualtrics Snapchat Twitter Uber VMware Yahoo
 *
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 * Answers within 10^-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]P
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Constraints:
 * -10^5 <= num <= 10^5
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5*10^4 calls will be made to addNum and findMedian.
 *
 * Follow up:
 * #1: If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * #2: If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class FindMedianOfNumberStream {
    /**
     * NOT SOLVED by myself
     * idea: https://www.youtube.com/watch?v=1LkOrc-Le-Y
     * small addition from https://www.youtube.com/watch?v=itmhHWaHupI:
     *      min heap store bigger elements since we need to have the closest (to the median) value in the head.
     *      the same idea for max heap that stores lower elements
     *
     * time to learn + implement ~ 60 + 30 ~ 90 mins
     *
     * 3 attempts:
     * - wrote "Collections.reverse()" instead of "Collections.reverseOrder()"
     * - forgot to cast int -> double (by dividing by 2.0) in "(leftMaxHeap.peek() + rightMinHeap.peek())/2.0" row
     */
    private PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

    public FindMedianOfNumberStream() {
        //nothing to do
    }

    public static void main(String[] args) {
        FindMedianOfNumberStream obj = new FindMedianOfNumberStream();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
    }

    /**
     * time ~ O(1)
     */
    public double findMedian() {
        if (leftMaxHeap.size() == rightMinHeap.size()) {
            return (leftMaxHeap.peek() + rightMinHeap.peek())/2.0;  //NOTE: divide to double (2.0) to get double not rounded result!
        } else {
            //leftMaxHeap.size() > rightMinHeap.size()
            return leftMaxHeap.peek();
        }
    }

    /**
     * SOLUTION for add(..) method
     * https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%F0%9F%99%83%20Pattern%2009%3A%20Two%20Heaps.md
     */
    public void addNum2(int num) {
        if (leftMaxHeap.size() == 0 || leftMaxHeap.peek() >= num) {
            leftMaxHeap.offer(num);
        } else {
            rightMinHeap.offer(num);
        }

        rebalance(leftMaxHeap, rightMinHeap);
    }

    //re-balance so that rightMinHeap.size() <= leftMaxHeap.size() <= rightMinHeap.size() + 1
    private void rebalance(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap) {
        if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
            rightMinHeap.offer(leftMaxHeap.poll());
        } else if (rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.offer(rightMinHeap.poll());
        }
    }

    /**
     * Follow-up #1:
     * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
     *
     * idea:
     * see discussion of https://leetcode.com/problems/find-median-from-data-stream/description/
     * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
     * We can maintain an integer array of length 100 to store the count of each number along with a total count.
     * Then, we can iterate over the array to find the middle value to get our median.
     *
     * Time and space complexity would be O(100) = O(1).
     *
     * Example 1:
     * 1115539992
     *
     * map/array:
     * 1 2 3 5 9 - numbers
     * 2 1 1 2 3 - frequency
     * total amount of numbers = 10 =? 10/2 = 5
     *
     * set s = total/2
     * traverse through frequency part and s -= temp frequency
     * if (after subtraction of i-th frequency) s == 0 then the answer is (numbers[i] + number[i+1])/2.0
     *      example: 11233 55999
     * if s < 0 then the answer = numbers[i]
     *      example: 112355 999
     *
     * finally time ~ O(100) ~ O(1)
     */

    /**
     * Follow-up #2:  If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
     *
     * In this case, we need an integer array of length 100 and a hashmap for these numbers that are not in [0,100].
     * AS I understand:
     * we store number -> freq in the same way (i.e. in array) if 0 <= value <= 10
     * if numbers > 100, then put it top MAP: number -> freq (just to avoid too long array)
     *
     * do the sa,e as above:
     * set s = total/2
     * traverse through frequency part and s -= temp frequency
     * in theory we might end our array and still s > 0
     * then we will go through sorted keySet() of MAP
     *
     */

    /**
     * Follow-up #3: if the data is very very large. How would you handle it?
     *
     *  Answer is to use Tree instead of Heap (но чем это лучше? я хз)
     */

    //3 2 7 4
    //leftMaxHeap = 2 3
    //rightMinHeap = 4 7

    /**
     * KREVSKY IMPLEMENTATION:
     * offer ~ O(logN)
     * poll/remove ~ O(logN)
     * to total time complexity is from O(logN) to O(3*logN) - if we need to rebalance heaps => get O(logN)
     */
    public void addNum(int num) {
        if (leftMaxHeap.size() == 0) {
            leftMaxHeap.offer(num);
        } else {
            if (leftMaxHeap.peek() < num) {
                rightMinHeap.offer(num);
            } else {
                leftMaxHeap.offer(num);
            }

            //rebalance heaps if it is necessary
            if (leftMaxHeap.size() - rightMinHeap.size() > 1) {
                //move max element from left heap to the right heap
                int elementToMove = leftMaxHeap.poll();
                rightMinHeap.offer(elementToMove);
            } else if (rightMinHeap.size() - leftMaxHeap.size() > 1) {
                //move min element from right heap to the left heap
                int elementToMove = rightMinHeap.poll();
                leftMaxHeap.offer(elementToMove);
            }
        }
    }
}
