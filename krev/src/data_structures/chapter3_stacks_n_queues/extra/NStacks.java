package data_structures.chapter3_stacks_n_queues.extra;

import java.util.EmptyStackException;

/**
 * https://www.youtube.com/watch?v=DxW7VAsdX0o&list=PLNmW52ef0uwuvEW2yg2PxErsLF9ldA1WP&index=3
 * Interview Question: N Stacks
 *
 * Implement N > 0 stacks using a single arrays to store all stack data.
 * No stack should ne full unless the entire array is full
 *
 * BEST explanation: https://www.youtube.com/watch?v=S5cYO9k1Ja8 (GeeksforGeeks: K stacks in a single array)
 */
public class NStacks {
    public int[] topOfStack;
    public int[] stackData;
    public int[] nextIndex;
    public int nextAvailable = 0;

    /**
     * topOfStack = {-1, -1, -1}
     * stackData = {0, 0, 0, 0, 0, 0}
     * nextIndex = {1, 2, 3, 4, 5, -1}
     */
    public NStacks(int numStacks, int capacity) {
        this.topOfStack = new int[numStacks];
        for (int i = 0; i < numStacks; i++) {
            this.topOfStack[i] = -1;    //means i-th stack is empty (otherwise the value = the index in stackData
        }
        this.stackData = new int[capacity];
        this.nextIndex = new int[capacity];
        for (int i = 0; i < capacity - 1; i++) {
            nextIndex[i] = i+1;
        }
        nextIndex[capacity - 1] = -1;   //-1 means there is not next index
    }

    /**
     * Logic: https://www.youtube.com/watch?v=S5cYO9k1Ja8 (GeeksforGeeks: K stacks in a single array)
     * where to push the element? stackData[currentIndex]
     * what is the next free spot? nextIndex[currentIndex]
     * Update the top of stack:
     * - the next element becomes top of stack. Update its index in topOfStack[stack]
     * - BUT before that, link the current top as the next element of new top
     */
    public void push(int stack, int value) {
        if (stack < 0 || stack > topOfStack.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        //if we have available place to push
        if (nextAvailable < 0) {
            System.out.println("All stacks are full");
            return;
        }

        /**
         * Example:
         * push(0, 5)
         * push(1, 6)
         * topOfStack    = {0, 1, -1}
         * stackData     = {5, 6, 0, 0, 0, 0}
         * nextIndex     = {-1, -1, 3, 4, 5, -1}
         * nextAvailable = 2
         * currentIndex  = 1
         *
         */
        int currentIndex = nextAvailable;               //store index of the first free slot
        nextAvailable = nextIndex[currentIndex];        //update index of free slot to index of next slot in free list
        stackData[currentIndex] = value;                //put the item in the array
        nextIndex[currentIndex] = topOfStack[stack];    //update next of top (чтобы хранить индекс предыд эл-та данного стека)
        topOfStack[stack] = currentIndex;               //update top for given stack
        System.out.println("");
    }

    /**
     * Logic: https://www.youtube.com/watch?v=S5cYO9k1Ja8 (GeeksforGeeks: K stacks in a single array)
     * which element to pop?  topOfStack[stack]
     * what is the new top of stack?    Next of prev top, i.e. nextIndex[currentIndex];
     * Update free:
     * - now the popped index should be made as free (nextAvailable = currentIndex)
     * - BUT before that, the current free should be linked as the next available free spot (nextIndex[currentIndex] = nextAvailable)
     */
    public int pop(int stack) {
        if (stack < 0 || stack > topOfStack.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        //check if the stack is empty
        if (topOfStack[stack] == -1) {
            throw new EmptyStackException();
        }

        int currentIndex = topOfStack[stack];       //find index of top item in given stack
        int value = stackData[currentIndex];        //save the top item
        topOfStack[stack] = nextIndex[currentIndex];//change top to store next of previous top

        //attach the previous top to the beginning of free list
        nextIndex[currentIndex] = nextAvailable;
        nextAvailable = currentIndex;

        return value;
    }
}
