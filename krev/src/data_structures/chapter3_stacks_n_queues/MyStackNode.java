package data_structures.chapter3_stacks_n_queues;

public class MyStackNode<T> {
    public T data;
    public MyStackNode<T> next;

    public MyStackNode(T data) {
        this.data = data;
    }
}
