package data_structures.chapter2_linked_lists.amazon_igotanoffer.easy_linked_lists;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.ListNode;

/**
 * https://igotanoffer.com/blogs/tech/linked-list-interview-questions#basics
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * <p>
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * <p>
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 * <p>
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class Problem1_1_MergeTwoSortedLists {
    /**
     * KREVSKY SOLUTION
     * not-optimal
     * time to solve ~ 10-15 mins
     */
    public ListNode mergeTwoListsKREV(ListNode list1, ListNode list2) {
        if (list2 == null) return list1;
        if (list1 == null) return list2;

        ListNode head = null;
        ListNode tempLastNode = null;

        ListNode node1 = list1;
        ListNode node2 = list2;

        while (node1 != null || node2 != null) {
            ListNode tempNode = null;
            if (node1 == null) {    //yes, it is not optimal. it would be better to break the loop and attach the rest of list2 (i.e. node2) to tempLastNode
                tempNode = node2;
                node2 = node2.next;
            } else if (node2 == null) {    //yes, it is not optimal. it would be better to break the loop and attach the rest of list1 (i.e. node1) to tempLastNode
                tempNode = node1;
                node1 = node1.next;
            } else {
                if (node1.val >= node2.val) {
                    tempNode = node2;
                    node2 = node2.next;
                } else {
                    tempNode = node1;
                    node1 = node1.next;
                }
            }

            if (head == null) {
                head = tempNode;
                tempLastNode = tempNode;
            } else {
                tempLastNode.next = tempNode;
                tempLastNode = tempLastNode.next;
            }
        }

        return head;
    }

    /**
     * Optimal solution
     * https://leetcode.com/problems/merge-two-sorted-lists/solutions/3124870/java-easy-clean-0ms-100-beats/
     */
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;

        ListNode head = null;
        ListNode tail = null;
        if (a.val <= b.val) {
            head = tail = a;
            a = a.next;
        } else {
            head = tail = b;
            b = b.next;
        }

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                tail = a;
                a = a.next;
            } else {
                tail.next = b;
                tail = b;
                b = b.next;
            }
        }
        if (a == null) tail.next = b;
        else tail.next = a;
        return head;
    }
}
