package data_structures.chapter3_stacks_n_queues.extra;

public class StackFromQueuesTest {
    /**
     * Как видно из реализаций, есть два варианта
     * 1) pop - O(N), push - O(1)
     * 2) pop - O(1), push - O(N)
     * какую реализацию выбирать, зависит от частоты вызова этих методов.
     * Так что логично это уточнить на интервью
     */
    public static void main(String[] args) {
        StackFromQueues2 stack = new StackFromQueues2();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
    }
}
