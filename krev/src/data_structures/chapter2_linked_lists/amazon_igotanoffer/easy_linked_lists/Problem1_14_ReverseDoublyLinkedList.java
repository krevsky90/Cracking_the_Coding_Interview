package data_structures.chapter2_linked_lists.amazon_igotanoffer.easy_linked_lists;

import data_structures.chapter2_linked_lists.amazon_igotanoffer.DoubleListNode;

/**
 * https://igotanoffer.com/blogs/tech/linked-list-interview-questions#basics
 * https://leetcode.com/discuss/interview-question/algorithms/125004/reverse-a-doubly-link-list
 */
public class Problem1_14_ReverseDoublyLinkedList {
    public static void main(String[] args) {

    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 30 mins
     * 1 attempt
     * not optimal since it is better to check node rather than node.next - see SOLUTION #2
     */
    public static void reverseDoubleLinkedList(DoubleListNode head) {
        DoubleListNode node = head;
        DoubleListNode prevNode = node.prev;
        DoubleListNode nextNode = node.next;

        while (node.next != null) {
            node.next = prevNode;
            node.prev = nextNode;

            prevNode = node;
            node = nextNode;
            nextNode = node.next;
        }

        //need to link the latest not null element
        node.next = prevNode;
        node.prev = nextNode;
    }

    /**
     * DO NOT agree with this solution since in there is no link from the first (former last) node
     * initial: null <- 1 <-> 2 -> null
     * final:      2 <- 1 -> null
     * so there is not next and prev links from 2 node
     */
    public static void reverseDoubleLinkedList2(DoubleListNode head) {
        DoubleListNode node = head;

        DoubleListNode prevNode = null;
        while (node != null) {
            DoubleListNode nextNode = node.next;

            node.next = prevNode;
            node.prev = nextNode;

            prevNode = node;
            node = nextNode;
        }
    }


}
