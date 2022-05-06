package data_structures.chapter3_stacks_n_queues.extra;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=dQsZP8UvHVk&list=PLNmW52ef0uwuvEW2yg2PxErsLF9ldA1WP&index=6
 * <p>
 * Given a stack, reverse the items in the stack without using any additional data structure.
 * <p>
 * Идея:
 * т.е. нельзя юзать листы, массивы, стеки, очереди, то ПРИМЕНЯЕМ РЕКУРСИЮ! т.к. будем хранить значения, взятые из исходного стека, в переменных,
 * а эти переменные по сути будут помещаться в СТЕК ВЫЗОВОВ при рекурсивном подходе.
 * т.е. у нас получится неявное использование стека!
 */

//
public class ReverseStackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        reverseStack(stack);
        System.out.println("");
    }

    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int t = stack.pop();
        reverseStack(stack);    //move to the bottom of the stack and insertATBottom
        insertAtBottom(stack, t);
    }

    /**
     * @param stack
     * @param x     - element that should be pushed in the bottom of given stack
     */
    private static void insertAtBottom(Stack<Integer> stack, int x) {
        if (stack.isEmpty()) {
            stack.push(x);
        } else {
            int t = stack.pop();    //save temp top item in recursive callstack
            insertAtBottom(stack, x);
            stack.push(t);  //return t to the stack
        }
    }

}
