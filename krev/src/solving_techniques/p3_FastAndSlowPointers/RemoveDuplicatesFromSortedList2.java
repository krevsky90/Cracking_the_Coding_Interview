package solving_techniques.p3_FastAndSlowPointers;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.ListNode;

/**
 * 82. Remove Duplicates from Sorted List II (medium) (locked)
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii
 *
 * #Company (20.02.2025): 0 - 6 months Google 4 Meta 4 6 months ago Bloomberg 9 Amazon 8 Microsoft 7 Apple 4 Uber 3 Adobe 2 Tencent 2
 * <p>
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * <p>
 * Example 2:
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
public class RemoveDuplicatesFromSortedList2 {
    /**
     * NOT SOLVED by myself (mixed in fakehead and tracked next instead of cur)
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * BEATS ~ 100%
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fakeHead = new ListNode();
        fakeHead.next = head;

        ListNode prev = fakeHead;
        ListNode cur = head;

        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }

                prev.next = cur.next;
                cur = cur.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }

        return fakeHead.next;
    }
}
