package data_structures.chapter2_linked_lists;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.107
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a
 * function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295 = 912
 * Output: 2 -> 1 -> 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295 = 912
 * Output: 9 -> 1 -> 2. That is, 912.
 * Hints: #7, #30, #71, #95, #109
 *
 * ASSUMPTION/VALIDATION:
 *
 */
public class Problem2_5_follow_up {
    public static void main(String[] args) {
        LinkedListNode n11 = new LinkedListNode(7);
        LinkedListNode n12 = new LinkedListNode(1);
        LinkedListNode n13 = new LinkedListNode(6);
        n11.next = n12;
        n12.next = n13;
        System.out.println(linkedListToString(n11));

        LinkedListNode n21 = new LinkedListNode(5);
        LinkedListNode n22 = new LinkedListNode(9);
        LinkedListNode n23 = new LinkedListNode(2);
        n21.next = n22;
        n22.next = n23;
        System.out.println(linkedListToString(n21));

        LinkedListNode sum = addLists(n11, n21);
        System.out.println(linkedListToString(sum));
    }

    static class PartialSum {
        public LinkedListNode sum = null;
        public int carry = 0;
    }

    /**
     * One list may be shorter than the other, and we cannot handle this "on the fly:' For example, suppose we
     * were adding (1 -> 2 -> 3 -> 4) and (5 -> 6 -> 7). We need to know that the 5 should be "matched"with the
     * 2, not the 1. We can accomplish this by comparing the lengths of the lists in the beginning and padding
     * the shorter list with zeros.
     * 2. In the first part, successive results were added to the tail (Le., passed forward). This meant that the recursive
     * call would be passed the carry, and would return the result (which is then appended to the tail) . In
     * this case, however, results are added to the head (Le., passed backward). The recursive call must return
     * the result, as before, as well as the carry. This is not terribly challenging to implement, but it is more
     * cumbersome. We can solve this issue by creating a wrapper class called Partial Sum.
     */
    public static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
        int len1 = length(l1);
        int len2 = length(l2);

        /* Pad the shorter list with zeros - see note (1) */
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        /* Add lists */
        PartialSum sum = addListsHelper(l1, l2);

        /* If there was a carry value left over, insert this at the front of the list.
         * Otherwise, just return the linked list. */
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    protected static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }

        // Add smaller digits recursively
        PartialSum sum = addListsHelper(l1.next, l2.next);

        // Add carry to current data
        int value = sum.carry + l1.value + l2.value;

        // Insert sub of current digits
        LinkedListNode full_result = insertBefore(sum.sum, value % 10);

        // Return sub so far and the carry value
        sum.sum = full_result;
        sum.carry = value/10;
        return sum;


    }
    /* Pad the list with zeros */
    private static LinkedListNode padList(LinkedListNode l, int padding) {
        LinkedListNode head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    private static LinkedListNode insertBefore(LinkedListNode list, int value) {
        LinkedListNode node = new LinkedListNode(value);
        if (list != null) {
            node.next = list;
        }
        return node;
    }

    private static int length(LinkedListNode head) {
        if (head == null) {
             return 0;
        }

        int result = 1;
        LinkedListNode next = head.next;
        while (next != null) {
            result++;
            next = next.next;
        }

        return result;
    }
}
