package data_structures.chapter2_linked_lists;

import static data_structures.chapter2_linked_lists.LinkedListUtils.linkedListToString;

/**
 * p.106
 * <p>
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 * Hints: #8, #25, #41, #67, # 126
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) node != null
 * 2) k <= length of linked list
 */
public class Problem2_2 {
    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(2);
        LinkedListNode n3 = new LinkedListNode(3);
        LinkedListNode n4 = new LinkedListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        int k = 2;

        System.out.println(linkedListToString(n1));
        getKthToLastElement2(n1, k);
        LinkedListNode kth = getKthToLastElement3(n1, k);
        System.out.println(kth == null ? null : kth.value);
    }

    /**
     * Solution #2: Recursive
     * This algorithm recurses through the linked list. When it hits the end, the method passes back a counter set
     * to 0. Each parent call adds 1 to this counter. When the counter equals k, we know we have reached the kth
     * to last element of the linked list.
     * Implementing this is short and sweet - provided we have a way of "passing back" an integer value through
     * the stack. Unfortunately, we can't pass back a node and a counter using normal return statements. So how
     * do we handle this?
     * Approach A: Don't Return the Element.
     * One way to do this is to change the problem to simply printing the kth to last element. Then, we can pass
     * back the value of the counter simply through return values.
     */
    public static int getKthToLastElement2(LinkedListNode node, int k) {
        if (node == null) {
            return 0;
        }
        int index = getKthToLastElement2(node.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + node.value);
        }

        return index;
    }

    /**
     * SOLUTION #3:iterative
     */
    public static LinkedListNode getKthToLastElement3(LinkedListNode node, int k) {
        if (node == null) {
            return null;
        }
        LinkedListNode p1 = node;
        LinkedListNode p2 = node;
        int i = 1;

        /* Move p1 k nodes into the list */
        while (i < k) {
            p2 = p2.next;
            if (p2 == null) {
                ///it means that length of linked list less than k
                return null;
            }
            i++;
        }

        //now we have 'distance' = k-1 between p1 and p2
        //Move them at the same pace. When p2 hits the end, p1 will be at the right element
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }



}
