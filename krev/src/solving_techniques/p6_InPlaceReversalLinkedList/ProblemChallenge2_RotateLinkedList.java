package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;
import data_structures.chapter2_linked_lists.LinkedListUtils;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394ef9cc6ebad0cc48b8076
 * OR
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/
 * <p>
 * Given the head of a linked list, rotate the list to the right by k places.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * <p>
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 */
public class ProblemChallenge2_RotateLinkedList {
    public static void main(String[] args) {
        //example #1
        LinkedListNode n11 = new LinkedListNode(1);
        LinkedListNode n12 = new LinkedListNode(2);
        LinkedListNode n13 = new LinkedListNode(3);
        LinkedListNode n14 = new LinkedListNode(4);
        LinkedListNode n15 = new LinkedListNode(5);
        n11.next = n12;
        n12.next = n13;
        n13.next = n14;
        n14.next = n15;

        int k1 = 2;
        LinkedListUtils.printLinkedList(rotateRight(n11, k1));

        //example #2
        LinkedListNode n20 = new LinkedListNode(0);
        LinkedListNode n21 = new LinkedListNode(1);
        LinkedListNode n22 = new LinkedListNode(2);
        n20.next = n21;
        n21.next = n22;

        int k2 = 4;
        LinkedListUtils.printLinkedList(rotateRight(n20, k2));
    }

    /**
     * KREVSKY SOLUTION:
     *
     * NOT optimal, since we traverse the whole list by for-loop and while-loop => time ~ O(n^2)
     * time to solve ~ 20 mins
     * time to debug ~ 4 mins
     * 1 attempt
     */

    // 0 -> 1 -> 2  => 2 -> 0 -> 1 = (set pointers to initial position for new cycle of rotation) => 2 -> 0 -> 1
    // s                    s                                                                        s
    //      p                   p                                                                    p
    //           e     e                                                                             e
    public static LinkedListNode rotateRight(LinkedListNode head, int k) {
        if (head == null || head.next == null) return head;

        //find real number of rotations
        int len = 0;
        LinkedListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        int rotations = k % len;

        LinkedListNode start = head;
        LinkedListNode prev = head;
        LinkedListNode end = head;

        for (int i = 0; i < rotations; i++) {
            //2.1) find positions
            while (end.next != null) {
                prev = end;
                end = end.next;
            }

            //2.2) and rotate
            end.next = start;
            prev.next = null;

            start = end;
            prev = end;
        }

        return end;
    }

    /**
     * KREVSKY SOLUTION: optimal
     * idea:
     * 0) count length of the list
     * 1) count real number of rotations = k % length
     * 2) find the node that will be new head, remove 'next' pointer, that points to this node, join the last node of the list to the initial head
     *
     * time to solve ~ 20 mins
     *
     * time ~ O(n)
     *
     * 2 attempts:
     * - incorrect linked the nodes (had to write "lastNode.next = head;")
     *
     * BEATS = 100%
     */
    public LinkedListNode rotateRight2(LinkedListNode head, int k) {
        if (head == null || head.next == null) return head;

        int length = 0;
        LinkedListNode cur = head;
        LinkedListNode lastNode = null;
        while (cur != null) {
            lastNode = cur;
            cur = cur.next;
            length++;
        }

        int rotates = k % length;
        if (rotates == 0) return head;

        int i = 1;
        cur = head;
        while (length - rotates > i) {
            cur = cur.next;
            i++;
        }
        LinkedListNode newHead = cur.next;
        cur.next = null;
        lastNode.next = head;

        return newHead;
    }
}