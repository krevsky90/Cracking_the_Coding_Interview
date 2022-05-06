package data_structures.chapter2_linked_lists;

import java.util.HashSet;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.106
 * <p>
 * 2.1. Remove Duplicates: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 * Hints: #9, #40
 * <p>
 * ASSUMPTION:
 */
public class Problem2_1 {
    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(1);
        LinkedListNode n3 = new LinkedListNode(3);
        n1.next = n2;
        n2.next = n3;

        System.out.println(linkedListToString(n1));
        removeDuplicates(n1);

        System.out.println(linkedListToString(n1));
    }

    //KREVSKY
    public static void removeDuplicates(LinkedListNode n) {
        if (n == null || n.next == null) return;
        LinkedListNode p1 = n;
        while (p1 != null) {
            LinkedListNode p2 = p1.next;
            while (p2 != null) {
                if (p1.value == p2.value) {
                    p1.next = p2.next; // it is correct even if p2 is the last element of list
                }
                p2 = p2.next;
            }
            p1 = p1.next;
        }
    }

    /**
     * SOLUTION with hashTable:
     * In order to remove duplicates from a linked list, we need to be able to track duplicates. A simple hash table
     * will work well here.
     * In the below solution, we simply iterate through the linked list, adding each element to a hash table. When
     * we discover a duplicate element, we remove the element and continue iterating. We can do this all in one
     * pass since we are using a linked list.
     */
    public static void deleteDups(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.value)) {
                previous.next = n.next;
            } else {
                set.add(n.value);
                previous = n;
            }
            n = n.next;
        }
    }

    /**
     * SOLUTION using 2 pointers = like KREVSKY
     */
    public static void deleteDups2(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
            /* Remove all future nodes that have the same value */
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.value == current.value) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
                current = current.next;
            }
        }
    }
}
