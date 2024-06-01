package solving_techniques.p3_FastAndSlowPointers;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * 1721. Swapping Nodes in a Linked List
 * You are given the head of a linked list, and an integer k.
 * Return the head of the linked list after swapping the values of the kth node from the beginning
 * and the kth node from the end (the list is 1-indexed).
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * <p>
 * Example 2:
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * <p>
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 10^5
 * 0 <= Node.val <= 100
 */
public class SwappingNodesInLinkedList {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 17 mins
     * idea:
     * 1) find k-th element from the beginning
     * 2) find k-th element from the end (using 2 pointers and ga[ in k nodes between them)
     * 3) swap the value of these 2 found nodes
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 100%
     */
    public LinkedListNode swapNodes(LinkedListNode head, int k) {
        LinkedListNode node1 = head;
        int c1 = 1;
        while (c1 < k) {
            node1 = node1.next;
            c1++;
        }

        LinkedListNode node2 = head;
        LinkedListNode tempNode = node1;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
            node2 = node2.next;
        }

        int tempVal = node1.value;
        node1.value = node2.value;
        node2.value = tempVal;

        return head;
    }
}
