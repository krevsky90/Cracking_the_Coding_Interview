package data_structures.chapter3_stacks_n_queues;

import java.util.EmptyStackException;

/**
 * p.110
 * Stack Min: How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 * Hints: #27, #59, #78
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem3_2_StackWithMin {
    /**
     * SOLUTION #1:
     * One solution is to have just a single int value, minValue, that's a member of the Stack class. When
     * minValue is popped from the stack, we search through the stack to find the new minimum. Unfortunately,
     * this would break the constraint that push and pop operate in O(1) time.
     *
     * If we kept track of the minimum at each state, we would be able to easily know the minimum. We can do
     * this by having each node record what the minimum beneath itself is. Then, to find the min, you just look at
     * what the top element thinks is the min.
     *
     * When you push an element onto the stack, the element is given the current minimum. It sets its "local min" to be the min.
     */
    private MyStackNodeWithMin top;

    public void push(int data) {
        int min = Math.min(data, isEmpty() ? Integer.MAX_VALUE : peek().minOfSubstack);
        MyStackNodeWithMin newNode = new MyStackNodeWithMin(data, min);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    public MyStackNodeWithMin peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getMinValue() {
        return top.minOfSubstack;
    }
}
