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
 * Current solution has push with O(N) and pop with O(1) time complexity
 */
public class StackFromQueues1 {
    private Queue<Integer> primary = new LinkedList<>();
    private Queue<Integer> secondary = new LinkedList<>();

    public void push(int x) {
        secondary.add(x);
        while (!primary.isEmpty()) {
            secondary.add(primary.remove());
        }
        //just rename queues
        Queue<Integer> temp = primary;
        primary = secondary;
        secondary = temp;
    }

    public int pop() {
        if (primary.isEmpty()) {
            throw new IndexOutOfBoundsException();    //for example
        }
        return primary.remove();
    }
}
