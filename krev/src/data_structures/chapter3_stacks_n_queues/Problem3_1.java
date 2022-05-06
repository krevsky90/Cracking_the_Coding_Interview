package data_structures.chapter3_stacks_n_queues;

import java.util.EmptyStackException;

/**
 * p.110
 * Three in One: Describe how you could use a single array to implement three stacks.
 * Hints: #2, #12, #38, #58
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem3_1 {
    /**
     * SOLUTION:
     * Approach 1: Fixed Division
     * We can divide the array in three equal parts and allow the individual stack to grow in that limited space.
     * Note: We will use the notation "[" to mean inclusive of an end point and "(" to mean exclusive of an end point.
     * For stack 1 we will use [0, n/3)
     * For stack 2 we will use [n/3, 2n/3)
     * For stack 3 we will use [2n/3, n)
     */
    public static class FixedMultiStack {
        private int numberOfStacks = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;    //the number of elements in each stack

        public FixedMultiStack(int stackSize) {
            stackCapacity = stackSize;
            values = new int[stackCapacity*numberOfStacks];
            sizes = new int[numberOfStacks];
        }

        public void push(int stackNumber, int value) throws Exception {
            if (isFull(stackNumber)) {
                throw new Exception("FullStackException");
            }
            sizes[stackNumber]++;
            int topIndex = indexOfTop(stackNumber);
            values[topIndex] = value;
        }

        public int pop(int stackNumber) {
            if(isEmpty(stackNumber)) {
                throw new EmptyStackException();
            }
            int topIndex = indexOfTop(stackNumber);
            int value = values[topIndex];
            values[topIndex] = 0; // clear/remove node from stack
            sizes[stackNumber]--;
            return value;
        }

        public int peek(int stackNumber) {
            if(isEmpty(stackNumber)) {
                throw new EmptyStackException();
            }
            int topIndex = indexOfTop(stackNumber);
            return values[topIndex];
        }


        public boolean isEmpty(int stackNumber) {
            return sizes[stackNumber] == 0;
        }

        public boolean isFull(int stackNumber) {
            return sizes[stackNumber] >= stackCapacity;
        }

        //NOTE: top is the LAST element inserted to this stack/subarray
        // Because if we think that top is the first element of subarray,
        // we need to move all the elements of subarray each time when we add new element to this one (such operation costs O(n))
        private int indexOfTop(int stackNumber) {
            return stackCapacity*stackNumber + sizes[stackNumber] - 1;
        }
    }
}
