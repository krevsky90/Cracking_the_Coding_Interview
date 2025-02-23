package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List (medium) (locked)
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list
 * #Company (23.02.2025): 0 - 3 months Meta 20 0 - 6 months TikTok 2 6 months ago Microsoft 4 Amazon 2 Nvidia 2
 * OR
 * https://www.geeksforgeeks.org/convert-binary-tree-to-doubly-linked-list-using-morris-traversal/
 * <p>
 * #Company: Amazon Bloomberg Databricks Facebook Google Lyft Microsoft Oracle
 * <p>
 * Convert a BST to a sorted circular doubly-linked list in-place.
 * Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
 * <p>
 * Let's take the following BST as an example, it may help you understand the problem better:
 * <p>
 * <p>
 * We want to transform this BST into a circular doubly linked list.
 * Each node in a doubly linked list has a predecessor and successor.
 * For a circular doubly linked list, the predecessor of the first element is the last element,
 * and the successor of the last element is the first element.
 * <p>
 * The figure below shows the circular doubly linked list for the BST above.
 * The "head" symbol means the node it points to is the smallest element of the linked list.
 * <p>
 * Specifically, we want to do the transformation in place.
 * After the transformation, the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor.
 * We should return the pointer to the first element of the linked list.
 */
public class ConvertBinaryTreeToDoublyLinkedList {
    public static void main(String[] args) {
        /* Constructing below tree
                    10
                   /  \
                  20   30
                 /  \
                40   60            */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(60);

        TreeNode head = morrisInOrderTraversalCommonAlgorithm(root);
        ConvertBinaryTreeToDoublyLinkedList obj = new ConvertBinaryTreeToDoublyLinkedList();
//        TreeNode head = obj.inOrderTraversalSolution(root);

        //expected 40 <-> 20 <-> 60 <-> 10 <-> 30
        TreeNode temp = head;
        String res = "";
        while (temp.right != head) {
            res += temp.val + " -> ";
            temp = temp.right;
        }
        res += temp.val;    //add the latest node (which -> head)
        System.out.println(res);

        System.out.println("-------------");
        TreeNode latestNode = temp;
        res = "";
        while (temp.left != latestNode) {
            res = " <- " + temp.val + res;
            temp = temp.left;
        }
        res = temp.val + res;
        System.out.println(res);    //add head (which -> latestNode)
    }

    /**
     * KREVSKY SOLUTION #1 (23.02.2025:
     * idea: transform subtree recursively and return leftmost and rightmost nodes to link them with top node
     * <p>
     * time to solve ~ 11 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n) because of recursion
     * <p>
     * 3 attempts:
     * - forgot about root == null
     * - mistake in linking right != null case
     * <p>
     * BEATS ~ 100%
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return root;

        List<TreeNode> bounds = helper(root);
        bounds.get(0).left = bounds.get(1);
        bounds.get(1).right = bounds.get(0);
        return bounds.get(0);
    }

    private List<TreeNode> helper(TreeNode cur) {
        TreeNode first = cur;
        TreeNode last = cur;

        if (cur.left != null) {
            List<TreeNode> bounds = helper(cur.left);
            cur.left = bounds.get(1);
            bounds.get(1).right = cur;
            first = bounds.get(0);
        }

        if (cur.right != null) {
            List<TreeNode> bounds = helper(cur.right);
            cur.right = bounds.get(0);
            bounds.get(0).left = cur;
            last = bounds.get(1);
        }

        return Arrays.asList(first, last);
    }

    /**
     * KREVSKY SOLUTION #2:
     * idea: simple in-order traversal + store prev node and head node
     * time to solve ~ 10 mins
     * time ~ O(N)
     * space ~ O(N) - due to used memory for recursive stack
     * 1 attempt
     */
    TreeNode prev = null;
    TreeNode head = null;

    public TreeNode inOrderTraversalSolution(TreeNode root) {
        inOrderTraversalSolutionRecursively(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    public void inOrderTraversalSolutionRecursively(TreeNode root) {
        if (root != null) {
            inOrderTraversalSolutionRecursively(root.left);

            if (prev == null) {
                head = root;
                prev = root;
            } else {
                root.left = prev;
                prev.right = root;
                prev = root;
            }

            inOrderTraversalSolutionRecursively(root.right);
        }
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve + debug ~ 20 + 8 mins
     * <p>
     * 2 attempts:
     * - incorrect initialize tempNode
     */
    public static TreeNode morrisInOrderTraversalCommonAlgorithm(TreeNode root) {
        TreeNode fakeHead = new TreeNode(-1);
        TreeNode prevNode = fakeHead;
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                //create double links to current node to linked list
                prevNode.right = current;
                current.left = prevNode;

                prevNode = prevNode.right;

                current = current.right;
            } else {
                //find predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    //create loop
                    predecessor.right = current;
                    current = current.left;
                } else {
                    //remove loop
                    predecessor.right = null;
                    //create double links to current node to linked list
                    prevNode.right = current;
                    current.left = prevNode;

                    prevNode = prevNode.right;

                    current = current.right;
                }
            }
        }

        TreeNode result = fakeHead.right;

        result.left = prevNode; //loop doubly linked list
        prevNode.right = result;

        return result;
    }
}
