package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.ListNode;

/**
 * 328. Odd Even Linked List (medium)
 * https://leetcode.com/problems/odd-even-linked-list
 * <p>
 * #Company: Amazon Bloomberg Capital One eBay Facebook Google Microsoft
 * <p>
 * Given the head of a singly linked list,
 * group all the nodes with odd indices together followed by the nodes with even indices,
 * and return the reordered list.
 * <p>
 * The first node is considered odd, and the second node is even, and so on.
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * <p>
 * Example 2:
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 * Constraints:
 * The number of nodes in the linked list is in the range [0, 10^4].
 * -10^6 <= Node.val <= 10^6
 */
public class OddEvenLinkedList {
    /**
     * KREVSKY SOLUTION:
     * <p>
     * time to solve ~ 18 mins
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode p1 = head;
        if (p1 == null || p1.next == null) return head;

        ListNode p2 = p1.next;
        ListNode evenHead = p2;

        boolean stop = false;
        while (!stop) {
            if (p1.next == null) {
                break;
            } else if (p1.next.next == null) {
                p1.next = null;
                stop = true;
            } else {
                ListNode nnp1 = p1.next.next;
                p1.next = nnp1;
                p1 = nnp1;
            }

            if (p2.next == null) {
                break;
            } else if (p2.next.next == null) {
                p2.next = null;
                stop = true;
            } else {
                ListNode nnp2 = p2.next.next;
                p2.next = nnp2;
                p2 = nnp2;
            }
        }

        p1.next = evenHead;

        return head;
    }

    /**
     * info: https://www.youtube.com/watch?v=c7cZjCVtTFs&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=135
     * idea: the same
     * + if p2 != null && p2.next != null => p1 and p1.next are NOT null!
     * + even if p2.next.next = null then p2.next = p2.next.next is OK
     */
    public ListNode oddEvenListOptimized(ListNode head) {
        ListNode p1 = head;
        if (p1 == null || p1.next == null) return head;

        ListNode p2 = p1.next;
        ListNode evenHead = p2;

        boolean stop = false;
        while (p2 != null && p2.next != null) {
            p1.next = p1.next.next;
            p1 = p1.next;

            p2.next = p2.next.next;   //might be null, but in this case in fact we set p2.next = null
            p2 = p2.next;
        }

        p1.next = evenHead;

        return head;
    }
}
