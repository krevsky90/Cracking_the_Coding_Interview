package solving_techniques.p31_OrderedSet;

import java.util.Comparator;
import java.util.TreeSet;

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
     * NOT SOLVED by myself:
     * idea:
     * keep pair (idx, value)
     * to imitate stack - use TreeSet that keeps such pairs and sort them by idx
     * to sort pairs by value and then by stack - use another TreeSet
     * if we remove pair from one TreeSet => we need to remove pair with the same content (idx, value) from another TreeSet
     */
    private TreeSet<int[]> stack;
    private TreeSet<int[]> values;
    int cnt;

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        int[] input = {2, 1, 3, 6, 4, 6, 2, 1};
        for (int i : input) {
            maxStack.push(i);
        }
        System.out.println("");
    }

    public MaxStack() {
        stack = new TreeSet<>((a,b) -> a[0] - b[0]);
        values = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        cnt = 0;
    }

    public void push(int x) {
        stack.add(new int[]{cnt, x});
        values.add(new int[]{x, cnt});
        cnt++;
    }

    public int pop() {
        int[] pair = stack.pollLast();
        values.remove(new int[]{pair[1], pair[0]});
        return pair[1];
    }

    public int top() {
        return stack.last()[1];
    }

    public int peekMax() {
        return values.last()[0];
    }

    public int popMax() {
        int[] pair = values.pollLast();
        stack.remove(new int[]{pair[1], pair[0]});
        return pair[0];
    }
}
