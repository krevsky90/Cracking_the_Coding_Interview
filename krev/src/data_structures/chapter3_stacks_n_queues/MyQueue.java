package data_structures.chapter3_stacks_n_queues;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private MyQueueNode<T> first;
    private MyQueueNode<T> last;

    // add item to the end of the list
    public void add(T data) {
        MyQueueNode<T> newNode = new MyQueueNode(data);
        if (last != null) {
            last.next = newNode;
        }
        last = newNode;
        if (first == null) {
            first = last;
        }
    }

    // Remove the first item in the list.
    public T remove() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        T data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return data;
    }

    // return the top of the queue.
    public T peek() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private static class MyQueueNode<T> {
        private T data;
        private MyQueueNode<T> next;

        public MyQueueNode(T data) {
            this.data = data;
        }
    }
}
