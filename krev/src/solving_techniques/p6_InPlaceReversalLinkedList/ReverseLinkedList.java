package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394905626086d487e96e724
 * OR
 * 206. Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/submissions/
 *
 * Given the head of a Singly LinkedList, reverse the LinkedList.
 * Write a function to return the new head of the reversed LinkedList.
 */
public class ReverseLinkedList {
    /**
     * time to solve < 5 mins
     * time ~ O(n)
     * space ~ O(1)
     *
     * 1 attempt
     *
     */
    public LinkedListNode reverse(LinkedListNode head) {
        if (head == null) return head;

        LinkedListNode prev = null;
        LinkedListNode cur = head;

        while (cur != null) {
            LinkedListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
