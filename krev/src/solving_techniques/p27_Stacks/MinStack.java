package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 155. Min Stack
 * https://leetcode.com/problems/min-stack
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Example 1:
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 * Constraints:
 *
 * -2^31 <= val <= 23^1 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 */
public class MinStack {
    /**
     * KREVSKY SOLUTION:
     * idea: store separate stack of min values. the sized of stacks are always the same! we add new min and existing min once again!
     * time to solve ~ 25 mins
     *
     * 2 attempts:
     * - initially did not add the same element as min value of updated stack
     */
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> stackOfMins = new Stack<>();

    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        if (stackOfMins.isEmpty() || val < stackOfMins.peek()) {
            stackOfMins.push(val);
        } else {
            stackOfMins.push(stackOfMins.peek());
        }
    }

    public void pop() {
        stack.pop();
        stackOfMins.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stackOfMins.peek();
    }
}
