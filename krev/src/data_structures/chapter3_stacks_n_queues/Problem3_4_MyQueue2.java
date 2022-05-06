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
public class Problem3_4_MyQueue2<T> {
    /**
     * SOLUTION #2:
     * Solution #1 will work, but if two pop / peeks are performed back-to-back, we're needlessly moving elements.
     * We can implement a "lazy" approach where we let the elements sit in s2 until we absolutely must reverse the
     * elements.
     * In this approach, stackNewest has the newest elements on top and stackOldest has the oldest
     * elements on top. When we dequeue an element, we want to remove the oldest element first, and so we
     * dequeue from stackOldest. If stackOldest is empty, then we want to transfer all elements from
     * stackNewest into this stack in reverse order. To insert an element, we push onto stackNewest, since it
     * has the newest elements on top.
     */
    private Stack<T> newestStack, oldestStack;

    public Problem3_4_MyQueue2() {
        newestStack = new Stack<>();
        oldestStack = new Stack<>();
    }

    /**
     * NOTE: we don't need to move elements from oldestStack to the newestStack before addition of some elements!
     * because newestStack provides us the last element that was added.
     * And if we remove element -> add element -> remove element, then the last 'remove' action will be applied to oldestStack (if it is not empty)
     *
     * Example: oldest[], newest[3->2->1] -- remove --> oldest[2->3], newest[]
     * -- add(4) --> oldest[2->3], newest[4] -- add(5) --> oldest[2->3], newest[5->4]
     * -- remove --> oldest[3], newest[5->4] --> remove --> oldest[], newest[5->4]
     * -- remove --> oldest[5], newest[]
     */
    public void add(T item) {
        newestStack.push(item);
    }

    public T removeKrev() {
        moveElementsFromNewestToOldest();
        return oldestStack.pop();
    }

    public T peekKrev() {
        moveElementsFromNewestToOldest();
        return oldestStack.peek();
    }

    private void moveElementsFromNewestToOldest() {
        if (oldestStack.isEmpty()) {
            //move all elements to oldestStack
            while (!newestStack.isEmpty()) {
                oldestStack.push(newestStack.pop());
            }
        }
    }

    public boolean isEmpty() {
        return newestStack.isEmpty();
    }
}
