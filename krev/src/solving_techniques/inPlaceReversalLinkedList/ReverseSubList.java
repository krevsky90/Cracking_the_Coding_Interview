package solving_techniques.inPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394ddf7a05eca1732753441
 * OR
 * 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 * <p>
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class ReverseSubList {
    /**
     * KREVSKY solution
     * time to solve ~ 29 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     */
    public LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
        LinkedListNode prevStart = null;
        LinkedListNode start = null;
        LinkedListNode postEnd = null;

        //traverse through the list and initialize the pointers above
        int i = 1;
        LinkedListNode prev = null;
        LinkedListNode cur = head;
        while (cur != null) {
            if (i == left) {
                prevStart = prev;
                start = cur;
            }

            if (i == right) {
                postEnd = cur.next;
                //break the link cur -> cur.next
                cur.next = null;
                //and stop iterating
                break;
            }

            prev = cur;
            cur = cur.next;
            i++;
        }

        //reverse subList with head = start
        LinkedListNode reversedList = reverse(start);

        //if left == 1 (and prevStart == null) => head will be changed
        if (left == 1) {
            head = reversedList;
        } else {
            prevStart.next = reversedList;
        }

        //find the last node of reversedList
        LinkedListNode lastNode = reversedList;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        //concat lastNode and postEnd
        lastNode.next = postEnd;

        return head;
    }

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