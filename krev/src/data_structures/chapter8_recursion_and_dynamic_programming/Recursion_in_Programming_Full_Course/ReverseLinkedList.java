package data_structures.chapter8_recursion_and_dynamic_programming.Recursion_in_Programming_Full_Course;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://youtu.be/IJDJ0kBx2LM?t=4189
 */
public class ReverseLinkedList {
    public static LinkedListNode reverseList(LinkedListNode head) {
        if (head == null || head.next == null) return head;

        LinkedListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return p;
    }
}
