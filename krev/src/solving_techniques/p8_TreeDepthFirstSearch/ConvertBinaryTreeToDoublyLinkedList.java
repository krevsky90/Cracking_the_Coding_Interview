package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List (medium) (locked)
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list
 * OR
 * https://www.geeksforgeeks.org/convert-binary-tree-to-doubly-linked-list-using-morris-traversal/
 *
 * #Company: Amazon Bloomberg Databricks Facebook Google Lyft Microsoft Oracle
 *
 * Convert a BST to a sorted circular doubly-linked list in-place.
 * Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
 *
 * Let's take the following BST as an example, it may help you understand the problem better:
 *
 *
 * We want to transform this BST into a circular doubly linked list.
 * Each node in a doubly linked list has a predecessor and successor.
 * For a circular doubly linked list, the predecessor of the first element is the last element,
 *      and the successor of the last element is the first element.
 *
 * The figure below shows the circular doubly linked list for the BST above.
 * The "head" symbol means the node it points to is the smallest element of the linked list.
 *
 * Specifically, we want to do the transformation in place.
 * After the transformation, the left pointer of the tree node should point to its predecessor,
 *      and the right pointer should point to its successor.
 * We should return the pointer to the first element of the linked list.
 *
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
     * KREVSKY SOLUTION #2:
     * idea: simple in-order traversal + store prev node and head node
     * time to solve ~ 10 mins
     * time ~ O(n)
     * space ~ O(1)
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
     *
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
