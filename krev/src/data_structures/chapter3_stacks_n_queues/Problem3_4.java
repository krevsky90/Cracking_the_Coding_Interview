package data_structures.chapter3_stacks_n_queues;

/**
 * p.111
 * Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 * Hints: #98, #114
 * <p>
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem3_4 {
    public static void main(String[] args) {
        Problem3_4_MyQueue2<Integer> queue = new Problem3_4_MyQueue2();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.peekKrev());
        System.out.println(queue.removeKrev());
        System.out.println(queue.peekKrev());
        System.out.println(queue.removeKrev());
        System.out.println(queue.peekKrev());
        System.out.println(queue.removeKrev());
        queue.add(5);
        System.out.println(queue.peekKrev());

    }
}
