package data_structures.chapter3_stacks_n_queues;

import java.util.Stack;

/**
 * p.111
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
 * Hints: # 15, #32, #43
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem3_5 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(7);
        stack.push(10);
        stack.push(5);
        stack.push(8);
        stack.push(3);
        stack.push(1);
        stack.push(12);

        System.out.println("");
        sortStack(stack);
        System.out.println("");

    }

    /**
     * SOLUTION #1:
     * to have 3 stacks:
     * s1 - original stack
     * s2 - final (sorted stack)
     * s3 - temp stack
     * Each time we
     * 1) find the min element of s1
     * 2) pop all elements above the min from s1 and push them to s3
     * 3) pop and push current min from s2 to s1
     * 4) (optionally since it can be optimized) pop and push all elements from s3 to s1
     * Problems:
     * 1) runtime complexity - O(N^2)
     * 2) we need 2 extra stack, but not 1 as required
     *
     */

    /**
     * SOLUTION #2:
     * we can sort sl by inserting each element from sl in order into s2.
     * Imagine we have the following stacks, where s2 is "sorted" and sl is not:
     * s1: 5 10 7
     * s2: 12 8 3 1
     * When we pop 5 from s1, we need to find the right place in s2 to insert this number. In this case, the correct
     * place is on s2 just above 3. How do we get it there? We can do this by popping 5 from sl and holding it
     * in a temporary variable (s1temp). Then, we move 12 and 8 over to s1 (by popping them from s2 and pushing them
     * onto s1) and then push 5 onto s2.
     * Note that 8 and 12 are still in s1 - and that's okay! We just repeat the same steps for those two numbers as
     * we did for 5.
     *
     * This algorithm is 0(N^2) time and 0(N) space.
     */
    public static void sortStack(Stack<Integer> s1) {
        Stack<Integer> s2 = new Stack();
        while (!s1.isEmpty()) {
            Integer s1temp = s1.pop();
            //find appropriate place in s2 to push 's1temp' value
            while (!s2.empty() && s2.peek() > s1temp) {
                s1.push(s2.pop());
            }
            s2.push(s1temp);
        }

        //copy elements from s2 back into s1
        while (!s2.empty()) {
            s1.push(s2.pop());
        }
    }
}