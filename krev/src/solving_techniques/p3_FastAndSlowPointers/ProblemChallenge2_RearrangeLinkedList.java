package solving_techniques.p3_FastAndSlowPointers;

import data_structures.chapter2_linked_lists.LinkedListNode;

import static data_structures.chapter2_linked_lists.LinkedListUtils.printLinkedList;

/**
 * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63923313ae2ec690ac22b61d
 * OR
 * 143. Reorder List
 * https://leetcode.com/problems/reorder-list
 *
 * Given the head of a Singly LinkedList, write a method to modify the LinkedList such
 * that the nodes from the second half of the LinkedList are inserted alternately to the nodes from the first half in reverse order.
 * So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.
 *
 * Your algorithm should use only constant space the input LinkedList should be modified in-place.
 *
 * Example 1:
 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> 12 -> null
 * Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null
 *
 */

public class ProblemChallenge2_RearrangeLinkedList {
    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(2);
        LinkedListNode n3 = new LinkedListNode(3);
        LinkedListNode n4 = new LinkedListNode(4);
        LinkedListNode n5 = new LinkedListNode(5);
        LinkedListNode n6 = new LinkedListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        printLinkedList(n1);
        rearrangeLinkedList(n1);
        printLinkedList(n1);

    }

    /**
     * https://www.geeksforgeeks.org/rearrange-a-given-linked-list-in-place/
     * Idea:
     * 1) split list into 2 lists
     * 2) reverse 2nd list (in-place)
     * 3) merge both lists
     */

    //1 - 2 - 3 - 4
    //        s
    public static void rearrangeLinkedList(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode prevSlow = head;
        LinkedListNode fast = head;

        //1) split list into 2 lists
        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //if the list has odd number of nodes - move slow -> slow.next
        //1 -> 2 -> 3
        //     s
        //          f
        if (fast != null) {
            prevSlow = slow;
            slow = slow.next;
        }

        //1 - 2 - 3 - 4
        //        s
        //    p
        //                f
        prevSlow.next = null;   //split into 2 sub-lists

        LinkedListNode start1 = head;

        // 2) reverse 2nd list (in-place)
        LinkedListNode start2 = reverseLinkedList(slow);

        // 3) merge both lists
        mergeLists(start1, start2);
    }

    private static void mergeLists(LinkedListNode node1, LinkedListNode node2) {
        while (node1 != null && node2 != null) {
            if (node1 != null) {
                LinkedListNode tempNext1 = node1.next;
                node1.next = node2;
                node1 = tempNext1;
            }

            if (node2 != null) {
                LinkedListNode tempNext2 = node2.next;
                node2.next = node1;
                node2 = tempNext2;
            }
        }
    }

    private static LinkedListNode reverseLinkedList(LinkedListNode head) {
        LinkedListNode prev = null;
        LinkedListNode cur = head;
        LinkedListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}