package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.ListNode;
import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
 * <p>
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
 * height-balanced binary search tree.
 * <p>
 * Example 1:
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in head is in the range [0, 2 * 10000].
 * -100000 <= Node.val <= 100000
 */
public class Problem2_16_ConvertSortedListToBST {
    /**
     * KREVSKY SOLUTION
     * time to solve = 27 mins
     * time to test on paper = 9 min
     * 1 attempt
     * NOTE: the idea 'to find mid element' was taken from https://www.youtube.com/watch?v=5IQF13nNq6A
     */
    public TreeNode sortedListToBSTKrev(ListNode head) {
        return sortedListToBST(head, null);
    }

    // DEBUGGING:
    //-10 -3 0 5 9
    //sortedListToBST(-10, null)
    //  endNode = null
    //  fast = 9
    //  slow = 0
    //  root = Node(0)
    //   leftTreeNode = sortedListToBST(-10, 0)
    //      endNode = 0
    //      fast = 0
    //      slow = -3
    //      root = Node(-3)
    //      leftTreeNode = sortedListToBST(-10, -3) = Node(-10)
    //      rightTreeNode = sortedListToBST(0, 0) = null
    //  rightTreeNode = sortedListToBST(5, null)
    //      endNode = null
    //      fast = null
    //      slow = 9
    //      root = Node(9)
    //      leftTreeNode = sortedListToBST(5, 9) = Node(5)
    //      rightTreeNode = sortedListToBST(null, null) = null

    private TreeNode sortedListToBST(ListNode head, ListNode endNode) {
        //stop recursion if
        if (head == null || head == endNode) return null;

        //stop recursion if
        if (head.next == endNode) {
            return new TreeNode(head.val);
        }

        //otherwise find middle element (root)
        ListNode fast = head;
        ListNode slow = head;
        while (fast != endNode && fast.next != endNode) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);

        TreeNode leftTreeNode = sortedListToBST(head, slow);
        TreeNode rightTreeNode = sortedListToBST(slow.next, endNode);

        root.left = leftTreeNode;
        root.right = rightTreeNode;

        return root;
    }

    /**
     * https://www.youtube.com/watch?v=5IQF13nNq6A
     * the idea is the same, but we broke the link between the previous node of mid node and mid node
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        ListNode mid = mid(head);
        TreeNode root = new TreeNode(mid.val);

        if (head == mid) return root;

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }

    private ListNode mid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        //break the link
        if (prev != null) {
            prev.next = null;
        }

        return slow;
    }

}
