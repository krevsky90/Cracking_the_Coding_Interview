package solving_techniques.p28_MonotonicStack;

import data_structures.chapter2_linked_lists.LinkedListNode;

import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/64c2049b13a8a4eb9452a6d6
 * OR
 * 2487. Remove Nodes From Linked List (medium)
 * https://leetcode.com/problems/remove-nodes-from-linked-list
 * <p>
 * You are given the head of a linked list.
 * <p>
 * Remove every node which has a node with a greater value anywhere to the right side of it.
 * <p>
 * Return the head of the modified linked list.
 * <p>
 * Example 1:
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * <p>
 * Example 2:
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 * <p>
 * Constraints:
 * The number of the nodes in the given list is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 */
public class RemoveNodesFromLinkedList {
    /**
     * KREVSKY SOLUTION:
     * idea #1: monotonic stack
     * idea #2: build sorted linked list inside stack's data
     *
     * time to solve ~ 12 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 2 attempts:
     * - forgot "current = current.next;"
     *
     * BEATS = 5%
     */
    public LinkedListNode removeNodes(LinkedListNode head) {
        LinkedListNode current = head;
        Stack<LinkedListNode> stack = new Stack<>();
        while (current != null) {
            while (!stack.isEmpty() && stack.peek().value < current.value) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                stack.peek().next = current;
            }

            stack.add(current);
            current = current.next;
        }

        //now ew have sorted stack of linked ListNodes
        //and head of this linked list is in the bottom of stack
        while (!stack.isEmpty()) {
            current = stack.pop();
        }

        return current;
    }
}
