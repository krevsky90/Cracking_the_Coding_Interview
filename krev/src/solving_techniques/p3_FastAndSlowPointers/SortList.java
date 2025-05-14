package solving_techniques.p3_FastAndSlowPointers;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.ListNode;

/**
 * 148. Sort List (medium)
 * https://leetcode.com/problems/sort-list
 * <p>
 * #Company (14.05.2025): 0 - 3 months Meta 6 Google 4 Amazon 3 Microsoft 2 Lyft 2 0 - 6 months Bloomberg 2 6 months ago Apple 7 TikTok 7 Adobe 6 Oracle 2 ByteDance 2 Uber 2 Accenture 2 Yahoo 2
 * <p>
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * <p>
 * Example 1:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * <p>
 * Example 2:
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * <p>
 * Example 3:
 * Input: head = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^5 <= Node.val <= 10^5
 * <p>
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortList {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15-20 mins
     * <p>
     * idea: merge sort
     * <p>
     * time ~ O(N*logN)
     * space ~ O(1)
     * <p>
     * 1 attempt:
     * <p>
     * BEATS ~ 96%
     */
    public ListNode sortList(ListNode head) {
        if (head == null) return head;

        ListNode end = head;
        while (end.next != null) {
            end = end.next;
        }

        return sortList(head, end);
    }

    private ListNode sortList(ListNode begin, ListNode end) {
        if (begin == end) return begin;
        ListNode fast = begin;  //fast pointer
        ListNode mid = begin;   //slow pointer
        ListNode prevMid = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prevMid = mid;
            mid = mid.next;
        }

        prevMid.next = null;    //break the link between 2 sublists

        ListNode begin1 = sortList(begin, prevMid);
        ListNode begin2 = sortList(mid, end);

        return merge(begin1, begin2);
    }

    /**
     * Official sortList method.
     * just less code since we don't have sortList(ListNode begin, ListNode end) method
     */
    public ListNode sortListOfficial(ListNode head) {
        if (head == null) return head;
        if (head.next == null) return head; //when list = 1 element, do nothing and return it

        ListNode fast = head;  //fast pointer
        ListNode mid = head;   //slow pointer
        ListNode prevMid = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prevMid = mid;
            mid = mid.next;
        }

        prevMid.next = null;    //break the link between 2 sublists

        ListNode begin1 = sortListOfficial(head);
        ListNode begin2 = sortListOfficial(mid);

        return merge(begin1, begin2);
    }

    private ListNode merge(ListNode begin1, ListNode begin2) {
        ListNode fakeHead = new ListNode();
        ListNode temp = fakeHead;

        while (begin1 != null && begin2 != null) {
            if (begin1.val < begin2.val) {
                temp.next = begin1;
                temp = begin1;
                begin1 = begin1.next;
            } else {
                temp.next = begin2;
                temp = begin2;
                begin2 = begin2.next;
            }
        }

        temp.next = (begin1 != null) ? begin1 : begin2; //just link the rest part of non-empty sublist

        return fakeHead.next;
    }
}
