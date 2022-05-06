package data_structures.chapter3_stacks_n_queues;

public class MyStackNodeWithMin {
    public int data;
    public MyStackNodeWithMin next;
    public int minOfSubstack;

    public MyStackNodeWithMin(int data, int min) {
        this.data = data;
        this.minOfSubstack = min;
    }
}
