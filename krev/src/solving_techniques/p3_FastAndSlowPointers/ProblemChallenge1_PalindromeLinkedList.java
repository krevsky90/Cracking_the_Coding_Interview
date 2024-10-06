package solving_techniques.p3_FastAndSlowPointers;

import data_structures.chapter2_linked_lists.LinkedListNode;

/**
 * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63922d27b689b698075a9ab3
 * OR
 * Cracking_the_Coding_Interview\krev\src\data_structures\chapter2_linked_lists\Problem2_6.java
 * OR
 * 234. Palindrome Linked List (easy)
 * https://leetcode.com/problems/palindrome-linked-list
 *
 * #Company: Adobe Amazon Apple Bloomberg Facebook Google Grab Intel IXL Microsoft Snapchat Twitter VMware Yandex
 *
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 *
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class ProblemChallenge1_PalindromeLinkedList {
    /**
     * SOLUTION #2: 06.10.2024
     * info: https://www.youtube.com/watch?v=yOzXms1J6Nk
     *
     * BEATS ~ 86%
     */
    public boolean isPalindrome2(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        //1. find middle
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //2. reverse 2nd part
        LinkedListNode prev = null;
        while (slow != null) {
            LinkedListNode nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }

        //3. check palindrome
        LinkedListNode left = head;
        LinkedListNode right = prev;
        while (right != null) {
            if (left.value != right.value) return false;

            left = left.next;
            right = right.next;
        }

        return true;
    }

    /**
     * Solution #1: without changing the LinkedList = Cracking_the_Coding_Interview\krev\src\data_structures\chapter2_linked_lists\Problem2_6.java
     * BUT I DON'T UNDERSTAND IT IN DETAILS!
     */

    /**
     * Solution #2: with changes in the LinkedList
     * https://leetcode.com/problems/palindrome-linked-list/solutions/1137696/short-easy-w-explanation-t-o-n-s-o-1-solution-using-fast-slow/
     *
     * BEATS ~ 99%
     */

    //null <- 1 <- 2   2 -> 1
    //slow = null
    //fast = null
    //nextNode = 2
    //prev = null
    public boolean isPalindrome(LinkedListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;

        LinkedListNode fast = head;

        //3 pointers approach to reverse linked list
        LinkedListNode prev = null;
        LinkedListNode slow = head; //i.e. current
        LinkedListNode nextNode = null;

        //move through the list and reverse its first half
        while (fast != null && fast.next != null) {
            fast = fast.next.next;

            nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }

        //if fast != null => list has odd amount of elements => has only one middle => move slow to the next node to ignore middle
        if (fast != null) {
            slow = slow.next;
        }

        //compare reversed sublist with head1 = prev and sublist with head2 = slow
        while (slow != null) {
            if (slow.value != prev.value) {
                return false;
            } else {
                slow = slow.next;
                prev = prev.next;
            }
        }

        return true;
    }
}
