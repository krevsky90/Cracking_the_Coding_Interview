package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.Collections;
import java.util.Stack;

/**
 * p.147
 * 8.6 Towers of Hanoi:
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
 * different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order
 * of size from top to bottom (Le., each disk sits on top of an even larger one). You have the following
 * constraints:
 * (1) Only one disk can be moved at a time.
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk.
 * Write a program to move the disks from the first tower to the last using stacks.
 * Hints: # 144, #224, #250, #272, #318
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_6 {
    /**
     * KREVSKY SOLUTION - START
     */
    /**
     * I've created separate class only for debugging
     */
    private static class MyStack<T> {
        public Stack<T> stack = new Stack<>();
        public String name;

        public MyStack(String name) {
            this.name = name;
        }

        public void push(T el) {
            stack.push(el);
        }

        public T pop() {
            return stack.pop();
        }

        public int size() {
            return stack.size();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public String toString() {
            return this.name + ":" + stack.toString();
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> src = new MyStack<Integer>("0");
        src.push(6);
        src.push(5);
        src.push(4);
        src.push(3);
        src.push(2);
        src.push(1);
        MyStack<Integer> dest = new MyStack<>("2");
        MyStack<Integer> buf = new MyStack<>("1");

        System.out.println("src: " + src.toString());

        int n = src.size();
        move(n, src, dest, buf, 0);

        System.out.println("dest: " + dest.toString());
    }

    public static <T> void move(int n, MyStack<T> src, MyStack<T> dest, MyStack<T> buf, int level) {
        String shift = " ".repeat(level);
        System.out.println(shift + "move(" + n + ", " + src.toString() + ", " + dest.toString() + ", " + buf.toString() + ")");
        if (src == null || n <= 0 || src.size() < n) {
            return;
        }

        if (n == 1) {
            buf.push(src.pop());
        } else if (n == 2) {
            buf.push(src.pop());
            dest.push(src.pop());
            dest.push(buf.pop());
        } else {
            move(n - 1, src, buf, dest, level + 1);
            dest.push(src.pop());
            move(n - 1, buf, dest, src, level + 1);
        }
        System.out.println(shift + "\tcur:" + src.toString() + " | " + dest.toString() + " | " + buf.toString());
    }
    /**
     * KREVSKY SOLUTION - END
     */
}