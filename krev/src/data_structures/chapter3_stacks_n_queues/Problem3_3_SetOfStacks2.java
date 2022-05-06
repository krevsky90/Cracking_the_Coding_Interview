package data_structures.chapter3_stacks_n_queues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * p.111
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks. push () and SetOfStacks. pop() should behave identically to a single stack
 * (that is, pop() should return the same values as it would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt (int index) which performs a pop operation on a specific substack.
 * Hints: #64, #87
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem3_3_SetOfStacks2<T> {
    /**
     * current class is to implement FOLLOW UP part!
     */
    private List<Problem3_3_Stack> stacks = new ArrayList<>();
    private int capacity;

    public Problem3_3_SetOfStacks2(int capacity) {
        this.capacity = capacity;
    }

    /**
     * ORIGINAL SOLUTION:
     * This method performs a pop operation on a specific substack.
     * SOLUTION for FOLLOW UP:
     * we can imagine a "rollover" system.
     * If we pop an element from stack 1, we need to remove the bottom of stack 2 and push it onto stack 1.
     * We then need to rollover from stack 3 to stack 2, stack 4 to stack 3, etc.
     */
    public T popAt(int index) {
        return shiftElements(index, true);
    }

    /**
     * ORIGINAL SOLUTION
     */
    private T shiftElements(int index, boolean removeTop) {
        Problem3_3_Stack<T> currentStack = stacks.get(index);
        T removeItem;
        if (removeTop) {
            removeItem = currentStack.pop();
        } else {
            removeItem = currentStack.removeBottom();
        }

        if (currentStack.isEmpty()) {
            stacks.remove(index);
        } else if (stacks.size() > index + 1) {
            T pushElement = shiftElements(index + 1, false);
            currentStack.push(pushElement);
        }
        return removeItem;
    }

    public void push(T item) {
        //choose stack
        Problem3_3_Stack<T> currentStack = getLastStack();
        if (currentStack == null || currentStack.isFull()) {
            //create new empty stack
            Problem3_3_Stack<T> stack = new Problem3_3_Stack<>(this.capacity);
            stacks.add(stack);
            stack.push(item);
        } else {
            currentStack.push(item);
        }
    }

    public T pop() {
        Problem3_3_Stack<T> currentStack = getLastStack();
        if (currentStack == null) {
            throw new EmptyStackException();
        }
        T item = currentStack.pop();    //if current stack was empty before popping, this call of pop() method throws EmptyStackException
        if (currentStack.isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }
        return item;
    }



    private Problem3_3_Stack<T> getLastStack() {
        if (stacks.isEmpty()) return null;
        return stacks.get(stacks.size() - 1);
    }
}
