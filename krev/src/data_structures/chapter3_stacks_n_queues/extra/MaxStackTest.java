package data_structures.chapter3_stacks_n_queues.extra;

public class MaxStackTest {
    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        System.out.println(stack.max());
    }
}
