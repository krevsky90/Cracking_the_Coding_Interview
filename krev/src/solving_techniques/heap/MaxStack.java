package solving_techniques.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * 716. Max Stack (hard)
 * https://leetcode.com/problems/max-stack
 * <p>
 * #Company (31.03.2025): 0 - 3 months LinkedIn 27 Amazon 3 Lyft 3 Meta 2 0 - 6 months Walmart Labs 4 TikTok 2 6 months ago Google 3 Oracle 2 Snap 2
 * <p>
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 * <p>
 * Implement the MaxStack class:
 * <p>
 * MaxStack() Initializes the stack object.
 * void push(int x) Pushes element x onto the stack.
 * int pop() Removes the element on top of the stack and returns it.
 * int top() Gets the element on the top of the stack without removing it.
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * Output
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 * <p>
 * Explanation
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] the top of the stack and the maximum number is 5.
 * stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
 * stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
 * stk.top();     // return 5, [5, 1, 5] the stack did not change.
 * stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
 * stk.top();     // return 1, [5, 1] the stack did not change.
 * stk.peekMax(); // return 5, [5, 1] the stack did not change.
 * stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
 * stk.top();     // return 5, [5] the stack did not change.
 * <p>
 * <p>
 * Constraints:
 * -10^7 <= x <= 10^7
 * At most 10^5 calls will be made to push, pop, top, peekMax, and popMax.
 * There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
 */
public class MaxStack {
    /**
     * NOT solved by myself, but the idea was similar (but less optimal)
     * <p>
     * idea:
     * 1) keep monotonically increasing counter to give smth like idx to added elements
     * 2) keep stack for top and pop operations, BUT st
     * 3) keep Priority Queue (max heap) of elements [idx, value], sorted by value, then - idx
     * 4) when we remove element from stack, we mark idx as removed => when we will retrieve smth from Heap, we will check if the elements is removed
     * 4.2) vise versa: when we remove element from Heap, we mark idx as removed => when we will retrieve smth from stack, we will check if the elements is removed
     * <p>
     * time to implement ~ 15 mins
     * <p>
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 80%
     */
    // stack =    2 1 3 6 4 6 2 1
    // maxStack = 2 2 3 6 6 6 6 6
    private Stack<int[]> stack;         //[0] - idx, [1] - value
    private PriorityQueue<int[]> pq;    //[0] - idx, [1] - value
    private Set<Integer> removed;   //idx of removed elements
    private int cnt = 0;

    public MaxStack() {
        stack = new Stack<>();
        //max heap: sort by value, then - by index (max value/index should ne on the top)
        pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
        removed = new HashSet<>();
    }

    public void push(int x) {
        stack.add(new int[]{cnt, x});
        pq.add(new int[]{cnt, x});
        cnt++;
    }

    public int pop() {
        while (removed.contains(stack.peek()[0])) {
            stack.pop();
        }

        int[] pair = stack.pop();
        removed.add(pair[0]);
        return pair[1];
    }

    public int top() {
        while (removed.contains(stack.peek()[0])) {
            stack.pop();
        }
        return stack.peek()[1];
    }

    public int peekMax() {
        while (removed.contains(pq.peek()[0])) {
            pq.poll();
        }
        return pq.peek()[1];
    }

    public int popMax() {
        while (removed.contains(pq.peek()[0])) {
            pq.poll();
        }
        int[] pair = pq.poll();
        removed.add(pair[0]);
        return pair[1];
    }
}
