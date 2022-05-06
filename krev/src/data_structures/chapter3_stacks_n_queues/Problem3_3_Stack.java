package data_structures.chapter3_stacks_n_queues;

import java.util.EmptyStackException;

public class Problem3_3_Stack<T> {
    private int capacity;
    public MyDoubleNode<T> top, bottom;
    public int size = 0;

    public Problem3_3_Stack(int capacity) {
        this.capacity = capacity;
    }

    public boolean push(T v) {
        if (size >= capacity) return false;

        size++;
        MyDoubleNode newNode = new MyDoubleNode(v);
        if (size == 1) bottom = newNode;
        join(newNode, top);
        top = newNode;

        return true;
    }

    public T pop() {
        if (top == null) throw new EmptyStackException();

        T res = top.data;
        size--;
        top = top.below;
        return res;
    }

    public T removeBottom() {
        MyDoubleNode<T> b = bottom;
        bottom = bottom.above;
        if (bottom != null) bottom.below = null;
        size--;
        return b.data;
    }

    public void join(MyDoubleNode above, MyDoubleNode below) {
        if (below != null) below.above = above;
        if (above != null) above.below = below;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return capacity == size;
    }


    static class MyDoubleNode<T> {
        public T data;
        public MyDoubleNode above;
        public MyDoubleNode below;

        public MyDoubleNode(T data) {
            this.data = data;
        }
    }
}
