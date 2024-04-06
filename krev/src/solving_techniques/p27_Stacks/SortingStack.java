package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/64902bd715f14528a3ef7363
 * <p>
 * Given a stack, sort it using only stack operations (push and pop).
 * <p>
 * You can use an additional temporary stack,
 * but you may not copy the elements into any other data structure (such as an array).
 * The values in the stack are to be sorted in descending order, with the largest elements on top.
 * <p>
 * Examples
 * 1. Input: [34, 3, 31, 98, 92, 23]
 * Output: [3, 23, 31, 34, 92, 98]
 * <p>
 * 2. Input: [4, 3, 2, 10, 12, 1, 5, 6]
 * Output: [1, 2, 3, 4, 5, 6, 10, 12]
 * <p>
 * 3. Input: [20, 10, -5, -1]
 * Output: [-5, -1, 10, 20]
 */

//NOTE: the same as src/data_structures/chapter3_stacks_n_queues/Problem3_5.java
public class SortingStack {
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 10, 12, 1, 5, 6};
        Stack<Integer> stack = new Stack<>();
        for (int i : arr) {
            stack.add(i);
        }

        //NOTE: looks like the examples are differs from the requirement "sorted in descending order, with the largest elements on top."
        //so I solved to meet the requirements
        new SortingStack().sortStack(stack);
        ;
        String res = "";
        while (!stack.isEmpty()) {
            res += stack.pop() + ", ";
        }

        System.out.println(res);
    }

    /**
     * NOT SOLVED by myself
     * info: https://www.youtube.com/watch?v=K0XXVSL4wUo
     * but I inverted the solution since I need max value on top of stack
     * <p>
     * time to implement ~ 8 mins
     * <p>
     * 1 attempt
     */
    public void sortStack(Stack<Integer> srcStack) {
        Stack<Integer> tempStack = new Stack<>();

        while (!srcStack.isEmpty()) {
            int el = srcStack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() < el) {
                srcStack.add(tempStack.pop());
            }
            tempStack.add(el);
        }

        //move all elements from tempStack to srcStack
        while (!tempStack.isEmpty()) {
            srcStack.add(tempStack.pop());
        }
    }

    /**
     * SOLUTION #2: recursive
     * info:
     * https://leetcode.com/discuss/interview-question/algorithms/125398/given-a-stack-sort-it-in-non-decreasing-order
     */
    public void sortStack2(Stack<Integer> s) {
        // base case
        if (s.size() == 0 || s.size() == 1) {
            return;
        }
        int temp = s.peek();
        s.pop();
        sortStack2(s);
        insert(s, temp);
    }

    private void insert(Stack<Integer> s, int temp) {
        if (s.empty() || s.peek() < temp) {
            s.push(temp);
            return;
        }

        int val = s.peek();
        s.pop();
        insert(s, temp);
        s.push(val);
    }
}
