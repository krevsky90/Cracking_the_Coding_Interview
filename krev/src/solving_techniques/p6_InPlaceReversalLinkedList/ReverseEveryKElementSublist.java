package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394dfe0a05eca17327534ff
 * OR (+-)
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
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
     * KREVSKY SOLUTION #1:
     * <p>
     * time to solve ~ 60 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     */


    // it=0
    // k=2

    // 2      ->        1      ->        4    ->       3       ->       6      ->       5     ->   null
    // head
    //                                                                curHead
    //                                                                                  curEnd                    
    //                                                                                             curNode
    //                                                                                             nextHead


    public LinkedListNode reverseKGroup1(LinkedListNode head, int k) {
        int it = 0;
        LinkedListNode curHead = head;
        LinkedListNode curEnd = null;
        LinkedListNode nextHead = null;
        LinkedListNode curNode = head;

        while (curNode != null && it < k) {
            curNode = curNode.next;
            it++;
        }

        if (it == k) {
            nextHead = curNode;
            //reverse list with curHead
            curHead = reverseList(curHead, k);
            //move through sublist to find curEnd
            curEnd = findEndNode(curHead);

            it = 0;
        } else {
            //it means while-loop stopped because the whole linked list is shorter than K => nothing to reverse
            return head;
        }

        head = curHead;

        while (curNode != null) {
            while (curNode != null && it < k) {
                curNode = curNode.next;
                it++;
            }

            if (it == k) {
                curHead = nextHead;
                nextHead = curNode;
                //reverse list with curHead
                curHead = reverseList(curHead, k);

                //link curEnd from previous sublist and curHead - head of current reversed sublist
                curEnd.next = curHead;

                //move through sublist to find curEnd
                curEnd = findEndNode(curHead);

                it = 0;
            } else {
                //it means while stops because the rest part of linked list is shorter than K => nothing to reverse
                curEnd.next = nextHead;
                // break; - not necessary since curNode = null => while-loop will be stopped
            }
        }

        return head;
    }

    // null <- 1 <- 2   3, k = 2
    // prev = 2
    // cur = 3
    // i = 1
    //     next = 3

    private LinkedListNode reverseList(LinkedListNode head, int k) {
        LinkedListNode prevNode = null;
        LinkedListNode curNode = head;
        for (int i = 0; i < k; i++) {
            LinkedListNode nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }

        return prevNode;
    }

    private LinkedListNode findEndNode(LinkedListNode head) {
        LinkedListNode end = head;
        LinkedListNode cur = head;
        while (cur != null) {
            end = cur;
            cur = cur.next;
        }

        return end;
    }

    /**
     * KREVSKY SOLUTION #2.1:
     * NOTE: this solution is for the problem with ending
     * "If, in the end, you are left with a sub-list with less than ?k? elements, reverse it too."
     *
     * idea: the same as src/solving_techniques/p6_InPlaceReversalLinkedList/ProblemChallenge1_ReverseAlternatingKElementSublist.java
     * globally we store:
     *     resultNode - to set once and return as result
     *     current (initially = head)
     *     previous (initially = null)
     *     lastNodeOfPreviousPart (initially = null). To link PreviousPart and just reversed
     * inside the loop we store:
     *     headOfSubList (initially = current, then it will become end node of reversed sublist). To link just reversed part to the following part (i.e. current)
     *
     * time to solve ~ 80 mins
     *
     * time ~ O(n)
     * space ~ O(1)
     * 3 attempts:
     * - typos
     * - forgot to return resultNode
     *
     */
    public LinkedListNode reverseKGroup21(LinkedListNode head, int k) {
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

    /**
     * KREVSKY SOLUTION #2.2:
     * NOTE: this solution is for leetcode's description
     * (i.e. "If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.")
     * = KREVSKY SOLUTION #2.2: + bootleg with checking the amount of the rest elements of the list
     */
    public LinkedListNode reverseKGroup22(LinkedListNode head, int k) {
        LinkedListNode resultNode = null;   //don't want to change head pointer => introduce resultNode
        LinkedListNode current = head;
        LinkedListNode previous = null;
        LinkedListNode lastNodeOfPreviousPart = null;
        while (current != null) {
            LinkedListNode headOfSubList = current;

            //ATTENTION!
            //bootleg to find whether the current sublist has k elements
            //if not - don't reverse and return the resultNode
            LinkedListNode tempNode = current;
            for (int j = 0; j < k; j++) {
                if (tempNode == null) return resultNode;
                tempNode = tempNode.next;
            }

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

    /**
     * https://leetcode.com/problems/reverse-nodes-in-k-group/solutions/11440/non-recursive-java-solution-and-idea/
     * idea: set prev and next nodes to 'reverse' method to have ability to link reversed part with prev and next parts of the list
     * if i % k == 0 then we reverse sublist
     * else we just move forward to find end.next node and set it into 'reverse' method in 'if' block
     */
    public LinkedListNode reverseKGroupLeetcode(LinkedListNode head, int k) {
        LinkedListNode begin;
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        LinkedListNode dummyhead = new LinkedListNode(-1);
        dummyhead.next = head;
        begin = dummyhead;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;

    }

    public LinkedListNode reverse(LinkedListNode begin, LinkedListNode end) {
        LinkedListNode curr = begin.next;
        LinkedListNode next, first;
        LinkedListNode prev = begin;
        first = curr;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }
}
