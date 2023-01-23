package data_structures.chapter2_linked_lists.amazon_igotanoffer.easy_linked_lists;

import data_structures.chapter2_linked_lists.LinkedListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://igotanoffer.com/blogs/tech/linked-list-interview-questions
 * https://www.geeksforgeeks.org/print-reverse-of-a-linked-list-without-actually-reversing/
 *
 */
public class Problem1_13_PrintInReverse {
    public static void main(String[] args) {
        List<LinkedListNode> list = new LinkedList<>();
        LinkedListNode node1 = new LinkedListNode(1);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node3 = new LinkedListNode(3);
        node1.next = node2;
        node2.next = node3;
        printInReverse(node1);
    }

    /**
     * KREVSKY SOLUTION
     * time to solve ~ 2-3 mins
     */
    public static void printInReverse(LinkedListNode head) {
        if (head == null) return;

        printInReverse(head.next);
        System.out.println(head.value);
    }
}
