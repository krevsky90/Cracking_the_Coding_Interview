package solving_techniques.p28_MonotonicStack;

import java.util.Stack;

/**
 * 1944. Number of Visible People in a Queue (hard)
 * https://leetcode.com/problems/number-of-visible-people-in-a-queue
 * <p>
 * #Company: Facebook ???
 * <p>
 * There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order.
 * You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
 * <p>
 * A person can see another person to their right in the queue if everybody in between is shorter than both of them.
 * More formally,
 * the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
 * <p>
 * Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
 * <p>
 * Example 1:
 * Input: heights = [10,6,8,5,11,9]
 * Output: [3,1,2,1,1,0]
 * Explanation:
 * Person 0 can see person 1, 2, and 4.
 * Person 1 can see person 2.
 * Person 2 can see person 3 and 4.
 * Person 3 can see person 4.
 * Person 4 can see person 5.
 * Person 5 can see no one since nobody is to the right of them.
 * <p>
 * Example 2:
 * Input: heights = [5,1,2,3,10]
 * Output: [4,1,1,1,0]
 * <p>
 * Constraints:
 * n == heights.length
 * 1 <= n <= 10^5
 * 1 <= heights[i] <= 10^5
 * All the values of heights are unique.
 */
public class NumberOfVisiblePeopleInQueue {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use monotonic stack
     * 2) each time when we pop() element from the stack, we increase counter/result for i-th position
     * 3) if stack is still not empty after the step 2, then we add +1 to the result
     * <p>
     * time to solve ~ 20 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     *
     * BEATS ~ 50%
     */
    public int[] canSeePersonsCount(int[] heights) {
        int[] result = new int[heights.length];

        Stack<Integer> stack = new Stack<>();

        for (int i = heights.length - 1; i >= 0; i--) {
            int counter = 0;
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                counter++;
            }

            if (!stack.isEmpty()) {
                //since i-th element 'sees' the top element of the stack
                counter++;
            }

            result[i] = counter;

            stack.add(heights[i]);
        }

        return result;
    }
}
