package data_structures.chapter2_linked_lists;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.106
 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
 * before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
 * to be after the elements less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right partitions.
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 * NOTE!!! It seems like an error! because the solution from the book gives different result:
 * (3) -> (2) -> (1) -> (5) -> (8) -> (5) -> (10)
 * Hints: #3, #24
 *
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem2_4 {
    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(3);
        LinkedListNode n2 = new LinkedListNode(5);
        LinkedListNode n3 = new LinkedListNode(8);
        LinkedListNode n4 = new LinkedListNode(5);
        LinkedListNode n5 = new LinkedListNode(10);
        LinkedListNode n6 = new LinkedListNode(2);
        LinkedListNode n7 = new LinkedListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        System.out.println(linkedListToString(n1));
        LinkedListNode partitionResult = partitionKrevsky(n1, 5);
        System.out.println(linkedListToString(partitionResult));
    }

    /**
     * SOLUTION modified by Krevsky
     * we can actually create two different linked lists: one for elements less than x, and one for elements greater than or equal to x.
     * We iterate through the linked list, inserting elements into our before list or our after list. Once we reach
     * the end of the linked list and have completed this splitting, we merge the two lists.
     * This approach is mostly "stable" in that elements stay in their original order, other than the necessary movement
     * around the partition.
    /* Pass in the head of the linked list and the value to partition around */
    public static LinkedListNode partitionKrevsky(LinkedListNode node, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        while (node != null) {
            System.out.println("new node is " + node.value);
            LinkedListNode next = node.next;
            node.next = null;   //otherwise beforeStart ->  node -> next node -> .. And before list will include ALL initial linked list
            if (node.value < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = node;   //original solution has beforeEnd = beforeStart
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
                System.out.println("beforeList: " + linkedListToString(beforeStart));
            } else {
                if (afterStart == null ) {
                    afterStart = node;
                    afterEnd = node;    //original solution has afterEnd = afterStart
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
                System.out.println("afterList: " + linkedListToString(afterStart));
            }
            node = next;
        }

        //if before list is empty - return afterStart
        if (beforeStart == null) {
            return afterStart;
        }
        //concat before and after lists
        beforeEnd.next = afterStart;
        return beforeStart;
    }
}
