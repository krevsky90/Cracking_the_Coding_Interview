package data_structures.chapter3_stacks_n_queues;

import java.util.*;

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
 */
public class Problem3_3_SetOfStacks<T> {
    private List<Stack> stacks = new ArrayList<>();
    private int capacity;

    public Problem3_3_SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    /**
     * SOLUTION:
     * We have to be a bit careful here though: if the last stack is at capacity,
     * we need to create a new stack. Our code should look something like this:
     */
    public void push(T item) {
        //choose stack
        Stack<T> currentStack = getLastStack();
        if (currentStack == null || isFull(currentStack)) {
            //create new empty stack
            Stack<T> stack = new Stack<>();
            stacks.add(stack);
            stack.push(item);
        } else {
            currentStack.push(item);
        }
    }

    /**
     * SOLUTION:
     * it should operate on the last stack. If the last stack is empty (after popping),
     * then we should REMOVE (!) the stack from the list of stacks.
     */
    public T pop() {
        Stack<T> currentStack = getLastStack();
        if (currentStack == null) {
            throw new EmptyStackException();
        }
        T item = currentStack.pop();    //if current stack was empty before popping, this call of pop() method throws EmptyStackException
        if (currentStack.isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }
        return item;
    }


    private Stack<T> getLastStack() {
        if (stacks.isEmpty()) return null;
        return stacks.get(stacks.size() - 1);
    }

    private boolean isFull(Stack stack) {
        return stack.size() >= this.capacity;
    }
}