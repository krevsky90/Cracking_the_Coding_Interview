package data_structures.chapter3_stacks_n_queues.extra;

import java.util.EmptyStackException;

/**
 * https://www.youtube.com/watch?v=0bpDvc2VjPU&list=PLNmW52ef0uwuvEW2yg2PxErsLF9ldA1WP&index=1
 * <p>
 * Example for pop():
 * 8 (3) -> 2 (null) -> 3 (2) -> 2 (1) -> 1 (null), where nodeValue (oldMax)
 */

public class MaxStack {
    private class Node {
        private int value;
        private Node oldMax;
        private Node next;
    }

    private Node stack;
    private Node max;

    public void push(int x) {
        Node n = new Node();
        n.value = x;

        if (stack == null) {
            stack = n;
        } else {
            n.next = stack;
            stack = n;
        }

        //т.е. если новая нода не max, то у нее oldMax = null останется
        //иначе она затрекает предыд max значение
        if (max == null || n.value > max.value) {
            n.oldMax = max;
            max = n;
        }
    }

    public int pop() {
        if (stack == null) {
            throw new EmptyStackException();
        }

        Node n = stack;
        stack = stack.next;
        if (n.oldMax != null) {
            //or if (n == max) I think
            max = n.oldMax;
        }
        return n.value;
    }

    public int max() {
        if (max == null) throw new NullPointerException();
        return max.value;
    }

}
