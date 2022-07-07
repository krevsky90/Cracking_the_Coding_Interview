package data_structures.chapter3_stacks_n_queues;

import java.util.Stack;

/**
 * p.110
 * Stack Min: How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 * Hints: #27, #59, #78
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem3_2_StackWithMin2 extends Stack<Integer> {
    /**
     * SOLUTION (optimization):
     * There's just one issue with this: if we have a huge stack, we waste a lot of space by keeping track of the min
     * for every single element. We can (maybe) do a bit better than this by using an additional stack which keeps track of the mins
     */
    //stack of min values
    private Stack<Integer> stackOfMins;

    public Problem3_2_StackWithMin2() {
        stackOfMins = new Stack<>();
    }

    public Integer push(Integer data) {
        super.push(data);
        if (stackOfMins.isEmpty() || stackOfMins.peek() > data) {
            stackOfMins.push(data);
        }
        return data;
    }

    public Integer pop() {
        Integer result = super.pop();
        if (result == stackOfMins.peek()) {
            stackOfMins.pop();
        }

        return result;
    }


}
