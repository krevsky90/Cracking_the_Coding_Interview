package data_structures.chapter2_linked_lists.amazon_igotanoffer.easy_linked_lists;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.ListNode;

/**
 * https://igotanoffer.com/blogs/tech/linked-list-interview-questions#basics
 * https://leetcode.com/problems/reverse-linked-list/
 * <p>
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class Problem1_4_ReverseLinkedList {
    /**
     * KREVSKY SOLUTION
     * iterative approach
     * time to solve 33 mins
     * 1 attempt
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode prevNode = null;
        ListNode curNode = head;
        ListNode nextnextNode = null;
        while (curNode != null) {
            ListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }

        return prevNode;
    }
}
