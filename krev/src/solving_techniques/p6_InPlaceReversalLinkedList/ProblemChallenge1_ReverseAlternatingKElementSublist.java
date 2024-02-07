package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;
import data_structures.chapter2_linked_lists.LinkedListUtils;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394ea291182a7b58b691939
 * Problem Statement
 * Given the head of a LinkedList and a number ?k?, reverse every alternating ?k? sized sub-list starting from the head.
 * <p>
 * If, in the end, you are left with a sub-list with less than ?k? elements, reverse it too.
 */
public class ProblemChallenge1_ReverseAlternatingKElementSublist {
    public static void main(String[] args) {
        //initial LL: 1 -> 2 -> 3 -> 4 -> 6 -> 7 -> 8
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(2);
        LinkedListNode n3 = new LinkedListNode(3);
        LinkedListNode n4 = new LinkedListNode(4);
        LinkedListNode n5 = new LinkedListNode(5);
        LinkedListNode n6 = new LinkedListNode(6);
        LinkedListNode n7 = new LinkedListNode(7);
        LinkedListNode n8 = new LinkedListNode(8);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;


        LinkedListNode result = reverseAlternateKElements(n1, 3);
        LinkedListUtils.printLinkedList(result);    //expected  3 -> 2 -> 1 -> 4 -> 5 -> 6 -> 8 -> 7
    }

    /**
     * KREVSKY SOLUTION:
     * idea: is the same as https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2006:%20In-place%20Reversal%20of%20a%20LinkedList.md
     * time to solve ~ 75 mins
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * 1 attempt
     */
    public static LinkedListNode reverseAlternateKElements(LinkedListNode head, int k) {
        if (k <= 1 || head == null) return head;

        LinkedListNode resultNode = null;   //don't want to change head pointer => introduce resultNode

        LinkedListNode current = head;
        LinkedListNode previous = null;
        while (current != null) {
            LinkedListNode next = null;
            int i = 0;
            LinkedListNode lastNodeOfSubList = current;
            LinkedListNode lastNodeOfPreviousPart = previous;

            while (i < k && current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
                i++;
            }

            //connect already handled part of LL to the reversed part
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = previous;
            } else {
                resultNode = previous;
            }

            //connect reversed sublist to the further part of LL
            lastNodeOfSubList.next = current;

            previous = lastNodeOfSubList;

            //skip k elements
            i = 0;
            while (i < k && current != null) {
                previous = current;
                current = current.next;
                i++;
            }
        }

        return resultNode;
    }
}
