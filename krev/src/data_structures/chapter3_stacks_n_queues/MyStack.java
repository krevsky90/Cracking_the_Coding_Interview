package data_structures.chapter3_stacks_n_queues;

import java.util.EmptyStackException;

public class MyStack<T> {
    private MyStackNode<T> top;

    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    public void push(T data) {
        MyStackNode<T> node = new MyStackNode<>(data);
        node.next = top;
        top = node;
    }

    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

}
