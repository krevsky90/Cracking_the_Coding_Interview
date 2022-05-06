package data_structures.chapter2_linked_lists;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.106
 * Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
 * the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
 * that node.
 * EXAMPLE
 * Input: the node c from the linked list a -> b -> c -> d -> e -> f
 * Result: nothing is returned, but the new linked list looks like a -> b -> d -> e -> f
 * Hints: #72
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) nodeToRemove == null
 * 2) nodeToRemove.next == null
 */
public class Problem2_3 {
    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(2);
        LinkedListNode n3 = new LinkedListNode(3);
        LinkedListNode n4 = new LinkedListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(linkedListToString(n1));
        deleteMiddleNode(n2);
        System.out.println(linkedListToString(n1));
    }

    /**
     * SOLUTION:
     * In this problem, you are not given access to the head of the linked list. You only have access to that node.
     * The solution is simply to copy the data from the next node over to the current node, and then to delete the
     * next node.
     */
    public static boolean deleteMiddleNode(LinkedListNode nodeToRemove) {
        if (nodeToRemove == null || nodeToRemove.next == null) {
            return false;   //failure
        }
        LinkedListNode next = nodeToRemove.next;
        LinkedListNode nextnext = next.next;

        nodeToRemove.value = next.value;
        //additional from KREVSKY
        next.next = null;

        nodeToRemove.next = nextnext;

        return true;
    }
}
