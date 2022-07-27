package data_structures.chapter2_linked_lists.extra;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://www.youtube.com/watch?v=lMxYBLqt1Mg&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=10
 * Byte by Byte: Split a Linked List
 * <p>
 * Given a linked list, write a function that divides it into two halves.
 * This function should modify the original list and then return a pointer to the second half of the list
 *
 * Example 1:
 * input: list = 1 -> 2 -> 3 -> 4 -> 5 -> null
 * output: 4 -> 5
 *
 * Example 2:
 * input: list = 1 -> 2 -> 3 -> 4 -> null
 * output: 3 -> 4
 *
 */
public class SplitLinkedList {
    /**
     * ORIGINAL SOLUTION:
     * don't forget to remove link between some node and the head of new sublist!
     */
    public LinkedListNode divide(LinkedListNode list) {
        if (list == null) return null;
        LinkedListNode runner = list.next;

        while (runner != null) {
            runner = runner.next;
            if (runner == null) break;
            runner = runner.next;
            list = list.next;
        }

        //remove link!
        LinkedListNode toReturn = list.next;
        list.next = null;
        return toReturn;
    }

    /**
     * KREVSKY SOLUTION - INCORRECT because I didn't remove link.
     * i.e. if input 1 - 2 - 3 - 4 - 5 then
     * my output: 4 - 5, BUT there is link 3 -> 4
     */
    public static LinkedListNode divideKREV(LinkedListNode head) {
        if (head == null) return null;
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        if (p2 != null) p1 = p1.next;

        return p1;
    }
}
