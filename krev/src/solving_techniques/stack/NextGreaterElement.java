package solving_techniques.stack;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/652377d9b28abf3c6db3b596
 *
 * Given an array, print the Next Greater Element (NGE) for every element.
 * The Next Greater Element for an element x is the first greater element on the right side of x in the array.
 * Elements for which no greater element exist, consider the next greater element as -1.
 *
 * Example 1:
 *  Input: [4, 5, 2, 25]
 *  Output: [5, 25, 25, -1]
 *
 *  Example 2:
 *  Input: [13, 7, 6, 12]
 *  Output: [-1, 12, 12, -1]
 *
 * Example 3:
 *  Input: [1, 2, 3, 4, 5]
 *  Output: [2, 3, 4, 5, -1]
 *
 * Constraints:
 * 1 <= arr.length <= 10^4
 */

    //2 1 4 3
    //4 1
public class NextGreaterElement {
    public static void main(String[] args) {
        int[] arr2 = {13, 4, 6, 5, 12};
        int[] res2 = new NextGreaterElement().nextGreaterElementOptimized(arr2); //expected [-1, 5, 12, 12, -1]
        System.out.println("");
    }

    /**
     * NOT SOLVED by myself
     * info: https://www.youtube.com/watch?v=Dq_ObZwTY_Q
     * idea: use monotonic stack!
     * it gives us time ~ O(n) instead of naive solution that is about ~ O(n^2)
     */
    public int[] nextGreaterElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];

        //traverse from right to the left
        for (int i = arr.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                result[i] = -1;     //code duplication
            } else {
                while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = stack.peek();
                }
            }
            //we add i-th element to the stack in any case
            stack.add(arr[i]);
        }

        return result;
    }

    //or optimized
    public int[] nextGreaterElementOptimized(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];

        //traverse from right to the left
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            //we add i-th element to the stack in any case
            stack.add(arr[i]);
        }

        return result;
    }

}
