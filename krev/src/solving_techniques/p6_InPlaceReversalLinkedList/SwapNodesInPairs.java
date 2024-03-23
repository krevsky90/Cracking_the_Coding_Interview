package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * <p>
 * Example 2:
 * Input: head = []
 * Output: []
 * <p>
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 * <p>
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */
public class SwapNodesInPairs {
    /**
     * KREVSKY SOLUTION:
     * idea: the same as solving_techniques/p6_InPlaceReversalLinkedList/ReverseEveryKElementSublist.java
     * globally we store:
     * - resultNode - to set once and return as result
     * - current (initially = head)
     * - previous (initially = null)
     * - lastNodeOfPreviousPart (initially = null). To link PreviousPart and just reversed
     * inside the loop we store:
     * - headOfSubList (initially = current, then it will become end node of reversed sublist). To link just reversed part to the following part (i.e. current)
     * <p>
     * time to solve ~ 80 mins
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * 3 attempts:
     * - typos
     * - forgot to return resultNode
     * <p>
     * BEATS = 100%
     */
    public LinkedListNode swapPairs(LinkedListNode head) {
        if (head == null || head.next == null) return head;
        return reverseKGroup(head, 2);
    }

    //NOTE: it reverses ALL groups even if the last group has less than k elements!
    // => this code does not fit to the problem ReverseEveryKElementSublist as is!
    public LinkedListNode reverseKGroup(LinkedListNode head, int k) {
        LinkedListNode resultNode = null;   //don't want to change head pointer => introduce resultNode
        LinkedListNode current = head;
        LinkedListNode previous = null;
        LinkedListNode lastNodeOfPreviousPart = null;
        while (current != null) {
            LinkedListNode headOfSubList = current;
            //reverse group of K elements
            int i = 0;
            while (i < k && current != null) {
                LinkedListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
                i++;
            }

            //connect already handled part of LL to the reversed part
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = previous;
            } else {
                resultNode = previous;  //set new head of the list. it will be returned as result of the method
            }

            //link reversed sublist to the rest (still NOT reversed) part of the list
            headOfSubList.next = current;

            //NOTE:
            //this is distinction from ProblemChallenge1_ReverseAlternatingKElementSublist
            //since here we don't move previous => we don't move the end of just reversed part
            lastNodeOfPreviousPart = headOfSubList;
        }

        return resultNode;
    }
}
