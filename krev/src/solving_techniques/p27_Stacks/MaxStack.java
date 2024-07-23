package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 716. Max Stack(easy) (blocked)
 * https://leetcode.com/problems/max-stack/
 * OR
 * https://leetcode.ca/all/716.html
 *
 * #Company: Yandex
 *
 * Design a max stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it.
 *      If you find more than one maximum elements, only remove the top-most one.
 *
 * Example 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 *
 * Note:
 * -1e7 <= x <= 1e7
 * Number of operations won't exceed 10000.
 * The last four operations won't be called when stack is empty.
 */
public class MaxStack {
    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        int[] arr1 = {1,5,4,2};

        for (int a : arr1) {
            maxStack.push(a);
        }

        System.out.println(maxStack.popMax());
        System.out.println("===");
    }
    /**
     * KREVSKY SOLUTION:
     * the same as https://github.com/awangdev/leet-code/blob/master/Java/716.%20Max%20Stack.java
     * idea:
     * Use two stacks:
     *     - one to keep regular elements
     *     - one to repeat the max at current stack level
     *
     * NOTE: the problem an also be solved by DoubleLinkedList and Treemap - see here https://leetcode.ca/2017-11-15-716-Max-Stack/
     * but this is too many lines of code
     */
    private Stack<Integer> stack;
    private Stack<Integer> max;

    public MaxStack() {
        stack = new Stack<>();
        max = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (max.isEmpty()) {
            max.push(x);
        } else {
            max.push(Math.max(x, max.peek()));
        }
    }

    public int pop() {
        max.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max.peek();
    }

    public int popMax() {
        int maxVal = peekMax();
        Stack<Integer> temp = new Stack<>();

        while (stack.peek() != maxVal) {
//            temp.push(stack.pop());
//            max.pop();
            temp.push(pop());
        }

        //remove/pop found max element:
        pop();

        //push back the elements from temp to stack. also update max stack
        while (!temp.isEmpty()) {
            push(temp.pop());
        }

        return maxVal;
    }
}