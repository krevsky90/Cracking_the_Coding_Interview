package data_structures.chapter3_stacks_n_queues;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * p.111
 * Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 * Hints: #98, #114
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem3_4_MyQueue<T> {
    /**
     * SOLUTION #1:
     * we know that we need to modify peek () and pop () to go in reverse order. We can use our second stack to
     * reverse the order of the elements (by popping s1 and pushing the elements on to s2). In such an implementation,
     * on each peek() and pop() operation, we would pop everything from s1 onto s2,
     * perform the peek / pop operation, and then push everything back.
     */
    private Stack<T> newestStack, oldestStack;

    public Problem3_4_MyQueue() {
        newestStack = new Stack<>();
        oldestStack = new Stack<>();
    }

    public void add(T item) {
        newestStack.push(item);
    }

    public T removeKrev() {
        if (newestStack.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            //move all elements (except the first one) to the other (oldest) stack
            while (newestStack.size() > 1) {
                oldestStack.push(newestStack.pop());
            }

            T result = newestStack.pop();

            //move back all elements from oldest stack to the original (newest) one
            while (!oldestStack.isEmpty()) {
                newestStack.push(oldestStack.pop());
            }

            return result;
        }
    }

    public T peekKrev() {
        if (newestStack.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            //move all elements (except the first one) to the other (oldest) stack
            while (newestStack.size() > 1) {
                oldestStack.push(newestStack.pop());
            }

            T result = newestStack.peek();

            //move back all elements from oldest stack to the original (newest) one
            while (!oldestStack.isEmpty()) {
                newestStack.push(oldestStack.pop());
            }

            return result;
        }
    }

    public boolean isEmpty() {
        return newestStack.isEmpty();
    }
}
