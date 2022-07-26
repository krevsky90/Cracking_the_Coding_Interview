package data_structures.chapter2_linked_lists.extra;

import data_structures.chapter2_linked_lists.LinkedListNode;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=Os5FM4KQtxw&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=14
 * Byte by Byte: Interview Question: Palindromes
 * <p>
 * Given a linked list, write a function to determine whether the list is a palindrome
 *
 */
public class Palindromes {
    public static void main(String[] args) {
        //even length of the list
        LinkedListNode node11 = new LinkedListNode(1);
        LinkedListNode node12 = new LinkedListNode(2);
        LinkedListNode node13 = new LinkedListNode(2);
        LinkedListNode node14 = new LinkedListNode(1);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;

        //odd length of the list
        LinkedListNode node21 = new LinkedListNode(1);
        LinkedListNode node22 = new LinkedListNode(2);
        LinkedListNode node23 = new LinkedListNode(1);
        node21.next = node22;
        node22.next = node23;

        System.out.println(isPalindrome(node21));
    }

    /**
     * THE IDEA: store the first half of list to stack and compare its elements with the second part of the list
     * they should be the same if the list is palindrome
     */
    public static boolean isPalindrome(LinkedListNode node) {
        LinkedListNode slow = node;
        LinkedListNode fast = node;
        Stack<LinkedListNode> stack = new Stack<>();

        while (fast != null && fast.next != null) {
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        //if list has odd length - skip middle element:
        if (fast != null) {
            slow = slow.next;
        }

        //compare the rest part of linked list and stack's elements
        while (slow != null) {
            if (stack.pop().value != slow.value) return false;
            slow = slow.next;
        }

        return true;
    }
}
