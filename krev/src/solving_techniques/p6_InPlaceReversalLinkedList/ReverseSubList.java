package solving_techniques.p6_InPlaceReversalLinkedList;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394ddf7a05eca1732753441
 * OR
 * 92. Reverse Linked List II
 * https://leetcode.com/problems/reverse-linked-list-ii
 * <p>
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * Example 2:
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class ReverseSubList {
    /**
     * KREVSKY solution #1
     * time to solve ~ 29 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     */
    public LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
        LinkedListNode prevStart = null;
        LinkedListNode start = null;
        LinkedListNode postEnd = null;

        //traverse through the list and initialize the pointers above
        int i = 1;
        LinkedListNode prev = null;
        LinkedListNode cur = head;
        while (cur != null) {
            if (i == left) {
                prevStart = prev;
                start = cur;
            }

            if (i == right) {
                postEnd = cur.next;
                //break the link cur -> cur.next
                cur.next = null;
                //and stop iterating
                break;
            }

            prev = cur;
            cur = cur.next;
            i++;
        }

        //reverse subList with head = start
        LinkedListNode reversedList = reverse(start);

        //if left == 1 (and prevStart == null) => head will be changed
        if (left == 1) {
            head = reversedList;
        } else {
            prevStart.next = reversedList;
        }

        //find the last node of reversedList
        LinkedListNode lastNode = reversedList;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        //concat lastNode and postEnd
        lastNode.next = postEnd;

        return head;
    }

    public LinkedListNode reverse(LinkedListNode head) {
        if (head == null) return head;

        LinkedListNode prev = null;
        LinkedListNode cur = head;

        while (cur != null) {
            LinkedListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    /**
     * SOLUTION #2
     * info: https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2006:%20In-place%20Reversal%20of%20a%20LinkedList.md
     * more applicable for other similar problems that are based on this solution
     *
     * time to implement ~ 16 mins
     * 1 attempt
     */
    public LinkedListNode reverseBetweenBeauty(LinkedListNode head, int left, int right) {
        LinkedListNode current = head;
        //save pointer to the last node of first chunk to connect it to the head of future reversed part of LL
        LinkedListNode lastOfFirstChunk = null;
        int i = 0;
        while (current != null && i < left - 1) {
            lastOfFirstChunk = current;
            current = current.next;
            i++;
        }

        //we are interested in three parts ofthe LL,
        //1. the part before index p
        //2. the part between p and q
        //3. and the part after index q

        //save pointer to 'current' nod - it will be the latest node of reversed chunk (part of LL)
        LinkedListNode lastOfReversedChunk = current;

        //reverse!
        i = 0;
        LinkedListNode prev = null;
        LinkedListNode next = null;
        while (current != null && i < right - left + 1) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }

        //join first chunk + reversed chunk
        if (lastOfFirstChunk != null) {
            lastOfFirstChunk.next = prev;
        } else {
            //this means p = 1 i.e., we are changing the first node(head) of the LL
            head = prev;
        }

        //join reversed chunk + last chunk (its head = 'current' right now)
        lastOfReversedChunk.next = current;

        return head;
    }
}