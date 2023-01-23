package data_structures.chapter2_linked_lists.amazon_igotanoffer.easy_linked_lists;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://igotanoffer.com/blogs/tech/linked-list-interview-question
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description
 *
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * IS IT THE SAME AS
 * src/data_structures/chapter2_linked_lists/Problem2_7.java
 */
public class Problem1_16_FindMergePointOfTwoLists {
    /**
     * KREVSKY SOLUTION
     */
    public static LinkedListNode findIntersection(LinkedListNode head1, LinkedListNode head2) {
        if (head1 == null || head2 == null) return null;

        int len1 = getLength(head1);
        int len2 = getLength(head2);

        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                head1 = head1.next;
            }
        } else {
            for (int i = 0; i < len2 - len1; i++) {
                head2 = head2.next;
            }
        }

        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (head1 == head2) {
                return head1;
            } else {
                head1 = head1.next;
                head2 = head2.next;
            }
        }

        return null;
    }

    private static int getLength(LinkedListNode head) {
        int length = 0;
        LinkedListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            length++;
        }

        return length;
    }
}
