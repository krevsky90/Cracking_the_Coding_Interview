package data_structures.chapter2_linked_lists;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.107
 * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
 * node. Note that the intersection is defined based on reference, not value. That is, if the kth
 * node of the first linked list is the exact same node (by reference) as the jth node of the second
 * linked list, then they are intersecting.
 * Hints: #20, #45, #55, #65, #76, #93, #111, #120, #129
 *
 * ASSUMPTION/VALIDATION:
 * 1) list1 !=null and list2 != null
 *
 */
public class Problem2_7 {
    public static void main(String[] args) {
        LinkedListNode n11 = new LinkedListNode(7);
        LinkedListNode n12 = new LinkedListNode(1);
        LinkedListNode n13 = new LinkedListNode(6);
        LinkedListNode n14 = new LinkedListNode(9);
        n11.next = n12;
        n12.next = n13;
        n13.next = n14;
        System.out.println(linkedListToString(n11));

        LinkedListNode n21 = new LinkedListNode(5);
//        n21.next = n12;
        System.out.println(linkedListToString(n21));

        LinkedListNode result = getIntersectionNode(n11, n21);
        System.out.println(result == null ? null : result.value);


    }

    /**
     * KREVSKY
     */
    public static LinkedListNode getIntersectionNode(LinkedListNode n1, LinkedListNode n2) {
        if (n1 == null || n2 == null) return null;

        int length1 = getLength(n1);
        int length2 = getLength(n2);
        LinkedListNode p1 = n1;
        LinkedListNode p2 = n2;
        int delta12 = length1 - length2;
        int counter = 0;
        if (delta12 > 0) {
            while (counter < delta12) {
                p1 = p1.next;
                counter++;
            }
        } else if (delta12 < 0) {
            while (counter < Math.abs(delta12)) {
                p2 = p2.next;
                counter++;
            }
        }
        //now we have the same number of nodes from p1 to the end of list1 and from p2 to the end of list2
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return null;

    }

    private static int getLength(LinkedListNode n) {
        int length = 0;
        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
    }

    /**
     * SOLUTION: p.235
     * 1. Run through each linked list to get the lengths and the tails.
     * 2. Compare the tails. If they are different (by reference, not by value), return immediately. There is no intersection.
     * 3. Set two pointers to the start of each linked list.
     * 4. On the longer linked list, advance its pointer by the difference in lengths.
     * 5. Now, traverse on each linked list until the pointers are the same.
     */
    public static LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null || list2 == null) return null;

        Result result1 = getLengthAndTail(list1);
        Result result2 = getLengthAndTail(list2);
        if (result1.node != result2.node) {
            return null;
        }

        /* Set pointers to the start of each linked list. */
        LinkedListNode shorter = result1.length < result2.length ? list1 : list2;
        LinkedListNode longer = result1.length < result2.length ? list2 : list1;
        /* Advance the pointer for the longer linked list by difference in lengths. */
        longer = getKthNode(longer, Math.abs(result1.length - result2.length));

        /* Move both pointers until you have a collision. */
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        // return either one
        return longer;
    }

    private static Result getLengthAndTail(LinkedListNode n) {
        int length = 0;
        LinkedListNode tail = null;
        while (n != null) {
            length++;
            tail = n;
            n = n.next;
        }
        return new Result(tail, length);
    }

    private static LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current = head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }

    static class Result {
        public LinkedListNode node;
        public int length;

        public Result(LinkedListNode node, int length) {
            this.node = node;
            this.length = length;
        }
    }
}
