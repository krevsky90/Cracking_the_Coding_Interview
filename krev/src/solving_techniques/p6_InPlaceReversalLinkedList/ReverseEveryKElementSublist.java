package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394dfe0a05eca17327534ff
 * OR (+-)
 * 25. Reverse Nodes in k-Group (hard)
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * <p>
 * #Company (20.02.2025): 0 - 3 months Amazon 5 Bloomberg 4 Google 2 Meta 2 Microsoft 2 Sigmoid 2 Autodesk 2 Arista Networks 2 0 - 6 months Salesforce 3 Zeta 2 TikTok 2 6 months ago Adobe 11 Apple 8 Snowflake 8 Tesla 5 Walmart Labs 4 Uber 4 Commvault 4 Yahoo 3 Fortinet 3 Infosys 2
 * <p>
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * <p>
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * <p>
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * <p>
 * NOTE: I will solve leetcode's variation
 */
public class ReverseEveryKElementSublist {
    /**
     * BEST KREVSKY SOLUTION for leetcode problem!
     * additional idea: created fakeHead and link to real head. and prev = fakeHead
     * <p>
     * time to solve ~ 30 mins
     * <p>
     * 2 attempts:
     * -forgot to move
     * BEATS ~ 100%
     */
    public LinkedListNode reverseKGroup(LinkedListNode head, int k) {
        if (head == null || head.next == null) return head;

        LinkedListNode fakeHead = new LinkedListNode();
        fakeHead.next = head;

        LinkedListNode prev = fakeHead;
        LinkedListNode cur = head;
        LinkedListNode startSubList = cur;
        LinkedListNode endSubList = null;   //does not matter what is initial value

        while (cur != null) {
            int steps = 0;
            while (cur != null && steps < k - 1) {
                cur = cur.next;
                steps++;
            }

            if (cur != null) {
                //i.e. we will reverse current sublist
                endSubList = cur;

                cur = cur.next;
                endSubList.next = null; //break link

                //reverse sub-list [startSubList, endSubList]
                LinkedListNode reversedList = reverse(startSubList);

                prev.next = reversedList;
                startSubList.next = cur;

                prev = startSubList;
                startSubList = cur;
            }
            //NOTE: if we want to reverse the rest part of list even if its size < k
            // (as it is required here https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394dfe0a05eca17327534ff),
            // then add this code
//         else {
//            LinkedListNode reversedList = reverse(startSubList);
//            prev.next = reversedList;
//        }
        }

        return fakeHead.next;
    }

    private LinkedListNode reverse(LinkedListNode node) {
        LinkedListNode prev = null;
        while (node != null) {
            LinkedListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        return prev;
    }
}
