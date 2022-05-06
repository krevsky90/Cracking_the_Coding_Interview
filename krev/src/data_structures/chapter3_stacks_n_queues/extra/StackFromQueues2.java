package data_structures.chapter3_stacks_n_queues.extra;

/**
 * https://www.youtube.com/watch?v=Qk7obcDReHE&list=PLNmW52ef0uwuvEW2yg2PxErsLF9ldA1WP&index=4&t=59s
 * Interview Question: Stack from Queues
 *
 * Implement a stack with basie functionality (push and pop) using queues to store the data
 *
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Current solution has push with O(1) and pop with O(N) time complexity
 */
public class StackFromQueues2 {
    private Queue<Integer> primary = new LinkedList<>();
    private Queue<Integer> secondary = new LinkedList<>();

    public void push(int x) {
        primary.add(x);
    }

    public int pop() {
        if (primary.isEmpty()) {
            throw new IndexOutOfBoundsException();    //for example
        }

        while(primary.size() > 1) {
            secondary.add(primary.remove());
        }
        int result = primary.remove();
        //rename queues to make primary queue non empty and secondary queue empty
        Queue temp = secondary;
        secondary = primary;
        primary = temp;

        return result;
    }
}
