package solving_techniques.p3_FastAndSlowPointers;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * 19. Remove Nth Node From End of List (medium)
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list
 *
 * #Company: Adobe Amazon Apple Bloomberg Cisco Facebook Goldman Sachs Google Microsoft Oracle Yandex
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * Constraints:
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * Follow up: Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * 1 attempt
     *
     * BEATS = 100%
     */
    public LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
        LinkedListNode fast = head;
        int i = 1;
        while (i < n) {
            fast = fast.next;
            i++;
        }

        LinkedListNode prev = null;
        LinkedListNode slow = head;

        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev != null) {
            prev.next = slow.next;
        } else {
            return slow.next;
        }

        return head;
    }
}
