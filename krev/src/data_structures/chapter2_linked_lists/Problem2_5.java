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
public class Problem2_5 {
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

//        LinkedListNode sum = sumFromEndKrevsky2(n11, n21);
        LinkedListNode sum = sumRecursive(n11, n21, 0);
        System.out.println(linkedListToString(sum));
    }

    /**
     * KREVSKY 1
     */
    public static LinkedListNode sumFromEndKrevsky1(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode node1 = head1;
        LinkedListNode node2 = head2;
        LinkedListNode sumStart = null;
        LinkedListNode sumEnd = null;

        LinkedListNode tempNode = null;
        int digitOver = 0;
        while(!(node1 == null && node2 == null)) {
            int digitSum = -1;
            if (node1 != null && node2 != null) {
                digitSum = node1.value + node2.value + digitOver;
                node1 = node1.next;
                node2 = node2.next;
            } else if (node1 != null && node2 == null) {
                // for example:
                // 1 -> 9 -> 1
                // 9 -> null
                digitSum = node1.value + digitOver;
                node1 = node1.next;
            } else if (node1 == null && node2 != null) {
                // for example:
                // 9 -> null
                // 1 -> 9 -> 1
                digitSum = node2.value + digitOver;
                node2 = node2.next;
            }

            if (digitSum > 9) {
                digitOver = 1;
                digitSum -= 10;
            } else {
                digitOver = 0;
            }

            tempNode = new LinkedListNode(digitSum);
            if (sumStart == null) {
                sumStart = tempNode;
                sumEnd = tempNode;
            } else {
                sumEnd.next = tempNode;
                sumEnd = tempNode;
            }
        }
        //for case like (1 -> 9) + (9) = (0 -> 0 -> 1) we need to add extra node with (1) because digitOver = 1
        if (digitOver > 0) {
            tempNode = new LinkedListNode(digitOver);
            sumEnd.next = tempNode;
            sumEnd = tempNode;
        }

        return sumStart;
    }

    /**
     * KREVSKY 2 = optimized KREVSKY 1
     */
    public static LinkedListNode sumFromEndKrevsky2(LinkedListNode head1, LinkedListNode head2) {
        LinkedListNode node1 = head1;
        LinkedListNode node2 = head2;
        LinkedListNode sumStart = null;
        LinkedListNode sumEnd = null;

        LinkedListNode tempNode = null;
        int digitOver = 0;
        while(!(node1 == null && node2 == null)) {
            int digitSum = digitOver;
            if (node1 != null) {
                digitSum += node1.value;
                node1 = node1.next;
            }

            if (node2 != null) {
                digitSum += node2.value;
                node2 = node2.next;
            }

            if (digitSum > 9) {
                digitOver = 1;
                digitSum -= 10;
            } else {
                digitOver = 0;
            }

            tempNode = new LinkedListNode(digitSum);
            if (sumStart == null) {
                sumStart = tempNode;
                sumEnd = tempNode;
            } else {
                sumEnd.next = tempNode;
                sumEnd = tempNode;
            }
        }
        //for case like (1 -> 9) + (9) = (0 -> 0 -> 1) we need to add extra node with (1) because digitOver = 1
        if (digitOver > 0) {
            tempNode = new LinkedListNode(digitOver);
            sumEnd.next = tempNode;
            sumEnd = tempNode;
        }

        return sumStart;
    }

    /**
     * SOLUTION: p.226
     */
    public static LinkedListNode sumRecursive(LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        LinkedListNode result = new LinkedListNode();
        int value = carry;
        if (l1 != null) {
            value += l1.value;
        }

        if (l2 != null) {
            value += l2.value;
        }

        result.value = value % 10;

        // recurse
        if (l1 != null || l2 != null) { //NOTE: not necessary condition, I think!
            LinkedListNode more = sumRecursive(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
            result.next = more;
        }
        return result;
    }
}
