package data_structures.chapter2_linked_lists.amazon_igotanoffer.easy_linked_lists;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.ListNode;

/**
 * 21. Merge Two Sorted Lists (easy)
 * https://igotanoffer.com/blogs/tech/linked-list-interview-questions#basics
 * OR
 * https://leetcode.com/problems/merge-two-sorted-lists
 *
 * #Company: Adobe Airbnb Alibaba Amazon Apple Arista Networks Atlassian Bloomberg ByteDance Cisco Facebook Google Huawei Indeed Intel LinkedIn Lyft Microsoft Oracle Tencent Uber Visa VMware Walmart Labs Yahoo Yandex
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
     * KREVSKY SOLUTION #1
     * time to solve ~ 18 mins
     * time ~ O(n)
     * space ~ O(1)
     *
     * 2 attempts:
     * - wrote "list1 != null && list2 != null" but wanted to write "list1 != null || list2 != null"
     *
     * BEATS ~ 100%
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prev = new ListNode(); //fake node
        ListNode head = prev;
        ListNode temp = null;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                temp = list2;
                list2 = list2.next;
            } else if (list2 == null) {
                temp = list1;
                list1 = list1.next;
            } else {
                if (list1.val < list2.val) {
                    temp = list1;
                    list1 = list1.next;
                } else {
                    temp = list2;
                    list2 = list2.next;
                }
            }

            prev.next = temp;
            prev = prev.next;
        }

        return head.next;
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/merge-two-sorted-lists/solutions/3124870/java-easy-clean-0ms-100-beats/
     */
    public ListNode mergeTwoLists2(ListNode a, ListNode b) {
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
