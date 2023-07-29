package data_structures.chapter2_linked_lists.LLfortechnicalInterview;

import data_structures.chapter2_linked_lists.LinkedListNode;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public static void main(String[] args) {
        LinkedListNode nodeA = new LinkedListNode(1);
        LinkedListNode nodeB = new LinkedListNode(2);
        LinkedListNode nodeC = new LinkedListNode(3);
        LinkedListNode nodeD = new LinkedListNode(4);
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        Integer[] linkedListValues = getLinkedListValuesRecursively(nodeA);
        System.out.println("");
    }

    public static Integer[] getLinkedListValues(LinkedListNode head) {
        List<Integer> result = new ArrayList<>();
        LinkedListNode current = head;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }

        return result.toArray(new Integer[1]);
    }

    public static Integer[] getLinkedListValuesRecursively(LinkedListNode head) {
        List<Integer> result = new ArrayList<>();
        fillLinkedListValuesRecursively(head, result);

        return result.toArray(new Integer[1]);
    }

    private static void fillLinkedListValuesRecursively(LinkedListNode head, List<Integer> result) {
        if (head != null) {
            result.add(head.value);
            fillLinkedListValuesRecursively(head.next, result);
        }
    }

    /**
     * time ~ O(n)
     * space ~ O(1)
     **/
    public static int sumList(LinkedListNode head) {
        int sum = 0;
        LinkedListNode current = head;
        while (current != null) {
            sum += current.value;
            current = current.next;
        }

        return sum;
    }

    /**
     * time ~ O(n)
     * space ~ O(n) due to callstack
     **/
    public static int sumListRecursively(LinkedListNode head) {
        if (head == null) return 0;
        int sum = head.value + sumList(head.next);
        return sum;
    }

    /**
     * time ~ O(n)
     * space ~ O(n) due to callstack
     **/
    public static boolean findValueInLinkedListRecursively(LinkedListNode head, int value) {
        //base cases
        if (head == null) return false;
        if (head.value == value) return true;

        return findValueInLinkedListRecursively(head.next, value);
    }

    /**
     * time ~ O(n)
     * space ~ O(n) due to callstack
     **/
    public static int getValueLinkedListRecursively(LinkedListNode head, int n) {
        if (head == null) return Integer.MIN_VALUE;    //like error
        if (n == 0) return head.value;

        return getValueLinkedListRecursively(head.next, n - 1);
    }

    /**
     * time ~ O(n)
     * space ~ O(1)
     * NOTE: the same problem is resolved recursively here src/data_structures/chapter8_recursion_and_dynamic_programming/Recursion_in_Programming_Full_Course/ReverseLinkedList.java
     **/
    public static LinkedListNode reverseLinkedList(LinkedListNode head) {
        if (head == null || head.next == null) return head;
        LinkedListNode current = head;
        LinkedListNode prevNode = null;

        while (current != null) {
            LinkedListNode nextNode = current.next;
            current.next = prevNode;
            prevNode = current;
            current = nextNode;
        }

        return prevNode;
    }

    public static LinkedListNode reverseLinkedListRecursivelyHelper(LinkedListNode head) {
        return reverseLinkedListRecursivelyHelper(head, null);
    }

    private static LinkedListNode reverseLinkedListRecursivelyHelper(LinkedListNode head, LinkedListNode prev) {
        if (head == null) return prev;

        LinkedListNode nextNode = head.next;
        head.next = prev;
        return reverseLinkedListRecursivelyHelper(nextNode, head);
    }

    /**
     * time ~ O(min(n,m)), where n and m - lengths of linked lists
     * space ~ O(1)
     **/
    public static LinkedListNode zipperLists(LinkedListNode head1, LinkedListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        int count = 0;
        //if both lists are not empty - we will return head1
        //also we can set tail = head1

        LinkedListNode tail = head1;

        LinkedListNode current1 = head1.next;
        LinkedListNode current2 = head2;

        while (current1 != null && current2 != null) {
            if (count % 2 == 0) {
                tail.next = current2;
                tail = current2;
                current2 = current2.next;
            } else {
                tail.next = current1;
                tail = current1;
                current1 = current1.next;
            }

            count++;
        }

        if (current2 == null) {
            tail.next = current1;
        } else if (current1 == null) {
            tail.next = current2;
        }

        return head1;
    }

    /**
     * time ~ O(min(n,m))
     * space ~ O(min(n,m))) - since we have to store data in stack of recursion
     */
    public static LinkedListNode zipperListsRecursively(LinkedListNode head1, LinkedListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        LinkedListNode next1 = head1.next;
        LinkedListNode next2 = head2.next;

        head1.next = head2;
        head2.next = zipperListsRecursively(next1, next2);

        return head1;
    }
}
