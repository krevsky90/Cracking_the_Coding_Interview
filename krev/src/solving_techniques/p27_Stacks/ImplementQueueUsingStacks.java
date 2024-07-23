package solving_techniques.p27_Stacks;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks (easy)
 * https://leetcode.com/problems/implement-queue-using-stacks
 * <p>
 * #Company: Yandex
 * <p>
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * <p>
 * Implement the MyQueue class:
 * <p>
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 * <p>
 * You must use only standard operations of a stack,
 * which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively.
 * You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 * <p>
 * Example 1:
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 * <p>
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 * <p>
 * Constraints:
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, peek, and empty.
 * All the calls to pop and peek are valid.
 * <p>
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
 */
public class ImplementQueueUsingStacks {
    /**
     * SOLUTION:
     * info: https://www.youtube.com/watch?v=eanwa3ht3YQ
     * idea:
     * use stack1 to push new elements to it => O(1)
     * use stack2 to pop elements from it.
     * BUT IF it is empty - we move elements from stack1 to stack2 and then push from stack2 => amortized O(1)
     *
     * time to implement ~ 5 mins
     *
     * BEATS ~ 100%
     */
    class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            int value = peek();
            stack2.pop();
            return value;
        }

        public int peek() {
            //if stack2 is empty => move all elements from stack1 to stack2
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }

            //then peek from stack2
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
