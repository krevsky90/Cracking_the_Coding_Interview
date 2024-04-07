https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/64beb1e31a5d38cdee24dfde

Theory:
Monotonic Stack ensures that the elements inside the stack remain in an increasing or decreasing order.
This is achieved by enforcing specific push and pop rules

1. Monotonically Increasing Stack
A Monotonically Increasing Stack is a stack where elements are arranged in an ascending order from the bottom to the top.
Here, every new element that's pushed onto the stack is greater than or equal to the element below it.
If a new element is smaller, we pop the elements from the top of the stack
    until we find an element smaller than or equal to the new element, or the stack is empty.
This way, the stack always maintains an increasing order.

2. Monotonically Decreasing Stack - vise versa

=== Identifying Problems Suitable for Monotonic Stack ===
Problem Characteristics:
Monotonic Stacks are typically useful when dealing with problems that involve analyzing sequences or arrays,
especially when you need to find the next or previous larger or smaller element for each element in the array.
If you encounter a problem where the solution seems to require some sort of sequential step-by-step comparison,
it's likely a good candidate for using a Monotonic Stack.

Example Scenarios:
1) description mentions finding the "next greater element" or the "next smaller element" in an array.
2) Problems that involve finding maximum areas, such as in histograms, can also be solved effectively using Monotonic Stacks.
    Remember, the key is to identify patterns where a sequential step-by-step comparison is necessary.

Constructing Monotonic Stacks:
Template:
create an empty stack
for each element in the array:
    while stack is not empty AND top of stack is less than the current element:
        pop the stack
    push the current element to stack

Implementation:
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

Understanding Time Complexity:
Each element is processed only twice, once for the push operation and once for the pop operation.
As a result, the time complexity remains linear - O(N), where N = arr.length

Sequence of problems:
1) Next Greater Element (1) - done
2) Daily Temperatures (easy/medium) - done
3) Remove Nodes From Linked List (easy/medium) - done
4) Remove All Adjacent Duplicates In String (easy) - todo
5) Remove All Adjacent Duplicates in String II (medium) - todo
6) Remove K Digits (hard) - todo